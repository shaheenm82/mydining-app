package za.co.tbt.mydining.adapter;

import java.util.ArrayList;

import za.co.tbt.mydining.BranchFragment;
import za.co.tbt.mydining.BranchMapFragment;
import za.co.tbt.mydining.MenuFragment;
import za.co.tbt.mydining.R;
import za.co.tbt.mydining.db.Restaurant;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class RestaurantDetailsPagerAdapter extends FragmentPagerAdapter {
	public static final String ARG_TITLE = "title";
	ArrayList<Fragment> fragmentItems;
	
	public RestaurantDetailsPagerAdapter(FragmentManager fragment, Context context, Restaurant restaurant) {
		super(fragment);
		// TODO Auto-generated constructor stub
		Bundle args;
		Fragment f; 
		
		fragmentItems = new ArrayList<Fragment>();		
		
		f = new MenuFragment();
		//((MenuFragment)f).setRestaurantMenu(restaurant.getRestaurant_menu());
		args = new Bundle();
		fragmentItems.add(f);
		args.putString(ARG_TITLE, context.getString(R.string.title_menus));
		f.setArguments(args);
		
		f = new BranchFragment();
		//((BranchFragment)f).setRestaurantBranches(restaurant.getRestaurant_branches());
		args = new Bundle();
		fragmentItems.add(f);
		args.putString(ARG_TITLE, context.getString(R.string.title_branches));
		f.setArguments(args);
		
		f = new BranchMapFragment();
		args = new Bundle();
		fragmentItems.add(f);
		args.putString(ARG_TITLE, context.getString(R.string.title_map));
		f.setArguments(args);
	}

	@Override
	public Fragment getItem(int index) {
		// TODO Auto-generated method stub		
		return fragmentItems.get(index);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return fragmentItems.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		CharSequence title = fragmentItems.get(position).getArguments().getString(ARG_TITLE);
		
		return title;
	}
}
