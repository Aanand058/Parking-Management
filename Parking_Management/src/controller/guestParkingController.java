package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Utility.AlertUtils;
import Utility.SceneUtils;
import application.Main;
import database.DatabaseAccess;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Guest;
import model.Pass;
import model.Vehicle;

public class guestParkingController implements Initializable {

	@FXML
	private TextField addressTF;

	@FXML
	private Label dateLabel;

	@FXML
	private TextField emailTF;

	@FXML
	private TextField hoursTF;

	@FXML
	private TextField nameTF;

	@FXML
	private Button paymentBtn;

	@FXML
	private TextField phoneTF;

	@FXML
	private TextArea rateTA;

	@FXML
	private Label subtotalTF;

	@FXML
	private Label taxTF;

	@FXML
	private Label totalTF;

	@FXML
	private TextField vColorTF;

	@FXML
	private TextField vMakeTF;

	@FXML
	private TextField vModelTF;

	@FXML
	private TextField vPlateTF;

	@FXML
	private ComboBox<String> vType;
	
	
	private DatabaseAccess da;

	public guestParkingController() {
		da = new DatabaseAccess();
	}

	@FXML
	void paymentBtn(ActionEvent event) throws SQLException, IOException {

		if (nameTF.getText().isEmpty() || emailTF.getText().isEmpty() || phoneTF.getText().isEmpty()
				|| vMakeTF.getText().isEmpty() || vColorTF.getText().isEmpty() || vModelTF.getText().isEmpty()
				|| vPlateTF.getText().isEmpty() || vType.getSelectionModel().getSelectedItem() == null
				|| hoursTF.getText().isEmpty()) {

			AlertUtils.showAlertError("Please Enter all the values");
		} else {

			String name = nameTF.getText();
			String email = emailTF.getText();

			String address = addressTF.getText();
			String vehicleMake = vMakeTF.getText();
			String vehicleModel = vModelTF.getText();
			String vehiclePlate = vPlateTF.getText();
			String vehicleColor = vColorTF.getText();
			String vehicleTypeSelected = this.vType.getSelectionModel().getSelectedItem();
			
			int hours = Integer.parseInt(hoursTF.getText());
			Long phone = Long.parseLong(phoneTF.getText());
			
			
			boolean isValid = isValidEmail(email);

			if (isValid) {
				
				//Common ID
				int id= generateVehicleId();
				
				// Guest
				Guest guest = new Guest(id, name, email, phone, address);
				//DB
				boolean g = da.insertGuest(guest);
				
				
				//Vehicle 
				Vehicle vehicle = new Vehicle(id, vehicleTypeSelected, vehicleMake,vehicleModel,vehicleColor,vehiclePlate.toUpperCase());
				//DB
				boolean v = da.insertVehicle(vehicle);
				
				//Price Calculation Per Hour
				double subtotal = hours * Main.PARKING_PRICE_PER_HOUR;
				double tax = subtotal * Main.TAX_RATE;
				double total = subtotal + tax;
				
				LocalDateTime startDateTime = LocalDateTime.now();
				//Pass
				Pass pass = new Pass(id, startDateTime, hours,subtotal, tax,total);
				//DB
				boolean p = da.insertPass(pass);
				
				//TODO: Payment Gateway 
				
				//Pass Confirmed Screen 
				SceneUtils.setScene(event, "/view/PassConfirmed.fxml");
				
			}

		}

	}
	
	//5-Digit Number for Vehicle ID
	  public static int generateVehicleId() {
	        Random random = new Random();
	        return 10000 + random.nextInt(90000);
	    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Vehicle Choices
		vType.setItems(FXCollections.observableArrayList("Car", "Bike", "Van", "Truck"));

		// Hours Validation
		hoursTF.setOnKeyTyped(e -> {
			String input = e.getCharacter();
			if (!input.matches("[0-9]")) {
				hoursTF.setText("");
			}
		});

		rateTA.setText("Parking rates: $3.99 per hour, $20 per day.");

	}

	
	//Valid REGEX for email
	public static boolean isValidEmail(String email) {
		// Regular expression for a valid email address
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

		Pattern pattern = Pattern.compile(emailRegex);
		Matcher matcher = pattern.matcher(email);

		if (matcher.matches()) {
			return true;
		} else {
			AlertUtils.showAlertError("Invalid Email!");
			return false;
		}
	}

}
