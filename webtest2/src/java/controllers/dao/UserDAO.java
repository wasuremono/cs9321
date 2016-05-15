/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.dao;
import controllers.DBConn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

/**
 *
 * @author williamding
 */
public class UserDAO {
    
    public DBConn dbconn;
    
    private String QUERY_FIND_USER = "SELECT * from users WHERE username = ? AND password=? AND usertype= ?";
    
    public UserDAO(DBConn dbconn) {
        this.dbconn = dbconn;
    }

    public UserDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    
    public boolean createUser(String username, String password, String firsname, String lastname, String nickname) {
        return false;
    }
    
    public User findUser(String username, String password , String usertype) {
        try {
            ResultSet rs = this.dbconn.executeQuery(QUERY_FIND_USER);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public User insertUser(User user ){
        
         String sql = "INSERT INTO users (username,password,firstName,lastName,nickName,address,email,cardNumber,cardName,cardExpireDate,cvc,)VALUES('"
                     + user.getUsername()
                     + "','"
                     + user.getPassword()
                     + "','"
                     + user.getFirstName()
                     + "','"
                     + user.getLastName()
                     + "','"
                     + user.getNickName()
                     + "','"
                     + user.getAddress()
                     + "','"
                     + user.getEmail()
                     + "','"
                     + user.getCardNumber()
                     + "','"
                     + user.getCardName()
                     + "','"
                     + user.getCardExpireDate()
                     + "','"
                     + user.getCvc()
                     + "')";
      try {
            ResultSet rs = this.dbconn.executeQuery(sql);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
      return null;
    }
    
    public User deleteUser(User user){
        String sql =" DELETE FROM User WHERE username = '" + user.getUsername()+"AND password = '"+user.getPassword()+"'";
        try {
            ResultSet rs = this.dbconn.executeQuery(sql);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } 
        
    public User updateUser(User user){
         String sql = "UPDATE  userTable SET user_name= '"
                     + user.getUsername() + "', user_password= '"
                     + user.getPassword() + "', firstName= '"
                     + user.getFirstName() + "', lastName= '"
                     + user.getLastName() + "', nickName= '" 
                     + user.getNickName()+ "', address= '" 
                     + user.getAddress()+ "', email= '"
                     + user.getEmail()+ "' cardNumber= "
                     + user.getCardName()+"' cardNumber= " 
                     + user.getCardNumber()+"' cardExpireDate= " 
                     + user.getCardExpireDate()+"' cvc= " 
                     + user.getCvc();
         try {
            ResultSet rs = this.dbconn.executeQuery(sql);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }  
         return null;
    }  
    public User selectOneUserInfo(String name, String password){
         String sql = "SELECT * FROM user WHERE name = " + name+"AND password ="+password;
         try {
            ResultSet rs = this.dbconn.executeQuery(sql);
            User user = new User();
           while (rs.next()) {
                 user.setUsername(rs.getString("username"));
                 user.setPassword(rs.getString("password"));
                 user.setFirstName(rs.getString("firstName"));
                 user.setLastName(rs.getString("lastName"));
                 user.setNickName(rs.getString("nickName"));
                 user.setEmail(rs.getString("email"));
                 user.setAddress(rs.getString("address"));
                 user.setCardNumber(rs.getString("CardNumber"));
                 user.setCardName(rs.getString("CardName"));
                 user.setCardExpireDate(rs.getNString("CardExpireDate"));
                 user.setCvc(rs.getString("cvc"));
                   
             }
             return user;
   
             } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
             }  
         return null;
    } 
}

