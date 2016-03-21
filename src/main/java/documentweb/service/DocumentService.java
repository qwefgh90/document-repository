package documentweb.service;

import java.util.Optional;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import documentweb.model.DocumentModel;
import documentweb.repository.DocumentRepository;

@Service
public class DocumentService {
	
	@Autowired
	DocumentRepository documentRepository;
	
	public enum KeywordMode{
		DefaultRankAppliedToAllKeywords,
		DefaultRankAppliedToEachKeyword
	}
	
	public Optional<ArrayList<DocumentModel>> search(String rawString, int offset, int limitSize, KeywordMode kmode){
		String[] keywords = rawString.split(" ");//space
		ArrayList<DocumentModel> ss = null;
		if(kmode.equals(KeywordMode.DefaultRankAppliedToAllKeywords))
			return Optional.of(documentRepository.searchDocumentWhichContainsAllKeywords(keywords, offset, limitSize));
		else if(kmode.equals(KeywordMode.DefaultRankAppliedToEachKeyword))
			return Optional.of(documentRepository.searchDocumentWhichContainsEachKeyword(keywords, offset, limitSize));
		else
			return Optional.of(documentRepository.searchDocumentWhichContainsEachKeyword(keywords, offset, limitSize));
	}
}
