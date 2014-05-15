package za.co.tbt.mydining.adapter;

import java.util.ArrayList;
import java.util.List;

import za.co.tbt.mydining.R;
import za.co.tbt.mydining.db.Restaurant;
import za.co.tbt.mydining.view.NearByListViewHolder;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class NearByListAdapter extends ArrayAdapter<Restaurant> {
	List<Restaurant> items = new ArrayList<Restaurant>();	
	Context context;
	
	public NearByListAdapter(Context context){
		super(context, R.layout.list_nearby);
		
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
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		NearByListViewHolder listViewHolder;
		
		if (convertView == null){
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.list_nearby, null);
			
			listViewHolder = new NearByListViewHolder(context, convertView);
			
			convertView.setTag(listViewHolder);
		}
		else
		{
			listViewHolder = (NearByListViewHolder) convertView.getTag();
		}
		listViewHolder.populateFrom(items.get(position));
		
		return convertView;
	}
	
}
