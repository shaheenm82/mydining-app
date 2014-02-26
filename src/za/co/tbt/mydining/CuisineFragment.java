package za.co.tbt.mydining;

import za.co.tbt.mydining.adapter.DBListAdapter;
import za.co.tbt.mydining.db.CuisineDataSource;
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
		
		listAdapter = new DBListAdapter(getActivity(), cuisineDataSource.getAllCuisines());
		cuisineView.setAdapter(listAdapter);
		
        return rootView;
    }
	
	public CharSequence getTitle(){
		return getString(R.string.title_cuisines);		
	}
}
