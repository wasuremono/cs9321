package hotel.chain;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn = null;
	Vector<SearchResult> searchResult = new Vector<SearchResult>();	
	ResultSet rs = null;
	
    public enum cities{
		Sydney,
    	Brisbane,
    	Melbourne,
    	Darwin,
    	Adelaide,
    	Hobart,
    	Perth,
    	Canberra
    };  
    public enum RoomType{
		Single,
    	Twin,
    	Queen,
    	Executive,
    	Suite
    };
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = request.getRequestDispatcher("");
		String action = request.getParameter("action");			
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");	
		if(request.getParameterMap().containsKey("action")){
			if(action.equals("searchRoom")){
					searchResult.clear();
					System.out.println("Doing specific search");				
					java.util.Date checkin = null;
					java.util.Date checkout = null;
					String checkinString = request.getParameter("checkin");	
					System.out.println(action + "test");
					String checkoutString = request.getParameter("checkout");
					try {
						 checkin = dateFormat.parse(checkinString);
						 checkout = dateFormat.parse(checkoutString);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String searchRoom = "SELECT *,COUNT(*) as numRooms from rooms where location = ? and roomType = ?;";
					String location = request.getParameter("location");
					int maxprice = 0;
					if(!request.getParameter("maxprice").equals("")){
						maxprice = Integer.parseInt(request.getParameter("maxprice")); 
					}
					for(RoomType rt:RoomType.values()){
						try {
							conn = DatabaseTool.getConnection();
							PreparedStatement ps = conn.prepareStatement(searchRoom);
							ps.setString(1, location);
							ps.setString(2, rt.toString());
							rs = ps.executeQuery();
							while(rs.next()){
								if(rs.getInt("numRooms") !=0){
									SearchResult r = new SearchResult();
									Calculator p =  new Calculator();
									r.parseResultSet(rs);
									r.setNumRooms(rs.getInt("numRooms"));
									r.setCheckin(checkinString);
									r.setCheckout(checkoutString);
									int stayDuration = (int)(checkout.getTime() - checkin.getTime())/(1000*60*60*24);
									double avgCost = p.calculatePrice(r.getRoomType(), location, checkin, checkout, 1, 0);
									avgCost = avgCost/stayDuration;
									if(avgCost < maxprice || maxprice ==0){
										searchResult.add(r);
									}
								}
							}
						} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					System.out.println(searchResult.size());
				    request.setAttribute("searchResult", searchResult);
				    rd = request.getRequestDispatcher("/home.jsp");
				    rd.forward(request,response);
				    return;
				} else {
					
					   request.setAttribute("searchResult", searchResult);
					   rd = request.getRequestDispatcher("/home.jsp");
					   rd.forward(request,response);
					   return;
				}
			}else{	
				searchResult.clear();
				String getRooms = "select * from rooms where location = ? order by rand() LIMIT 1;";
				System.out.println("Initial random search");
				for(cities c:cities.values()){
				   	try {
						conn = DatabaseTool.getConnection();
						PreparedStatement ps = conn.prepareStatement(getRooms);
						ps.setString(1, c.toString());
						rs = ps.executeQuery();
						if(rs.next()){
							SearchResult r = new SearchResult();
							System.out.println(rs.getString("location"));
							r.parseResultSet(rs);
							searchResult.add(r);
						}
					} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				   }
				   request.setAttribute("searchResult", searchResult);		
				   rd = request.getRequestDispatcher("/home.jsp");
				   rd.forward(request,response);
				   return;
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
