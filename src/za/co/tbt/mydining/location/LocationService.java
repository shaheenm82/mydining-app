package za.co.tbt.mydining.location;

import java.util.ArrayList;
import java.util.List;

import za.co.tbt.mydining.db.NearbyRestaurantDataSource;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;

public class LocationService implements GooglePlayServicesClient.ConnectionCallbacks,
GooglePlayServicesClient.OnConnectionFailedListener{
	Context context;
	
	List<LocationUpdateListener> locUpdateListeners;
	LocationClient location_client;
	Location location;
	
	private static LocationService instance = null;
	
	protected LocationService(Context context) {
		// Exists only to defeat instantiation.
		this.context = context;
		
		location_client = new LocationClient(context, this, this);
	}
	
	public static LocationService getInstance(Context context) {
	      if(instance == null) {
	         instance = new LocationService(context);
	      }
	      return instance;
	}

	public void start(){
		location_client.connect();
	}
	
	public void stop(){
		location_client.disconnect();
	}
	/**
     * Verify that Google Play services is available before making a request.
     *
     * @return true if Google Play services is available, otherwise false
     */
    private boolean servicesConnected() {

        // Check that Google Play services is available
        int resultCode =
                GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);

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
    
    public void addLocationUpdateListener(LocationUpdateListener lulistener) {
		// TODO Auto-generated method stub
		if (locUpdateListeners == null){
			locUpdateListeners = new ArrayList<LocationUpdateListener>();
		}
		
		locUpdateListeners.add(lulistener);
		
		if(location != null){
			for (LocationUpdateListener listener : locUpdateListeners) {
				listener.locationUpdated(location);
			}			
		}
	}
    
	@Override
	public void onConnectionFailed(ConnectionResult connectionResult) {
		/*
         * Google Play services can resolve some errors it detects.
         * If the error has a resolution, try sending an Intent to
         * start a Google Play services activity that can resolve
         * error.
         */
        if (connectionResult.hasResolution()) {
        	/* We never seem to enter this method even with the GPS and Mobile Data
        	 * turned off.  Not even in airplane mode.
        	 */
            //try {
                // Start an Activity that tries to resolve the error
                //connectionResult.startResolutionForResult(
                //        this,
                //        LocationUtils.CONNECTION_FAILURE_RESOLUTION_REQUEST);
                /*
                * Thrown if Google Play services canceled the original
                * PendingIntent
                */
            //} catch (IntentSender.SendIntentException e) {
                // Log the error
            //    e.printStackTrace();
            //}
        } else {
            // If no resolution is available, display a dialog to the user with the error.
            //showErrorDialog(connectionResult.getErrorCode());
        }
	}

	@Override
	public void onConnected(Bundle connectionHint) {
		// TODO Auto-generated method stub
		if (servicesConnected()){
			location = location_client.getLastLocation();

			if ( locUpdateListeners != null && locUpdateListeners.size() != 0){
				for (LocationUpdateListener listener : locUpdateListeners) {
					listener.locationUpdated(location);
				}						
			}
			
			NearbyRestaurantDataSource nearbyDataSource = new NearbyRestaurantDataSource(context);
			nearbyDataSource.open();
			nearbyDataSource.updateNearbyDistances(location);
		}
	}

	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub
		
	}	
}
