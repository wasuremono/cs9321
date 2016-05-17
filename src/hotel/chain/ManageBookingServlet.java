package hotel.chain;

import java.io.IOException;
import java.sql.Connection;
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

/**
 * Servlet implementation class ManageBookingServlet
 */
@WebServlet("/ManageBooking")
public class ManageBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageBookingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String referenceID = request.getParameter("bookingRef");
		Vector<Cart> userCart = new Vector<Cart>();
		RequestDispatcher rd = request.getRequestDispatcher("");
		Connection conn;
		try {
			conn = DatabaseTool.getConnection();
			String select = "select * from bookings where referenceID = ?;";
			PreparedStatement ps = conn.prepareStatement(select);
			ps.setString(1, referenceID);
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
			DatabaseTool.endConnection(conn);
			request.setAttribute("cart", userCart);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		rd = request.getRequestDispatcher("/manage.jsp");
		rd.forward(request,response);
		return;	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
