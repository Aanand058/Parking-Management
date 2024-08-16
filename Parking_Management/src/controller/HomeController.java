package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.io.IOException;

import Utility.SceneUtils;
import application.Main;
import javafx.scene.Node;
import javafx.stage.Stage;

public class HomeController {

	@FXML
	private Button adminBtn;


	@FXML
	private Button parkAsGuestBtn;

	// Admin Panel
	@FXML
	void adminBtn(ActionEvent event) throws IOException {
		System.out.print("Admin Panel Button Clicked\n");
		
		//SceneUtils.setScene(event, "/view/Login.fxml");
		SceneUtils.setScene(event, "/view/AdminPanel.fxml");
	}


	// Park As Guest
	@FXML
	void parkAsGuestBtn(ActionEvent event) throws IOException {
		System.out.print("Park as Guest Button Clicked\n");
		SceneUtils.setScene(event, "/view/GuestParking.fxml");

	}

	

}
