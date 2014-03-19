package za.co.tbt.mydining.service;

public interface DBStatusListener {
	public void databaseStatusUpdated(String progress);
	public void databaseRetrieved(String version);
}
