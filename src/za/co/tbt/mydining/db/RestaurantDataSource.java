package za.co.tbt.mydining.db;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.util.Log;

public class RestaurantDataSource {
	public static final String REST_TABLE_NAME = "restaurant";
	public static final String REST_COLUMN_ID = "_id";
	public static final String REST_COLUMN_NAME = "name";
	public static final String REST_COLUMN_CUISINES = "cuisines";
	public static final String REST_COLUMN_LOGOS = "logo";
	private String allRestColumns[] = {REST_COLUMN_ID, REST_COLUMN_NAME, REST_COLUMN_CUISINES, REST_COLUMN_LOGOS}; 
	
	public static final String MENU_TABLE_NAME = "menu";
	public static final String MENU_COLUMN_ID = "_id";
	public static final String MENU_COLUMN_REST_ID = "rest_id";
	public static final String MENU_COLUMN_DISH = "dish";
	public static final String MENU_COLUMN_DESCRIPTION = "description";
	public static final String MENU_COLUMN_ADDITIONAL = "additional";
	public static final String MENU_COLUMN_PORTION = "portion_size";
	public static final String MENU_COLUMN_COST = "cost";
	public static final String MENU_COLUMN_SPECIAL = "special";
	public static final String MENU_COLUMN_VEG = "vegetarian";
	public static final String MENU_COLUMN_HEALTH = "healthy";
	public static final String MENU_COLUMN_HALAAL = "halaal";
	
	private String allMenuColumns[] = {MENU_COLUMN_ID, MENU_COLUMN_DISH, 
			MENU_COLUMN_DESCRIPTION, MENU_COLUMN_ADDITIONAL, MENU_COLUMN_PORTION, MENU_COLUMN_COST,
			MENU_COLUMN_SPECIAL, MENU_COLUMN_VEG, MENU_COLUMN_HEALTH, MENU_COLUMN_HALAAL}; 
	private String restMenuSelection = "rest_id = ? AND category = ?";
	/*private String orderByMenuCategory = " CASE category "
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
			+ "END";*/
	
	private String orderByMenuColumns = MENU_COLUMN_COST;
	
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
	public static final String BRANCH_COLUMN_DISTANCE = "distance";
	public static final String allBranchColumns[] = {BRANCH_COLUMN_ID, BRANCH_COLUMN_REST_ID, BRANCH_COLUMN_NAME,
			BRANCH_COLUMN_PROVINCE, BRANCH_COLUMN_SUBURB, BRANCH_COLUMN_ADDRESS, BRANCH_COLUMN_TELEPHONE_NO,
			BRANCH_COLUMN_LATITUDE, BRANCH_COLUMN_LONGITUDE, BRANCH_COLUMN_HALAAL, BRANCH_COLUMN_KOSHER, BRANCH_COLUMN_DISTANCE}; 
	private String restBranchSelection = "rest_id = ?";
	private String orderByBranchColumns = BRANCH_COLUMN_PROVINCE + ", " + BRANCH_COLUMN_SUBURB;
	
	public static final String REST_DISTANCE_KEY = "nearby_rest_pref";
	public static final String HALAAL_KEY = "halaal_pref";
	public static final String KOSHER_KEY = "kosher_pref";
	public static final String VEGETARIAN_KEY = "vegetarian_pref";
	public static final String VEGAN_KEY = "vegan_pref";
	
	Context context;
	SQLiteDatabase db;
	MyDiningDbOpenHelper dbHelper;
	
	public RestaurantDataSource(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		dbHelper = new MyDiningDbOpenHelper(context);
	}
	
	public void open() throws SQLException {
		//dbHelper.openDataBase();
	    db = dbHelper.getOpenDatabase();
	}

	public void close() {
		//dbHelper.close();
	}
	
	public List<Restaurant> getAllRestaurants() {
	    List<Restaurant> restaurants = new ArrayList<Restaurant>();

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
	
	public List<Restaurant> searchForRestaurants(String selection, String[] args) {
	    List<Restaurant> restaurants = new ArrayList<Restaurant>();

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
		List<Restaurant> restaurants;
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
		if (!cursor.isNull(3)){
			restaurant.setLogo(cursor.getString(3));
		}
		
	    return restaurant;
	}
	
	private Menu getRestaurantMenu(long id){
		Menu menu = null;
		MenuCategory mcat = null;
		MenuItem mitem = null;
		String[] rest_selection = new String[2];
		Cursor cursorItems;
		
		menu = new Menu();
		
		String getMenuCategories = "select * from menu_category "
				+ "inner join menu "
				+ "on menu_category._id = menu.category "
				+ "inner join master_category "
				+ "on master_category._id = menu_category.master_category "
				+ "where rest_id = " + id 
				+ " group by master_category, category "
				+ "order by master_category.rank";							
		
		Cursor cursorCategories = db.rawQuery(getMenuCategories, null);
		
		cursorCategories.moveToFirst();
		while (!cursorCategories.isAfterLast()) {
			mcat = new MenuCategory(cursorCategories.getString(2));
			mcat.setId(cursorCategories.getLong(0));
			mcat.setDescription(cursorCategories.getString(3));
			mcat.setAdditional(cursorCategories.getString(4));
			
			menu.addMenuCategory(mcat);
			
			rest_selection[0] = Long.valueOf(id).toString();
			rest_selection[1] = Long.valueOf(mcat.getId()).toString();
			
			cursorItems = db.query(MENU_TABLE_NAME,
			        allMenuColumns, restMenuSelection, rest_selection, null, null, orderByMenuColumns);
			
			cursorItems.moveToFirst();
			while (!cursorItems.isAfterLast()) {
				mitem = new MenuItem();
				mitem.setId(cursorItems.getLong(0));
				mitem.setDish(cursorItems.getString(1));
				mitem.setDescription(cursorItems.getString(2));
				mitem.setAdditional(cursorItems.getString(3));
				mitem.setPortion(cursorItems.getString(4));
				mitem.setCost(cursorItems.getFloat(5));
				
				if (cursorItems.getInt(6) == 0){
					mitem.setSpecial(false);
				}else{
					mitem.setSpecial(true);
				}
				
				if (cursorItems.getInt(7) == 0){
					mitem.setVegetarian(false);
				}else{
					mitem.setVegetarian(true);
				}
				
				if (cursorItems.getInt(8) == 0){
					mitem.setHealthy(false);
				}else{
					mitem.setHealthy(true);
				}
				
				mitem.setHalaal(cursorItems.getString(9));
				
				mcat.addMenuItem(mitem);
				
				cursorItems.moveToNext();
			}
			// make sure to close the cursor
			cursorItems.close();
			
			cursorCategories.moveToNext();
		}
		
		cursorCategories.close();
		    
		return menu;
	}
	
	private List<Branch> getRestaurantBranches(long id){
		List<Branch> branches;
		Branch branch = null;
		boolean halaal = false;
		boolean kosher = false;
		String distance_value;
		ArrayList<String> rest_selection = new ArrayList<String>();
		
		rest_selection.add(Long.valueOf(id).toString());
		
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
		
		distance_value = sharedPref.getString(REST_DISTANCE_KEY, "10000");
		restBranchSelection += " AND distance <= ?";
		rest_selection.add(distance_value);
		
		halaal = sharedPref.getBoolean(HALAAL_KEY, false);
		if (halaal){
			restBranchSelection += " AND halaal = ?";
			rest_selection.add("1");
		}
		
		kosher = sharedPref.getBoolean(KOSHER_KEY, false);
		if (kosher){
			restBranchSelection += " AND kosher = ?";
			rest_selection.add("1");
		}
		
		Cursor cursor = db.query(BRANCH_TABLE_NAME,
		        allBranchColumns, restBranchSelection, rest_selection.toArray(new String[rest_selection.size()]), null, null, orderByBranchColumns);
		branches = new ArrayList<Branch>();
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			
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
			
			branches.add(branch);
			
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
		    
		return branches;
	}
}
