package za.co.tbt.mydining.service;

public interface DBVersionCheckListener {
	public void databaseStatusUpdated(String progress);
	
	public void databaseUpToDate();
	
	public void databaseOutdated(String version, String size);
}
