package hotel.chain;

import java.io.IOException;
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

import beans.BookingBean;
import beans.UserBean;

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/CheckoutServlet")
//Handles availability checking and checkout
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
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
		UserBean u = (UserBean) request.getSession().getAttribute("userBean");
		Vector<Cart> userCart = new Vector<Cart>();
		boolean bookingOpen = true;
		//Book Rooms
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
				ps = conn.prepareStatement("SELECT COUNT(*) as numRooms from bookingorders where roomType = ? AND uid = ? AND checkin >=? and checkout <= ?;");
				ps.setString(1, b.getRoomType());
				ps.setInt(2, u.getId());
				ps.setDate(3, (Date) b.getCheckout());
				ps.setDate(4, (Date) b.getCheckin());
				rs = ps.executeQuery();
				if(rs.next()){
					userBookingCount = rs.getInt("numRooms");
				}
				//count all bookings for this roomtype
				ps = conn.prepareStatement("SELECT COUNT(*) as numRooms from bookings where roomType = ? AND checkin >=? and checkout <= ?;");
				rs = ps.executeQuery();
				ps.setDate(1, (Date) b.getCheckout());
				ps.setDate(2, (Date) b.getCheckin());
				if(rs.next()){
					totalBookingCount = rs.getInt("numRooms");
				}
				//count number of rooms
				
				ps = conn.prepareStatement("SELECT COUNT(*) as numRooms from rooms where roomType = ?;");
				rs = ps.executeQuery();
				
				if(rs.next()){
					totalRooms = rs.getInt("numRooms");
				}
				//if there aren't enough rooms notify and send back
				if(userBookingCount+totalBookingCount > totalRooms){
					bookingOpen = false;
					break;
				}
			}
			
			if(!bookingOpen){
				RequestDispatcher rd = request.getRequestDispatcher("/cart.jsp");
				String bookingError = "One or more of the selected rooms are currently unavailable, please try again at a later time or cancel current booking";
				request.setAttribute("bookingError",bookingError );
				return;
			}
			//Move from bookingorder to booking if all fulfilled
			if(bookingOpen){
				ps = conn.prepareStatement("UPDATE bookingOrders SET bookingDate = CURDATE() where uid = ? ;");
				ps.setInt(1, u.getId());
				ps.executeUpdate();		
				ps = conn.prepareStatement("Insert into bookings select * from bookingorders where uid = ?;");
				ps.executeUpdate();
				ps = conn.prepareStatement("delete from bookingorders where uid = ?;");
				ps.executeUpdate();
				DatabaseTool.endConnection(conn);
				//return
			}
			DatabaseTool.endConnection(conn);
			//return
		} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
