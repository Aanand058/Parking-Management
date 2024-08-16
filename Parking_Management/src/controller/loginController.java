package controller;

import java.io.IOException;

import Utility.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class loginController {

    @FXML
    private PasswordField passwordTF;

    @FXML
    private TextField userNameTF;

    @FXML
    void goBackBtnClicked(ActionEvent event) throws IOException {
    	//SceneUtils.setScene(event, "/view/Home.fxml");
    }

    @FXML
    void loginBtnClicked(ActionEvent event) {
    	System.out.print("Login Button Clicked \n");
    }

}
