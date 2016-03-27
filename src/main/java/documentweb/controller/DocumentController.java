package documentweb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import documentweb.model.DocumentModel;
import documentweb.service.DocumentService;
import documentweb.service.DocumentService.KeywordMode;

@Controller
public class DocumentController {
	@Autowired
	DocumentService documentService;
	
	@RequestMapping(path = "/search", method = RequestMethod.GET)
	@ResponseBody List<DocumentModel> search(
			@RequestParam("keyword") String keyword
			,@RequestParam("offset") int offset
			,@RequestParam("limitSize") int limitSize
			,@RequestParam("mode") String mode  ){
		
		Optional<List<DocumentModel>> result = documentService.search(keyword, offset, limitSize, KeywordMode.valueOf(mode));
		if(result.isPresent())
			return result.get();
		else
			return null;
	}
}
