package za.co.tbt.mydining.db;

import android.content.Context;
import android.location.Location;
import android.os.AsyncTask;

public class NearbyRestaurantUpdater extends
		AsyncTask<Location, Void, Void> {
	Context context;
	
	public NearbyRestaurantUpdater(Context context) {
		super();
		this.context = context;
	}

	@Override
	protected Void doInBackground(Location... params) {
		// TODO Auto-generated method stub
		NearbyRestaurantDataSource nearbyDataSource = new NearbyRestaurantDataSource(context);
		nearbyDataSource.open();
		nearbyDataSource.updateNearbyDistances(params[0]);
		
		return null;
	}
	
}
