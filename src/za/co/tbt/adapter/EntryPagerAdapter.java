/**
 * 
 */
package za.co.tbt.adapter;

import java.util.ArrayList;
import java.util.Locale;

import za.co.tbt.mydining.CuisineFragment;
import za.co.tbt.mydining.FavouriteFragment;
import za.co.tbt.mydining.OutletFragment;
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
	 * @param fm
	 */
	public EntryPagerAdapter(FragmentManager fm, Context context) {
		super(fm);		
		// TODO Auto-generated constructor stub
		Bundle args;
		Fragment f; 
		
		fragmentItems = new ArrayList<Fragment>();		
		
		f = new OutletFragment();
		args = new Bundle();
		
		fragmentItems.add(f);
		args.putString(ARG_TITLE, context.getString(R.string.title_outlets));
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
		// TODO Auto-generated method stub
		return fragmentItems.get(arg0);
	}

	/* (non-Javadoc)
	 * @see android.support.v4.view.PagerAdapter#getCount()
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return fragmentItems.size();
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		//Fragment fragment = getItem(position);
		CharSequence title = "Tab"+position;
		
		title = fragmentItems.get(position).getArguments().getString(ARG_TITLE);
		/*if (fragment instanceof OutletFragment){
			title = ((OutletFragment)fragment).getTitle();
		}else if (fragment instanceof CuisineFragment){
			title = ((CuisineFragment)fragment).getTitle();
		}else if (fragment instanceof FavouriteFragment){
			title = ((FavouriteFragment)fragment).getTitle();
		}*/
		return title;
	}

}
