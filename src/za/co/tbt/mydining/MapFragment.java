package za.co.tbt.mydining;

import za.co.tbt.mydining.adapter.DBListAdapter;
import za.co.tbt.mydining.db.CuisineDataSource;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link MenuFragment.OnFragmentInteractionListener} interface to handle
 * interaction events. Use the {@link MapFragment#newInstance} factory method
 * to create an instance of this fragment.
 * 
 */
public class MapFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_map, container, false);
        
        //ListView cuisineView = (ListView)rootView.findViewById(R.id.list_menus);
		
        
		/*cuisineDataSource = new CuisineDataSource(getActivity());
		cuisineDataSource.open();
		
		listAdapter = new DBListAdapter(getActivity());
		listAdapter.setItems(cuisineDataSource.getAllCuisines());
		cuisineView.setAdapter(listAdapter);*/
		
        return rootView;
	}

	public CharSequence getTitle(){
		return getString(R.string.title_menus);		
	}
}
