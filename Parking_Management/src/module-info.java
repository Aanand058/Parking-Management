module Parking_Management {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires java.sql;
	
	opens application to javafx.graphics, javafx.fxml;
	opens controller to javafx.graphics, javafx.fxml;
	opens model to javafx.base; 
	 exports model;
}
