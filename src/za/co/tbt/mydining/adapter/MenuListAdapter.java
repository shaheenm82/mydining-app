package za.co.tbt.mydining.adapter;

import za.co.tbt.mydining.R;
import za.co.tbt.mydining.db.Menu;
import za.co.tbt.mydining.db.MenuCategory;
import za.co.tbt.mydining.db.MenuItem;
import za.co.tbt.mydining.view.MenuCategoryListViewHolder;
import za.co.tbt.mydining.view.MenuItemListViewHolder;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

public class MenuListAdapter extends BaseExpandableListAdapter{
	Context context;
	Menu menu;
	
	public MenuListAdapter(Context context, Menu menu){
		//super(context, R.layout.list_dbitem);		
		this.context = context;
		this.menu = menu;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return ((MenuCategory)menu.getCategories().get(groupPosition)).getDishes().get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return (groupPosition * 1000) + childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub				
		MenuItemListViewHolder listviewHolder;
		MenuItem mitem = (MenuItem)getChild(groupPosition, childPosition);
		
		if (convertView == null){
			LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.list_menuitem, null);
			
			listviewHolder = new MenuItemListViewHolder(convertView);
			convertView.setTag(listviewHolder);
		}else{
			listviewHolder = (MenuItemListViewHolder) convertView.getTag();
		}
		
		listviewHolder.populateFrom(mitem);				
		
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return ((MenuCategory)menu.getCategories().get(groupPosition)).getDishes().size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return menu.getCategories().get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return menu.getCategories().size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return (groupPosition * 1000);
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		MenuCategoryListViewHolder listviewHolder;
		MenuCategory mcat = (MenuCategory) getGroup(groupPosition);
		
		if (convertView == null){
			LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.group_menucategory, null);
			
			listviewHolder = new MenuCategoryListViewHolder(convertView);
			convertView.setTag(listviewHolder);
		}else{
			listviewHolder = (MenuCategoryListViewHolder) convertView.getTag();
		}
		
		listviewHolder.populateFrom(mcat);
		
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
