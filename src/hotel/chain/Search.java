package hotel.chain;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UserBean;
import beans.RoomBean;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String sqlSearch = "select * from rooms inner join bookings on bookings.id != ?;";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//UserBean user = (UserBean) request.getSession().getAttribute("userBean");
		ArrayList<RoomDTO> searchResults = new ArrayList<RoomDTO>();
		String price = request.getParameter("price");
		if(price == "") {
			price = Integer.toString(Integer.MAX_VALUE);
		}
		
		try {
			Connection conn = DatabaseTool.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlSearch);
			/*ps.setString(1, request.getParameter("checkin"));
			ps.setString(2, request.getParameter("checkout"));
			ps.setString(3, request.getParameter("city"));
			ps.setString(4, request.getParameter("numberofrooms"));
			ps.setString(5, price);*/
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				RoomDTO room = new RoomDTO();
				room.setRoomId(rs.getInt("id"));
				room.setRoomPrice(rs.getInt("price"));
				room.setHotelLocation(rs.getString("location"));
				room.setRoomType(rs.getInt("roomType"));
								
				searchResults.add(room);
			}	
			
			request.setAttribute("results", searchResults);
			
			DatabaseTool.endConnection(conn);
		} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/Results.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
