package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Functions {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public static Connection getConnection() throws Exception {
		try {
		
			String url = "jdbc:mysql://localhost:3306/inventorymanagement";
			String username = "root";
			String password = "Sqlpassword";
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			//Establish connection with information above
			Connection conn = DriverManager.getConnection(url, username, password);
			return conn;
		} catch(Exception e) {
			System.out.println(e);
		}
	return null;
	}
	
	public void goBack (ActionEvent event, String resource) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
		root = loader.load();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void goToAdd (ActionEvent event, String resource) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
		root = loader.load();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	

	
	public void addToInventory(ActionEvent event, TextField passedID, TextField passedName, TextField passedQuantity, TextField passedValue, String table) throws IOException {
		
		
		String ID = passedID.getText();
		String name = passedName.getText();
		Integer quantity = null;
		Float value = null;
		boolean error = false;
		boolean pkError = false;
		if(ID.equals("")){
			passedID.setText("ID must be a string.");
			error = true;
		}
		if(name.equals("")) {
			passedName.setText("Name must be a string.");
			error = true;
		}
		try{
		    quantity = Integer.parseInt(passedQuantity.getText());
		}catch (NumberFormatException ex) {
			passedQuantity.setText("Quantity must be an int.");
			error = true;;
		}
		try{
		    value = Float.parseFloat(passedValue.getText());
		}catch (NumberFormatException ex) {
			passedValue.setText("Value must be a decimal.");
			error = true;
		}
		
		if(error) {
			return;
		}
		

		try {
			Connection conn = getConnection();
			
				PreparedStatement inserted = conn.prepareStatement("INSERT INTO "+table+" (id, name, quantity, value) VALUES ('"+ID+"', '"+name+"', '"+quantity+"', '"+value+"')");
				PreparedStatement insertedAll = conn.prepareStatement("INSERT INTO items (category, id, name, quantity, value) VALUES ('"+table+"', '"+ID+"', '"+name+"', '"+quantity+"', '"+value+"')");
				inserted.executeUpdate();
				insertedAll.executeUpdate();
		}catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
			pkError = true;
			passedID.setText("Duplicate ID not allowed.");
			System.out.println(e);
		}
		
		if(pkError) {
			return;
		}
		
		passedID.setText(null);
		passedName.setText(null);
		passedQuantity.setText(null);
		passedValue.setText(null);
	}
	
	public void viewCategory(ActionEvent event, String category) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(category));
		root = loader.load();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void setLabel(Label label, String table) throws Exception {
		StringBuilder resString = new StringBuilder();
		try {
			Connection conn = getConnection();
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM "+table+"");
			ResultSet res = statement.executeQuery();
			
			
			while(res.next()) {
				String id = res.getString("id");
	            String name = res.getString("name");
	            Integer quantity = res.getInt("quantity");
	            Float value = res.getFloat("value");
	            String category; 
	            if(table.equals("items")) {
	            	category = res.getString("category");
	            	resString.append("[ Category: " + category + " ] [ ID: " + id + " ] [ Name: " +  name + " ] [ Quantity: " + quantity + "  ] [ Value: " + value + " ]\n");
	            } 
	            else {
	            	resString.append("[ ID: " + id + " ] [ Name: " +  name + " ] [ Quantity: " + quantity + "  ] [ Value: " + value + " ]\n");
	            }
	            
	          
			}
			
			label.setText(String.valueOf(resString));
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	public void removeItem(String table, String ID, Label label) throws Exception {
		String scene;
		if(table.equals("meat")){
			scene = "MeatSceneMainController.fxml";
		}
		else if(table.equals("produce")) {
			scene = "ProduceSceneMainController.fxml";
		}
		else {
			scene = "DairySceneMainController.fxml";
		}
		Connection conn = getConnection();
		PreparedStatement statement = conn.prepareStatement("DELETE FROM "+table+" WHERE id = '"+ID+"'");		
		statement.executeUpdate();
		PreparedStatement statementAll = conn.prepareStatement("DELETE FROM items WHERE id = '"+ID+"'");
		statementAll.executeUpdate();
		setLabel(label, table);
	}

}
