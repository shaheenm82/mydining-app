package za.co.tbt.mydining.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

public class DBVersionChecker extends AsyncTask<String, String, Boolean> {
	private static final String DB_PATH = "";
	private static final String DB_VERSION_FILE = "version.txt";
	
	String server_version;
	//ProgressDialog checkVersion;
	
	DBVersionCheckListener dbStatusListener;
	
	public DBVersionChecker(DBVersionCheckListener dbstatuslistener) {
		// TODO Auto-generated constructor stub
		//checkVersion = new ProgressDialog((Activity) dbstatuslistener);
		//checkVersion.setCancelable(false);
		//checkVersion.setMessage("Loading");
		//checkVersion.show();
				//ProgressDialog.show((Activity) dbstatuslistener, "Loading...", "",true);
		dbStatusListener = dbstatuslistener;
	}
	
	@Override
	protected Boolean doInBackground(String... params) {
		// TODO Auto-generated method stub
		boolean new_version = false;
		String current_version = params[0];
		
		FTPClient ftpClient = new FTPClient();
	    
		InputStream version_in;
		BufferedReader version_reader;
		
		//download and check version file
		try {
			publishProgress("Connecting to Server");
			ftpClient.setConnectTimeout(2500);
			ftpClient.connect("shaheen.co.za");
		    if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())){
		    	publishProgress("Connected to Server");
		    	
		    	ftpClient.enterLocalPassiveMode();
			    ftpClient.login("anonymous@shaheen.co.za", "");
			    
			    if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())){
			    	publishProgress("Checking for Database Updates");					
			    	
			    	version_in = ftpClient.retrieveFileStream(DB_PATH + DB_VERSION_FILE);
				    version_reader = new BufferedReader(new InputStreamReader(version_in));
				        
				    server_version = version_reader.readLine();
				    version_in.close();
				        
				    publishProgress("Current Version " + current_version + ", Server Version " + server_version);
				    if (!server_version.equals(current_version)){
				    	new_version = true;
				    	publishProgress("New Database v" + server_version + " found.");				    	
				    }
				    ftpClient.logout();
			    }		
			    ftpClient.disconnect();
		    }		    		    		           
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
			//download database
		return new_version;
	}
	
	@Override
	protected void onProgressUpdate(String... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
		dbStatusListener.databaseStatusUpdated(values[0]);
	}
	
	@Override
	protected void onPostExecute(Boolean result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if (result == true){
			dbStatusListener.databaseOutdated(server_version);
		}else{
			publishProgress("Database Up To Date");
			dbStatusListener.databaseUpToDate();
		}
		//checkVersion.dismiss();
	}
}
