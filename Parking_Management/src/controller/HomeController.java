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
	private Button becomeMemberBtn;

	@FXML
	private Button membershipBtn;

	@FXML
	private Button parkAsGuestBtn;

	// Admin Panel
	@FXML
	void adminBtn(ActionEvent event) {
		// setScene(event, "/views/Login.fxml");
		System.out.print("Admin Panel Button Clicked\n");
	}

	// Become a Member
	@FXML
	void becomeMemberBtn(ActionEvent event) {
		System.out.print("Become a Member Button Clicked\n");
	}

	// Membership Holders
	@FXML
	void membershipBtn(ActionEvent event) {
		System.out.print("Memebership Holders Button Clicked\n");

	}

	// Park As Guest
	@FXML
	void parkAsGuestBtn(ActionEvent event) throws IOException {
		System.out.print("Park as Guest Button Clicked\n");
		//SceneUtils.setScene(event, "/view/GuestParking.fxml");
		
		SceneUtils.setScene(event, "/view/Payment.fxml");

	}

	

}
