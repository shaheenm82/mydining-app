package za.co.tbt.mydining;

import za.co.tbt.mydining.adapter.DBListAdapter;
import za.co.tbt.mydining.db.MyDiningDbOpenHelper;
import za.co.tbt.mydining.db.Restaurant;
import za.co.tbt.mydining.db.RestaurantDataSource;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;

public class RestaurantFragment extends Fragment implements OnItemSelectedListener {
	private ListView restView = null;
	private DBListAdapter listAdapter = null;
	private RestaurantDataSource restDataSource = null;
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_restaurant, container, false);         
        
        restView = (ListView)rootView.findViewById(R.id.list_restaurants);
        
        restView.setOnItemSelectedListener(this);
        
		restDataSource = new RestaurantDataSource(getActivity());
		restDataSource.open();
		
		listAdapter = new DBListAdapter(getActivity());
		listAdapter.setItems(restDataSource.getAllRestaurants());
		restView.setAdapter(listAdapter);		
		
        return rootView;
    }
	
	public void filter(String filter){
		String[] args = {"%" + filter + "%"};
		
		listAdapter.setItems(restDataSource.searchForRestaurants("name LIKE ?", args));
	}
	
	public CharSequence getTitle(){
		return getString(R.string.title_outlets);		
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Restaurant restaurant = (Restaurant)restView.getItemAtPosition(position);
		
		
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
}
