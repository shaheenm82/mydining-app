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
	public ImageView restaurantLogo = null;
	
	public RestaurantListViewHolder(Context context, View row) {
		// TODO Auto-generated constructor stub
		this.context = context;
		restaurantText = (TextView) row.findViewById(R.id.text_item);
		restaurantLogo = (ImageView) row.findViewById(R.id.logo);
	}

	public void populateFrom(Restaurant restaurant){
		String logo = restaurant.getLogo();
		String logo_id = "";
		
		restaurantText.setText(restaurant.getName());
		if (logo != null){
			logo_id = logo.substring(0,logo.length()-4);
			
			restaurantLogo.setImageResource(context.getResources().getIdentifier(logo_id, "drawable", context.getPackageName()));
		}
	}
}
