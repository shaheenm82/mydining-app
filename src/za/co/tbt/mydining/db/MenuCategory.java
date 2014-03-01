package za.co.tbt.mydining.db;

import java.util.ArrayList;
import java.util.List;

public class MenuCategory extends DBItem {
	List<DBItem> dishes;

	public MenuCategory(String name) {
		// TODO Auto-generated constructor stub
		super(name);
		dishes = new ArrayList<DBItem>();
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
