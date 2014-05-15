package za.co.tbt.mydining;

import za.co.tbt.mydining.adapter.FavouriteListAdapter;
import za.co.tbt.mydining.db.Favourite;
import za.co.tbt.mydining.db.FavouriteDataSource;
import za.co.tbt.mydining.db.Restaurant;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class FavouriteFragment extends Fragment implements OnItemClickListener {
	private ListView favView = null;
	private FavouriteListAdapter listAdapter = null;
	private FavouriteDataSource favDataSource = null;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_favourites, container, false);
        
        favView = (ListView)rootView.findViewById(R.id.list_favourites);
        
        favView.setOnItemClickListener(this);
        
		favDataSource = new FavouriteDataSource(getActivity());
		favDataSource.open();
		
		listAdapter = new FavouriteListAdapter(getActivity());
		listAdapter.setItems(favDataSource.getAllFavourites());
		favView.setAdapter(listAdapter);	
		
        return rootView;
    }
	
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		favDataSource.close();
	}
	
	public CharSequence getTitle(){
		return getString(R.string.title_favourites);		
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		Restaurant restaurant = ((Favourite)favView.getItemAtPosition(position)).getRestaurant();
		
		Intent intent = new Intent(getActivity(), RestaurantDetailActivity.class);
		intent.putExtra(RestaurantFragment.RESTAURANT_NAME, restaurant.getName());
		startActivity(intent);
	}
}
