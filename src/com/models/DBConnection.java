package com.models;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
	private static Connection connection = null;

	public static Connection getActiveConnection() {
		/*String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
		String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
		System.out.println(host);*/
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager
					.getConnection("jdbc:mysql://127.7.13.2:3306/firstapp?"
							+ "user=admintGr33ZH&password=C-P4hSHTa_ck&characterEncoding=utf8");
			/*connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/se2firstapp?"
						+ "user=root&password=sarah&characterEncoding=utf8");*/
			return connection;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
