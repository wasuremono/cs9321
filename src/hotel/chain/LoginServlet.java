package hotel.chain;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserBean;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SecureRandom random = new SecureRandom();  
	private MailService mailer = new MailService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getParameter("action");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		System.out.println(username +" "+password);
		RequestDispatcher rd = request.getRequestDispatcher("");
		String nameTest = "Select COUNT(*) as isTaken from users where username =?;";
		String login = "Select id,firstname,lastname,nickname,email,address,cardnumber,cardname,cardexpire,cvc from users where username = ? AND password = ?;";
		ResultSet rs= null;
		if(action.equals("login")){
			try {
				Connection conn = DatabaseTool.getConnection();
				PreparedStatement ps = conn.prepareStatement(login);
				ps.setString(1, username);
				ps.setString(2, password);
				rs = ps.executeQuery();
				if(!rs.next()){
					System.out.println("No such user found");
					
				}else{
					HttpSession mySession = request.getSession();
					UserBean u = new UserBean(0);
					u.setFirstName(rs.getString("firstname"));
					u.setLastName(rs.getString("lastname"));
					u.setNickName(rs.getString("nickname"));
					u.setId(rs.getInt("id"));
					u.setAddress(rs.getString("address"));
					u.setEmail(rs.getString("email"));
					u.setCardName(rs.getString("cardname"));
					u.setCardNumber(rs.getString("cardnumber"));
					u.setCvc(rs.getString("cvc"));
					u.setCardExpire(rs.getString("cardexpire"));
					mySession.setAttribute("u", u);
					rd = request.getRequestDispatcher("/view.jsp");	
					
				}
				
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}if(action.equals("logout")){
			HttpSession mySession = request.getSession();
			mySession.invalidate();
		}if(action.equals("register")){
			Connection conn;
			try {
				conn = DatabaseTool.getConnection();
				PreparedStatement ps = conn.prepareStatement(nameTest);
				ps.setString(1, username);
				rs = ps.executeQuery();
				if(!rs.next()){
					
					
				} else {
					if(rs.getInt("isTaken") == 0){
						System.out.println("Name is available");
						String addpending = "Insert into mydb.userspending (`username`,`password`,`email`,`verification`) VALUES(?,?,?,?);";
						String URL = generateURL();	
						ps = conn.prepareStatement(addpending);
						ps.setString(1, username);
						ps.setString(2, password);
						ps.setString(3, email);
						ps.setString(4, URL);
						ps.executeUpdate();
						String subject = "Thank you for registering!";
						String messageBody = "<p>Your account been has been made, to verify your account please click the link below.</p>\n"+"<p>http://localhost:8080/Assign2/profile?verify="+URL;
						mailer.sendMail(email, subject, messageBody);
					}else{
						System.out.println("nametaken");
					}
				}
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		rd.forward(request,response);
		return;
	}
	private String generateURL(){
		return new BigInteger(130, random).toString(32);
	}

}
