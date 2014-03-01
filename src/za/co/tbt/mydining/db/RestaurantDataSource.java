package za.co.tbt.mydining.db;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class RestaurantDataSource {
	public static final String REST_TABLE_NAME = "restaurant";
	public static final String REST_COLUMN_ID = "_id";
	public static final String REST_COLUMN_NAME = "name";
	public static final String REST_COLUMN_CUISINES = "cuisines";
	private String allRestColumns[] = {REST_COLUMN_ID, REST_COLUMN_NAME, REST_COLUMN_CUISINES}; 
	
	public static final String MENU_TABLE_NAME = "menu";
	public static final String MENU_COLUMN_ID = "_id";
	public static final String MENU_COLUMN_REST_ID = "rest_id";
	public static final String MENU_COLUMN_CATEGORY = "category";
	public static final String MENU_COLUMN_DISH = "dish";
	public static final String MENU_COLUMN_DESCRIPTION = "description";
	public static final String MENU_COLUMN_ADDITIONAL = "additional";
	public static final String MENU_COLUMN_COST = "cost";
	public static final String MENU_COLUMN_SPECIAL = "special";
	public static final String MENU_COLUMN_VEG = "vegetarian";
	public static final String MENU_COLUMN_HEALTH = "healthy";
	private String allMenuColumns[] = {MENU_COLUMN_ID, MENU_COLUMN_REST_ID, MENU_COLUMN_CATEGORY,
			MENU_COLUMN_DISH, MENU_COLUMN_DESCRIPTION, MENU_COLUMN_ADDITIONAL, MENU_COLUMN_COST,
			MENU_COLUMN_SPECIAL, MENU_COLUMN_VEG, MENU_COLUMN_HEALTH}; 
	private String restMenuSelection = "rest_id = ?";
	private String orderByMenuColumns = MENU_COLUMN_CATEGORY + ", " + MENU_COLUMN_COST;
	
	
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

	    Cursor cursor = db.query(REST_TABLE_NAME,
	        allRestColumns, null, null, null, null, null);

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

	    Cursor cursor = db.query(REST_TABLE_NAME,
	        allRestColumns, selection, args, null, null, null);

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
	
	public Restaurant loadRestaurantDetails(String restaurant_name){
		List<DBItem> restaurants;
		Restaurant restaurant;
		
		String[] args = {restaurant_name};
		
		restaurants = searchForRestaurants("name = ?", args);
		
		if (restaurants.size() == 0){
			return null;
		}
		
		restaurant = (Restaurant)restaurants.get(0);
		
		restaurant.setRestaurant_menu(getRestaurantMenu(restaurant.getId()));
		
		return restaurant;
	}
	
	private Restaurant getRestaurant(Cursor cursor) {
		//default constructor taking in the restaurant name
		Restaurant restaurant = new Restaurant(cursor.getString(1));
		
		restaurant.setId(cursor.getLong(0));
		//restaurant.setName(cursor.getString(1));
		restaurant.setCuisines(cursor.getString(2));
	    return restaurant;
	}
	
	private Menu getRestaurantMenu(long id){
		Menu menu = null;
		MenuCategory mcat = null;
		MenuItem mitem = null;
		
		String category = "";
		String[] rest_selection = {new Long(id).toString()};
		
		Cursor cursor = db.query(MENU_TABLE_NAME,
		        allMenuColumns, restMenuSelection, rest_selection, null, null, orderByMenuColumns);
		menu = new Menu();
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			if (!category.equals(cursor.getString(2))){
				category = cursor.getString(2);
				mcat = new MenuCategory(category);
				
				menu.addMenuCategory(mcat);							
			}
			
			mitem = new MenuItem();
			mitem.setId(cursor.getLong(0));
			mitem.setDish(cursor.getString(3));
			mitem.setDescription(cursor.getString(4));
			mitem.setAdditional(cursor.getString(5));
			mitem.setCost(cursor.getFloat(6));
			if (cursor.getInt(7) == 0){
				mitem.setSpecial(false);
			}else{
				mitem.setSpecial(true);
			}
			
			if (cursor.getInt(8) == 0){
				mitem.setVegetarian(false);
			}else{
				mitem.setSpecial(true);
			}
			
			if (cursor.getInt(9) == 0){
				mitem.setSpecial(false);
			}else{
				mitem.setSpecial(true);
			}
			
			mcat.addMenuItem(mitem);
			
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
		    
		return menu;
	}
}
