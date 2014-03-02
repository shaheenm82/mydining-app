package za.co.tbt.mydining.view;

import za.co.tbt.mydining.R;
import za.co.tbt.mydining.db.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MenuItemListViewHolder {
	public TextView menuDishText = null;
	public TextView menuDescriptionText = null;
	public TextView menuAdditionalText = null;
	public TextView menuSpecialText = null;
	public TextView menuVegetarianText = null;
	public TextView menuHealthyText = null;
	public TextView menuCostText = null;
	
	public MenuItemListViewHolder(View row) {
		// TODO Auto-generated constructor stub
		menuDishText = (TextView) row.findViewById(R.id.text_dish);
		menuDescriptionText = (TextView) row.findViewById(R.id.text_description);
		menuAdditionalText = (TextView) row.findViewById(R.id.text_additional);
		menuSpecialText = (TextView) row.findViewById(R.id.text_special);
		menuVegetarianText = (TextView) row.findViewById(R.id.text_vegetarian);
		menuHealthyText = (TextView) row.findViewById(R.id.text_healthy);
		menuCostText = (TextView) row.findViewById(R.id.text_cost);
	}

	public void populateFrom(MenuItem mitem){
		menuDishText.setText(mitem.getDish());
		menuDescriptionText.setText(mitem.getDescription());
		menuAdditionalText.setText(mitem.getAdditional());
		menuCostText.setText(String.format("%.2f", mitem.getCost()));
		if (mitem.isSpecial()){
			menuSpecialText.setText("S");
		}else{
			menuSpecialText.setText("");
		}
		
		if (mitem.isVegetarian()){
			menuVegetarianText.setText("V");
		}else{
			menuVegetarianText.setText("");
		}
		
		if (mitem.isHealthy()){
			menuHealthyText.setText("H");
		}else{
			menuHealthyText.setText("");
		}
	}
}
