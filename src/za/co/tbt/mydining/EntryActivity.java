package za.co.tbt.mydining;

import java.util.List;

import za.co.tbt.mydining.adapter.EntryPagerAdapter;
import za.co.tbt.mydining.db.DBItem;
import za.co.tbt.mydining.db.MyDiningDbOpenHelper;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

public class EntryActivity extends FragmentActivity implements
		ActionBar.TabListener, OnQueryTextListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	//SectionsPagerAdapter mSectionsPagerAdapter;
	EntryPagerAdapter mEntryPagerAdapter;
	
	MyDiningDbOpenHelper diningHelper;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_entry);

		//create database helper
		diningHelper = new MyDiningDbOpenHelper(this);
		diningHelper.createDatabase();		
				
		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mEntryPagerAdapter = new EntryPagerAdapter(getSupportFragmentManager(), getApplicationContext());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mEntryPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mEntryPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mEntryPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
		
		performSearch(getIntent());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.entry, menu);
		
		 // Associate searchable configuration with the SearchView
	    SearchManager searchManager =
	           (SearchManager) getSystemService(Context.SEARCH_SERVICE);
	    SearchView searchView =
	            (SearchView) menu.findItem(R.id.action_search).getActionView();
	    searchView.setSearchableInfo(
	            searchManager.getSearchableInfo(getComponentName()));
	    searchView.setOnQueryTextListener(this);
	    
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()){
			case R.id.action_settings:
				openSettings();
				return true;
			default:
				return super.onOptionsItemSelected(item);		
		}		
	}
	
	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		
		performSearch(intent);
	}
	
	public void openSettings(){
		Intent intent = new Intent(this, SettingsActivity.class);
    	startActivity(intent);
	}
	
	private void performSearch(Intent intent){
		if (Intent.ACTION_SEARCH.equals(intent.getAction())){
			//List<DBItem> items;
			int i = mViewPager.getCurrentItem();
			String query = intent.getStringExtra(SearchManager.QUERY);
						
			switch (i){
			case 0:
				((RestaurantFragment)mEntryPagerAdapter.getItem(i)).filter(query);
				break;
			case 1:
				((CuisineFragment)mEntryPagerAdapter.getItem(i)).filter(query);
				break;
			default:
				return;
			}
		}
	}

	@Override
	public boolean onQueryTextChange(String newText) {
		// TODO Auto-generated method stub
		boolean retVal = true;
		if (newText.length() == 0){
			int i = mViewPager.getCurrentItem();
						
			switch (i){
			case 0:
				((RestaurantFragment)mEntryPagerAdapter.getItem(i)).filter("");
				break;
			case 1:
				((CuisineFragment)mEntryPagerAdapter.getItem(i)).filter("");
				break;				
			default:
				retVal = false;
			}
		}
		
		return retVal;
	}

	@Override
	public boolean onQueryTextSubmit(String query) {
		// TODO Auto-generated method stub
		return false;
	}
}
