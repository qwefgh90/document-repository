package documentweb.model;

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
}
