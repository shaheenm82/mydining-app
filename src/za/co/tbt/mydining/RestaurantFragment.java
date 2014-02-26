package za.co.tbt.mydining;

import za.co.tbt.mydining.adapter.DBListAdapter;
import za.co.tbt.mydining.db.MyDiningDbOpenHelper;
import za.co.tbt.mydining.db.RestaurantDataSource;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class RestaurantFragment extends Fragment {
	private DBListAdapter listAdapter = null;
	//private Context context;
	
	RestaurantDataSource restDataSource = null;
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_restaurant, container, false);         
        
        ListView restView = (ListView)rootView.findViewById(R.id.list_restaurants);
		
        
		restDataSource = new RestaurantDataSource(getActivity());
		restDataSource.open();
		
		listAdapter = new DBListAdapter(getActivity(), restDataSource.getAllRestaurants());
		restView.setAdapter(listAdapter);		
		
        return rootView;
    }
	
	public CharSequence getTitle(){
		return getString(R.string.title_outlets);		
	}
}
