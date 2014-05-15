package za.co.tbt.mydining.view;

import za.co.tbt.mydining.R;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DBListViewHolder {
	private TextView itemText;
	private ImageView logo;
	
	public DBListViewHolder(View row) {
		// TODO Auto-generated constructor stub
		itemText = (TextView) row.findViewById(R.id.text_item);
		logo = (ImageView) row.findViewById(R.id.logo);
	}

	public void populateFrom(String r){
		itemText.setText(r);
		 
		logo.setImageResource(R.drawable.ic_cuisine);		
	}
}
