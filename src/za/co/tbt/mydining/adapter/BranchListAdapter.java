package za.co.tbt.mydining.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import za.co.tbt.mydining.R;
import za.co.tbt.mydining.db.Branch;
import za.co.tbt.mydining.location.LocationService2;
import za.co.tbt.mydining.view.BranchListViewHolder;
import za.co.tbt.mydining.view.BranchProvinceListViewHolder;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;

public class BranchListAdapter extends BaseExpandableListAdapter{
	Context context;
	List<String> provinces;
	HashMap<String, List<Branch>> branches;
	//private LocationProvider locProvider;
	
	public BranchListAdapter(Context context, List<Branch> pbranches){
		//super(context, R.layout.list_dbitem);		
		this.context = context;
		
		setBranches(pbranches);
	}

	public void setBranches(List<Branch> branches) {
		String province = "";
		List<Branch> details;
		
		details = new ArrayList<Branch>();
		this.provinces = new ArrayList<String>();
		this.branches = new HashMap<String, List<Branch>>();
		
		for (Branch branch : branches) {
			if (! province.equals(branch.getProvince())){
				if ( details.size() > 0 ){
					this.branches.put(province, details);
				}
				
				province = branch.getProvince();
				
				provinces.add(province);
				details = new ArrayList<Branch>();								
			}
			
			details.add(branch);
		}
		
		this.branches.put(province, details);
		
		notifyDataSetInvalidated();
		
		/*for (int i = 0; i < getGroupCount(); i++) {
			getGroupView(1, false, null, null);			
		}*/
	}
	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return branches.get(provinces.get(groupPosition)).get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return (groupPosition * 1000) + childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub				
		BranchListViewHolder listviewHolder;
		Branch branch = (Branch)getChild(groupPosition, childPosition);
		
		if (convertView == null){
			LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.list_branch, null);
						
			listviewHolder = new BranchListViewHolder(convertView);
			
			Button btnNavigate = (Button) convertView.findViewById(R.id.button_navigation);						
			btnNavigate.setOnClickListener( listviewHolder);
			
			convertView.setTag(listviewHolder);
		}else{
			listviewHolder = (BranchListViewHolder) convertView.getTag();
		}
		
		LocationService2.getInstance(context).addLocationUpdateListener(listviewHolder);		
		listviewHolder.populateFrom(branch);				
		
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return branches.get(provinces.get(groupPosition)).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return provinces.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return provinces.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return (groupPosition * 1000);
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		BranchProvinceListViewHolder listviewHolder;
		String province = (String) getGroup(groupPosition);
		
		if (convertView == null){
			LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.group_branchprovince, null);
			
			listviewHolder = new BranchProvinceListViewHolder(convertView);
			convertView.setTag(listviewHolder);
		}else{
			listviewHolder = (BranchProvinceListViewHolder) convertView.getTag();
		}
		
		listviewHolder.populateFrom(province);
		
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
