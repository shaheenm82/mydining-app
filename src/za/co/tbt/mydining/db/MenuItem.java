package za.co.tbt.mydining.db;

public class MenuItem extends DBItem{
	String description;
	String additional;
	float cost;
	boolean vegetarian;
	boolean special;
	boolean healthy;
	
	public MenuItem() {
		// TODO Auto-generated constructor stub
		super("");
		
		description = "";
		additional = "";
		cost = 0;
		vegetarian = false;
		special = false;
		healthy = false;
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
}
