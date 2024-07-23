package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	 // Global variables for parking prices and tax rate
    public static final double PARKING_PRICE_PER_HOUR = 3.99; 
    public static final double PARKING_PRICE_PER_DAY = 20.0; 
    public static final double TAX_RATE = 0.13; 

	
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane  root = (AnchorPane )FXMLLoader.load(getClass().getResource("/view/Home.fxml"));
			Scene scene = new Scene(root,795,540);
			scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
