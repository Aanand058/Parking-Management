package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Card;
import model.Guest;
import model.GuestAndVehicleInfo;
import model.Pass;
import model.Status;
import model.Vehicle;

public class DatabaseAccess {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/Parking";
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "root";

	// Guest Table
	private static final String GUEST_TABLE = "CREATE TABLE IF NOT EXISTS Guest (id INT PRIMARY KEY, name VARCHAR(50), email VARCHAR(50), phone VARCHAR(50), address VARCHAR(100))";
	private static final String GUEST_QRY = "INSERT INTO Guest (id, name, email, phone, address) VALUES (?, ?, ?, ?, ?)";

	// Vehicle Table
	private static final String VEHICLE_TABLE = "CREATE TABLE IF NOT EXISTS Vehicle (vehicleId INT PRIMARY KEY, type VARCHAR(50), make VARCHAR(50), model VARCHAR(50), color VARCHAR(50), licensePlate VARCHAR(50))";
	private static final String VEHICLE_QRY = "INSERT INTO Vehicle (vehicleId, type, make, model, color, licensePlate) VALUES (?, ?, ?, ?, ?, ?)";

	// Pass Table
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

	// Insert Vehicle into Database
	public boolean insertVehicle(Vehicle v) throws SQLException {
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

	// Insert Pass into Database
	public boolean insertPass(Pass p) throws SQLException {
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

	// Login Function
	// Method to validate user credentials
	public boolean validateLogin(String username, String password, String position) {
		String EMP_TABLE = "SELECT * FROM employees WHERE username = ? AND password = ? AND position = ?";
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(EMP_TABLE)) {
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, position);
			ResultSet rs = stmt.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Vehicle Data
	public ObservableList<Vehicle> loadVehicleDataFromDB() {
		ObservableList<Vehicle> vehicles = FXCollections.observableArrayList();

		String query = "SELECT vehicleId, type, make, model, color, licensePlate FROM Vehicle";

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				int vehicleId = rs.getInt("vehicleId");
				String type = rs.getString("type");
				String make = rs.getString("make");
				String model = rs.getString("model");
				String color = rs.getString("color");
				String licensePlate = rs.getString("licensePlate");

				Vehicle vehicle = new Vehicle(vehicleId, type, make, model, color, licensePlate);
				vehicles.add(vehicle);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vehicles;
	}

	// Status Data (Pass)
	public ObservableList<Status> loadPassStatus() {
		ObservableList<Status> allStatus = FXCollections.observableArrayList();

		String quertyString = "SELECT passId, startDateTime, validTill FROM pass";

		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
				PreparedStatement stmt = connection.prepareStatement(quertyString);
				ResultSet rs = stmt.executeQuery(quertyString)) {

			while (rs.next()) {
				int passId = rs.getInt("passId");
				String startDateTime = rs.getString("startDateTime");
				String validTill = rs.getString("validTill");

				Status s = new Status(passId, startDateTime, validTill);
				allStatus.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return allStatus;

	}

	public GuestAndVehicleInfo searchGuest(String searchString) {
		GuestAndVehicleInfo guestVehicleInfo = null;

		String query = "SELECT g.name, g.email, g.phone, g.address, v.type, v.make, v.model, v.color, v.licensePlate "
				+ "FROM Guest g " + "JOIN Vehicle v ON g.id = v.vehicleId "
				+ "WHERE g.email = ? OR g.phone = ? OR g.id = ?";

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setString(1, searchString); // Set email
			stmt.setString(2, searchString); // Set phone
			stmt.setInt(3, parseAsInt(searchString));

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				String type = rs.getString("type");
				String make = rs.getString("make");
				String model = rs.getString("model");
				String color = rs.getString("color");
				String licensePlate = rs.getString("licensePlate");

				guestVehicleInfo = new GuestAndVehicleInfo(name, email, phone, address, type, make, model, color,
						licensePlate);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return guestVehicleInfo;
	}

	// Helper method to parse searchString as an integer
	private int parseAsInt(String searchString) {
		try {
			return Integer.parseInt(searchString);
		} catch (NumberFormatException e) {
			return -1; // Return an invalid ID if it cannot be parsed as an integer
		}
	}

	// UPDATE GUEST and VEHICLE DETAILS
	public boolean updateGuestAndVehicleInfo(String searchString, GuestAndVehicleInfo updatedInfo) {
		String updateGuestQuery = "UPDATE Guest SET name = ?, email = ?, phone = ?, address = ? "
				+ "WHERE email = ? OR phone = ? OR id = ?";
		String updateVehicleQuery = "UPDATE Vehicle SET type = ?, make = ?, model = ?, color = ?, licensePlate = ? "
				+ "WHERE vehicleId = (SELECT id FROM Guest WHERE email = ? OR phone = ? OR id = ?)";

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
			// Update Guest Information
			PreparedStatement guestStmt = conn.prepareStatement(updateGuestQuery);
			guestStmt.setString(1, updatedInfo.getName());
			guestStmt.setString(2, updatedInfo.getEmail());
			guestStmt.setString(3, updatedInfo.getPhone());
			guestStmt.setString(4, updatedInfo.getAddress());
			guestStmt.setString(5, searchString); // For email
			guestStmt.setString(6, searchString); // For phone

			// Handle id separately to avoid NumberFormatException
			try {
				int id = Integer.parseInt(searchString);
				guestStmt.setInt(7, id); // For id
			} catch (NumberFormatException e) {
				guestStmt.setNull(7, java.sql.Types.INTEGER); // If not an ID, set to null or handle accordingly
			}

			guestStmt.executeUpdate();

			// Update Vehicle Information
			PreparedStatement vehicleStmt = conn.prepareStatement(updateVehicleQuery);
			vehicleStmt.setString(1, updatedInfo.getType());
			vehicleStmt.setString(2, updatedInfo.getMake());
			vehicleStmt.setString(3, updatedInfo.getModel());
			vehicleStmt.setString(4, updatedInfo.getColor());
			vehicleStmt.setString(5, updatedInfo.getLicensePlate());
			vehicleStmt.setString(6, searchString); // For email
			vehicleStmt.setString(7, searchString); // For phone

			// Handle id separately to avoid NumberFormatException
			try {
				int id = Integer.parseInt(searchString);
				vehicleStmt.setInt(8, id); // For id
			} catch (NumberFormatException e) {
				vehicleStmt.setNull(8, java.sql.Types.INTEGER); // If not an ID, set to null or handle accordingly
			}

			vehicleStmt.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public List<Guest> getAllGuest() {
		List<Guest> guests = new ArrayList<>();
		String query = "SELECT id, name, email, phone, address FROM Guest";

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Long phone = rs.getLong("phone");
				String address = rs.getString("address");

				Guest guest = new Guest(id, name, email, phone, address);
				guests.add(guest);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return guests;
	}

	public List<Vehicle> getAllVehicle() {
		List<Vehicle> vehicles = new ArrayList<>();
		String query = "SELECT vehicleId, type, make, model, color, licensePlate FROM Vehicle";

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				int vehicleId = rs.getInt("vehicleId");
				String type = rs.getString("type");
				String make = rs.getString("make");
				String model = rs.getString("model");
				String color = rs.getString("color");
				String licensePlate = rs.getString("licensePlate");

				Vehicle vehicle = new Vehicle(vehicleId, type, make, model, color, licensePlate);
				vehicles.add(vehicle);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vehicles;
	}

}
