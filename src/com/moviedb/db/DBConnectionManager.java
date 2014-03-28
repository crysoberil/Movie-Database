package com.moviedb.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {

	private static Connection connection = null;

	public static Connection getConnection() {

		if (connection == null) {
			try {
//				System.out.println("Initiating");
				Class.forName("oracle.jdbc.OracleDriver");
				connection = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521:main", "crysoberil",
						"clrscr");

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return connection;

	}

}
