/**
 * 
 */
package za.co.tbt.mydining.adapter;

import java.util.ArrayList;
import za.co.tbt.mydining.CuisineFragment;
import za.co.tbt.mydining.FavouriteFragment;
import za.co.tbt.mydining.NearByFragment;
import za.co.tbt.mydining.RestaurantFragment;
import za.co.tbt.mydining.R;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * @author shaheen
 *
 */
public class EntryPagerAdapter extends FragmentPagerAdapter {
	public static final String ARG_TITLE = "title";
	ArrayList<Fragment> fragmentItems;
	
	
	/**
	 * @param fragment
	 */
	public EntryPagerAdapter(FragmentManager fragment, Context context) {
		super(fragment);		
		Bundle args;
		Fragment f; 
		
		fragmentItems = new ArrayList<Fragment>();		
		
		f = new RestaurantFragment();
		args = new Bundle();
		
		fragmentItems.add(f);
		args.putString(ARG_TITLE, context.getString(R.string.title_outlets));
		f.setArguments(args);
		
		f = new NearByFragment();
		args = new Bundle();
		fragmentItems.add(f);
		args.putString(ARG_TITLE, context.getString(R.string.title_nearby));
		f.setArguments(args);
		
		f = new CuisineFragment();
		args = new Bundle();
		fragmentItems.add(f);
		args.putString(ARG_TITLE, context.getString(R.string.title_cuisines));
		f.setArguments(args);
		
		f = new FavouriteFragment();
		args = new Bundle();
		fragmentItems.add(f);
		args.putString(ARG_TITLE, context.getString(R.string.title_favourites));
		f.setArguments(args);
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.FragmentPagerAdapter#getItem(int)
	 */
	@Override
	public Fragment getItem(int arg0) {
		return fragmentItems.get(arg0);
	}

	/* (non-Javadoc)
	 * @see android.support.v4.view.PagerAdapter#getCount()
	 */
	@Override
	public int getCount() {
		return fragmentItems.size();
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		CharSequence title = "Tab"+position;
		
		title = fragmentItems.get(position).getArguments().getString(ARG_TITLE);
		return title;
	}

}
