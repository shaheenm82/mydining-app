package za.co.tbt.mydining.view;

import za.co.tbt.mydining.R;
import za.co.tbt.mydining.db.MenuCategory;
import android.view.View;
import android.widget.TextView;

public class MenuCategoryListViewHolder {
	public TextView menuCategoryText = null;
	
	public MenuCategoryListViewHolder(View row) {
		// TODO Auto-generated constructor stub
		menuCategoryText = (TextView) row.findViewById(R.id.text_menuCategory);
	}

	public void populateFrom(MenuCategory mcat){
		menuCategoryText.setText(mcat.getName());
	}
}
