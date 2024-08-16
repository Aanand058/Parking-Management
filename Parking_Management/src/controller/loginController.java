package controller;

import java.io.IOException;

import Utility.AlertUtils;
import Utility.SceneUtils;
import database.DatabaseAccess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class loginController {

	@FXML
	private PasswordField passwordTF;

	@FXML
	private TextField userNameTF;

	private DatabaseAccess da;

	public loginController() {
		da = new DatabaseAccess();
	}

	@FXML
	void goBackBtnClicked(ActionEvent event) throws IOException {
		SceneUtils.setScene(event, "/view/Home.fxml");
	}

	@FXML
	void loginBtnClicked(ActionEvent event) throws IOException {
		System.out.print("Login Button Clicked \n");

		String username = userNameTF.getText().trim();
		String password = passwordTF.getText().trim();
		String adminString = "admin";

		if (da.validateLogin(username, password, adminString)) {
			System.out.println("Login successful");
			SceneUtils.setScene(event, "/view/adminPanel");
		} else {
			AlertUtils.showAlertError("Invalid Credentials!!!");
		}

	}

}
