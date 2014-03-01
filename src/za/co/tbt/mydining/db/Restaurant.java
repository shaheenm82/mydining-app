package za.co.tbt.mydining.db;

public class Restaurant extends DBItem{
	private String cuisines;
	private Menu restaurant_menu;
	
	public Restaurant(String name) {
		// TODO Auto-generated constructor stub
		super(name);
	}
	
	public String getCuisines() {
		return cuisines;
	}
	public void setCuisines(String cuisines) {
		this.cuisines = cuisines;
	}
	/**
	 * @return the restaurant_menu
	 */
	public Menu getRestaurant_menu() {
		return restaurant_menu;
	}
	/**
	 * @param restaurant_menu the restaurant_menu to set
	 */
	public void setRestaurant_menu(Menu restaurant_menu) {
		this.restaurant_menu = restaurant_menu;
	}	
}
