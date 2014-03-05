package za.co.tbt.mydining;

import java.util.List;

import za.co.tbt.mydining.adapter.DBListAdapter;
import za.co.tbt.mydining.db.CuisineDataSource;
import za.co.tbt.mydining.db.DBItem;
import za.co.tbt.mydining.db.RestaurantDataSource;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class CuisineFragment extends Fragment{
	private DBListAdapter listAdapter = null;
	//private Context context;
	
	CuisineDataSource cuisineDataSource = null;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_cuisine, container, false);
        
        ListView cuisineView = (ListView)rootView.findViewById(R.id.list_cuisines);
		
        
		cuisineDataSource = new CuisineDataSource(getActivity());
		cuisineDataSource.open();
		
		listAdapter = new DBListAdapter(getActivity());
		listAdapter.setItems(cuisineDataSource.getAllCuisines());
		cuisineView.setAdapter(listAdapter);
		
        return rootView;
    }
	
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
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
}
