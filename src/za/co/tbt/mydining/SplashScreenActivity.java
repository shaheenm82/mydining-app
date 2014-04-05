package za.co.tbt.mydining;

import za.co.tbt.mydining.db.MyDiningDbOpenHelper;
import za.co.tbt.mydining.service.DBDownloadListener;
import za.co.tbt.mydining.service.DBVersionCheckListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class SplashScreenActivity extends Activity implements DBVersionCheckListener, 
	DialogInterface.OnClickListener, DBDownloadListener{
	private ProgressDialog checkDBDialog;
	private TextView textStatus;
	
	private MyDiningDbOpenHelper diningHelper;
	
	String server_version;
	
	// Splash screen timer
    private static int SPLASH_TIME_OUT = 1000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		
		textStatus = (TextView) findViewById(R.id.text_sync_status);
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
		/*if ( checkDBDialog == null && server_version != null){
			checkDBDialog = ProgressDialog.show(this, "", "",true);
		}
		Log.d("ssm", progress);
		if ( checkDBDialog == null){
			checkDBDialog.setMessage(progress);
		}*/
		textStatus.setText(progress  + "...");
	}

	@Override
	public void databaseOutdated(String version) {
		// TODO Auto-generated method stub
		server_version = version;
		
		//checkDBDialog.dismiss();
		//checkDBDialog = null;
		
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
		/*if ( checkDBDialog == null ){
			checkDBDialog = ProgressDialog.show(this, "", "",true);
		}
		Log.d("ssm", progress);
		checkDBDialog.setMessage(progress);*/
		textStatus.setText(progress);
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
		//checkDBDialog.dismiss();
		
		new Handler().postDelayed(new Runnable() {
			 
            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
 
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
            	Intent intent = new Intent(SplashScreenActivity.this, EntryActivity.class);
        		startActivity(intent);
 
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
	}
}
