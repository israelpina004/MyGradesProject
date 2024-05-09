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
import javafx.stage.Stage;

public class MainViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button grove;

    @FXML
    private Button spitzer;

    @FXML
    void initialize() {
        assert grove != null : "fx:id=\"grove\" was not injected: check your FXML file 'MainView.fxml'.";
        assert spitzer != null : "fx:id=\"spitzer\" was not injected: check your FXML file 'MainView.fxml'.";

    }
    
    @FXML
    public void groveStudents(ActionEvent e) throws IOException {
    	grove.getScene().getWindow().hide();
    	
    	Stage groveMenu = new Stage();
    	Parent root = FXMLLoader.load(getClass().getResource("/fxmlFiles/GroveStudentsMenu.fxml"));
    	Scene scene = new Scene(root);
    	groveMenu.setScene(scene);
    	groveMenu.show();
    	groveMenu.setResizable(false);
    }
    
    @FXML
    public void spitzerStudents(ActionEvent e) throws IOException {
    	spitzer.getScene().getWindow().hide();
    	
    	Stage spitzerMenu = new Stage();
    	Parent root = FXMLLoader.load(getClass().getResource("/fxmlFiles/SpitzerStudentsMenu.fxml"));
    	Scene scene = new Scene(root);
    	spitzerMenu.setScene(scene);
    	spitzerMenu.show();
    	spitzerMenu.setResizable(false);
    }

}


