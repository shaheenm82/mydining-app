package za.co.tbt.mydining;

import java.util.List;

import za.co.tbt.mydining.db.Branch;
import za.co.tbt.mydining.db.Menu;

public interface RestaurantDataSupplier {
	public Menu requestRestaurantMenu();
	
	public List<Branch> requestRestaurantBranches();
}
