package documentweb.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import documentweb.model.DocumentSearchModel;
import documentweb.service.DocumentService;
import documentweb.service.DocumentService.KeywordMode;
import documentweb.service.FileDownloadService;

@Controller
public class DocumentController {

	Logger LOG = LoggerFactory.getLogger(DocumentController.class);

	@Autowired
	DocumentService documentService;
	@Autowired
	FileDownloadService fileDownloadService;

	@RequestMapping(path = "/search", method = RequestMethod.GET)
	@ResponseBody
	List<DocumentSearchModel> search(@RequestParam("keyword") String keyword, @RequestParam("offset") int offset,
			@RequestParam("limitSize") int limitSize, @RequestParam("mode") String mode) {
		Optional<List<DocumentSearchModel>> result = documentService.search(keyword, offset, limitSize,
				KeywordMode.valueOf(mode));
		if (result.isPresent())
			return result.get();
		else
			return null;
	}

	@RequestMapping(path = "/download/{fileHash}/{fileName}", method = RequestMethod.GET)
	void downloadFile(@PathVariable String fileHash, @PathVariable String fileName, HttpServletResponse response) {
		try {
			fileName = new String(Base64.getDecoder().decode(fileName), "utf-8");
		} catch (UnsupportedEncodingException e2) {
			try {
				response.sendError(500);
			} catch (IOException e) {
				LOG.warn(e.fillInStackTrace().toString());
			}
		}

		Optional<InputStream> result = fileDownloadService.getFileBinary(fileHash, fileName);
		int index = fileName.lastIndexOf('.');
		index = (index != -1 && index == fileName.length() - 1) ? -1 : index + 1;

		response.setContentType("application/" + (index != -1 ? fileName.substring(index) : '*'));
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
		if (result.isPresent()) {
			try {
				IOUtils.copy(result.get(), response.getOutputStream());
				response.getOutputStream().flush();
			} catch (IOException e) {
				LOG.warn(e.fillInStackTrace().toString());
				try {
					response.sendError(500);
				} catch (IOException e1) {
					LOG.warn(e.fillInStackTrace().toString());
				}
			}
		} else {
			try {
				response.sendError(204);
			} catch (IOException e) {
				LOG.warn(e.fillInStackTrace().toString());
			}
		}
	}
}
