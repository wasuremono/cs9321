package hotel.chain;


import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;


public class Calculator {
	public double calculatePrice(String roomType,String location,Date checkin, Date checkout,int numRooms,int extraBeds){
		double totalCost = 0.0;
		int basePrice = 0;
		Connection conn = null;
		try {
			conn = DatabaseTool.getConnection();
			ResultSet rs = null;
			PreparedStatement ps  = conn.prepareStatement("select price from rooms where roomType = ? limit 1;");
			ps.setString(1, roomType);
			rs = ps.executeQuery();
			if(rs.next()){
				basePrice = rs.getInt("price");
				
			}
			int stayDuration = (int)(checkout.getTime() - checkin.getTime())/(1000*60*60*24);
			int adjDays = 0;
			int extraBedPrice = 35;
			Double adjPrice = 0.0;
			Vector<java.util.Date> start = new Vector<java.util.Date>();
			Vector<java.util.Date> end = new Vector<java.util.Date>();
			Vector<Double> rate = new Vector<Double>();
			ps = conn.prepareStatement("select rate, start, end from roomrates where location = ? and roomType = ? AND ((start <= ? AND end >= ?));");
			ps.setString(1,location);
			ps.setString(2, roomType);
			ps.setDate(3, new java.sql.Date(checkout.getTime()));
			ps.setDate(4, new java.sql.Date(checkin.getTime()));
			rs = ps.executeQuery();
			while(rs.next()){						
				start.add(new java.util.Date(rs.getDate("start").getTime()));
				end.add(new java.util.Date(rs.getDate("end").getTime()));
				rate.add(rs.getDouble("rate"));
			}
			for(int i=0;i<start.size();i++){
				//enda-starta,enda-startb,endb-starta,endb-startb
				//a = this
				int overLapA =(int)(checkout.getTime() - checkin.getTime())/(1000*60*60*24);
				int overLapB =(int)(checkout.getTime() - start.get(i).getTime())/(1000*60*60*24);
				int overLapC =(int)(end.get(i).getTime() - checkin.getTime())/(1000*60*60*24);
				int overLapD =(int)(end.get(i).getTime() - start.get(i).getTime())/(1000*60*60*24);
				int minA = Math.min(overLapA,overLapB);
				int minB = Math.min(overLapC,overLapD);
				int ratePeriod = Math.min(minA, minB);
				adjDays += ratePeriod;
				adjPrice += adjDays*rate.get(i)*(basePrice*numRooms+extraBeds*extraBedPrice);
			}
			adjPrice += (stayDuration - adjDays)*basePrice;
			totalCost = adjPrice;
			
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totalCost;
		
	}
}
