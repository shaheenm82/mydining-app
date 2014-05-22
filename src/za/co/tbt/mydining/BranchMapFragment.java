package za.co.tbt.mydining;

import java.util.List;

import za.co.tbt.mydining.db.Branch;
import za.co.tbt.mydining.location.LocationClientBinder;
import za.co.tbt.mydining.location.LocationService;
import za.co.tbt.mydining.location.LocationUpdateListener;
import za.co.tbt.mydining.location.LocationUtils;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link MenuFragment.OnFragmentInteractionListener} interface to handle
 * interaction events. Use the {@link BranchMapFragment#newInstance} factory method
 * to create an instance of this fragment.
 * 
 */
public class BranchMapFragment extends Fragment implements LocationUpdateListener{
	//private LocationProvider locProvider;
	private Location location = null;
	private GoogleMap map = null;
	private LatLng you = null;
	
	//LocationService2 locationService;
	private boolean map_initialised;
	private boolean location_available;
	LocationClientBinder clientBinder;
	boolean mBound = false;
	
	View rootView = null;
	
	private RestaurantDataSupplier restDataSupplier = null;
	private List<Branch> restaurant_branches = null;	
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		//locProvider = (LocationProvider)activity;	
		//locationService = LocationService2.getInstance(activity);
				
		//locationService.addLocationUpdateListener(this);
		
		Intent intent = new Intent(activity, LocationService.class);
		getActivity().bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
		
		restDataSupplier = (RestaurantDataSupplier) activity;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		if (rootView != null) {
	        ViewGroup parent = (ViewGroup) rootView.getParent();
	        if (parent != null)
	            parent.removeView(rootView);
	    }
	    try {
	    	rootView = inflater.inflate(R.layout.fragment_map, container, false);				        		
		} catch (InflateException e) {
	        /* map is already there, just return view as it is */
	    }
		
	    restaurant_branches = restDataSupplier.requestRestaurantBranches();
        
        return rootView;
	}		
		
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		//if (!locationService.servicesConnected()){
		//	locationService.start();
		//}
		
		//this.location = locationService.getLocation();
		
		//if (location != null){
		//	location_available = true;
		//}
		
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		// Get a handle to the Map Fragment
		map = ((SupportMapFragment)getActivity().getSupportFragmentManager().findFragmentById(R.id.map_branches)).getMap();		
						
		map.setMyLocationEnabled(true);
		map_initialised = true;                
					
		displayBranchesOnMap();
		
		if (location_available == true){
			updateMapCamera();
		}
	}
	
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		map_initialised = false;
		location_available = false;
		//locationService.stop();
		super.onStop();
	}
			
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		getActivity().unbindService(mConnection);
		super.onDestroy();
	}

	private void updateMapCamera(){
		BitmapDescriptor bitmapDescriptor 
		   = BitmapDescriptorFactory.defaultMarker(
		     BitmapDescriptorFactory.HUE_ROSE);
		
		you = LocationUtils.getLatLng(getActivity(), location);
		//LatLng me = new LatLng(you.latitude +0.1, you.longitude + 0.1);
		
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(you,13));
        
        map.addMarker(new MarkerOptions()
                .title("You")
                .snippet("You are here")
                .position(you));
    }
	
	public CharSequence getTitle(){
		return getString(R.string.title_menus);		
	}

	@Override
	public void locationUpdated(Location location) {
		// TODO Auto-generated method stub
		location_available = true;
		
		this.location = location;			
			
		if (map_initialised == true){	
			updateMapCamera();		
		}
	}
	
	private void displayBranchesOnMap(){
		String logo;
		String logo_id = "";
		BitmapDescriptor bitmapDescriptor;
		
		logo = restDataSupplier.requestRestaurantDetails().getLogo();
		
		
		if (restaurant_branches != null){
			if (logo != null){
				logo_id = logo.substring(0,logo.length()-4);		
				
				bitmapDescriptor 
				   = BitmapDescriptorFactory.fromResource(getResources().getIdentifier(logo_id, "drawable", getActivity().getPackageName()));
			}else{
				bitmapDescriptor 
				= BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE);
			}
			
			for (Branch branch : restaurant_branches) {
				map.addMarker(new MarkerOptions()
                	.title(branch.getName())
                	.snippet(branch.getAddress())
                	.position(new LatLng(branch.getLatitude(), branch.getLongitude()))
                	.icon(bitmapDescriptor));
			}
		}
	}

	/** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                IBinder service) {
        	Log.d("ssm", "onServicesConnected");
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            clientBinder = (LocationClientBinder) service;
            clientBinder.addLocationUpdateListener(BranchMapFragment.this);
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };
}
