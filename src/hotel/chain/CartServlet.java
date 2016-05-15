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
		
		String checkin = request.getParameter("checkin");	
		String checkout = request.getParameter("checkout");	
		String action = request.getParameter("action");
		Vector<Cart> userCart = new Vector<Cart>();
		//UserBean u = (UserBean) request.getSession().getAttribute("userBean");
		UserBean u = new UserBean(1);
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
		}
		//if(addbooking)
		//Add Room	
		if(action.equals("add")){
			try{
				conn = DatabaseTool.getConnection();
				PreparedStatement ps = conn.prepareStatement("INSERT INTO bookingorders(`checkin`,`checkout`,`uid`,`roomType`,`extraBed`,'bookingDate')VALUES(?,?,?,?,?,NOW());");
				ps.setString(1, checkin);
				ps.setString(2, checkout);
				//Change this to current user based on session
				ps.setInt(3, u.getId());
				//Change this to selected roomType
				ps.setString(4, "Single");
				ps.setBoolean(5, false);
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
			try{
				conn = DatabaseTool.getConnection();
				PreparedStatement ps = conn.prepareStatement("UPDATE bookingOrders SET extraBed = !extraBed where id =?;");
				//change this to bookingid
				ps.setInt(1,u.getId() );	
				ps.executeUpdate();		
				DatabaseTool.endConnection(conn);
			} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//if action is cart then display it
		if(action.equals("viewCart")){
			RequestDispatcher rd = request.getRequestDispatcher("/cart.jsp");
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
				DatabaseTool.endConnection(conn);
				request.setAttribute("cart", userCart);
			} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rd.forward(request, response);
			return;
		}
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
