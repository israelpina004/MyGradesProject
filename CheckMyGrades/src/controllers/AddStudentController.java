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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddStudentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField classification;

    @FXML
    private TextField engAvg;

    @FXML
    private TextField firstName;

    @FXML
    private Button grove;

    @FXML
    private TextField histAvg;

    @FXML
    private TextField lastName;

    @FXML
    private TextField mathAvg;

    @FXML
    private Button menu;

    @FXML
    private TextField sciAvg;

    @FXML
    private Button spitzer;

    @FXML
    private ChoiceBox<String> whatDept;

    @FXML
    void initialize() {
        assert classification != null : "fx:id=\"classification\" was not injected: check your FXML file 'AddStudent.fxml'.";
        assert engAvg != null : "fx:id=\"engAvg\" was not injected: check your FXML file 'AddStudent.fxml'.";
        assert firstName != null : "fx:id=\"firstName\" was not injected: check your FXML file 'AddStudent.fxml'.";
        assert grove != null : "fx:id=\"grove\" was not injected: check your FXML file 'AddStudent.fxml'.";
        assert histAvg != null : "fx:id=\"histAvg\" was not injected: check your FXML file 'AddStudent.fxml'.";
        assert lastName != null : "fx:id=\"lastName\" was not injected: check your FXML file 'AddStudent.fxml'.";
        assert mathAvg != null : "fx:id=\"mathAvg\" was not injected: check your FXML file 'AddStudent.fxml'.";
        assert menu != null : "fx:id=\"menu\" was not injected: check your FXML file 'AddStudent.fxml'.";
        assert sciAvg != null : "fx:id=\"sciAvg\" was not injected: check your FXML file 'AddStudent.fxml'.";
        assert spitzer != null : "fx:id=\"spitzer\" was not injected: check your FXML file 'AddStudent.fxml'.";
        assert whatDept != null : "fx:id=\"whatDept\" was not injected: check your FXML file 'AddStudent.fxml'.";

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

