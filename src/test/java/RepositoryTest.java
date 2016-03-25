
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import documentweb.model.DocumentModel;
import documentweb.repository.DocumentRepository;
import documentweb.servlet.config.RootContext;
import documentweb.servlet.config.ServletContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {ServletContext.class, RootContext.class})
public class RepositoryTest {
	@Autowired
	DocumentRepository repo;
	
	Logger logger = LoggerFactory.getLogger(RepositoryTest.class);
	
	@Test
	public void eachKeywordTest(){
		List<DocumentModel> list = repo.searchDocumentWhichContainsEachKeyword(new String[]{"company"}, 0, 20);
		logger.info(""+list.size());
		for(DocumentModel model : list){
			logger.info(model.toString());
		}
		assertTrue(list.size()>0);
	}
}
