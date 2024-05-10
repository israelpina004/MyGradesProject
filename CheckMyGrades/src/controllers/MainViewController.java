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
    void initialize() {
        assert grove != null : "fx:id=\"grove\" was not injected: check your FXML file 'MainView.fxml'.";

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

}


