package za.co.tbt.mydining.adapter;

import java.util.ArrayList;
import java.util.List;

import za.co.tbt.mydining.R;
import za.co.tbt.mydining.db.Favourite;
import za.co.tbt.mydining.view.FavouriteListViewHolder;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class FavouriteListAdapter extends ArrayAdapter<Favourite> {
	List<Favourite> favourites = new ArrayList<Favourite>();	
	Context context;
	
	public FavouriteListAdapter(Context context){
		super(context, R.layout.list_favourite);
		
		this.context = context;
	}
	
	public List<Favourite> getItems() {
		return favourites;
	}

	public void setItems(List<Favourite> favourites) {
		clear();
		this.favourites = favourites;
		
		addAll(favourites);		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		FavouriteListViewHolder listViewHolder;
		
		if (convertView == null){
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.list_favourite, null);
			
			listViewHolder = new FavouriteListViewHolder(convertView);
			
			convertView.setTag(listViewHolder);
		}
		else
		{
			listViewHolder = (FavouriteListViewHolder) convertView.getTag();
		}
		listViewHolder.populateFrom(favourites.get(position));
		
		return convertView;
	}
	
}
