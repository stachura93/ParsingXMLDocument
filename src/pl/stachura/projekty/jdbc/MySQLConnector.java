package pl.stachura.projekty.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class represents connection from database (MYSQL)
 * Singleton design pattern
 * 
 * @author Stachura Bartlomiej
 */
public class MySQLConnector {
	private static MySQLConnector instance = new MySQLConnector();
	///////////////////////////////////////////////////////////////////////
	public static final String URL = "jdbc:mysql://localhost:3306/Test";
	public static final String USER = "root";
	public static final String PASSWORD = "";
	///////////////////////////////////////////////////////////////////////
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
}
