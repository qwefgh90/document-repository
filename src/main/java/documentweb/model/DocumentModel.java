package documentweb.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class DocumentModel {
	long id;
	String refUrl;
	String refSummary;
	String refTitle;
	Long firstAccessTime;
	String fullFilePath;
	String fileName;
	String fileContent;
	String fileSummary;
	String fileHash;
	boolean isDownloading;
	String downloadLink;
	int retryCount;
	boolean isEUCKRPage;
	char sphinx_yn='N';
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public Long getFirstAccessTime() {
		return firstAccessTime;
	}
	public void setFirstAccessTime(Long firstAccessTime) {
		this.firstAccessTime = firstAccessTime;
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
	public String getFileContent() {
		return fileContent;
	}
	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}
	public String getFileSummary() {
		return fileSummary;
	}
	public void setFileSummary(String fileSummary) {
		this.fileSummary = fileSummary;
	}
	public String getFileHash() {
		return fileHash;
	}
	public void setFileHash(String fileHash) {
		this.fileHash = fileHash;
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
	public int getRetryCount() {
		return retryCount;
	}
	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
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
	
	
}
