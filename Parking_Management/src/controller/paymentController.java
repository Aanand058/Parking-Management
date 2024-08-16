package controller;

import java.net.URL;
import java.util.ResourceBundle;

import Utility.AlertUtils;
import database.DatabaseAccess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import model.Card;

public class paymentController implements Initializable {

	@FXML
	private ToggleGroup card;

	@FXML
	private TextField cardNumberTF;

	@FXML
	private Label chargedLabel;

	@FXML
	private Button completeTBtn;

	@FXML
	private RadioButton creditCardRB;

	@FXML
	private TextField cvvTF;

	@FXML
	private RadioButton debitCardRB;

	@FXML
	private ChoiceBox<Integer> monthCB;

	@FXML
	private TextField nameTF;

	@FXML
	private ChoiceBox<Integer> yearCB;
	
	
	@FXML
	void CompleteTransBtnClicked(ActionEvent event) {

		RadioButton selectedPaymentMethod = (RadioButton) card.getSelectedToggle();

		if (selectedPaymentMethod == null) {
			AlertUtils.showAlertError("Invalid Selection");
			return;
		}

		if (cardNumberTF.getText().isEmpty() || cvvTF.getText().isEmpty() || nameTF.getText().isEmpty()
				|| monthCB.getValue() == null || yearCB.getValue() == null) {
			AlertUtils.showAlertError("Error: All fields are required.");
			return;
		}

		String cardType = selectedPaymentMethod.getText();
		String cardNumber = cardNumberTF.getText();
		int cvv = Integer.parseInt(cvvTF.getText());
		String name = nameTF.getText();
		int expiryMonth = monthCB.getValue();
		int expiryYear = yearCB.getValue();

		//Card Number Formatter
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < cardNumber.length(); i++) {
			if (i % 4 == 0 && i != 0) {
				result.append(" ");
			}

			result.append(cardNumber.charAt(i));
		}

		System.out.println(result.toString());
		System.out.print("\n");

		//Card Obj
		Card card = new Card(cardType, result.toString(), expiryMonth, expiryYear, cvv, name);
		
		// Not a good practice to store card details in DB 
		
		
		// Show Payment Confirmed Please Park
		AlertUtils.showAlertConfirmation("Thank you for purchasing the Pass.");
		

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// Populate monthCB with values from 1 to 12
		for (int i = 1; i <= 12; i++) {
			monthCB.getItems().add(i);
		}

		// Populate yearCB with the next 20 years starting from the current year
		int currentYear = java.time.Year.now().getValue();
		for (int i = currentYear; i <= currentYear + 20; i++) {
			yearCB.getItems().add(i);
		}

		// Restrict cardNumberTF to only accept numeric values and limit to 16 digits
		cardNumberTF.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\d*") || newValue.length() > 16) {
				// If the new value is not numeric or exceeds 16 digits, revert to the old value
				cardNumberTF.setText(oldValue);
			}
		});

		// Restrict cvvTF to only accept numeric values and limit to 3 digits
		cvvTF.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\d*") || newValue.length() > 3) {
				cvvTF.setText(oldValue);
			}
		});
	}

}
