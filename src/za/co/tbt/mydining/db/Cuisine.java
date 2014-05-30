package za.co.tbt.mydining.db;

public class Cuisine extends DBItem{
	private String description;
	
	public Cuisine(String name) {
		super(name);
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
