package documentweb.repository;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import documentweb.model.DocumentSearchModel;

@Repository
public class DocumentSearchRepository {
	private JdbcTemplate jdbcTemplate;
	ArrayList<DocumentSearchModel> empty = new ArrayList<DocumentSearchModel>();

	Logger LOG = LoggerFactory.getLogger(DocumentSearchRepository.class);

	@Autowired()
	@Qualifier(value="sphinxDataSource")
	public void setDataSource(DataSource ds){
		this.jdbcTemplate = new JdbcTemplate(ds);
	}

	// JDBC-backed implementations of the methods on the CorporateEventDao follow...
	public List<DocumentSearchModel> searchDocumentWhichContainsAllKeywords(String[] keywordArray, int offset, int limitSize){
		/*
		 * SELECT id, fileName, weight() FROM Document where match('"*신규설치*" "*기업부설*" ') option ranker=bm25;
		 * 		
fields: 3
  field 0: downloadlink
  field 1: filename
  field 2: filecontent
attrs: 9
  attr 0: filename, string, bitoff 0
  attr 1: firstaccesstime, bigint, bitoff 32
  attr 2: fullfilepath, string, bitoff 96
  attr 3: isdownloading, bool, bitoff 128
  attr 4: refsummary, string, bitoff 160
  attr 5: reftitle, string, bitoff 192
  attr 6: refurl, string, bitoff 224
  attr 7: filecontent, string, bitoff 256
  attr 8: filesummary, string, bitoff 288
		 */

		StringBuilder builder = new StringBuilder();
		for(int i = 0 ; i < keywordArray.length ; i++){
			if(keywordArray[i].length() > 1){
				builder.append("\"*");
				builder.append(keywordArray[i]);
				builder.append("*\" ");
				if(i != keywordArray.length-1){
					builder.append(" | ");
				}
			}
		}
		List<DocumentSearchModel> sset = jdbcTemplate.query("SELECT filename, filesummary, refurl, firstaccesstime, fullfilepath from Document where match(?) limit ?,? option ranker=bm25"
				, new BeanPropertyRowMapper<DocumentSearchModel>(DocumentSearchModel.class)
				, builder.toString(), offset, limitSize);
		
		return sset;
	}

	public List<DocumentSearchModel> searchDocumentWhichContainsEachKeyword(String[] keywordArray, int offset, int limitSize){


		StringBuilder builder = new StringBuilder();
		for(int i = 0 ; i < keywordArray.length ; i++){
			if(keywordArray[i].length() > 1){
				builder.append("\"*");
				builder.append(keywordArray[i]);
				builder.append("*\" ");
			}
		}

		List<DocumentSearchModel> sset = jdbcTemplate.query("SELECT filename, filesummary, refurl, firstaccesstime, fullfilepath from Document where match(?) limit ?,? option ranker=bm25"
				, new BeanPropertyRowMapper<DocumentSearchModel>(DocumentSearchModel.class)
				, builder.toString(), offset, limitSize);
		
		return sset;
	}
}
