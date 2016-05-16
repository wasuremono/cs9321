package hotel.chain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cart implements Serializable{

	int id = 0;
	int uid = 0;
	Date checkin = null;
	Date checkout = null;
	boolean extraBed = false;
	String roomType = "";
	Date bookingDate = null;
	
	public int getId() {
		return id;
	}

	public int getUid() {
		return uid;
	}

	public Date getCheckin() {
		return checkin;
	}

	public Date getCheckout() {
		return checkout;
	}

	public boolean isExtraBed() {
		return extraBed;
	}

	public String getRoomType() {
		return roomType;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	
	public void parseResultSet(ResultSet rs) throws SQLException, ParseException{
		SimpleDateFormat sqldateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		id = rs.getInt("id");
		java.sql.Date sqlcheckin = rs.getDate("checkin");
		checkin = new java.util.Date(sqlcheckin.getTime());
		java.sql.Date sqlcheckout = rs.getDate("checkout");
		checkout = new java.util.Date(sqlcheckout.getTime());
		//checkin = sqldateFormat.parse(rs.getString("checkin"));
		//checkin = dateFormat.parse(new SimpleDateFormat("dd-MM-yyyy").format(checkin));
		//checkout = sqldateFormat.parse(rs.getString("checkout"));
		//checkout = dateFormat.parse(new SimpleDateFormat("dd-MM-yyyy").format(checkout));
		uid = rs.getInt("uid");
		roomType = rs.getString("roomType");
		extraBed = rs.getBoolean("extraBed");
		if(rs.getDate("bookingDate") != null){
			//bookingDate = sqldateFormat.parse(rs.getString("bookingDate"));
			//bookingDate = dateFormat.parse(new SimpleDateFormat("dd-MM-yyyy").format(bookingDate));
			java.sql.Date sqlbooking = rs.getDate("bookingDate");
			bookingDate = new java.util.Date(sqlbooking.getTime());
		}
	}
}
