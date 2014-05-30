package za.co.tbt.mydining.db;

import java.util.ArrayList;
import java.util.List;

public class Menu {
	List<DBItem> categories;

	public Menu() {
		categories = new ArrayList<DBItem>();
	}
	
	public void addMenuCategory(MenuCategory mcategory){
		categories.add(mcategory);
	}
	
	public List<DBItem> getCategories() {
		return categories;
	}

	public void setCategories(List<DBItem> categories) {
		this.categories = categories;
	}
	
	
}
