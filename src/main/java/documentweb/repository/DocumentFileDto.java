package documentweb.repository;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity(name = "DocumentFile")
public class DocumentFileDto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	long docId;
	
	@Lob
	@Column(columnDefinition="LONGBLOB")
	Blob docFile;
	
	public DocumentFileDto() {
		super();
	}
	public DocumentFileDto(long id, long docId, Blob docFile) {
		super();
		this.id = id;
		this.docId = docId;
		this.docFile = docFile;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getDocId() {
		return docId;
	}
	public void setDocId(long docId) {
		this.docId = docId;
	}
	public Blob getDocFile() {
		return docFile;
	}
	public void setDocFile(Blob docFile) {
		this.docFile = docFile;
	}
	
	
}
