package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Card;
import model.Guest;
import model.Pass;
import model.Vehicle;

public class DatabaseAccess {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/Parking";
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "root";

	// Guest Table
	private static final String GUEST_TABLE = "CREATE TABLE IF NOT EXISTS Guest (id INT PRIMARY KEY, name VARCHAR(50), email VARCHAR(50), phone VARCHAR(50), address VARCHAR(100))";
	private static final String GUEST_QRY = "INSERT INTO Guest (id, name, email, phone, address) VALUES (?, ?, ?, ?, ?)";
	
	//Vehicle Table 
	private static final String VEHICLE_TABLE = "CREATE TABLE IF NOT EXISTS Vehicle (vehicleId INT PRIMARY KEY, type VARCHAR(50), make VARCHAR(50), model VARCHAR(50), color VARCHAR(50), licensePlate VARCHAR(50))";
	private static final String VEHICLE_QRY = "INSERT INTO Vehicle (vehicleId, type, make, model, color, licensePlate) VALUES (?, ?, ?, ?, ?, ?)";
	
	//Pass Table 
	private static final String PASS_TABLE = "CREATE TABLE IF NOT EXISTS Pass (passId INT PRIMARY KEY, startDateTime DATETIME, validTill DATETIME, hours INT, tax DOUBLE, subTotal DOUBLE, total DOUBLE)";
	private static final String PASS_QRY = "INSERT INTO Pass (passid, startDateTime, validTill, hours, tax, subTotal, total) VALUES (?, ?, ?, ?, ?, ?, ?)";


	// Insert Guest into Database
	public boolean insertGuest(Guest g) throws SQLException {

		try {
			Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

			PreparedStatement ct = conn.prepareStatement(GUEST_TABLE);
			ct.executeUpdate();
			
			String phone = Long.toString(g.getPhone());
			
			PreparedStatement insertStatement = conn.prepareStatement(GUEST_QRY);
			insertStatement.setInt(1, g.getId());
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
	
	//Insert Vehicle into Database 
	public boolean insertVehicle(Vehicle v) throws SQLException{
		try {
			Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

			PreparedStatement ct = conn.prepareStatement(VEHICLE_TABLE);
			ct.executeUpdate();
			
			PreparedStatement insertStatement = conn.prepareStatement(VEHICLE_QRY);
			insertStatement.setInt(1, v.getVehicleId());
			insertStatement.setString(2, v.getType());
			insertStatement.setString(3, v.getMake());
			insertStatement.setString(4, v.getModel());
			insertStatement.setString(5, v.getColor());
			insertStatement.setString(6, v.getLicensePlate());
			insertStatement.executeUpdate();

			System.out.println("Vehicle inserted successfully!");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	
	//Insert Pass into Database 
	public boolean insertPass(Pass p) throws SQLException{
		try {
			Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

			PreparedStatement ct = conn.prepareStatement(PASS_TABLE);
			ct.executeUpdate();
			
			PreparedStatement insertStatement = conn.prepareStatement(PASS_QRY);
			insertStatement.setInt(1, p.getId());
			insertStatement.setObject(2, p.getStartDateTime());
			insertStatement.setObject(3, p.getValidTill());
			insertStatement.setInt(4, p.getHours());
			insertStatement.setDouble(5, p.getTax());
			insertStatement.setDouble(6, p.getSubTotal());
			insertStatement.setDouble(7, p.getTotal());
			insertStatement.executeUpdate();

			System.out.println("Pass inserted successfully!");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean insertCard(Card card) throws SQLException {
		
		
		
		
		
		
		
		
		return false;
	}

}
