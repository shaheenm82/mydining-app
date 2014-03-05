package za.co.tbt.mydining;

import java.util.List;

import za.co.tbt.mydining.adapter.RestaurantDetailsPagerAdapter;
import za.co.tbt.mydining.db.Branch;
import za.co.tbt.mydining.db.Restaurant;
import za.co.tbt.mydining.db.RestaurantDataSource;
import za.co.tbt.mydining.location.LocationProvider;
import za.co.tbt.mydining.location.LocationUpdateListener;
import za.co.tbt.mydining.location.LocationUtils;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;

public class RestaurantDetailActivity extends FragmentActivity implements
		ActionBar.TabListener, GooglePlayServicesClient.ConnectionCallbacks,
        GooglePlayServicesClient.OnConnectionFailedListener, RestaurantDataSupplier, LocationProvider {

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

	LocationUpdateListener locUpdateListener;
	LocationClient location_client;
	Location location;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurant_detail);

		// Get the message from the intent
	    Intent intent = getIntent();
	    String rest_name = intent.getStringExtra(RestaurantFragment.RESTAURANT_NAME);
	    restDataSource = new RestaurantDataSource(this);
	    restDataSource.open();
	    restaurant = restDataSource.loadRestaurantDetails(rest_name);

	    // Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setTitle(rest_name);
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
		
		location_client = new LocationClient(this, this, this);		
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub		
		location_client.connect();
		super.onStart();
	}
	
	/*
     * Handle results returned to this Activity by other Activities started with
     * startActivityForResult(). In particular, the method onConnectionFailed() in
     * LocationUpdateRemover and LocationUpdateRequester may call startResolutionForResult() to
     * start an Activity that handles Google Play services problems. The result of this
     * call returns here, to onActivityResult.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        // Choose what to do based on the request code
        switch (requestCode) {
            // If the request code matches the code sent in onConnectionFailed
            case LocationUtils.CONNECTION_FAILURE_RESOLUTION_REQUEST :
                switch (resultCode) {
                    // If Google Play services resolved the problem
                    case Activity.RESULT_OK:                                          
                    break;
                    // If any other result was returned by Google Play services
                    default:     
                    	showErrorDialog(resultCode);
                    break;
                }
            // If any other request code was received
            default:               
            	showErrorDialog(resultCode);
               break;
        }
    }
    
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		location_client.disconnect();
		super.onStop();		
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		restDataSource.close();
	}

    /**
     * Verify that Google Play services is available before making a request.
     *
     * @return true if Google Play services is available, otherwise false
     */
    private boolean servicesConnected() {

        // Check that Google Play services is available
        int resultCode =
                GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

        // If Google Play services is available
        if (ConnectionResult.SUCCESS == resultCode) {
            // Continue
        	showErrorDialog(resultCode);
            return true;
        // Google Play services was not available for some reason
        } else {
            // Display an error dialog
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(resultCode, this, 0);
            if (dialog != null) {
                ErrorDialogFragment errorFragment = new ErrorDialogFragment();
                errorFragment.setDialog(dialog);
                errorFragment.show(getFragmentManager(), "");
            }
            return false;
        }
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.restaurant_detail, menu);
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

    /*
     * Called by Location Services if the attempt to
     * Location Services fails.
     */
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    	Toast.makeText(getApplicationContext(), "Map onConnectionFailed", Toast.LENGTH_SHORT).show();
        /*
         * Google Play services can resolve some errors it detects.
         * If the error has a resolution, try sending an Intent to
         * start a Google Play services activity that can resolve
         * error.
         */
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(
                        this,
                        LocationUtils.CONNECTION_FAILURE_RESOLUTION_REQUEST);
                /*
                * Thrown if Google Play services canceled the original
                * PendingIntent
                */
            } catch (IntentSender.SendIntentException e) {
                // Log the error
                e.printStackTrace();
            }
        } else {
            // If no resolution is available, display a dialog to the user with the error.
            showErrorDialog(connectionResult.getErrorCode());
        }
    }

	@Override
	public void onConnected(Bundle connectionHint) {
		// TODO Auto-generated method stub
		if (servicesConnected()){
			location = location_client.getLastLocation();

			if ( locUpdateListener != null ){
				locUpdateListener.locationUpdated(location);							
			}
		}
	}

	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub		
	}
	
	/**
     * Show a dialog returned by Google Play services for the
     * connection error code
     *
     * @param errorCode An error code returned from onConnectionFailed
     */
    private void showErrorDialog(int errorCode) {

        // Get the error dialog from Google Play services
        Dialog errorDialog = GooglePlayServicesUtil.getErrorDialog(
            errorCode,
            this,
            LocationUtils.CONNECTION_FAILURE_RESOLUTION_REQUEST);

        // If Google Play services can provide an error dialog
        if (errorDialog != null) {

            // Create a new DialogFragment in which to show the error dialog
            ErrorDialogFragment errorFragment = new ErrorDialogFragment();

            // Set the dialog in the DialogFragment
            errorFragment.setDialog(errorDialog);

            // Show the error dialog in the DialogFragment
            errorFragment.show(getFragmentManager(), "");
        }
    }
    
    /**
     * Define a DialogFragment to display the error dialog generated in
     * showErrorDialog.
     */
    public static class ErrorDialogFragment extends DialogFragment {

        // Global field to contain the error dialog
        private Dialog mDialog;

        /**
         * Default constructor. Sets the dialog field to null
         */
        public ErrorDialogFragment() {
            super();
            mDialog = null;
        }

        /**
         * Set the dialog to display
         *
         * @param dialog An error dialog
         */
        public void setDialog(Dialog dialog) {
            mDialog = dialog;
        }

        /*
         * This method must return a Dialog to the DialogFragment.
         */
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return mDialog;
        }
    }

	@Override
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
	public void setLocationUpdateListener(LocationUpdateListener lulistener) {
		// TODO Auto-generated method stub
		locUpdateListener = lulistener;
		
		if(location != null){
			locUpdateListener.locationUpdated(location);
		}
	}
}
