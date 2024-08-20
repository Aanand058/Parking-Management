package controller;

import java.net.URL;
import java.util.ResourceBundle;

import database.DatabaseAccess;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Pass;
import model.Status;
import model.Vehicle;

public class AdminController implements Initializable{

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
    
    
    
    

}
