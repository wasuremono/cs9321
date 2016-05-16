package hotel.chain;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Vector;
import java.text.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BookingBean;
import beans.UserBean;

/**
 * Servlet implementation class ModifyCartServlet
 */
@WebServlet("/ModifyCartServlet")
public class ModifyCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		UserBean u = (UserBean)request.getSession().getAttribute("userBean");
		String checkinStr = request.getParameter("checkin");	
		String checkoutStr = request.getParameter("checkout");
		String roomType = request.getParameter("roomType");
		String location = request.getParameter("location");
		String numRooms = request.getParameter("numRooms");
		//SimpleDateFormat sqldateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date checkin = null;
		java.util.Date checkout = null;
		RequestDispatcher rd = request.getRequestDispatcher("");
		Connection conn = null;
		Vector<Cart> userCart = new Vector<Cart>();
		String action = request.getParameter("action");
		userCart.clear();
		
		
		if(request.getParameterMap().containsKey("checkin")){
			try {
				checkin =  dateFormat.parse(checkinStr);
				checkout = dateFormat.parse(checkoutStr);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(action.equals("addRoom")){
			try{
				//populate cart with existing booking
				conn = DatabaseTool.getConnection();
				PreparedStatement pssel = conn.prepareStatement("SELECT * FROM bookings WHERE uid =?;");
				//change this to bookingid
				pssel.setInt(1,u.getId());
				ResultSet rs = pssel.executeQuery();
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
				//move existing fields into booking orders (view cart)
				PreparedStatement psins = conn.prepareStatement("INSERT INTO bookingorders SELECT * FROM bookings where uid =?;");
				psins.executeUpdate();
				//add into Booking Orders, redirect to viewCart
				PreparedStatement psadd = conn.prepareStatement("INSERT INTO bookingorders(`checkin`,`checkout`,`uid`,`roomType`,`extraBed`,`bookingDate`,`location`,`numRooms`)VALUES(?,?,?,?,?,NULL,?,?);");
				psadd.setDate(1, new java.sql.Date(checkin.getTime()));
				psadd.setDate(2, new java.sql.Date(checkout.getTime()));
				psadd.setInt(3, u.getId());
				psadd.setString(4, roomType);
				psadd.setInt(5, 0);
				psadd.setString(6, location);
				psadd.setInt(7, Integer.parseInt(numRooms));
				psadd.executeUpdate();
				DatabaseTool.endConnection(conn);
				
			} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rd = request.getRequestDispatcher("CartServlet?action=viewCart2");
		
			
		}
		
		
		/**try {
				conn = DatabaseTool.getConnection();
				//need further checks
				PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) as numRooms from bookings where checkin >=? and checkout <= ?; and roomtype");
				ps.setString(1, checkout);
				ps.setString(2, checkin);
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					RequestDispatcher rd = request.getRequestDispatcher("/cart.jsp");
					String bookingError = "One or more of the selected rooms are currently unavailable, please try again at a later time or cancel current booking";
					conn = DatabaseTool.getConnection();
					request.setAttribute("bookingError",bookingError );
					return;
				}
				else {
							ps = conn.prepareStatement("INSERT INTO bookingorders(`checkin`,`checkout`,`uid`,`roomType`,`extraBed`,'bookingDate')VALUES(?,?,?,?,?,NOW());");
							ps.setString(1, checkin);
							ps.setString(2, checkout);
							ps.setInt(3, u.getId());
							//Change this to selected roomType
							ps.setString(4, "Single");
							ps.setBoolean(5, false);
							ps.executeUpdate();
							DatabaseTool.endConnection(conn);
					} 
				//confirm booking when in orders and checkout as normal if confirmed
		}
		catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}**/
		
	}	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
