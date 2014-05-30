package za.co.tbt.mydining.view;

import za.co.tbt.mydining.R;
import za.co.tbt.mydining.db.Restaurant;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class RestaurantListViewHolder {
	private Context context;
	public TextView restaurantText = null;
	public TextView sloganText = null;
	public ImageView restaurantLogo = null;
	
	public RestaurantListViewHolder(Context context, View row) {
		this.context = context;
		restaurantText = (TextView) row.findViewById(R.id.text_item);
		sloganText = (TextView) row.findViewById(R.id.text_slogan);
		restaurantLogo = (ImageView) row.findViewById(R.id.logo);
	}

	public void populateFrom(Restaurant restaurant){
		String logo = restaurant.getLogo();
		String logo_id = "";
		
		restaurantText.setText(restaurant.getName());
		sloganText.setText(restaurant.getSlogan());
		if (logo != null){
			logo_id = logo.substring(0,logo.length()-4);
			
			restaurantLogo.setImageResource(context.getResources().getIdentifier(logo_id, "drawable", context.getPackageName()));
		}
	}
}
