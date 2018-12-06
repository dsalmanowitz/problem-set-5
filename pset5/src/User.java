/**
 * Just like last time, the User class is responsible for retrieving
 * (i.e., getting), and updating (i.e., setting) user information.
 * This time, though, you'll need to add the ability to update user
 * information and display that information in a formatted manner.
 * 
 * Most of the functionality for this class should have already been
 * implemented last time. You can always reference my Github repository
 * for inspiration (https://github.com/rwilson-ucvts/java-sample-atm).
 */

public class User {
	private int PIN;
	private String fname;
	private String lname;
	private int dob;
	private int phone;
	private String address;
	private String city;
	private String state;
	private String postalCode;
	
	public User(int PIN, String fname, String lname, int dob, int phone, String address, String city, String state, String postalCode) {
		this.PIN = PIN;
		this.fname = fname;
		this.lname = lname;
		this.dob = dob;
		this.phone = phone;
		this.address = address;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
	}
	
	public int getPIN() {
		return PIN;
	}
	public String getFName() {
		return fname;
	}
	public String getLName() {
		return lname;
	}
	public int getDOB() {
		return dob;
	}
	public int getPhone() {
		return phone;
	}
	public String getAddress() {
		return address;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPIN(int oldPIN, int PIN) {
		if (oldPIN == this.PIN) {
			this.PIN = PIN;
		}
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
}