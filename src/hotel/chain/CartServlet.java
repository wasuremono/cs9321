package hotel.chain;

import beans.BookingBean;
import beans.UserBean;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
//Handles adding to cart and removing from cart
//Also handles adding room and adding/removing beds
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession mySession = request.getSession(false);
		UserBean u = (UserBean) mySession.getAttribute("u");
		String checkinString = request.getParameter("checkin");	
		String checkoutString = request.getParameter("checkout");
		String location = request.getParameter("location");
		String roomType = request.getParameter("roomType");
		String numRooms = request.getParameter("numRooms");
		SimpleDateFormat sqldateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date checkin = null;
		java.util.Date checkout = null;
		RequestDispatcher rd = request.getRequestDispatcher("");
		
		if(request.getParameterMap().containsKey("checkin")){
			try {
				checkin =  dateFormat.parse(checkinString);
				//checkin =  sqldateFormat.parse(new SimpleDateFormat("yyyy-MM-dd").format(checkin));	
				checkout = dateFormat.parse(checkoutString);
				//checkout = sqldateFormat.parse(new SimpleDateFormat("yyyy-MM-dd").format(checkout));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		String action = request.getParameter("action");
		Vector<Cart> userCart = new Vector<Cart>();
		userCart.clear();
		//UserBean u = (UserBean) request.getSession().getAttribute("userBean");
		
		Connection conn = null;
		
		//if(removeBooking)
		if(action.equals("remove")){
			try{
				conn = DatabaseTool.getConnection();
				PreparedStatement ps = conn.prepareStatement("delete from bookingOrders where uid =?;");
				ps.setInt(1,u.getId() );	
				ps.executeUpdate();
				DatabaseTool.endConnection(conn);
			} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//response.sendRedirect("cartServlet?action=viewCart");
			rd = request.getRequestDispatcher("CartServlet?action=viewCart");
		}
		//if(addbooking)
		//Add Room	
		if(action.equals("add")){
			try{
				conn = DatabaseTool.getConnection();
				PreparedStatement ps = conn.prepareStatement("INSERT INTO bookingorders(`checkin`,`checkout`,`uid`,`roomType`,`extraBed`,`bookingDate`,`location`,`numRooms`)VALUES(?,?,?,?,?,NULL,?,?);");
				ps.setDate(1, new java.sql.Date(checkin.getTime()));
				ps.setDate(2, new java.sql.Date(checkout.getTime()));
				//Change this to current user based on session
				ps.setInt(3, u.getId());
				//Change this to selected roomType
				ps.setString(4, roomType);
				ps.setInt(5, 0);				
				ps.setString(6, location);
				ps.setInt(7, Integer.parseInt(numRooms));
				//bookingDate as NOW();
				ps.executeUpdate();
				DatabaseTool.endConnection(conn);
				
				//return the booking id?
			} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(action.equals("update")){
		//if edit
		//Get bookingBean
		String bookingID = request.getParameter("booking_ID");	
		String extraBed = request.getParameter("extraBed");	
			try{
				conn = DatabaseTool.getConnection();
				PreparedStatement ps = conn.prepareStatement("UPDATE bookingOrders SET extraBed = ? where id =?;");
				//change this to bookingid
				ps.setInt(1, Integer.parseInt(extraBed));
				ps.setInt(2,Integer.parseInt(bookingID));	
				ps.executeUpdate();		
				DatabaseTool.endConnection(conn);
			} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rd = request.getRequestDispatcher("CartServlet?action=viewCart");

		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//if action is cart then display it
		if(action.equals("viewCart")){
			try{
				conn = DatabaseTool.getConnection();
				PreparedStatement ps = conn.prepareStatement("select * from bookingOrders where uid =?;");
				//change this to bookingid
				ps.setInt(1,u.getId());
				ResultSet rs = ps.executeQuery();
				if(!rs.next()){
					System.out.println("No Results");
				} else {
					rs.beforeFirst();
					while(rs.next()){
						Cart c = new Cart();
						c.parseResultSet(rs);
						userCart.add(c);
					}	
				}
				for(Cart c:userCart){
					int basePrice = 0;
					ps = conn.prepareStatement("select price from rooms where roomType = ?;");
					ps.setString(1, c.getRoomType());
					rs = ps.executeQuery();
					if(rs.next()){
						basePrice = rs.getInt("price");
						
					}
					int stayDuration = (int)(c.getCheckout().getTime() - c.getCheckin().getTime())/(1000*60*60*24);
					int adjDays = 0;
					int extraBedPrice = 35;
					Double adjPrice = 0.0;
					Vector<java.util.Date> start = new Vector<java.util.Date>();
					Vector<java.util.Date> end = new Vector<java.util.Date>();
					Vector<Double> rate = new Vector<Double>();
					ps = conn.prepareStatement("select rate, start, end from roomrates where location = ? and roomType = ? AND ((start <= ? AND end >= ?));");
					ps.setString(1, c.getLocation());
					ps.setString(2, c.getRoomType());
					ps.setDate(3, new java.sql.Date(c.getCheckout().getTime()));
					ps.setDate(4, new java.sql.Date(c.getCheckin().getTime()));
					rs = ps.executeQuery();
					while(rs.next()){						
						start.add(new java.util.Date(rs.getDate("start").getTime()));
						end.add(new java.util.Date(rs.getDate("end").getTime()));
						rate.add(rs.getDouble("rate"));
					}
					for(int i=0;i<start.size();i++){
						//enda-starta,enda-startb,endb-starta,endb-startb
						//a = this
						int overLapA =(int)(c.getCheckout().getTime() - c.getCheckin().getTime())/(1000*60*60*24);
						int overLapB =(int)(c.getCheckout().getTime() - start.get(i).getTime())/(1000*60*60*24);
						int overLapC =(int)(end.get(i).getTime() - c.getCheckin().getTime())/(1000*60*60*24);
						int overLapD =(int)(end.get(i).getTime() - start.get(i).getTime())/(1000*60*60*24);
						int minA = Math.min(overLapA,overLapB);
						int minB = Math.min(overLapC,overLapD);
						int ratePeriod = Math.min(minA, minB);
						adjDays += ratePeriod;
						adjPrice += adjDays*rate.get(i)*(basePrice*c.getNumRooms()+c.getExtraBed()*extraBedPrice);
					}
					adjPrice += (stayDuration - adjDays)*basePrice;
					c.cost = adjPrice;
				}
				DatabaseTool.endConnection(conn);
				request.setAttribute("cart", userCart);
			} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			rd = request.getRequestDispatcher("/cart.jsp");	
		}
		
		//response.sendRedirect("cartServlet?action=viewCart");		
		rd.forward(request,response);
		return;	
		//if origin was search then return to search
		//if origin was cart, then update and return to cart
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
