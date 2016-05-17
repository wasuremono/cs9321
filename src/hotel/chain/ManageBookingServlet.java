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
		Vector<Cart> userCart = new Vector<Cart>();
		RequestDispatcher rd = request.getRequestDispatcher("");
		Calculator p = new Calculator();
		double totalCost = 0;
		Connection conn;
		if(request.getParameterMap().containsKey("action")){
			
			String action = request.getParameter("action");
			String referenceID = request.getParameter("bookingRef");
			System.out.println("actionTime " + action);
			if(action.equals("login")){
				String pin = request.getParameter("pin");
				System.out.println("Pin is "+pin+" bookingRef is "+referenceID);
				try {
					String bookingRef =null;
					conn = DatabaseTool.getConnection();
					if(request.getParameterMap().containsKey("bookingRef")){
					bookingRef = request.getParameter("bookingRef");
					}else{
						bookingRef = (String) request.getAttribute("refID");
					}
					String select = "select * from bookings where referenceID = ? and pin =?;";
					PreparedStatement ps = conn.prepareStatement(select);
					ps.setString(1, bookingRef);
					ps.setString(2, pin);
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
						c.cost = p.calculatePrice(c.getRoomType(), c.getLocation(), c.getCheckin(), c.getCheckout(), c.getNumRooms(), c.getExtraBed());
						totalCost += c.cost;
					}
					DatabaseTool.endConnection(conn);
					request.setAttribute("referenceID", bookingRef);
					request.setAttribute("cost", totalCost);
					request.setAttribute("cart", userCart);
					request.setAttribute("confirm", true);
					if(request.getAttribute("add") != null){
						boolean add = (boolean)request.getAttribute("add");
						int bookingID = (int) request.getAttribute("bookingID");
						request.setAttribute("bookingID",bookingID);
						request.setAttribute("add",add);
						System.out.println("got back to management");
					}				
					rd = request.getRequestDispatcher("/manage.jsp");
					rd.forward(request,response);
					return;
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(action.equals("remove")){
				try{
					conn = DatabaseTool.getConnection();
					PreparedStatement ps = conn.prepareStatement("delete from bookings where referenceID =?;");
					ps.setString(1,request.getParameter("referenceID"));	
					ps.executeUpdate();
					DatabaseTool.endConnection(conn);
				} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//response.sendRedirect("cartServlet?action=viewCart");
				rd = request.getRequestDispatcher("ManageBooking?action=viewBooking");
				rd.forward(request, response);
				return;
			}if(action.equals("update")){
				String bookingID = request.getParameter("booking_ID");
				String extraBed = request.getParameter("extraBed");
				String refID = request.getParameter("ref_id");
					try{
						conn = DatabaseTool.getConnection();
						PreparedStatement ps = conn.prepareStatement("UPDATE bookings SET extraBed = ? where id =?;");
						//change this to bookingid
						ps.setInt(1, Integer.parseInt(extraBed));
						ps.setInt(2,Integer.parseInt(bookingID));	
						ps.executeUpdate();		
						DatabaseTool.endConnection(conn);
					} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					request.setAttribute("bookingRef", refID);	
					request.setAttribute("add", false);
					response.sendRedirect("/Assign2/ManageBooking?bookingRef="+refID);
					return;
				
			}if(action.equals("viewBooking")){
				try {
					String ref = request.getParameter("bookingRef");
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
					
					for(Cart c:userCart){						
						c.cost = p.calculatePrice(c.getRoomType(), c.getLocation(), c.getCheckin(), c.getCheckout(), c.getNumRooms(), c.getExtraBed());
						totalCost += c.cost;
					}
					DatabaseTool.endConnection(conn);
					request.setAttribute("referenceID", referenceID);
					request.setAttribute("cost", totalCost);
					request.setAttribute("cart", userCart);
					request.setAttribute("add", false);
					rd = request.getRequestDispatcher("/manage.jsp");
					rd.forward(request,response);
					return;
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} if(action.equals("add")){
				String bookingID = request.getParameter("bookingID");
				String roomType = request.getParameter("roomType");
				String location = request.getParameter("location");
				String currRoom = request.getParameter("currRoom");
				String refID = request.getParameter("refid");
				int totalBookings = 0;
				int numRooms = 0;
				ResultSet rs = null;
				try {
					conn = DatabaseTool.getConnection();
					String select = "select numRooms from bookings where location = ? AND roomType = ?;";
					System.out.println(refID+"test");
					PreparedStatement ps = conn.prepareStatement(select);
					ps.setString(1,location);
					ps.setString(2, roomType);
					rs = ps.executeQuery();
					Cart c = new Cart();
					while(rs.next()){
						totalBookings += rs.getInt("numRooms");
						
					}
					System.out.println(refID+"test2");
					select = "select count(*) as numRooms from rooms where location =? AND roomType=?;";
					ps = conn.prepareStatement(select);
					ps.setString(1,location);
					ps.setString(2, roomType);
					rs = ps.executeQuery();
					if(rs.next()){
						numRooms = rs.getInt("numRooms");
					}
					if(numRooms - totalBookings > 1){
						String update = "Update bookings set numRooms = ? where id =?;";
						ps = conn.prepareStatement(update);
						ps.setInt(1, Integer.parseInt(currRoom)+1);
						ps.setInt(2, Integer.parseInt(bookingID));
						ps.executeUpdate();
						request.setAttribute("bookingID", bookingID);		
						request.setAttribute("add", true);
						System.out.println(refID+"test3");
						request.getRequestDispatcher("/ManageBooking");	
						rd.forward(request, response);
						System.out.println(refID+"test4");
						return;
					}else{
						request.setAttribute("bookingID", bookingID);
						request.setAttribute("add", false);
					}
					
					request.setAttribute("refID", refID);
					System.out.println(refID+"test5");
					request.getRequestDispatcher("ManageBooking");	
					System.out.println(refID+"test6");
					rd.forward(request, response);
					System.out.println(refID+"test6.5");
					return;
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("someerror");
				}
				System.out.println(refID+"test7");
				request.setAttribute("bookingID", bookingID);
				request.setAttribute("add", false);	
				request.getRequestDispatcher("/ManageBooking?bookingRef=");	
				rd.forward(request, response);	
				System.out.println(refID+"test8");
				return;
			}
		} else {
			System.out.println("management");
			try {
				String bookingRef =null;
				conn = DatabaseTool.getConnection();
				if(request.getParameterMap().containsKey("bookingRef")){
				bookingRef = request.getParameter("bookingRef");
				}else{
					bookingRef = (String) request.getAttribute("refID");
				}
				String select = "select * from bookings where referenceID = ?;";
				PreparedStatement ps = conn.prepareStatement(select);
				ps.setString(1, bookingRef);
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
					c.cost = p.calculatePrice(c.getRoomType(), c.getLocation(), c.getCheckin(), c.getCheckout(), c.getNumRooms(), c.getExtraBed());
					totalCost += c.cost;
				}
				DatabaseTool.endConnection(conn);
				request.setAttribute("referenceID", bookingRef);
				request.setAttribute("cost", totalCost);
				request.setAttribute("cart", userCart);
				if(request.getAttribute("add") != null){
					boolean add = (boolean)request.getAttribute("add");
					int bookingID = (int) request.getAttribute("bookingID");
					request.setAttribute("bookingID",bookingID);
					request.setAttribute("add",add);
					System.out.println("got back to management");
				}				
				rd = request.getRequestDispatcher("/manage.jsp");
				rd.forward(request,response);
				return;
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
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

}
