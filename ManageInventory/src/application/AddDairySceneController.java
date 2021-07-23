package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddDairySceneController {
	
	@FXML
	Button goBackButton;
	@FXML
	TextField dairyID;
	@FXML
	TextField dairyName;
	@FXML
	TextField dairyQuantity;
	@FXML
	TextField dairyValue;
	@FXML
	Label errorLabel;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	static Functions access = new Functions();
	
	
	// Establish a connection to the database
	public static Connection getConnection() throws Exception {
		
		return access.getConnection();
	}
	
	public void addToInventory(ActionEvent event) throws IOException {
		
		access.addToInventory(event, dairyID, dairyName, dairyQuantity, dairyValue, "dairy");

	}
	
	public void goBack (ActionEvent event) throws IOException {
		access.goBack(event, "DairySceneMain.fxml");

	}
	

	

	


}
