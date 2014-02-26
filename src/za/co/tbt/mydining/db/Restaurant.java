package za.co.tbt.mydining.db;

public class Restaurant extends DBItem{
	private String cuisines;
	
	public String getCuisines() {
		return cuisines;
	}
	public void setCuisines(String cuisines) {
		this.cuisines = cuisines;
	}	
}
