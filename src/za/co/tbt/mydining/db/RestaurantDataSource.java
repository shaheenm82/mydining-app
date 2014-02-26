package za.co.tbt.mydining.db;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class RestaurantDataSource {
	public static final String TABLE_NAME = "restaurant";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_CUISINES = "cuisines";
	private String allColumns[] = {COLUMN_ID, COLUMN_NAME, COLUMN_CUISINES}; 
	
	SQLiteDatabase db;
	MyDiningDbOpenHelper dbHelper;
	
	public RestaurantDataSource(Context context) {
		// TODO Auto-generated constructor stub
		dbHelper = new MyDiningDbOpenHelper(context);
	}
	
	public void open() throws SQLException {
		dbHelper.openDataBase();
	    db = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}
	
	public List<DBItem> getAllRestaurants() {
	    List<DBItem> restaurants = new ArrayList<DBItem>();

	    Cursor cursor = db.query(TABLE_NAME,
	        allColumns, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Restaurant rest = getRestaurant(cursor);
	      restaurants.add(rest);
	      cursor.moveToNext();
	    }
	    // make sure to close the cursor
	    cursor.close();
	    return restaurants;
	}
	
	public List<DBItem> searchForRestaurants(String selection, String[] args) {
	    List<DBItem> restaurants = new ArrayList<DBItem>();

	    Cursor cursor = db.query(TABLE_NAME,
	        allColumns, selection, args, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Restaurant rest = getRestaurant(cursor);
	      restaurants.add(rest);
	      cursor.moveToNext();
	    }
	    // make sure to close the cursor
	    cursor.close();
	    return restaurants;
	}

	private Restaurant getRestaurant(Cursor cursor) {
		Restaurant restaurant = new Restaurant();
		restaurant.setId(cursor.getLong(0));
		restaurant.setName(cursor.getString(1));
		restaurant.setCuisines(cursor.getString(2));
	    return restaurant;
	}
}
