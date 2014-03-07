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
	private String orderByMenuCategory = " CASE category "
			+ "WHEN 'Light Meals' THEN 0 "
			+ "WHEN 'Regular Meals' THEN 1 "
			+ "WHEN 'Combo Meals' THEN 2 "
			+ "WHEN 'Family Meals' THEN 3 "
			+ "WHEN 'Specialty Meals' THEN 4 "
			+ "WHEN 'Kids Meals' THEN 5 "
			+ "WHEN 'Salads' THEN 6 "
			+ "WHEN 'Sides' THEN 7 "
			+ "WHEN 'Extras' THEN 8 "
			+ "WHEN 'Drinks' THEN 9 "
			+ "WHEN 'Desserts' THEN 10 "
			+ "END";
	private String orderByMenuColumns = orderByMenuCategory + ", " + MENU_COLUMN_COST;
	
	public static final String BRANCH_TABLE_NAME = "branch";
	public static final String BRANCH_COLUMN_ID = "_id";
	public static final String BRANCH_COLUMN_REST_ID = "rest_id";
	public static final String BRANCH_COLUMN_NAME = "name";
	public static final String BRANCH_COLUMN_PROVINCE = "province";
	public static final String BRANCH_COLUMN_SUBURB = "suburb";
	public static final String BRANCH_COLUMN_ADDRESS = "address";
	public static final String BRANCH_COLUMN_TELEPHONE_NO = "telephone_no";
	public static final String BRANCH_COLUMN_LATITUDE = "location_lat";
	public static final String BRANCH_COLUMN_LONGITUDE = "location_long";
	public static final String BRANCH_COLUMN_HALAAL = "halaal";
	public static final String BRANCH_COLUMN_KOSHER = "kosher";
	private String allBranchColumns[] = {BRANCH_COLUMN_ID, BRANCH_COLUMN_REST_ID, BRANCH_COLUMN_NAME,
			BRANCH_COLUMN_PROVINCE, BRANCH_COLUMN_SUBURB, BRANCH_COLUMN_ADDRESS, BRANCH_COLUMN_TELEPHONE_NO,
			BRANCH_COLUMN_LATITUDE, BRANCH_COLUMN_LONGITUDE, BRANCH_COLUMN_HALAAL, BRANCH_COLUMN_KOSHER}; 
	private String restBranchSelection = "rest_id = ?";
	private String orderByBranchColumns = BRANCH_COLUMN_PROVINCE + ", " + BRANCH_COLUMN_SUBURB;
	
	
	SQLiteDatabase db;
	MyDiningDbOpenHelper dbHelper;
	
	public RestaurantDataSource(Context context) {
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
		restaurant.setRestaurant_branches(getRestaurantBranches(restaurant.getId()));
		
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
				mitem.setVegetarian(true);
			}
			
			if (cursor.getInt(9) == 0){
				mitem.setHealthy(false);
			}else{
				mitem.setHealthy(true);
			}
			
			mcat.addMenuItem(mitem);
			
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
		    
		return menu;
	}
	
	private List<Branch> getRestaurantBranches(long id){
		List<Branch> branches;
		Branch branch = null;
		
		String[] rest_selection = {new Long(id).toString()};
		
		Cursor cursor = db.query(BRANCH_TABLE_NAME,
		        allBranchColumns, restBranchSelection, rest_selection, null, null, orderByBranchColumns);
		branches = new ArrayList<Branch>();
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			
			branch = new Branch(cursor.getString(2));
			branch.setId(cursor.getLong(0));
			branch.setProvince(cursor.getString(3));
			branch.setSuburb(cursor.getString(4));
			branch.setAddress(cursor.getString(5));
			branch.setTelephone_no(cursor.getString(6));
			branch.setLatitude(cursor.getString(7));
			branch.setLongitude(cursor.getString(8));
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
			
			branches.add(branch);
			
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
		    
		return branches;
	}
}
