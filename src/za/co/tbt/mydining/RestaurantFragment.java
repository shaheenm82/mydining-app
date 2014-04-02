package za.co.tbt.mydining;

import java.util.List;

import za.co.tbt.mydining.adapter.RestaurantListAdapter;
import za.co.tbt.mydining.db.DBItem;
import za.co.tbt.mydining.db.FavouriteDataSource;
import za.co.tbt.mydining.db.Restaurant;
import za.co.tbt.mydining.db.RestaurantDataSource;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class RestaurantFragment extends Fragment implements OnItemClickListener {
	public static final String RESTAURANT_NAME = "restaurant_name";
	
	private ListView restView = null;
	private RestaurantListAdapter listAdapter = null;
	private RestaurantDataSource restDataSource = null;
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_restaurant, container, false);         
        
        restView = (ListView)rootView.findViewById(R.id.list_restaurants);
        
        restView.setOnItemClickListener(this);
        
		restDataSource = new RestaurantDataSource(getActivity());
		restDataSource.open();
		
		listAdapter = new RestaurantListAdapter(getActivity());
		listAdapter.setItems(restDataSource.getAllRestaurants());
		restView.setAdapter(listAdapter);		
		
        return rootView;
    }
	
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		
		restDataSource.close();
	}
	
	public int filter(String filter){
		String[] args = {filter + "%"};
		
		List<Restaurant> items = restDataSource.searchForRestaurants("name LIKE ?", args); 
		
		listAdapter.setItems(items);
		
		return items.size();
	}
	
	public int filterCuisines(String filter){
		String[] args = {"%" + filter + "%"};
		
		List<Restaurant> items = restDataSource.searchForRestaurants("cuisines LIKE ?", args); 
		
		listAdapter.setItems(items);
		
		return items.size();
	}
	
	public CharSequence getTitle(){
		return getString(R.string.title_outlets);		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		Restaurant restaurant = (Restaurant)restView.getItemAtPosition(position);
		
		FavouriteDataSource favDataSource = new FavouriteDataSource(getActivity());
		favDataSource.open();
		favDataSource.addFavourite(restaurant);
		favDataSource.close();
		
		Intent intent = new Intent(getActivity(), RestaurantDetailActivity.class);
		intent.putExtra(RESTAURANT_NAME, restaurant.getName());
		startActivity(intent);
	}
}
