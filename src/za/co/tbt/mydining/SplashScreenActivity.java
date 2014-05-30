package za.co.tbt.mydining;

import za.co.tbt.mydining.db.MyDiningDbOpenHelper;
import za.co.tbt.mydining.db.NearbyRestaurantUpdater;
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
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.preference.PreferenceManager;

public class SplashScreenActivity extends Activity implements DBVersionCheckListener, 
	DialogInterface.OnClickListener, DBDownloadListener, LocationUpdateListener	{
	private ProgressDialog checkDBDialog;
	private MyDiningDbOpenHelper diningHelper;
	
	NearbyRestaurantUpdater nearbyRestaurantUpdater;
	LocationClientBinder clientBinder;
	boolean mBound = false;
	
	String server_version;
	boolean location_updated;
	
	// Splash screen timer
    private static int SPLASH_TIME_OUT = 0;
	private static final String DB_VERSION_KEY = "db_version_pref";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		
		location_updated = false;
		
		checkDBDialog = ProgressDialog.show(this, "", "",true);
		checkDBDialog.setIndeterminate(true);
		
		
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
		String db_version = sharedPref.getString(DB_VERSION_KEY, "");
		
		DBVersionChecker dbVersionChecker = new DBVersionChecker(this);
		dbVersionChecker.execute(db_version);
		
		Intent intent = new Intent(SplashScreenActivity.this, LocationService.class);
    	bindService(intent, mConnection, BIND_AUTO_CREATE);
	}

	
	@Override
	protected void onDestroy() {
		unbindService(mConnection);
		super.onDestroy();
	}


	@Override
	public void databaseStatusUpdated(String progress) {
		//Log.d("ssm", progress);
	}

	@Override
	public void databaseOutdated(String version, String size) {
		int db_size;
		server_version = version;
		
		checkDBDialog.dismiss();
		checkDBDialog = null;
		
		db_size = Integer.parseInt(size)/1024;
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		builder.setMessage("A new Database version has been found (" + db_size + "kB).  Would you like to download it now?");
		builder.setPositiveButton("Yes", this);
		builder.setNegativeButton("No", this);
		builder.show();
	}

	@Override
	public void databaseUpToDate() {
		enterApplication();
	}
	
	@Override
	public void onClick(DialogInterface dialog, int which) {
		checkDBDialog = ProgressDialog.show(this, "", "",true);
		
		switch (which){
		case DialogInterface.BUTTON_POSITIVE:
			//download database
			DBDownloadService dbDownloader = new DBDownloadService(this);
			dbDownloader.execute(MyDiningDbOpenHelper.DATABASE_PATH + MyDiningDbOpenHelper.DATABASE_NAME + ".tmp");
			break;
		case DialogInterface.BUTTON_NEGATIVE:
			//show EntryActivity
			//Log.d("ssm", "Entering application");
			enterApplication();
			break;
		}
	}

	@Override
	public void databaseDownloadStatusUpdated(String progress) {
	}

	@Override
	public void databaseRetrieved(boolean success) {
		if (success == true){
			diningHelper = new MyDiningDbOpenHelper(this);
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
                // This method will be executed once the timer is over
                // Start your app main activity
            	//Log.d("ssm", "Starting Intent");
            	Intent intent = new Intent(SplashScreenActivity.this, EntryActivity.class);
            	//Log.d("ssm", "Starting Activity");
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
		location_updated = true;
		
		nearbyRestaurantUpdater = new NearbyRestaurantUpdater(this);
		nearbyRestaurantUpdater.execute(location);
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
