package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Utility.SceneUtils;
import database.DatabaseAccess;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Guest;
import model.GuestAndVehicleInfo;
import model.Pass;
import model.Status;
import model.Vehicle;
import model.exportToCSV;

public class AdminController implements Initializable {

	@FXML
	private TableColumn<Vehicle, String> tableColMake;

	@FXML
	private TableColumn<Vehicle, String> tableColPlate;

	@FXML
	private TableColumn<Vehicle, Integer> tableColSN;

	@FXML
	private TableColumn<Vehicle, String> tableColVehicle;

	@FXML
	private TableColumn<Vehicle, String> tableColModel;

	@FXML
	private TableView<Vehicle> tableV;

	@FXML
	private TableColumn<Status, Long> statusCol;

	@FXML
	private TableView<Status> statusTV;

	private DatabaseAccess da;

	public AdminController() {
		da = new DatabaseAccess();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// Vehicle Table View Data
		tableColSN.setCellValueFactory(new PropertyValueFactory<>("vehicleId"));
		tableColVehicle.setCellValueFactory(new PropertyValueFactory<>("type"));
		tableColMake.setCellValueFactory(new PropertyValueFactory<>("make"));
		tableColModel.setCellValueFactory(new PropertyValueFactory<>("model"));
		tableColPlate.setCellValueFactory(new PropertyValueFactory<>("licensePlate"));

		// Load data from the database
		ObservableList<Vehicle> vehicles = da.loadVehicleDataFromDB();

		// Set the data in the TableView
		tableV.setItems(vehicles);

		// Pass Table View Data
		statusCol.setCellValueFactory(new PropertyValueFactory<>("isValid"));

		// Load Pass data from the database
		ObservableList<Status> passes = da.loadPassStatus();

		// Set the data in the Status TableView
		statusTV.setItems(passes);

	}

	@FXML
	void editGuestBtn(ActionEvent event) throws IOException {
		System.out.println("Edit Guest");
		SceneUtils.setScene(event, "/view/EditGuest.fxml");

	}

	@FXML
	void exitBtn(ActionEvent event) {
		Platform.exit();
	}

	@FXML
	void extractToCSV(ActionEvent event) {
		List<Guest> allGuests = da.getAllGuest();
		List<Vehicle> allVehicles = da.getAllVehicle();

		List<exportToCSV> exportList = new ArrayList<>();

		for (Guest guest : allGuests) {
			for (Vehicle vehicle : allVehicles) {
				if (guest.getId() == vehicle.getVehicleId()) {
					exportToCSV exportRecord = new exportToCSV(guest.getId(), guest.getName(), guest.getEmail(),
							Long.toString(guest.getPhone()), // Assuming phone is long, convert to String
							guest.getAddress(), vehicle.getType(), vehicle.getMake(), vehicle.getModel(),
							vehicle.getColor(), vehicle.getLicensePlate());
					exportList.add(exportRecord);
				}
			}
		}

		// Export to CSV
		String fileName = "guest_vehicle_info_quotes.csv";
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
			// Write CSV header
			writer.write("ID,Name,Email,Phone,Address,Type,Make,Model,Color,License Plate");
			writer.newLine();

			// Write data rows
			for (exportToCSV record : exportList) {
				writer.write(record.getID() + "," + wrapInQuotes(record.getName()) + ","
						+ wrapInQuotes(record.getEmail()) + "," + wrapInQuotes(record.getPhone()) + ","
						+ wrapInQuotes(record.getAddress()) + "," + wrapInQuotes(record.getType()) + ","
						+ wrapInQuotes(record.getMake()) + "," + wrapInQuotes(record.getModel()) + ","
						+ wrapInQuotes(record.getColor()) + "," + wrapInQuotes(record.getLicensePlate()));
				writer.newLine();
			}

			System.out.println("Data exported to " + fileName + " successfully.");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed to export data to CSV.");
		}

	}

	//Wrap in Quotes
	private String wrapInQuotes(String value) {
		if (value.contains(",")) {
			return "\"" + value + "\"";
		}
		return value;
	}

}
