package za.co.tbt.mydining;

import java.util.List;

import za.co.tbt.mydining.adapter.MenuListAdapter;
import za.co.tbt.mydining.db.Menu;
import za.co.tbt.mydining.db.Restaurant;
import za.co.tbt.mydining.db.RestaurantDataSource;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link MenuFragment.OnFragmentInteractionListener} interface to handle
 * interaction events. Use the {@link MenuFragment#newInstance} factory method
 * to create an instance of this fragment.
 * 
 */
public class MenuFragment extends Fragment {
	//private ListView restView = null;
	private RestaurantDataSupplier restDataSupplier = null;
	private ExpandableListView menuView = null;
	private MenuListAdapter menuAdapter = null;
	//private RestaurantDataSource restDataSource = null;
	private Menu restaurant_menu = null;	
	
	private String filter;
	
	public void setRestaurantMenu(Menu menu){
		this.restaurant_menu = menu;
		
		menuAdapter = new MenuListAdapter(getActivity(), restaurant_menu);
		menuView.setAdapter(menuAdapter);		        
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		restDataSupplier = (RestaurantDataSupplier) activity;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        
        menuView = (ExpandableListView)rootView.findViewById(R.id.expandable_menu);
		
        restaurant_menu = restDataSupplier.requestRestaurantMenu();
        
        menuAdapter = new MenuListAdapter(getActivity(), restaurant_menu);
		menuView.setAdapter(menuAdapter);
		
		return rootView;
	}

	public int filterMenu(String filter){
		//this.filterType = "name";
		RestaurantDataSource restDataSource;
		
		this.filter = filter + "%";
		
		restDataSource = new RestaurantDataSource(getActivity());
    	restDataSource.open();
		
		restaurant_menu = restDataSource.getRestaurantMenu(restDataSupplier.requestRestaurantDetails().getId(), filter); 
		
		menuAdapter.setMenu(restaurant_menu);
		
		return restaurant_menu.getCategories().size();
	}
	
	public CharSequence getTitle(){
		return getString(R.string.title_menus);		
	}
}
