package documentweb.service;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import documentweb.repository.DocumentDto;
import documentweb.repository.DocumentFileDto;
import documentweb.repository.DocumentFileRepository;
import documentweb.repository.DocumentRepository;

@Service
public class FileDownloadService {

	Logger LOG = LoggerFactory.getLogger(FileDownloadService.class);

	@Autowired
	DocumentRepository documentRepository;
	
	@Autowired
	DocumentFileRepository documentFileRepository;

	public Optional<InputStream> getFileBinary(String fileHash, String fileName) {
		DocumentDto dto = documentRepository.findFirstByFileHashAndFileName(fileHash, fileName);
		if (dto == null)
			return Optional.empty();
		long docId = dto.getId();
		DocumentFileDto file = documentFileRepository.findFirstByDocId(docId);
		if (file == null)
			return Optional.empty();
		InputStream is = null;
		try {
			is = file.getDocFile().getBinaryStream();
		} catch (SQLException e) {
			LOG.warn(e.fillInStackTrace().toString());
		}
		if(is == null)
			return Optional.empty();;
		return Optional.of(is);
	}
}
