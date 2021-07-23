package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddProduceSceneController {

	@FXML
	Button goBackButton;
	@FXML
	TextField produceID;
	@FXML
	TextField produceName;
	@FXML
	TextField produceQuantity;
	@FXML
	TextField produceValue;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	static Functions access = new Functions();
	
	
	// Establish a connection to the database
		public static Connection getConnection() throws Exception {
			return access.getConnection();
		}
		
		public void addToInventory(ActionEvent event) throws IOException {
			
			access.addToInventory(event, produceID, produceName, produceQuantity, produceValue, "produce");
			
		}
	
	public void goBack (ActionEvent event) throws IOException {
		access.goBack(event, "ProduceSceneMain.fxml");
		
	}
	
	
	
}
