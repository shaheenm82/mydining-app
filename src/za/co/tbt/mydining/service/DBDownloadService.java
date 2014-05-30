package za.co.tbt.mydining.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

public class DBDownloadService extends AsyncTask<String, String, Boolean> {
	
	private static final String DB_PATH = "";
	private static final String DB_FILE = "dining.sqlite";
	
	ProgressDialog downloadDB;
	DBDownloadListener dbDownloadListener;
	
	public DBDownloadService(DBDownloadListener dbdownloadlistener) {
		dbDownloadListener = dbdownloadlistener;
	}
	
	@Override
	protected Boolean doInBackground(String...params) {
		boolean success = false;
		
		FTPClient ftpClient = new FTPClient();
	    
		OutputStream db_out;
		
		//download and check version file
		try {
			ftpClient.setConnectTimeout(2500);
			ftpClient.connect("shaheen.co.za");
		    
			if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())){
		    	publishProgress("Connected to Server");
		    	
		    	ftpClient.enterLocalPassiveMode();
			    ftpClient.login("anonymous@shaheen.co.za", "");
			    if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())){
			    	publishProgress("Retrieving Database");
				        
			    	try {
			    		db_out = new BufferedOutputStream(new FileOutputStream(params[0]));
				        success = ftpClient.retrieveFile(DB_PATH + DB_FILE, db_out);
				            
				        db_out.close();
				    } catch (Exception e){
				    	e.printStackTrace();				            
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
		return success;
	}
	
	@Override
	protected void onProgressUpdate(String... values) {
		super.onProgressUpdate(values);
		dbDownloadListener.databaseDownloadStatusUpdated(values[0]);
	}
	
	@Override
	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);
		if (result == true){
			publishProgress("Loading new Database");
			
		}else{
			publishProgress("Failed to retrieve Database");			
		}
		dbDownloadListener.databaseRetrieved(result);
	}

}
