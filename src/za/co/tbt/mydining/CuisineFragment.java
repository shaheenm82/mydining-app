package za.co.tbt.mydining;

import java.util.List;

import za.co.tbt.mydining.adapter.DBListAdapter;
import za.co.tbt.mydining.db.Cuisine;
import za.co.tbt.mydining.db.CuisineDataSource;
import za.co.tbt.mydining.db.DBItem;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class CuisineFragment extends Fragment implements OnItemClickListener{
	private DBListAdapter listAdapter = null;
	private ListView cuisineView = null;
	CuisineDataSource cuisineDataSource = null;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_cuisine, container, false);
        
        cuisineView = (ListView)rootView.findViewById(R.id.list_cuisines);
		
        cuisineView.setOnItemClickListener(this);
        
		cuisineDataSource = new CuisineDataSource(getActivity());
		cuisineDataSource.open();
		
		listAdapter = new DBListAdapter(getActivity());
		listAdapter.setItems(cuisineDataSource.getAllCuisines());
		cuisineView.setAdapter(listAdapter);
		
        return rootView;
    }
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		cuisineDataSource.close();
	}
	
	public int filter(String filter){
		String[] args = {"%" + filter + "%"};
		
		List<DBItem> items = cuisineDataSource.searchForCuisines("name LIKE ?", args); 
		
		listAdapter.setItems(items);
		
		return items.size();
	}
	
	public CharSequence getTitle(){
		return getString(R.string.title_cuisines);		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Cuisine cuisine = (Cuisine)cuisineView.getItemAtPosition(position);
		
		EntryActivity entryActivity = ((EntryActivity)getActivity());
				
		entryActivity.getActionBar().setSelectedNavigationItem(0);
		((RestaurantFragment) entryActivity.mEntryPagerAdapter.getItem(0)).filterCuisines(cuisine.getName());
	}
}
