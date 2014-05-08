package za.co.tbt.mydining;

import java.util.ArrayList;
import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;

import za.co.tbt.mydining.adapter.NearByListAdapter;
import za.co.tbt.mydining.adapter.RestaurantListAdapter;
import za.co.tbt.mydining.db.Branch;
import za.co.tbt.mydining.db.FavouriteDataSource;
import za.co.tbt.mydining.db.NearbyRestaurantDataSource;
import za.co.tbt.mydining.db.Restaurant;
import za.co.tbt.mydining.db.RestaurantDataSource;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link NearByFragment.OnFragmentInteractionListener} interface to handle
 * interaction events. Use the {@link NearByFragment#newInstance} factory method
 * to create an instance of this fragment.
 * 
 */
public class NearByFragment extends Fragment implements OnItemClickListener, OnSharedPreferenceChangeListener{
	private ListView nearbyView = null;
	private NearByListAdapter listAdapter = null;
	private NearbyRestaurantDataSource nearbyDataSource = null;
	
	public static final String RESTAURANT_NAME = "restaurant_name";
	public static final String RESTAURANT_BRANCH = "restaurant_branch";
	public static final String REST_DISTANCE_KEY = "nearby_rest_pref";
	
		@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
			View rootView = inflater.inflate(R.layout.fragment_restaurant, container, false);         
	        
			nearbyView = (ListView)rootView.findViewById(R.id.list_restaurants);
	        
			nearbyView.setOnItemClickListener(this);
	        
			nearbyDataSource = new NearbyRestaurantDataSource(getActivity());
			nearbyDataSource.open();
			
			listAdapter = new NearByListAdapter(getActivity());
			listAdapter.setItems(nearbyDataSource.findNearbyRestaurants());
			nearbyView.setAdapter(listAdapter);		
			
			SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
			sharedPref.registerOnSharedPreferenceChangeListener(this);
			
	        return rootView;
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
			
		nearbyDataSource.close();
	}
		
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Restaurant restaurant = (Restaurant) nearbyView.getItemAtPosition(position);
		long branch = ((ArrayList<Branch>)restaurant.getRestaurant_branches()).get(0).getId();
				
		FavouriteDataSource favDataSource = new FavouriteDataSource(getActivity());
		favDataSource.open();
		favDataSource.addFavourite(restaurant);
		favDataSource.close();
				
		Log.d("ssm", "selected branch = " + branch);
		
		Intent intent = new Intent(getActivity(), RestaurantDetailActivity.class);
		intent.putExtra(RESTAURANT_NAME, restaurant.getName());
		intent.putExtra(RESTAURANT_BRANCH, branch);
		startActivity(intent);
			
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		// TODO Auto-generated method stub
		if (key.equals(REST_DISTANCE_KEY)){
			listAdapter.setItems(nearbyDataSource.findNearbyRestaurants());
		}
	}

}
