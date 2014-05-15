package za.co.tbt.mydining.view;

import za.co.tbt.mydining.R;
import za.co.tbt.mydining.db.Favourite;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class FavouriteListViewHolder {
	public TextView favRestaurantText = null;
	public TextView favSelectedText = null;
	public TextView favLastText = null;
	
	public FavouriteListViewHolder(View row) {
		// TODO Auto-generated constructor stub
		favRestaurantText = (TextView) row.findViewById(R.id.text_fav_rest);
		favSelectedText = (TextView) row.findViewById(R.id.text_fav_selected);
		favLastText = (TextView) row.findViewById(R.id.text_fav_last);		
	}

	public void populateFrom(Favourite favourite){
		favRestaurantText.setText(favourite.getRestaurant().getName());
		favSelectedText.setText("Selected " + favourite.getSelected() + " times");
		Log.d("ssm","viewing Favourites date " + favourite.getSelected_date());
		
		favLastText.setText("Last selected : " + favourite.getSelected_date());		
	}
}
