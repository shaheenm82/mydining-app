package za.co.tbt.mydining.view;

import za.co.tbt.mydining.R;
import za.co.tbt.mydining.db.MenuCategory;
import android.view.View;
import android.widget.TextView;

public class BranchProvinceListViewHolder {
	public TextView branchProvinceText = null;
	
	public BranchProvinceListViewHolder(View row) {
		// TODO Auto-generated constructor stub
		branchProvinceText = (TextView) row.findViewById(R.id.text_branchProvince);
	}

	public void populateFrom(String bprovince){
		branchProvinceText.setText(bprovince);
	}
}
