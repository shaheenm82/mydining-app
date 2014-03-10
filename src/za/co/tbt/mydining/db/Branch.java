package za.co.tbt.mydining.db;

public class Branch extends DBItem {
	String province;
	String suburb;
	String address;
	String telephone_no;
	double latitude;
	double longitude;
	boolean halaal;
	boolean kosher;
	
	public Branch(String name) {
		// TODO Auto-generated constructor stub
		super(name);		
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getSuburb() {
		return suburb;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address.replaceAll(", ","\n");
	}

	public String getTelephone_no() {
		return telephone_no;
	}

	public void setTelephone_no(String telephone_no) {
		this.telephone_no = telephone_no;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public boolean isHalaal() {
		return halaal;
	}

	public void setHalaal(boolean halaal) {
		this.halaal = halaal;
	}

	public boolean isKosher() {
		return kosher;
	}

	public void setKosher(boolean kosher) {
		this.kosher = kosher;
	}
	
	
}
