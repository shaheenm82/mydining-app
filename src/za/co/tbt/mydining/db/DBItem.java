package za.co.tbt.mydining.db;

public class DBItem {
	private long id;
	private String name;
	
	public DBItem(String name) {
		id = 0;
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
