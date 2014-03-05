package za.co.tbt.mydining;

import za.co.tbt.mydining.location.LocationProvider;
import za.co.tbt.mydining.location.LocationUpdateListener;
import za.co.tbt.mydining.location.LocationUtils;
import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
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
	private LocationProvider locProvider;
	private Location location = null;
	private GoogleMap map = null;
	private LatLng you = null;
	
	private boolean map_initialised;
	private boolean location_available;
	
	View rootView = null;
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		locProvider = (LocationProvider)activity;	
		locProvider.setLocationUpdateListener(this);
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
				
        return rootView;
	}		
		
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		// Get a handle to the Map Fragment
		map = ((SupportMapFragment)getActivity().getSupportFragmentManager().findFragmentById(R.id.map_branches)).getMap();		
						
		map.setMyLocationEnabled(true);
		map_initialised = true;                
						
		if (location_available == true){
			updateMapCamera();
		}
	}
	
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		map_initialised = false;
		location_available = false;
		super.onStop();
	}
			
	private void updateMapCamera(){
		you = LocationUtils.getLatLng(getActivity(), location);
		
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
	
	

}
