package za.co.tbt.mydining.db;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateFormat;
import android.util.Log;

public class FavouriteDataSource {
	public static final String TABLE_NAME = "favourites";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_REST = "rest_id";
	public static final String COLUMN_SELECTED = "selected";
	public static final String COLUMN_SELECTED_DATE = "selected_date";
	private String allColumns[] = {COLUMN_ID, COLUMN_REST, COLUMN_SELECTED, COLUMN_SELECTED_DATE};
	private String orderByColumns = COLUMN_SELECTED_DATE + " DESC, " + COLUMN_SELECTED + " DESC";
	
	SQLiteDatabase db;
	MyDiningDbOpenHelper dbHelper;
	Context context;
	
	public FavouriteDataSource(Context context) {
		// TODO Auto-generated constructor stub
		dbHelper = new MyDiningDbOpenHelper(context);
	}
	
	public void open() throws SQLException {
		//dbHelper.openDataBase();
	    db = dbHelper.getOpenDatabase();
	}

	public void close() {
		//dbHelper.close();
	}
	
	public List<Favourite> getAllFavourites() {
	    List<Favourite> favourites = new ArrayList<Favourite>();

	    Cursor cursor = db.query(TABLE_NAME,
	        allColumns, null, null, null, null, orderByColumns);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	    	Favourite favourite = getFavourite(cursor);
	    	favourites.add(favourite);
	    	cursor.moveToNext();
	    }
	    // make sure to close the cursor
	    cursor.close();
	    return favourites;
	}

	public List<Favourite> searchForFavourites(String selection, String[] args) {
	    List<Favourite> favourites = new ArrayList<Favourite>();

	    Cursor cursor = db.query(TABLE_NAME,
	        allColumns, selection, args, null, null, orderByColumns);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Favourite rest = getFavourite(cursor);
	      favourites.add(rest);
	      cursor.moveToNext();
	    }
	    // make sure to close the cursor
	    cursor.close();
	    return favourites;
	}
	
	private Favourite getFavourite(Cursor cursor) {
		RestaurantDataSource restDataSource;
		List<DBItem> restaurants;
		Favourite favourite = new Favourite();
		
		restDataSource = new RestaurantDataSource(context);
		restDataSource.open();
		String[] args = {cursor.getString(1)};
		
		restaurants = restDataSource.searchForRestaurants("_id = ?", args);
		
		favourite.setId(cursor.getLong(0));		
		if (restaurants.size() == 0){
			favourite.setRestaurant(null);			
		}else{
			favourite.setRestaurant((Restaurant)restaurants.get(0));			
		}
		favourite.setSelected(cursor.getInt(2));
		favourite.setSelected_date(cursor.getString(3));
		
		restDataSource.close();
		
	    return favourite;
	}
	
	public void addFavourite( Restaurant restaurant){
		List<Favourite> favourites;
		Favourite favourite;
		String[] args = {Long.valueOf(restaurant.getId()).toString()};
		
		favourites = searchForFavourites("rest_id = ?", args);
				
		//Log.d("ssm","Favourites date " + DateFormat.format("yyyy-MM-dd", Calendar.getInstance()));
		
		if (favourites.size() == 0){
			//Favourite does not exist
			db.execSQL("INSERT INTO " + TABLE_NAME + " ( " + COLUMN_REST + ", " + COLUMN_SELECTED + ", " + COLUMN_SELECTED_DATE + " )"
					+ " VALUES ( " + restaurant.getId() + ", 1, '" + DateFormat.format("yyyy-MM-dd", Calendar.getInstance()) + "')");
		}else{
			//favourite exists
			favourite = favourites.get(0);
			
			db.execSQL("UPDATE " + TABLE_NAME + " "
					+ " SET " + COLUMN_SELECTED + " = " + (favourite.getSelected() + 1) + ", "
					+ COLUMN_SELECTED_DATE + " = '" + DateFormat.format("yyyy-MM-dd", Calendar.getInstance()) + "'"
					+ " WHERE " + COLUMN_ID + " = " + favourite.getId());		
		}		
	}
}
