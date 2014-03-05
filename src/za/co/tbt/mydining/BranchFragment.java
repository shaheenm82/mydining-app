package za.co.tbt.mydining;

import java.util.List;

import za.co.tbt.mydining.adapter.BranchListAdapter;
import za.co.tbt.mydining.db.Branch;
import za.co.tbt.mydining.db.Menu;
import za.co.tbt.mydining.db.RestaurantDataSource;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link MenuFragment.OnFragmentInteractionListener} interface to handle
 * interaction events. Use the {@link BranchFragment#newInstance} factory method
 * to create an instance of this fragment.
 * 
 */
public class BranchFragment extends Fragment {
	private RestaurantDataSupplier restDataSupplier = null;
	private ExpandableListView branchView = null;
	private BranchListAdapter branchAdapter = null;
	
	private List<Branch> restaurant_branches = null;	
	
	public void setRestaurantBranches(List<Branch> branches){
		this.restaurant_branches = branches;
		
		branchAdapter = new BranchListAdapter(getActivity(), restaurant_branches);
		branchView.setAdapter(branchAdapter);
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
		View rootView = inflater.inflate(R.layout.fragment_branch, container, false);
        
        branchView = (ExpandableListView)rootView.findViewById(R.id.expandable_branch);		
		
        restaurant_branches = restDataSupplier.requestRestaurantBranches();
        
        branchAdapter = new BranchListAdapter(getActivity(), restaurant_branches);
		branchView.setAdapter(branchAdapter);
		
        return rootView;
	}

	public CharSequence getTitle(){
		return getString(R.string.title_menus);		
	}
}
