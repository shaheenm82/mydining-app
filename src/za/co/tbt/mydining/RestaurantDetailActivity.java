package za.co.tbt.mydining;

import java.util.List;

import za.co.tbt.mydining.adapter.RestaurantDetailsPagerAdapter;
import za.co.tbt.mydining.db.Branch;
import za.co.tbt.mydining.db.Restaurant;
import za.co.tbt.mydining.db.RestaurantDataSource;
import za.co.tbt.mydining.location.LocationService;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.SearchView.OnQueryTextListener;

public class RestaurantDetailActivity extends FragmentActivity implements
		ActionBar.TabListener, RestaurantDataSupplier, OnQueryTextListener
		{

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	RestaurantDetailsPagerAdapter mSectionsPagerAdapter;
	RestaurantDataSource restDataSource;
	Restaurant restaurant;	

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	LocationService locationService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		String logo;
		String logo_id = "";
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurant_detail);

		locationService = LocationService.getInstance(getApplicationContext());
		
		// Get the message from the intent
	    Intent intent = getIntent();
	    String rest_name = intent.getStringExtra(RestaurantFragment.RESTAURANT_NAME);
	    
	    long branch_id = intent.getLongExtra(NearByFragment.RESTAURANT_BRANCH, 0);
	    
	    restDataSource = new RestaurantDataSource(this);
    	restDataSource.open();
    	
    	//Log.d("ssm", "branch_id = " + branch_id);
    	
	    if (branch_id > 0 ){
	    	Log.d("ssm", "rest_name = " + rest_name + " branch_id = " + branch_id);
	    	restaurant = restDataSource.loadRestaurantDetails(rest_name, branch_id);
	    	
	    	Log.d("ssm", "rest name = " + restaurant.getName() + " branch = " + restaurant.getRestaurant_branches().get(0).getName());
	    }else{
	    	restaurant = restDataSource.loadRestaurantDetails(rest_name);
	    }
	    
	    // Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setTitle(rest_name);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		logo = restaurant.getLogo();
		if (logo != null){
			logo_id = logo.substring(0,logo.length()-4);
			actionBar.setIcon(getResources().getIdentifier(logo_id, "drawable", getPackageName()));			
		}
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new RestaurantDetailsPagerAdapter(
				getSupportFragmentManager(), this, restaurant);

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

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
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
		
		//location_client = new LocationClient(this, this, this);		
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub		
		//location_client.connect();
		super.onStart();
		locationService.start();
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
        case R.id.action_show_all:
        	onQueryTextChange("");
			return true;
        case R.id.action_settings:
			openSettings();
			return true;
		case R.id.action_version:
			openCheckForUpdates();
			return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		//location_client.disconnect();
		super.onStop();	
		locationService.stop();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		restDataSource.close();
	}
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.restaurant_detail, menu);
		
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

	//@Override
	public za.co.tbt.mydining.db.Menu requestRestaurantMenu() {
		// TODO Auto-generated method stub
		return restaurant.getRestaurant_menu();
	}

	@Override
	public List<Branch> requestRestaurantBranches() {
		// TODO Auto-generated method stub
		return restaurant.getRestaurant_branches();
	}	

	@Override
	public Restaurant requestRestaurantDetails() {
		// TODO Auto-generated method stub
		return restaurant;
	}

	private void performSearch(Intent intent){
		if (Intent.ACTION_SEARCH.equals(intent.getAction())){
			int i = mViewPager.getCurrentItem();
			int returned = 0;
			
			String query = intent.getStringExtra(SearchManager.QUERY);
						
			switch (i){
			case 0:
				returned = ((MenuFragment)mSectionsPagerAdapter.getItem(i)).filterMenu(query);
				break;
			case 1:
				returned = ((BranchFragment)mSectionsPagerAdapter.getItem(i)).filterBranch(query);
				break;
			default:
				return;
			}
			
			if (returned == 0){
				Toast.makeText(getApplicationContext(), "No records found.", Toast.LENGTH_SHORT).show();
			}
		}			
	}
	
	@Override
	public boolean onQueryTextSubmit(String query) {
		// TODO Auto-generated method stub
		Intent intent = getIntent();
		intent.setAction(Intent.ACTION_SEARCH);
		intent.putExtra(SearchManager.QUERY, query);
		
		performSearch(intent);
		
		return true;
	}

	@Override
	public boolean onQueryTextChange(String newText) {
		// TODO Auto-generated method stub
		//if (newText.length() == 0){
			Intent intent = getIntent();
			intent.setAction(Intent.ACTION_SEARCH);
			intent.putExtra(SearchManager.QUERY, newText);
			
			performSearch(intent);
			
			return true;
		//}else{
		//	return false;
		//}
	}
	
	public void openSettings(){
		Intent intent = new Intent(this, SettingsActivity.class);
    	startActivity(intent);
	}
	
	public void openCheckForUpdates(){
		Intent intent = new Intent(this, SplashScreenActivity.class);
    	startActivity(intent);
	}
}
