package za.co.tbt.mydining.db;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.preference.PreferenceManager;

public class NearbyRestaurantDataSource {
	private Context context;
	
	public static final String REST_DISTANCE_KEY = "nearby_rest_pref";
	
	private String locationBranchColumns[] = {RestaurantDataSource.BRANCH_COLUMN_ID, 
			RestaurantDataSource.BRANCH_COLUMN_LATITUDE, 
			RestaurantDataSource.BRANCH_COLUMN_LONGITUDE};
	
	private String nearBranchSelection = "distance <= ?";	
	private String orderByNearColumn = RestaurantDataSource.BRANCH_COLUMN_DISTANCE;
	
	SQLiteDatabase db;
	MyDiningDbOpenHelper dbHelper;
	
	public NearbyRestaurantDataSource(Context context) {
		this.context = context;
		dbHelper = new MyDiningDbOpenHelper(context);
	}
	
	public void open() throws SQLException {
		db = dbHelper.getOpenDatabase();
	}

	public void close() {
	}
	
	public void updateNearbyDistances(Location location){
		long branch_id;
		Location branch_location;
		
		float distance;
		
		Cursor cursor = db.query(RestaurantDataSource.BRANCH_TABLE_NAME, locationBranchColumns, null, null, null, null, null);

		branch_location = new Location(location);
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			branch_id = cursor.getLong(0);
			branch_location.setLatitude(cursor.getDouble(1));
			branch_location.setLongitude(cursor.getDouble(2));
			
			distance = location.distanceTo(branch_location);
			
			db.execSQL("UPDATE " + RestaurantDataSource.BRANCH_TABLE_NAME + " "
					+ " SET " + RestaurantDataSource.BRANCH_COLUMN_DISTANCE + " = " + distance 
					+ " WHERE " + RestaurantDataSource.BRANCH_COLUMN_ID + " = " + branch_id);
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
	}
	
	public List<Restaurant> findNearbyRestaurants(){
		List<Restaurant> restaurants;
		Restaurant restaurant;
		Branch branch;
		
		RestaurantDataSource restDataSource = new RestaurantDataSource(context);
		restDataSource.open();
		restaurants = new ArrayList<Restaurant>();
		
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
		String[] dist_args = {sharedPref.getString(REST_DISTANCE_KEY, "10000")};
		
		Cursor cursor = db.query(RestaurantDataSource.BRANCH_TABLE_NAME, RestaurantDataSource.allBranchColumns, nearBranchSelection, dist_args, null, null, orderByNearColumn);
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			String[] rest_args = {cursor.getString(1)};
			
			restaurant = restDataSource.searchForRestaurants("_id = ?", rest_args).get(0) ;
			
			branch = new Branch(cursor.getString(2));
			branch.setId(cursor.getLong(0));
			branch.setProvince(cursor.getString(3));
			branch.setSuburb(cursor.getString(4));
			branch.setAddress(cursor.getString(5));
			branch.setTelephone_no(cursor.getString(6));
			branch.setLatitude(cursor.getDouble(7));
			branch.setLongitude(cursor.getDouble(8));
			if (cursor.getInt(9) == 0){
				branch.setHalaal(false);
			}else{
				branch.setHalaal(true);
			}
			
			if (cursor.getInt(10) == 0){
				branch.setKosher(false);
			}else{
				branch.setKosher(true);
			}
			
			branch.setDistance(cursor.getFloat(11));
			
			restaurant.addBranch(branch);
			
			restaurants.add(restaurant);
			
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
		
		return restaurants;
	}
}
