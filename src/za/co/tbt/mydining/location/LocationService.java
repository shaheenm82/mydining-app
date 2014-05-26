package za.co.tbt.mydining.location;

import za.co.tbt.mydining.db.NearbyRestaurantUpdater;
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

	//List<LocationUpdateListener> locUpdateListeners;
	LocationClient location_client;
	Location location;
	LocationClientBinder clientBinder;
	
	//public LocationService() {
	//}

	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		location_client = new LocationClient(this, this, this);
	
		location_client.connect();

		clientBinder = new LocationClientBinder();
		//locUpdateListeners = new ArrayList<LocationUpdateListener>();
	}

	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		location_client.disconnect();
		super.onDestroy();
	}


	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		//Log.d("ssm", "connecting location client");
		
		return clientBinder;
	}


	/*public void addLocationUpdateListener(LocationUpdateListener lulistener) {
		// TODO Auto-generated method stub
		locUpdateListeners.add(lulistener);
		
		if(location!=null){
			for (LocationUpdateListener listener : locUpdateListeners) {
				listener.locationUpdated(location);
			}			
		}
	}*/
	
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
		// TODO Auto-generated method stub
		/*for (LocationUpdateListener listener : locUpdateListeners) {
			listener.locationUpdateFailed();
		}*/
	}

	@Override
	public void onConnected(Bundle connectionHint) {
		//Log.d("ssm", "onConnected");
		if (servicesConnected()){
			
			location = location_client.getLastLocation();							
			
			/*NearbyRestaurantDataSource nearbyDataSource = new NearbyRestaurantDataSource(this);
			nearbyDataSource.open();
			nearbyDataSource.updateNearbyDistances(location);*/
			
			NearbyRestaurantUpdater nrupdater = new NearbyRestaurantUpdater(this);
			nrupdater.execute(location);
			
			/*for (LocationUpdateListener listener : locUpdateListeners) {
				listener.locationUpdated(location);
			}*/
			
		//	Log.d("ssm", "setting location");
			clientBinder.setLocation(location);
		}
	}


	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub
		
	}
}
