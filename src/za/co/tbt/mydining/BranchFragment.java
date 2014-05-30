package za.co.tbt.mydining;

import java.util.List;

import za.co.tbt.mydining.adapter.BranchListAdapter;
import za.co.tbt.mydining.db.Branch;
import za.co.tbt.mydining.db.RestaurantDataSource;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link MenuFragment.OnFragmentInteractionListener} interface to handle
 * interaction events. Use the {@link BranchFragment#newInstance} factory method
 * to create an instance of this fragment.
 * 
 */
public class BranchFragment extends Fragment{
	private RestaurantDataSupplier restDataSupplier = null;
	private ExpandableListView branchView = null;
	private BranchListAdapter branchAdapter = null;
	
	
	private List<Branch> restaurant_branches = null;
	
	private String filter;
	
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
        
        TextView notice = (TextView) rootView.findViewById(R.id.text_branch_notification);
        if (restaurant_branches.size() == 0){
			notice.setVisibility(View.VISIBLE);
		}else{
			setRestaurantBranches(restaurant_branches);
			notice.setVisibility(View.INVISIBLE);
		}        
        
        return rootView;
	}

	public int filterBranch(String filter){
		List<Branch> branches;
		RestaurantDataSource restDataSource;
		
		this.filter = "%" + filter + "%";
		
		restDataSource = new RestaurantDataSource(getActivity());
    	restDataSource.open();
		
    	branches = restDataSource.getRestaurantBranches(restDataSupplier.requestRestaurantDetails().getId(), this.filter);
		branchAdapter.setBranches(branches);
		
		return branches.size();
	}
	
	public CharSequence getTitle(){
		return getString(R.string.title_menus);		
	}	
}
