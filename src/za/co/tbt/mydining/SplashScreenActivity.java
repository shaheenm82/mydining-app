package za.co.tbt.mydining;

import za.co.tbt.mydining.db.MyDiningDbOpenHelper;
import za.co.tbt.mydining.db.NearbyRestaurantDataSource;
import za.co.tbt.mydining.location.LocationClientBinder;
import za.co.tbt.mydining.location.LocationService;
import za.co.tbt.mydining.location.LocationUpdateListener;
import za.co.tbt.mydining.service.DBDownloadListener;
import za.co.tbt.mydining.service.DBDownloadService;
import za.co.tbt.mydining.service.DBVersionCheckListener;
import za.co.tbt.mydining.service.DBVersionChecker;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashScreenActivity extends Activity implements DBVersionCheckListener, 
	DialogInterface.OnClickListener, DBDownloadListener, LocationUpdateListener{
	private ProgressDialog checkDBDialog;
	private TextView textStatus;
	private MyDiningDbOpenHelper diningHelper;
	//private LocationService2 locationService;
	//LocationService locationService;
	LocationClientBinder clientBinder;
	boolean mBound = false;
	
	String server_version;
	boolean location_updated;
	
	// Splash screen timer
    private static int SPLASH_TIME_OUT = 1000;
	private static final String DB_VERSION_KEY = "db_version_pref";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		int time_waited = 0;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		
		checkDBDialog = ProgressDialog.show(this, "", "",true);
		checkDBDialog.setIndeterminate(true);
		
		location_updated = false;
		textStatus = (TextView) findViewById(R.id.text_sync_status);
		//create database helper
		/*SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
		Editor prefEditor = sharedPref.edit();
			
		prefEditor.putString("db_version_pref", "0.1.3");
		prefEditor.commit();*/
		
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
		String db_version = sharedPref.getString(DB_VERSION_KEY, "");
		
		DBVersionChecker dbVersionChecker = new DBVersionChecker(this);
		dbVersionChecker.execute(db_version);
		
		//diningHelper = new MyDiningDbOpenHelper(this);
		//diningHelper.createDatabase();
		//diningHelper.openDataBase();
		
//		while (time_waited < 5){
//    		try {
//				Thread.sleep(1000);
//				time_waited ++;
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}            		
//    	}
//		//checkDBDialog.dismiss();
		Intent intent = new Intent(SplashScreenActivity.this, LocationService.class);
    	bindService(intent, mConnection, BIND_AUTO_CREATE);
		
		//enterApplication();
	}

	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		unbindService(mConnection);
		super.onDestroy();
	}


	@Override
	public void databaseStatusUpdated(String progress) {
		// TODO Auto-generated method stub
		/*if ( checkDBDialog == null ){//&& server_version != null
			checkDBDialog = ProgressDialog.show(this, "", "",true);
		}
		//Log.d("ssm", progress);
		if ( checkDBDialog != null){
			checkDBDialog.setMessage(progress);
		}*/
		textStatus.setText(progress  + "...");
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
			checkDBDialog = ProgressDialog.show(this, "", "",true);
			DBDownloadService dbDownloader = new DBDownloadService(this);
			dbDownloader.execute(MyDiningDbOpenHelper.DATABASE_PATH + MyDiningDbOpenHelper.DATABASE_NAME + ".tmp");
			//diningHelper.downloadDB();
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
		//Log.d("ssm", progress);
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
		new Handler().postDelayed(new Runnable() {
			 
            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
 
            @Override
            public void run() {
            	//int time_waited = 0;
//            	while (!location_updated && time_waited < 5){
//            		try {
//						Thread.sleep(1000);
//						time_waited ++;
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}            		
//            	}
            	
            	
                // This method will be executed once the timer is over
                // Start your app main activity
            	Intent intent = new Intent(SplashScreenActivity.this, EntryActivity.class);
        		startActivity(intent);
        		
        		checkDBDialog.dismiss();
        		checkDBDialog = null;
        		
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);		
	}

	@Override
	public void locationUpdated(Location location) {
		// TODO Auto-generated method stub
		//locationService.stop();
		
		location_updated = true;
	}
	
	/** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            clientBinder = (LocationClientBinder) service;
            clientBinder.addLocationUpdateListener(SplashScreenActivity.this);
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

}
