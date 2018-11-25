package com.pokemon.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	static Connection connection = null;

	public static Connection getConnection() {
		try {
			if (connection == null) {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pokemon", "root", "root");
			}
		} catch (Exception e) {
			System.out.println("Exception in Database Connection: " + e.getMessage());
		}
		return connection;
	}
}
