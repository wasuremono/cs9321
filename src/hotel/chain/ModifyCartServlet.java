package hotel.chain;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		
		
		String checkin = request.getParameter("checkin");	
		String checkout = request.getParameter("checkout");	
		UserBean u = (UserBean)request.getSession().getAttribute("userBean");
		Connection conn = null;
		
		try {
				conn = DatabaseTool.getConnection();
				//need further checks
				PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) as numRooms from bookings where checkin >=? and checkout <= ?;");
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
