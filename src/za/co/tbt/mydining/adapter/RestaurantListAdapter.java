package za.co.tbt.mydining.adapter;

import java.util.ArrayList;
import java.util.List;

import za.co.tbt.mydining.R;
import za.co.tbt.mydining.db.DBItem;
import za.co.tbt.mydining.db.Restaurant;
import za.co.tbt.mydining.view.RestaurantListViewHolder;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class RestaurantListAdapter extends ArrayAdapter<Restaurant> {
	List<Restaurant> items = new ArrayList<Restaurant>();	
	Context context;
	
	public RestaurantListAdapter(Context context){
		super(context, R.layout.list_dbitem);
		
		this.context = context;
	}
	
	public List<Restaurant> getItems() {
		return items;
	}

	public void setItems(List<Restaurant> items) {
		clear();
		this.items = items;
		
		addAll(items);		
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		RestaurantListViewHolder listViewHolder;
		
		if (convertView == null){
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.list_dbitem, null);
			
			listViewHolder = new RestaurantListViewHolder(context, convertView);
			
			convertView.setTag(listViewHolder);
		}
		else
		{
			listViewHolder = (RestaurantListViewHolder) convertView.getTag();
		}
		listViewHolder.populateFrom(items.get(position));
		
		return convertView;
	}
	
}
