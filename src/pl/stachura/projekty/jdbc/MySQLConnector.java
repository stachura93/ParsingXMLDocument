package pl.stachura.projekty.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class MySQLConnector {
    private static MySQLConnector instance = new MySQLConnector();
    public static final String URL = "jdbc:mysql://localhost:3306/Test";
    public static final String USER = "root";
    public static final String PASSWORD = "";
    public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver"; 
     
    private MySQLConnector() {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
     
    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("ERROR: Unable to Connect to Database.");
        }
        return connection;
    }   
     
    public static Connection getConnection() {
        return instance.createConnection();
    }
    
    
    public static void main(String[] args) {
    	String QUERY = "select name,surname,login from USER";
    	
    	//using try-with-resources to avoid closing resources (boiler plate code)
        try(Connection con = MySQLConnector.getConnection();
                Statement stmt = (Statement) con.createStatement();
                ResultSet rs = stmt.executeQuery(QUERY)) {  
             
            while(rs.next()){
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String login = rs.getString("login");
                System.out.println(name + ", " +surname+ ", " + login);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
}
