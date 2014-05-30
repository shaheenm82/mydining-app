package za.co.tbt.mydining.view;

import java.text.DecimalFormat;

import za.co.tbt.mydining.R;
import za.co.tbt.mydining.db.Branch;
import za.co.tbt.mydining.db.Restaurant;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class NearByListViewHolder {
	private Context context;
	public TextView restaurantText = null;
	public ImageView restaurantLogo = null;
	public TextView branchText = null;
	public TextView distanceText = null;
	
	public NearByListViewHolder(Context context, View row) {
		this.context = context;
		restaurantText = (TextView) row.findViewById(R.id.text_restaurant);
		restaurantLogo = (ImageView) row.findViewById(R.id.image_rest_logo);
		branchText = (TextView) row.findViewById(R.id.text_branch);
		distanceText = (TextView) row.findViewById(R.id.text_distance);
	}

	public void populateFrom(Restaurant restaurant){
		int branch_count = 0;
		String logo = restaurant.getLogo();
		String logo_id = "";
		DecimalFormat df = new DecimalFormat("#0.0 km");
		Branch branch;
		
		restaurantText.setText(restaurant.getName());
		if (logo != null){
			logo_id = logo.substring(0,logo.length()-4);
			
			restaurantLogo.setImageResource(context.getResources().getIdentifier(logo_id, "drawable", context.getPackageName()));
		}
		branch_count = restaurant.getRestaurant_branches().size();
		
		if (branch_count > 0 && branch_count <= 1){
			branch = restaurant.getRestaurant_branches().get(0);
			
			branchText.setText(branch.getName());
			distanceText.setText(df.format(branch.getDistance()/1000));
		}		
	}
}
