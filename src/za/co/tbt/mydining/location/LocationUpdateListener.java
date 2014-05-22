package za.co.tbt.mydining.location;

import android.location.Location;

public interface LocationUpdateListener {
	//public void locationUpdateFailed();
	public void locationUpdated(Location location);
}
