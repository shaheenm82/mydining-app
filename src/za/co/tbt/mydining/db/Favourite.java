package za.co.tbt.mydining.db;

import java.util.Calendar;

import android.text.format.DateFormat;
import android.util.Log;

public class Favourite {
	private long id;
	private Restaurant restaurant;
	private int selected;
	private String selected_date;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	public int getSelected() {
		return selected;
	}
	public void setSelected(int selected) {
		this.selected = selected;
	}
	
	public String getSelected_date() {
		return selected_date;
	}
	
	public void setSelected_date(String selected_date) {
		//Log.d("ssm","loading Favourites date " + selected_date);
		
		this.selected_date = selected_date;
	}	
}
