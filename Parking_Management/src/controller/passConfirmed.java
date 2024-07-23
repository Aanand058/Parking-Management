package controller;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class passConfirmed {

    @FXML
    void exitBtn(ActionEvent event) {
    	Platform.exit();
    }

}
