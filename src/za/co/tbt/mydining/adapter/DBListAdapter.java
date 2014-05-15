package za.co.tbt.mydining.adapter;

import java.util.ArrayList;
import java.util.List;

import za.co.tbt.mydining.R;
import za.co.tbt.mydining.db.DBItem;
import za.co.tbt.mydining.view.DBListViewHolder;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class DBListAdapter extends ArrayAdapter<DBItem> {
	List<DBItem> items = new ArrayList<DBItem>();	
	Context context;
	
	public DBListAdapter(Context context){
		super(context, R.layout.list_dbitem);
		
		this.context = context;
	}
	
	public List<DBItem> getItems() {
		return items;
	}

	public void setItems(List<DBItem> items) {
		clear();
		this.items = items;
		
		addAll(items);		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		DBListViewHolder listViewHolder;
		
		if (convertView == null){
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.list_dbitem, null);
			
			listViewHolder = new DBListViewHolder(convertView);
			
			convertView.setTag(listViewHolder);
		}
		else
		{
			listViewHolder = (DBListViewHolder) convertView.getTag();
		}
		listViewHolder.populateFrom(items.get(position).toString());
		
		return convertView;
	}
	
}
