package za.co.tbt.mydining.view;

import za.co.tbt.mydining.R;
import za.co.tbt.mydining.db.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuItemListViewHolder {
	public TextView menuDishText = null;
	public TextView menuDescriptionText = null;
	public TextView menuAdditionalText = null;
	public TextView menuSpecialText = null;
	public ImageView menuVegetarianImage = null;
	public ImageView menuHealthyImage = null;
	public TextView menuCostText = null;
	
	public MenuItemListViewHolder(View row) {
		// TODO Auto-generated constructor stub
		menuDishText = (TextView) row.findViewById(R.id.text_dish);
		menuDescriptionText = (TextView) row.findViewById(R.id.text_description);
		menuAdditionalText = (TextView) row.findViewById(R.id.text_additional);
		menuSpecialText = (TextView) row.findViewById(R.id.text_special);
		menuVegetarianImage = (ImageView) row.findViewById(R.id.image_vegetarian);
		menuHealthyImage = (ImageView) row.findViewById(R.id.image_healthy);
		menuCostText = (TextView) row.findViewById(R.id.text_cost);
	}

	public void populateFrom(MenuItem mitem){
		menuDishText.setText(mitem.getDish());
		menuDescriptionText.setText(mitem.getDescription());
		menuAdditionalText.setText(mitem.getAdditional());
		menuCostText.setText(String.format("R %.2f", mitem.getCost()));
		if (mitem.isSpecial()){
			menuSpecialText.setText("S");
		}else{
			menuSpecialText.setText("");
		}
		
		if (mitem.isVegetarian()){
			menuVegetarianImage.setImageResource(R.drawable.ic_vegetarian);
		}else{
			menuVegetarianImage.setImageResource(android.R.color.transparent);
		}
		
		if (mitem.isHealthy()){
			menuHealthyImage.setImageResource(R.drawable.ic_healthy);
		}else{
			menuHealthyImage.setImageResource(android.R.color.transparent);
		}
	}
}
