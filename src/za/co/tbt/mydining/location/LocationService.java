package za.co.tbt.mydining.location;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;

public class LocationService extends Service implements GooglePlayServicesClient.ConnectionCallbacks,
GooglePlayServicesClient.OnConnectionFailedListener{
	
	
	public LocationService() {
	}

	LocationClient location_client;
	Location location;
	LocationClientBinder clientBinder;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		location_client = new LocationClient(this, this, this);
	
		location_client.connect();

		clientBinder = new LocationClientBinder();
	}

	
	@Override
	public void onDestroy() {
		location_client.disconnect();
		super.onDestroy();
	}


	@Override
	public IBinder onBind(Intent intent) {
		return clientBinder;
	}
	
	/**
     * Verify that Google Play services is available before making a request.
     *
     * @return true if Google Play services is available, otherwise false
     */
    public boolean servicesConnected() {

        // Check that Google Play services is available
        int resultCode =
                GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

        // If Google Play services is available
        if (ConnectionResult.SUCCESS == resultCode) {
            // Continue
        	//showErrorDialog(resultCode);
            return true;
        // Google Play services was not available for some reason
        } else {
            // Display an error dialog
            //Dialog dialog = GooglePlayServicesUtil.getErrorDialog(resultCode, this, 0);
            //if (dialog != null) {
            //    ErrorDialogFragment errorFragment = new ErrorDialogFragment();
            //    errorFragment.setDialog(dialog);
            //    errorFragment.show(getFragmentManager(), "");
            //}
            return false;
        }
    }
    
	@Override
	public void onConnectionFailed(ConnectionResult result) {
	}

	@Override
	public void onConnected(Bundle connectionHint) {
		if (servicesConnected()){
			
			location = location_client.getLastLocation();							
			
			clientBinder.setLocation(location);
		}
	}


	@Override
	public void onDisconnected() {
	}
}
