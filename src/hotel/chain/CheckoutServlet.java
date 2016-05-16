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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.BookingBean;
import beans.UserBean;

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/CheckoutServlet")
//Handles availability checking and checkout
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SecureRandom random = new SecureRandom();
	private String checkin;
	private String checkout;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public enum RoomType{
		Single,
    	Twin,
    	Queen,
    	Executive,
    	Suite
    };
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession mySession = request.getSession(false);
		UserBean u = (UserBean) mySession.getAttribute("u");
		String action = request.getParameter("action");
		Vector<Cart> userCart = new Vector<Cart>();
		boolean bookingOpen = true;
		//Book Rooms
		if(action.equals("checkout")){
			try {
				Connection conn = DatabaseTool.getConnection();
				//for each userbookingorder
				//sum up number of each roomtype
				conn = DatabaseTool.getConnection();
				PreparedStatement ps = conn.prepareStatement("select * from bookingOrders where uid =?;");
				//change this to bookingid
				ps.setInt(1,u.getId());
				ResultSet rs = ps.executeQuery();
				if(!rs.next()){
					System.out.println("No Bookings for selected user");
				} else {
					rs.beforeFirst();
					while(rs.next()){
						Cart c = new Cart();
						c.parseResultSet(rs);
						userCart.add(c);
					}	
				}
				for(Cart b:userCart){
					//select booking id
					//check for roomType and checkin/out Date
					//check availability
					//if any rooms are unavailable->return to cart
				//}
				//TODO: Check for rooms based on which hotel they belong to
				//for each roomtype:
				//for(RoomType rt:RoomType.values()){
					int userBookingCount = 0;
					int totalBookingCount = 0;
					int totalRooms = 0;
					//count user bookings for this roomtype
					ps = conn.prepareStatement("SELECT numRooms from bookingorders where roomType = ? AND uid = ? AND ((checkin <= ? AND checkout >= ?) OR (checkin <= ? AND checkout >= ?)) and location = ?;");
					ps.setString(1, b.getRoomType());
					ps.setInt(2, u.getId());
					ps.setDate(3, new java.sql.Date(b.getCheckout().getTime()));
					ps.setDate(4, new java.sql.Date(b.getCheckout().getTime()));
					ps.setDate(5, new java.sql.Date(b.getCheckin().getTime()));
					ps.setDate(6, new java.sql.Date(b.getCheckin().getTime()));
					ps.setString(7, b.getLocation());
					rs = ps.executeQuery();					
					if(rs.next()){
						userBookingCount = rs.getInt("numRooms");
						System.out.println("userbookingcount" + userBookingCount);
					}
					//count all bookings for this roomtype
					ps = conn.prepareStatement("SELECT COUNT(*) as numRooms from bookings where roomType = ? AND ((checkin <= ? AND checkout >= ?) OR (checkin <= ? AND checkout >= ?)) and location = ?;");
					ps.setString(1, b.getRoomType());
					ps.setDate(2, new java.sql.Date(b.getCheckout().getTime()));
					ps.setDate(3, new java.sql.Date(b.getCheckout().getTime()));
					ps.setDate(4, new java.sql.Date(b.getCheckin().getTime()));
					ps.setDate(5, new java.sql.Date(b.getCheckin().getTime()));
					ps.setString(6, b.getLocation());
					rs = ps.executeQuery();
					if(rs.next()){
						totalBookingCount = rs.getInt("numRooms");
						System.out.println("totalBookingCount" + totalBookingCount);
					}
					//count number of rooms
					
					ps = conn.prepareStatement("SELECT COUNT(*) as numRooms from rooms where roomType = ? and location = ?;");
					ps.setString(1, b.getRoomType());
					ps.setString(2, b.getLocation());
					rs = ps.executeQuery();
					
					if(rs.next()){
						totalRooms = rs.getInt("numRooms");
						System.out.println("numRoomst" + totalRooms);
					}
					//if there aren't enough rooms notify and send back
					if(userBookingCount+totalBookingCount > totalRooms){
						bookingOpen = false;
						System.out.println("not enough rooms");
						break;
					}
				}
				
				if(!bookingOpen){
					System.out.println("Redirecting");
					RequestDispatcher rd = request.getRequestDispatcher("CartServlet?action=viewCart");
					String bookingError = "One or more of the selected rooms are currently unavailable, please try again at a later time or cancel current booking";
					request.setAttribute("bookingError",bookingError );
					rd.forward(request, response);
					return;
				}
				//Move from bookingorder to booking if all fulfilled
				if(bookingOpen){					
					request.setAttribute("user", u);
					RequestDispatcher rd = request.getRequestDispatcher("/checkout.jsp");
					rd.forward(request, response);
					return;
				}
				DatabaseTool.endConnection(conn);
				//return
			} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(action.equals("confirm")){
			Connection conn;
			try {
				int referenceID = 0;
				conn = DatabaseTool.getConnection();
				String select = "select id from bookingorders where uid = ?;";
				String update = "UPDATE bookingOrders SET bookingDate = CURDATE(), referenceID = ?,pin =? where uid = ? ;";
				String insert = "Insert into bookings select * from bookingorders where uid = ?;";
				String delete = "delete from bookingorders where uid = ?;";	
				String URL = generateURL();
				String pin = generatePin();
				PreparedStatement ps = conn.prepareStatement(select);
				ps.setInt(1, u.getId());
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					referenceID = rs.getInt("id");
				}
				ps = conn.prepareStatement(update);				
				ps.setString(1, URL);
				ps.setString(2, pin);
				ps.setInt(3, u.getId());
				ps.executeUpdate();
				ps = conn.prepareStatement(insert);
				ps.setInt(1, u.getId());
				ps.executeUpdate();
				ps = conn.prepareStatement(delete);
				ps.setInt(1, u.getId());
				ps.executeUpdate();
				DatabaseTool.endConnection(conn);
				MailService mailer = new MailService();
				System.out.println("Preparing to send");
				mailer.sendMail(u.getEmail(),"Your Hotel Booking has been confirmed","<p>Your booking has been made, to check your booking follow the link below with the provided pin.</p>\n"+"<p>http://localhost:8080/Assign2/ManageBooking?bookingRef="+URL+"</p>\n"+"PIN: "+pin);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private String generateURL(){
		return new BigInteger(130, random).toString(32);
	}
	
	private String generatePin(){
		int num = random.nextInt(10000);
		return String.format("%05d", num);
	}

}
