package za.co.tbt.mydining.view;

import za.co.tbt.mydining.R;
import za.co.tbt.mydining.db.Branch;
import android.view.View;
import android.widget.TextView;

public class BranchListViewHolder {
	public TextView branchSuburbText = null;
	public TextView branchNameText = null;
	public TextView branchAddressText = null;
	public TextView branchTelephoneText = null;
	public TextView branchHalaalText = null;
	public TextView branchKosherText = null;
	
	public BranchListViewHolder(View row) {
		// TODO Auto-generated constructor stub
		branchSuburbText = (TextView) row.findViewById(R.id.text_branch_suburb);
		branchNameText = (TextView) row.findViewById(R.id.text_branch_name);
		branchAddressText = (TextView) row.findViewById(R.id.text_branch_address);
		branchTelephoneText = (TextView) row.findViewById(R.id.text_branch_tel);
		branchHalaalText = (TextView) row.findViewById(R.id.text_branch_halaal);
		branchKosherText = (TextView) row.findViewById(R.id.text_branch_kosher);
	}

	public void populateFrom(Branch branch){
		branchSuburbText.setText(branch.getSuburb());
		branchNameText.setText(branch.getName());
		branchAddressText.setText(branch.getAddress());
		branchTelephoneText.setText(branch.getTelephone_no());
		if (branch.isHalaal()){
			branchHalaalText.setText("H");
		}else{
			branchHalaalText.setText("");
		}
		
		if (branch.isKosher()){
			branchKosherText.setText("K");
		}else{
			branchKosherText.setText("");
		}				
	}
}
