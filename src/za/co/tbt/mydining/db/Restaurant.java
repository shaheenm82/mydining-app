package za.co.tbt.mydining.db;

import java.util.ArrayList;
import java.util.List;

public class Restaurant extends DBItem{
	private String cuisines;
	private Menu restaurant_menu;
	private String logo;
	private List<Branch> restaurant_branches;
	
	public Restaurant(String name) {
		// TODO Auto-generated constructor stub
		super(name);
		setRestaurant_branches(new ArrayList<Branch>());
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

	/**
	 * @return the restaurant_branches
	 */
	public List<Branch> getRestaurant_branches() {
		return restaurant_branches;
	}

	/**
	 * @param restaurant_branches the restaurant_branches to set
	 */
	public void setRestaurant_branches(List<Branch> restaurant_branches) {
		this.restaurant_branches = restaurant_branches;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}	
}
