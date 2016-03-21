package documentweb.repository;

import java.util.ArrayList;
import java.util.TreeSet;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import documentweb.model.DocumentModel;

@Repository
public class DocumentRepository {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// JDBC-backed implementations of the methods on the CorporateEventDao follow...
	public ArrayList<DocumentModel> searchDocumentWhichContainsAllKeywords(String[] keywords, int offset, int limitSize){
		ArrayList<DocumentModel> sset = new ArrayList<DocumentModel>();
		return sset;
	}
	
	public ArrayList<DocumentModel> searchDocumentWhichContainsEachKeyword(String[] keywords, int offset, int limitSize){
		ArrayList<DocumentModel> sset = new ArrayList<DocumentModel>();
		return sset;
	}
}
