
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import documentweb.model.DocumentSearchModel;
import documentweb.repository.DocumentDto;
import documentweb.repository.DocumentFileDto;
import documentweb.repository.DocumentFileRepository;
import documentweb.repository.DocumentRepository;
import documentweb.repository.DocumentSearchRepository;
import documentweb.service.FileDownloadService;
import documentweb.servlet.config.RootContext;
import documentweb.servlet.config.ServletContext;

import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { ServletContext.class, RootContext.class })
public class RepositoryTest {
	Logger logger = LoggerFactory.getLogger(RepositoryTest.class);
	
	@Autowired
	DocumentSearchRepository repo;
	@Autowired
	DocumentRepository repoDoc;
	@Autowired
	DocumentFileRepository repoFile;
	@Autowired
	FileDownloadService fdservice;

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = webAppContextSetup(this.wac).build();
	}

	/**
	 * print result of "company" searching
	 */
	@Test
	public void eachKeywordTest() {
		List<DocumentSearchModel> list = repo.searchDocumentWhichContainsEachKeyword(new String[] { "company" }, 0, 20);
		logger.info("" + list.size());
		for (DocumentSearchModel model : list) {
			//logger.info(model.toString());
			logger.info(model.getFileHash());
		}
	}

	/**
	 * binary update / download / service object TEST
	 * @throws Exception 
	 */
	@Test
	public void downloadTest() throws Exception {
		dt = new DocumentDto();
		DocumentDto dt22 = new DocumentDto();
		dt.setFileName("test.hwp");
		dt.setFileHash("DRY=DONTREPEATYOURCODE");
		dt = repoDoc.save(dt);
		dt22 = repoDoc.findFirstByFileHashAndFileName("DRY=DONTREPEATYOURCODE", "test.hwp");

		// Repository find with HASH TEST
		assertTrue(dt.getId() == dt22.getId());

		// SAVE FILE TO DB
		byte[] bt = { (byte) 0xff, (byte) 0xaa };
		Blob b = new SerialBlob(bt);
		dtofile = new DocumentFileDto();
		dtofile.setDocId(dt.getId());
		dtofile.setDocFile(b);
		dtofile = repoFile.save(dtofile);

		// Service find File TEST
		Optional<InputStream> result = fdservice.getFileBinary("DRY=DONTREPEATYOURCODE", "test.hwp");
		byte[] buf = new byte[2];
		result.get().read(buf);

		// BINARY TEST
		assertTrue(buf[0] == (byte) 0xff);
		assertTrue(buf[1] == (byte) 0xaa);
		
		// Spring Controller TEST
		String url = "/download/DRY=DONTREPEATYOURCODE/"+new String(Base64.getEncoder().encode("test.hwp".getBytes()),"utf-8");
		String url2 = "/download/NOR/"+new String(Base64.getEncoder().encode("test.hwp".getBytes()),"utf-8");

		mockMvc.perform(get(url).accept("application/*"))
		.andExpect(status().isOk())
		.andExpect(content().bytes(new byte[]{(byte)0xff, (byte)0xaa}))
		.andExpect(content().contentType("application/hwp"));
		
		mockMvc.perform(get(url2).accept("application/*"))
		.andExpect(status().isNoContent());
	}

	@After
	public void clean() {
		if (dt != null)
			repoDoc.delete(dt);
		if (dtofile != null)
			repoFile.delete(dtofile);
	}

	DocumentDto dt;
	DocumentFileDto dtofile;
}
