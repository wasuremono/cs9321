package hotel.chain;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class UserRegister
 */
@WebServlet(description = "UserRegister", urlPatterns = { "/UserRegister" })
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("userName");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String nickName = request.getParameter("nickName");
        String cardNumber =request.getParameter("cardNumber");
        String cardName =request.getParameter("cardName");
        String cardExpireDate= request.getParameter("cardExpireDate");
        String cvc= request.getParameter("cvc");
        
        try {
        	String sqlCmd = "INSERT INTO users(username, password, firstName, lastName, nickName, address, email, cardNumber, cardName, cardExpire, cvc, userType) VALUES(?,?,?,?, ?,?,?,?, ?,?,?,?)";
            DatabaseTool db = new DatabaseTool();
			PreparedStatement preparedStatement = db.getConnection().prepareStatement(sqlCmd);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, firstName);
			preparedStatement.setString(4, lastName);
			preparedStatement.setString(5, email);
			preparedStatement.setString(6, address);
			preparedStatement.setString(7, nickName);
			preparedStatement.setString(8, cardNumber);
			preparedStatement.setString(9, cardName);
			preparedStatement.setString(10, cardExpireDate);
			preparedStatement.setString(11, cvc);
			preparedStatement.setInt(12, 1);
			preparedStatement.executeUpdate();
			
			
			response.setContentType("text/html;charset=UTF-8");
	        try (PrintWriter out = response.getWriter()) {
	            out.println("<!DOCTYPE html>");
	            out.println("<html>");
	            out.println("<head>");
	            out.println("<title>Sign up</title>");            
	            out.println("</head>");
	            out.println("<body>");
	            out.println("<h1>SignUp Successful: </h1>");
	            out.println("<p>Please go to Login Page</p>");
	            out.println("</body>");
	            out.println("</html>");
	        }
        } catch (Exception e) {
			System.err.println(e.getMessage());
			response.setContentType("text/html;charset=UTF-8");
	        try (PrintWriter out = response.getWriter()) {
	            out.println("<!DOCTYPE html>");
	            out.println("<html>");
	            out.println("<head>");
	            out.println("<title>Sign up error</title>");            
	            out.println("</head>");
	            out.println("<body>");
	            out.println("<h1>SignUp Error: </h1>");
	            out.println("<p>" + e.getMessage() + "</p>");
	            out.println("</body>");
	            out.println("</html>");
	        }
		}
        
        
	}

}
