package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Scene1Controller {
	
	@FXML
	Label inventoryLabel;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	private Stage stageA;
	private Scene sceneA;
	private Parent rootA;
	static Functions access = new Functions();
	
	public void viewMeat (ActionEvent event) throws IOException {
		access.viewCategory(event, "MeatSceneMain.fxml");
		//access.setLabel(label, "meat");
	}
	
	public void viewProduce (ActionEvent event) throws IOException {
		access.viewCategory(event, "ProduceSceneMain.fxml");
		//Scene2Controller scene2Controller = loader.getController();
		//scene2Controller.displayOccurrences(fileName);
	}
	
	public void viewDairy (ActionEvent event) throws IOException {
		access.viewCategory(event, "DairySceneMain.fxml");
		//Scene2Controller scene2Controller = loader.getController();
		//scene2Controller.displayOccurrences(fileName);
	}
	
	// Establish a connection to the database
	public static Connection getConnection() throws Exception {
		return access.getConnection();
	}
	
	public void initialize() throws Exception {
			access.setLabel(inventoryLabel, "items");
	}
		



}
