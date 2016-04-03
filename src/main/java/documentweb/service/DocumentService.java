package documentweb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import documentweb.model.DocumentSearchModel;
import documentweb.repository.DocumentSearchRepository;

@Service
public class DocumentService {
	
	@Autowired
	DocumentSearchRepository documentRepository;
	
	public enum KeywordMode{
		DefaultRankAppliedToAllKeywords,
		DefaultRankAppliedToEachKeyword
	}
	
	public Optional<List<DocumentSearchModel>> search(String rawString, int offset, int limitSize, KeywordMode kmode){
		String[] keywords = rawString.split(" ");//space
		if(kmode.equals(KeywordMode.DefaultRankAppliedToAllKeywords))
			return Optional.of(documentRepository.searchDocumentWhichContainsAllKeywords(keywords, offset, limitSize));
		else if(kmode.equals(KeywordMode.DefaultRankAppliedToEachKeyword))
			return Optional.of(documentRepository.searchDocumentWhichContainsEachKeyword(keywords, offset, limitSize));
		else
			return Optional.of(documentRepository.searchDocumentWhichContainsEachKeyword(keywords, offset, limitSize));
	}
}
