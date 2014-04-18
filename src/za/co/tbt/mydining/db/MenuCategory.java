package za.co.tbt.mydining.db;

import java.util.ArrayList;
import java.util.List;

public class MenuCategory extends DBItem {
	String description;
	String additional;
	
	List<DBItem> dishes;

	public MenuCategory(String name) {
		// TODO Auto-generated constructor stub
		super(name);
		dishes = new ArrayList<DBItem>();
	}
	
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getAdditional() {
		return additional;
	}


	public void setAdditional(String additional) {
		this.additional = additional;
	}


	public void addMenuItem(MenuItem mitem){
		dishes.add(mitem);
	}
	
	public List<DBItem> getDishes() {
		return dishes;
	}

	public void setDishes(List<DBItem> dishes) {
		this.dishes = dishes;
	}		
}
