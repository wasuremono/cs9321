package hotel.chain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class SearchResult implements Serializable{

	private int id;
	private int price;
	private String roomType;
	private String location;
	String checkin;
	String checkout;
	int numRooms;
	
	public int getId() {
		return id;
	}
	public int getPrice() {
		return price;
	}
	public String getRoomType() {
		return roomType;
	}
	public String getLocation() {
		return location;
	}
	public int getNumRooms() {
		return numRooms;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void setNumRooms(int numRooms) {
		this.numRooms = numRooms;
	}
	public void parseResultSet(ResultSet rs) throws SQLException {
		id = rs.getInt("id");
		price = rs.getInt("price");
		roomType = rs.getString("roomType");
		location = rs.getString("location");
		
	}
	public String getCheckin() {
		return checkin;
	}
	public String getCheckout() {
		return checkout;
	}
	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}
	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}
	
}
