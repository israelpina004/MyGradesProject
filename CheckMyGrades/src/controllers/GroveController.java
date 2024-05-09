package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class GroveController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addStudent;

    @FXML
    private Label classification;

    @FXML
    private Label engAvg;

    @FXML
    private Label firstName;

    @FXML
    private Label histAvg;

    @FXML
    private Label lastName;

    @FXML
    private Label mathAvg;

    @FXML
    private Button menu;

    @FXML
    private Label sciAvg;

    @FXML
    private ComboBox<String> selectStudent;

    @FXML
    void initialize() {
        assert addStudent != null : "fx:id=\"addStudent\" was not injected: check your FXML file 'GroveStudentsMenu.fxml'.";
        assert classification != null : "fx:id=\"classification\" was not injected: check your FXML file 'GroveStudentsMenu.fxml'.";
        assert engAvg != null : "fx:id=\"engAvg\" was not injected: check your FXML file 'GroveStudentsMenu.fxml'.";
        assert firstName != null : "fx:id=\"firstName\" was not injected: check your FXML file 'GroveStudentsMenu.fxml'.";
        assert histAvg != null : "fx:id=\"histAvg\" was not injected: check your FXML file 'GroveStudentsMenu.fxml'.";
        assert lastName != null : "fx:id=\"lastName\" was not injected: check your FXML file 'GroveStudentsMenu.fxml'.";
        assert mathAvg != null : "fx:id=\"mathAvg\" was not injected: check your FXML file 'GroveStudentsMenu.fxml'.";
        assert menu != null : "fx:id=\"menu\" was not injected: check your FXML file 'GroveStudentsMenu.fxml'.";
        assert sciAvg != null : "fx:id=\"sciAvg\" was not injected: check your FXML file 'GroveStudentsMenu.fxml'.";
        assert selectStudent != null : "fx:id=\"selectStudent\" was not injected: check your FXML file 'GroveStudentsMenu.fxml'.";

    }
    
    @FXML
    public void mainMenu(ActionEvent e) throws IOException {
    	menu.getScene().getWindow().hide();
    	
    	Stage mainScreen = new Stage();
    	Parent root = FXMLLoader.load(getClass().getResource("/fxmlFiles/MainView.fxml"));
    	Scene scene = new Scene(root);
    	mainScreen.setScene(scene);
    	mainScreen.show();
    	mainScreen.setResizable(false);
    }

}


