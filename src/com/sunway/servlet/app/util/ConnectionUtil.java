package com.sunway.servlet.app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	private static Connection connection;

	public static Connection getConnection() throws ClassNotFoundException, SQLException  {
		if (connection == null) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunway-servlet-app", "root", "mysql");
		}
		return connection;
	}

	public static void closeConnection() throws SQLException {
		if (connection != null) {
			connection.close();
			connection = null;
		}
	}
}
