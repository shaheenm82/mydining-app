package za.co.tbt.mydining.db;

public class Favourite {
	private long id;
	private Restaurant restaurant;
	private int selected;
	private long selected_date;
	
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
	
	public long getSelected_date() {
		return selected_date;
	}
	
	public void setSelected_date(long selected_date) {
		this.selected_date = selected_date;
	}	
}
