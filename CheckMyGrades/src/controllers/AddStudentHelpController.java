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

public class AddStudentHelpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addStudent;

    @FXML
    void addStudentScreen(ActionEvent event) throws IOException {
    	addStudent.getScene().getWindow().hide();
    	
    	Stage add = new Stage();
    	Parent root = FXMLLoader.load(getClass().getResource("/fxmlFiles/AddStudent.fxml"));
    	Scene scene = new Scene(root);
    	add.setScene(scene);
    	add.show();
    	add.setResizable(false);
    }

    @FXML
    void initialize() {
        assert addStudent != null : "fx:id=\"addStudent\" was not injected: check your FXML file 'AddStudentHelp.fxml'.";

    }

}

