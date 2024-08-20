package controller;

import Utility.AlertUtils;
import database.DatabaseAccess;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.GuestAndVehicleInfo;

public class editGuestController {

	@FXML
	private TextField searchTF;

	@FXML
	private TextField addressTF;

	@FXML
	private TextField colorTF;

	@FXML
	private TextField emailTF;

	@FXML
	private TextField licensePlateTF;

	@FXML
	private TextField makeTF;

	@FXML
	private TextField modelTF;

	@FXML
	private TextField nameTF;

	@FXML
	private TextField phoneTF;

	@FXML
	private TextField typeTF;

	private DatabaseAccess da;

	public editGuestController() {
		da = new DatabaseAccess();
	}

	@FXML
	void exitBtn(ActionEvent event) {
		Platform.exit();
	}

	@FXML
	void saveBtn(ActionEvent event) {
		// Validate that all required fields are filled
		if (nameTF.getText().isEmpty() || emailTF.getText().isEmpty() || phoneTF.getText().isEmpty()
				|| addressTF.getText().isEmpty() || typeTF.getText().isEmpty() || makeTF.getText().isEmpty()
				|| modelTF.getText().isEmpty() || colorTF.getText().isEmpty() || licensePlateTF.getText().isEmpty()) {

			AlertUtils.showAlertError("Please fill all fields before saving.");
			return;
		}

		// Gather data from the text fields
		String name = nameTF.getText().trim();
		String email = emailTF.getText().trim();
		String phone = phoneTF.getText().trim();
		String address = addressTF.getText().trim();
		String type = typeTF.getText().trim();
		String make = makeTF.getText().trim();
		String model = modelTF.getText().trim();
		String color = colorTF.getText().trim();
		String licensePlate = licensePlateTF.getText().trim();

		// Assuming GuestAndVehicleInfo includes an ID or search field to identify the
		// record in the database
		String searchString = searchTF.getText().trim();

		// Create a GuestAndVehicleInfo object with the updated data
		GuestAndVehicleInfo updatedInfo = new GuestAndVehicleInfo(name, email, phone, address, type, make, model, color,
				licensePlate);

		// Attempt to update the record in the database
		boolean success = da.updateGuestAndVehicleInfo(searchString, updatedInfo);

		// Provide feedback to the user
		if (success) {
			AlertUtils.showAlertConfirmation("Updated:");
		} else {
			AlertUtils.showAlertError("Failed to update details. Please try again.");
		}

	}

	@FXML
	void searchBtn(ActionEvent event) {

		if (searchTF.getText().isEmpty()) {
			AlertUtils.showAlertError("Please enter a valid Value");
		}

		String searchString = searchTF.getText().trim();

		GuestAndVehicleInfo info = da.searchGuest(searchString); // This could be an email, phone, or ID

		if (info != null) {
			nameTF.setText(info.getName());
			emailTF.setText(info.getEmail());
			phoneTF.setText(info.getPhone());
			addressTF.setText(info.getAddress());
			typeTF.setText(info.getType());
			makeTF.setText(info.getMake());
			modelTF.setText(info.getModel());
			colorTF.setText(info.getColor());
			licensePlateTF.setText(info.getLicensePlate());
		} else {
			clearFields();
			System.out.println("Guest not found.");
			AlertUtils.showAlertError("Guest not found.");
		}

	}

	private void clearFields() {
		nameTF.clear();
		emailTF.clear();
		phoneTF.clear();
		addressTF.clear();
		typeTF.clear();
		makeTF.clear();
		modelTF.clear();
		colorTF.clear();
		licensePlateTF.clear();
	}

}
