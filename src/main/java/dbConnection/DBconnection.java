package dbConnection;

import java.sql.*;

public class DBconnection {

	public static Connection getConnection() {

		Connection con = null;

		final String URL = "jdbc:postgresql://localhost:5432/ToDoList";
		final String USERNAME = "postgres";
		final String PASSWORD = "200903";

		try {
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return con;
	}
	
	public static void closeConnection(Connection con, PreparedStatement prStmt, ResultSet res) {
		try {
			if(res != null) res.close();
			if(prStmt != null) prStmt.close();
			if(con != null) con.close();

		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
}
