package za.co.tbt.mydining.db;

public class MenuItem extends DBItem{
	String description;
	String additional;
	String portion;
	float cost;
	boolean vegetarian;
	boolean special;
	boolean healthy;
	String halaal;
	
	public MenuItem() {
		super("");
		
		description = "";
		additional = "";
		portion = "";
		cost = 0;
		vegetarian = false;
		special = false;
		healthy = false;
		halaal = "R";
	}
	
	public String getDish() {
		return getName();
	}
	
	public void setDish(String dish) {
		setName(dish);
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
	
	public double getCost() {
		return cost;
	}
	
	public void setCost(float cost) {
		this.cost = cost;
	}
	
	public boolean isVegetarian() {
		return vegetarian;
	}
	
	public void setVegetarian(boolean vegetarian) {
		this.vegetarian = vegetarian;
	}
	
	public boolean isSpecial() {
		return special;
	}
	
	public void setSpecial(boolean special) {
		this.special = special;
	}
	
	public boolean isHealthy() {
		return healthy;
	}
	
	public void setHealthy(boolean healthy) {
		this.healthy = healthy;
	}

	public String getPortion() {
		return portion;
	}

	public void setPortion(String portion) {
		this.portion = portion;
	}

	public String getHalaal() {
		return halaal;
	}

	public void setHalaal(String halaal) {
		this.halaal = halaal;
	}
}
