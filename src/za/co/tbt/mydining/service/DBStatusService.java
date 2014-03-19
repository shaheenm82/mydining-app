package za.co.tbt.mydining.service;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import android.os.AsyncTask;
import android.util.Log;

public class DBStatusService extends AsyncTask<String, String, Boolean> {
	
	private static final String DB_PATH = "www/";
	private static final String DB_VERSION_FILE = "version.txt";
	private static final String DB_FILE = "dining.sqlite";
	
	DBStatusListener dbStatusListener;
	String server_version;
	
	public DBStatusService(DBStatusListener dbstatuslistener) {
		// TODO Auto-generated constructor stub
		dbStatusListener = dbstatuslistener;
	}
	
	@Override
	protected Boolean doInBackground(String... params) {
		// TODO Auto-generated method stub
		boolean success = false;
		String retVal = null;
		String current_version = params[0];
		
		FTPClient ftpClient = new FTPClient();
	    
		InputStream version_in;
		BufferedReader version_reader;
		
		OutputStream db_out;
		
		//download and check version file
		try {
			publishProgress("Connecting to Server");
			ftpClient.setConnectTimeout(5000);
			Log.d("ssm", "connecting to mozart");
		    ftpClient.connect("mozart.homenet.org");
		    Log.d("ssm", "Reply: " + ftpClient.getReplyString());
		    if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())){
		    	publishProgress("Connected to Server");
		    	ftpClient.enterLocalPassiveMode();
			    Log.d("ssm", "logging in");
			    ftpClient.login("pi", "raspberry");
			    Log.d("ssm", "Reply: " + ftpClient.getReplyString());
			    if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())){
			    	publishProgress("Checking for Database Updates");					
			    	Log.d("ssm", "getting reader");
				    version_in = ftpClient.retrieveFileStream(DB_PATH + DB_VERSION_FILE);
				    Log.d("ssm", "Reply: " + ftpClient.getReplyString());

				    version_reader = new BufferedReader(new InputStreamReader(version_in));
				        
				    Log.d("ssm", "reading line");
				    server_version = version_reader.readLine();
				    version_in.close();
				        
				    if (!server_version.equals(current_version)){
				    	publishProgress("Retrieving Database");
				    	
				        
				        try {
				            db_out = new BufferedOutputStream(new FileOutputStream(params[1]));
				            success = ftpClient.retrieveFile(DB_PATH + DB_FILE, db_out);
				            
				            db_out.close();
				        } catch (Exception e){
				        	e.printStackTrace();				            
				        }

				    }
				    Log.d("ssm", "closing connection");
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
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
		dbStatusListener.databaseStatusUpdated(values[0]);
	}
	
	@Override
	protected void onPostExecute(Boolean result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if (result == true){
			publishProgress("Loading new Database");
			dbStatusListener.databaseRetrieved(server_version);
		}else{
			publishProgress("Failed to retrieve Database");
			dbStatusListener.databaseRetrieved(server_version);
		}
	}

}
