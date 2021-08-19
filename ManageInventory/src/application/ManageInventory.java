
package application;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ManageInventory extends Application {
	
	
	/**
	 * Method to set stage for word occurrences
	 */
	@Override
	public void start(Stage primaryStage) {
		
		// Try/Catch block to set and show initial scene
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			// Set app icon
			Image icon = new Image("invIcon.png");
			primaryStage.resizableProperty().set(false);
			primaryStage.setTitle("Inventory Management");
			primaryStage.getIcons().add(icon);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		
		createTables();
		launch(args);
		
		Scanner input = new Scanner(System.in);


			
	}
	
	
	// Establish a connection to the database
		public static Connection getConnection() throws Exception {
			Functions db = new Functions();
			return db.getConnection();
		}

		// Create table in database to store properties
		// if it doesn't already exist
		public static void createTables() throws Exception {
			
			try {
				Connection conn = getConnection();
				PreparedStatement create = conn.prepareStatement("CREATE TABLE IF NOT EXISTS items(category varchar(255), id varchar(255), name varchar(255), quantity int(255), value decimal(4,2), PRIMARY KEY(id))");
				create.executeUpdate();
			}catch(Exception e) {
			System.out.println(e);
			}
			connect("items", "category");
			connect("meat", "");
			connect("dairy", "");
			connect("produce", "");
			
		}
		
		public static void connect(String type, String category) throws Exception{
			try {
				Connection conn = getConnection();
				PreparedStatement create = conn.prepareStatement("CREATE TABLE IF NOT EXISTS " +type+ "("+category+"id varchar(255), name varchar(255), quantity int(255), value decimal(4,2), PRIMARY KEY(id))");
				create.executeUpdate();
			}catch(Exception e) {
			System.out.println(e);
			}
		}

	

}

