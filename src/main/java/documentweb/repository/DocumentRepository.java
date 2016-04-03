package documentweb.repository;


import org.springframework.data.repository.CrudRepository;

public interface DocumentRepository extends CrudRepository<DocumentDto, Long> {
	DocumentDto findFirstByFileHashAndFileName(String fileHash, String fileName);
}
