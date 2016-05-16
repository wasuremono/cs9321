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
import javax.servlet.http.HttpSession;

import beans.UserBean;

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String verifyID = request.getParameter("verify");
		UserBean u = new UserBean(0);
		HttpSession mySession = request.getSession(false);
		String getVerify = "select * from userspending where verification = ?;";
		String insert = "Insert into users (username,password,email) select username,password,email from userspending where verification = ?;";
		String delete = "delete from userspending where verification = ?;";
		String getUser = "select id from users where username = ?;";
		RequestDispatcher rd = request.getRequestDispatcher("");
		ResultSet rs = null;
		try {
			Connection conn = DatabaseTool.getConnection();
			PreparedStatement ps = conn.prepareStatement(getVerify);
			ps.setString(1, verifyID);
			rs = ps.executeQuery();
			if(rs.next()){
				u.setUsername(rs.getString("username"));
				u.setEmail(rs.getString("email"));
				u.setPassword(rs.getString("password"));
			}
			ps = conn.prepareStatement(insert);
			ps.setString(1, verifyID);
			ps.executeUpdate();
			ps = conn.prepareStatement(delete);
			ps.setString(1, verifyID);
			ps.executeUpdate();
			ps = conn.prepareStatement(getUser);
			ps.setString(1, verifyID);
			rs = ps.executeQuery();
			if(rs.next()){
				u.setId(rs.getInt("id"));
			}
			mySession.setAttribute("u", u);
			rd = request.getRequestDispatcher("/editProfile.jsp");
			rd.forward(request, response);
			return;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		HttpSession mySession = request.getSession(false);
		UserBean u = (UserBean) mySession.getAttribute("u");
		RequestDispatcher rd = request.getRequestDispatcher("");
		String update = "update users set firstname = ?,lastname = ?, nickname = ?, email = ?, cardNumber = ?, cardExpire = ?, cardName = ?,cvc =?, password = ?,address = ? where id = ?;"; 
		if(action.equals("update")){
			u.setFirstName(request.getParameter("firstName"));
			u.setLastName(request.getParameter("lastName"));
			u.setNickName(request.getParameter("nickname"));
			u.setAddress(request.getParameter("address"));
			u.setEmail(request.getParameter("email"));
			u.setCardNumber(request.getParameter("cardNumber"));
			u.setCardName(request.getParameter("cardName"));
			u.setCardExpire(request.getParameter("cardExpire"));
			u.setCvc(request.getParameter("cvc"));
			if(request.getParameter("password").equals(request.getParameter("password2")) && !request.getParameter("password").equals("")){
				u.setPassword(request.getParameter("password"));
			}
			Connection conn;
			try {
				conn = DatabaseTool.getConnection();
				PreparedStatement ps = conn.prepareStatement(update);
				ps.setString(1, u.getFirstName());
				ps.setString(2, u.getLastName());
				ps.setString(3, u.getNickName());
				ps.setString(4, u.getEmail());
				ps.setString(5, u.getCardNumber());
				ps.setString(6, u.getCardExpire());
				ps.setString(7, u.getCardName());
				ps.setString(8, u.getCvc());
				ps.setString(9, u.getPassword());
				ps.setString(10, u.getAddress());
				ps.setInt(11, u.getId());
				ps.executeUpdate();
				mySession.setAttribute("u", u);
				rd = request.getRequestDispatcher("/view.jsp");
				rd.forward(request, response);
				return;
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		doGet(request, response);
	}

}
