package documentweb.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="Document")
public class DocumentDto implements Cloneable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;

	@Column(length = 2000)
	String refUrl;
	String refSummary;
	String refTitle;
	Long firstAccessTime;
	String fullFilePath;
	@Column(length = 400)
	String fileName;
	@Column(columnDefinition="LONGTEXT")
	String fileContent;
	String fileSummary;
	@Column(unique=true)
	String fileHash;
	boolean isDownloading;
	String downloadLink;
	int retryCount;
	boolean isEUCKRPage;
	char sphinx_yn='N';
	
	public DocumentDto() {
		super();
	}
	
	public DocumentDto(long id, String refUrl, String refSummary, String refTitle, Long firstAccessTime,
			String fullFilePath, String fileName, String fileContent, String fileSummary, String fileHash,
			boolean isDownloading, String downloadLink, int retryCount, boolean isEUCKRPage, char sphinx_yn) {
		super();
		this.id = id;
		this.refUrl = refUrl;
		this.refSummary = refSummary;
		this.refTitle = refTitle;
		this.firstAccessTime = firstAccessTime;
		this.fullFilePath = fullFilePath;
		this.fileName = fileName;
		this.fileContent = fileContent;
		this.fileSummary = fileSummary;
		this.fileHash = fileHash;
		this.isDownloading = isDownloading;
		this.downloadLink = downloadLink;
		this.retryCount = retryCount;
		this.isEUCKRPage = isEUCKRPage;
		this.sphinx_yn = sphinx_yn;
	}

	public DocumentDto(long id, String refUrl, String refSummary, String refTitle, Long firstAccessTime,
			String fullFilePath, String fileName, String fileContent, String fileSummary, String fileHash,
			boolean isDownloading, String downloadLink, int retryCount, char sphinx_yn) {
		super();
		this.id = id;
		this.refUrl = refUrl;
		this.refSummary = refSummary;
		this.refTitle = refTitle;
		this.firstAccessTime = firstAccessTime;
		this.fullFilePath = fullFilePath;
		this.fileName = fileName;
		this.fileContent = fileContent;
		this.fileSummary = fileSummary;
		this.fileHash = fileHash;
		this.isDownloading = isDownloading;
		this.downloadLink = downloadLink;
		this.retryCount = retryCount;
		this.sphinx_yn = sphinx_yn;
	}

	public boolean isEUCKRPage() {
		return isEUCKRPage;
	}

	public void setEUCKRPage(boolean isEUCKRPage) {
		this.isEUCKRPage = isEUCKRPage;
	}

	public char getSphinx_yn() {
		return sphinx_yn;
	}
	public void setSphinx_yn(char sphinx_yn) {
		this.sphinx_yn = sphinx_yn;
	}
	public int getRetryCount() {
		return retryCount;
	}
	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}
	public String getFileHash() {
		return fileHash;
	}
	public void setFileHash(String fileHash) {
		this.fileHash = fileHash;
	}
	public String getFileSummary() {
		return fileSummary;
	}
	public void setFileSummary(String fileSummary) {
		this.fileSummary = fileSummary;
	}
	public String getFileContent() {
		return fileContent;
	}
	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Long getFirstAccessTime() {
		return firstAccessTime;
	}
	public void setFirstAccessTime(Long firstAccessTime) {
		this.firstAccessTime = firstAccessTime;
	}
	public String getRefUrl() {
		return refUrl;
	}
	public void setRefUrl(String refUrl) {
		this.refUrl = refUrl;
	}
	public String getRefSummary() {
		return refSummary;
	}
	public void setRefSummary(String refSummary) {
		this.refSummary = refSummary;
	}
	public String getRefTitle() {
		return refTitle;
	}
	public void setRefTitle(String refTitle) {
		this.refTitle = refTitle;
	}
	public Long getAccessTime() {
		return firstAccessTime;
	}
	public void setAccessTime(Long accessTime) {
		this.firstAccessTime = accessTime;
	}
	public String getFullFilePath() {
		return fullFilePath;
	}
	public void setFullFilePath(String fullFilePath) {
		this.fullFilePath = fullFilePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public boolean isDownloading() {
		return isDownloading;
	}
	public void setDownloading(boolean isDownloading) {
		this.isDownloading = isDownloading;
	}
	public String getDownloadLink() {
		return downloadLink;
	}
	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	@Override
	public String toString() {
		return "RedisDocumentDto [refUrl=" + refUrl + ", refSummary="
				+ refSummary + ", refTitle=" + refTitle + ", firstAccessTime="
				+ firstAccessTime + ", fullFilePath=" + fullFilePath
				+ ", fileName=" + fileName + ", isDownloading=" + isDownloading
				+ ", downloadLink=" + downloadLink + "]";
	}

	public DocumentDto(long id, String refUrl, String refSummary, String refTitle, Long firstAccessTime,
			String fullFilePath, String fileName, boolean isDownloading, String downloadLink) {
		super();
		this.id = id;
		this.refUrl = refUrl;
		this.refSummary = refSummary;
		this.refTitle = refTitle;
		this.firstAccessTime = firstAccessTime;
		this.fullFilePath = fullFilePath;
		this.fileName = fileName;
		this.isDownloading = isDownloading;
		this.downloadLink = downloadLink;
	}
	
	
	public DocumentDto(long id, String fileName, String fileContent, String refUrl, String refSummary,
			String refTitle, Long firstAccessTime, String fullFilePath,String fileSummary,
			boolean isDownloading, String downloadLink) {
		super();
		this.id = id;
		this.refUrl = refUrl;
		this.refSummary = refSummary;
		this.refTitle = refTitle;
		this.firstAccessTime = firstAccessTime;
		this.fullFilePath = fullFilePath;
		this.fileName = fileName;
		this.fileContent = fileContent;
		this.fileSummary = fileSummary;
		this.isDownloading = isDownloading;
		this.downloadLink = downloadLink;
	}
	
	public DocumentDto(long id, String refUrl, String refSummary, String refTitle, Long firstAccessTime,
			String fullFilePath, String fileName, String fileContent, String fileSummary, String fileHash,
			boolean isDownloading, String downloadLink, int retryCount) {
		super();
		this.id = id;
		this.refUrl = refUrl;
		this.refSummary = refSummary;
		this.refTitle = refTitle;
		this.firstAccessTime = firstAccessTime;
		this.fullFilePath = fullFilePath;
		this.fileName = fileName;
		this.fileContent = fileContent;
		this.fileSummary = fileSummary;
		this.fileHash = fileHash;
		this.isDownloading = isDownloading;
		this.downloadLink = downloadLink;
		this.retryCount = retryCount;
	}
	
}
