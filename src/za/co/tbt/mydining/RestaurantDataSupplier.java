package za.co.tbt.mydining;

import java.util.List;

import za.co.tbt.mydining.db.Branch;
import za.co.tbt.mydining.db.Menu;
import za.co.tbt.mydining.db.Restaurant;

public interface RestaurantDataSupplier {
	public Menu requestRestaurantMenu();
	
	public List<Branch> requestRestaurantBranches();
	
	public Restaurant requestRestaurantDetails();
}
