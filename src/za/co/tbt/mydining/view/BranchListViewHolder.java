package za.co.tbt.mydining.view;

import java.text.DecimalFormat;

import za.co.tbt.mydining.R;
import za.co.tbt.mydining.db.Branch;
import za.co.tbt.mydining.location.LocationUpdateListener;
import android.content.ComponentName;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class BranchListViewHolder implements OnClickListener, LocationUpdateListener{
	Branch branch;
	private Location location = null;
	
	public TextView branchSuburbText = null;
	public TextView branchNameText = null;
	public TextView branchAddressText = null;
	public TextView branchTelephoneText = null;
	public ImageView branchHalaalImage = null;
	public ImageView branchKosherImage = null;
	
	public BranchListViewHolder(View row) {
		branchSuburbText = (TextView) row.findViewById(R.id.text_branch_suburb);
		branchNameText = (TextView) row.findViewById(R.id.text_branch_name);
		branchAddressText = (TextView) row.findViewById(R.id.text_branch_address);
		branchTelephoneText = (TextView) row.findViewById(R.id.text_branch_tel);
		branchHalaalImage = (ImageView) row.findViewById(R.id.image_branch_halaal);
		branchKosherImage = (ImageView) row.findViewById(R.id.image_branch_kosher);
	}

	public void populateFrom(Branch branch){
		this.branch = branch;
		DecimalFormat df = new DecimalFormat("#0.0 km");
		
		branchSuburbText.setText(branch.getSuburb() + " (" + df.format(branch.getDistance()/1000) + ")");
		branchNameText.setText(branch.getName());
		branchAddressText.setText(branch.getAddress());
		branchTelephoneText.setText(branch.getTelephone_no());
		if (branch.isHalaal()){
			branchHalaalImage.setImageResource(R.drawable.ic_halaal);
		}else{
			branchHalaalImage.setImageResource(android.R.color.transparent);
		}
		
		if (branch.isKosher()){
			branchKosherImage.setImageResource(R.drawable.ic_kosher);
		}else{
			branchKosherImage.setImageResource(android.R.color.transparent);
		}				
	}
	
	public Branch getBranch(){
		return branch;
	}

	@Override
	public void onClick(View v) {
		String saddr = "";
		
		if (location != null){
			saddr = "&saddr=" + location.getLatitude() + "," + location.getLongitude();
		}
		
		Intent intent = new Intent(Intent.ACTION_VIEW, 
			    Uri.parse("https://maps.google.com/maps?" + saddr + "&daddr=" + branch.getLatitude() + "," + branch.getLongitude() + "&mode=driving"));
			intent.setComponent(new ComponentName("com.google.android.apps.maps", 
			    "com.google.android.maps.MapsActivity"));
			v.getContext().startActivity(intent);
	}

	@Override
	public void locationUpdated(Location location) {
		this.location = location;
	}
}
