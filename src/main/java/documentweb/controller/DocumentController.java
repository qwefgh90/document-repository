package documentweb.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import documentweb.model.DocumentModel;
import documentweb.service.DocumentService;
import documentweb.service.DocumentService.KeywordMode;

@Controller
public class DocumentController {
	@Autowired
	DocumentService documentService;
	
	@RequestMapping(path = "/search", method = RequestMethod.GET, consumes={"application/*"})
	@ResponseBody ArrayList<DocumentModel> search(@RequestBody SearchRequestModel requestModel){
		Optional<ArrayList<DocumentModel>> result = documentService.search(requestModel.keyword, requestModel.offset, requestModel.limitSize, KeywordMode.valueOf(requestModel.mode));
		if(result.isPresent())
			return result.get();
		else
			return null;
	}
	
	class SearchRequestModel{
		String keyword;
		int offset;
		int limitSize;
		String mode; //KeywordMode
	}
	
}
