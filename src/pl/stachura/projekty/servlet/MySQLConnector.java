package pl.stachura.projekty.servlet;


import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.xml.bind.JAXBContext;


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
    	File file = new File("test1.xml");
    	JAXBContext jaxbContext = JAXBContext.newInstance(classesToBeBound)
	}
}
