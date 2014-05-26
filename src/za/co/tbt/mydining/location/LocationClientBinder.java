package za.co.tbt.mydining.location;

import java.util.ArrayList;
import java.util.List;

import za.co.tbt.mydining.db.NearbyRestaurantDataSource;
import android.location.Location;
import android.os.Binder;

public class LocationClientBinder extends Binder {
	Location location;
	List<LocationUpdateListener> locUpdateListeners;
	
	public LocationClientBinder() {
		locUpdateListeners = new ArrayList<LocationUpdateListener>();
	}

	public void addLocationUpdateListener(LocationUpdateListener lulistener) {
		// TODO Auto-generated method stub
		//Log.d("ssm", "adding location listener");
		locUpdateListeners.add(lulistener);
		
		if(location!=null){
			for (LocationUpdateListener listener : locUpdateListeners) {
				listener.locationUpdated(location);
			}			
		}
	}
	
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
				
		if(location!=null){
			for (LocationUpdateListener listener : locUpdateListeners) {
				//Log.d("ssm", "location updated");
				listener.locationUpdated(location);
			}			
		}
	}
	
	
}
