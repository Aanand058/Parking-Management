package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Guest;

public class DatabaseAccess {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/Parking";
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "root";

	// Guest Table
	private static final String GUEST_TABLE = "CREATE TABLE IF NOT EXISTS Guest (id VARCHAR(50) PRIMARY KEY, name VARCHAR(50), email VARCHAR(50), phone VARCHAR(50), address VARCHAR(100))";
	private static final String GUEST_QRY = "INSERT INTO Guest (id, name, email, phone, address) VALUES (?, ?, ?, ?, ?)";

	// Insert Guest into Database
	public boolean insertGuest(Guest g) throws SQLException {

		try {
			Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

			PreparedStatement ct = conn.prepareStatement(GUEST_TABLE);
			ct.executeUpdate();
			
			String phone = Long.toString(g.getPhone());
			
			PreparedStatement insertStatement = conn.prepareStatement(GUEST_QRY);
			insertStatement.setString(1, g.getId());
			insertStatement.setString(2, g.getName());
			insertStatement.setString(3, g.getEmail());
			insertStatement.setString(4, phone);
			insertStatement.setString(5, g.getAddress());
			insertStatement.executeUpdate();

			System.out.println("Guest inserted successfully!");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

}
