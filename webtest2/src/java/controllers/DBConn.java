package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author williamding
 */
public class DBConn {
    private static String dbURL = "jdbc:derby://localhost:1527/mydb;user=comp9321;password=comp9321";
    private static Connection conn = null;
    private static Statement stmt = null;
    
    public DBConn()
    { }
    
    private static void createConnection()
    {
        try
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            conn = DriverManager.getConnection(dbURL); 
        }
        catch (Exception except)
        {
            except.printStackTrace();
        }
    }
    
    public ResultSet executeQuery(String query) throws SQLException {
        createConnection();
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select name from student");
        conn.close();
        return rs;
    }
    
}
