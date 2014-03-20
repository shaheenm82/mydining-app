package za.co.tbt.mydining;

import za.co.tbt.mydining.db.MyDiningDbOpenHelper;
import za.co.tbt.mydining.service.DBDownloadListener;
import za.co.tbt.mydining.service.DBDownloadService;
import za.co.tbt.mydining.service.DBVersionCheckListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

public class SplashScreenActivity extends Activity implements DBVersionCheckListener, 
	DialogInterface.OnClickListener, DBDownloadListener{
	private ProgressDialog checkDBDialog;
	
	private MyDiningDbOpenHelper diningHelper;
	
	String server_version;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		
		//create database helper
		/*SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
		Editor prefEditor = sharedPref.edit();
			
		prefEditor.putString("db_version_pref", "0.1.3");
		prefEditor.commit();*/
		
		diningHelper = new MyDiningDbOpenHelper(this);
		diningHelper.createDatabase();
		//diningHelper.openDataBase();
	}

	@Override
	public void databaseStatusUpdated(String progress) {
		// TODO Auto-generated method stub
		if ( checkDBDialog == null ){
			checkDBDialog = ProgressDialog.show(this, "", "",true);
		}
		Log.d("ssm", progress);
		checkDBDialog.setMessage(progress);
	}

	@Override
	public void databaseOutdated(String version) {
		// TODO Auto-generated method stub
		server_version = version;
		
		checkDBDialog.dismiss();
		checkDBDialog = null;
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		builder.setMessage("A new Database version has been found.  Would you like to download it now?");
		builder.setPositiveButton("Yes", this);
		builder.setNegativeButton("No", this);
		builder.show();
	}

	@Override
	public void databaseUpToDate() {
		// TODO Auto-generated method stub
		
		
		enterApplication();
	}
	
	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		switch (which){
		case DialogInterface.BUTTON_POSITIVE:
			//download database
			diningHelper.downloadDB();
			break;
		case DialogInterface.BUTTON_NEGATIVE:
			//show EntryActivity
			enterApplication();
			break;
		}
	}

	@Override
	public void databaseDownloadStatusUpdated(String progress) {
		// TODO Auto-generated method stub
		if ( checkDBDialog == null ){
			checkDBDialog = ProgressDialog.show(this, "", "",true);
		}
		Log.d("ssm", progress);
		checkDBDialog.setMessage(progress);
	}

	@Override
	public void databaseRetrieved(boolean success) {
		// TODO Auto-generated method stub
		if (success == true){
			if (diningHelper.updateDBFromResource()){
				
			SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
			Editor prefEditor = sharedPref.edit();
				
			prefEditor.putString("db_version_pref", server_version);
			prefEditor.apply();
		
			enterApplication();
			}else{
				checkDBDialog.setMessage("Error Updating DB");
				finish();
			}
		}
	}

	private void enterApplication(){
		Intent intent = new Intent(this, EntryActivity.class);
		startActivity(intent);
		
		checkDBDialog.dismiss();
		
		finish();
	}
}
