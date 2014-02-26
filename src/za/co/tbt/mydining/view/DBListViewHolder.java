package za.co.tbt.mydining.view;

import za.co.tbt.mydining.R;
import android.view.View;
import android.widget.TextView;

public class DBListViewHolder {
	public TextView itemText = null;
	
	public DBListViewHolder(View row) {
		// TODO Auto-generated constructor stub
		itemText = (TextView) row.findViewById(R.id.itemText);
	}

	public void populateFrom(String r){
		itemText.setText(r);
	}
}
