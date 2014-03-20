package za.co.tbt.mydining.service;

public interface DBDownloadListener {
	public void databaseDownloadStatusUpdated(String progress);
	
	public void databaseRetrieved(boolean success);
}
