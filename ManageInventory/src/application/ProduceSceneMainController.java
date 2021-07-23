package application;

import java.io.IOException;
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

public class ProduceSceneMainController {
	
	@FXML
	Button goBackButton;
	@FXML
	Label produceLabel;
	@FXML
	TextField produceText;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	private HashMap<Integer, ArrayList<String>> produceArr = new HashMap<>();
	static Functions access = new Functions();
	
	public void goBack (ActionEvent event) throws IOException {
		access.goBack(event, "Scene1.fxml");
	
	}
	
	public void goToAdd (ActionEvent event) throws IOException {
		access.goToAdd(event, "AddProduce.fxml");
	}
	
	public void initialize() throws Exception {
		access.setLabel(produceLabel, "produce");
	}
	
	public void removeItem(ActionEvent event) throws Exception {
		access.removeItem("produce", produceText.getText(), produceLabel);
	}
	

}
