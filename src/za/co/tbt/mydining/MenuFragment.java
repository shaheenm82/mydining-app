package za.co.tbt.mydining;

import za.co.tbt.mydining.adapter.MenuListAdapter;
import za.co.tbt.mydining.db.Menu;
import za.co.tbt.mydining.db.RestaurantDataSource;
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
	private MenuListAdapter menuAdapter = null;
	//private RestaurantDataSource restDataSource = null;
	private Menu restaurant_menu = null;	
	
	public void setRestaurantMenu(Menu menu){
		this.restaurant_menu = menu;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        
        ExpandableListView menuView = (ExpandableListView)rootView.findViewById(R.id.expandable_menu);
		        
        RestaurantDataSource restaurantDataSource = new RestaurantDataSource(getActivity());
		restaurantDataSource.open();
		
		menuAdapter = new MenuListAdapter(getActivity(), restaurant_menu);
		menuView.setAdapter(menuAdapter);
		
        return rootView;
	}

	public CharSequence getTitle(){
		return getString(R.string.title_menus);		
	}
}
