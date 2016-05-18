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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ModifyVerification
 */
@WebServlet("/ModifyVerification")
public class ModifyVerification extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ModifyVerification() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String ref = request.getParameter("refID");
		String pin = request.getParameter("pinNum");
		RequestDispatcher rd = request.getRequestDispatcher("");
		Connection conn;
		try {
			conn = DatabaseTool.getConnection();
			PreparedStatement pssel = conn.prepareStatement("SELECT * FROM bookings WHERE referenceID = ? AND pin = ?;");
			pssel.setString(1, ref);
			pssel.setString(2, pin);
			ResultSet rs = pssel.executeQuery();
			//PIN or reference wrong
			if(!rs.next()){
				rd = request.getRequestDispatcher("/error.jsp");
				rd.forward(request,response);
			}
			//forward to search
			else {
				rd = request.getRequestDispatcher("/modsearch.jsp");
				rd.forward(request,response);
			}
		}
		catch (Exception e) {
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
