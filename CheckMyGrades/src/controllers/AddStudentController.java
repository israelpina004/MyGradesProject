package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import dbConnection.DBHandler;

public class AddStudentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> classification;
    
    @FXML
    private ChoiceBox<String> gender;

    @FXML
    private ChoiceBox<String> major;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField course;
    
    @FXML
    private TextField courseGrade;
    
    @FXML
    private Button grove;
    
    @FXML
    private Button menu;
    
    @FXML
    private Button enterValues;
    
    private String userFirstName;
    private String userLastName;
    private String userGender;
    private String userClass;
    private String userMajor;
    private String userCourse;
    private double userCourseGrade;
    private String[] classes = {"Freshman", "Sophomore", "Junior", "Senior"};
    private String[] genders = {"Male", "Female", "Other"};
    private String[] groveMajors = {"Biomedical Engineering", "Chemical Engineering", "Civil Engineering",
    		"Computer Science", "Electrical Engineering", "Mechanical Engineering", "Computer Engineering",
    		"Cybersecurity", "Data Science & Engineering", "Earth System Science & Environmental Engineering",
    		"Information Systems", "Translational Medicine", "Sustainability in the Urban Environment"};
    
    private Connection connection;
    private DBHandler handler;

    @FXML
    void initialize() {
    	assert classification != null : "fx:id=\"classification\" was not injected: check your FXML file 'AddStudent.fxml'.";
        assert courseGrade != null : "fx:id=\"engAvg\" was not injected: check your FXML file 'AddStudent.fxml'.";
        assert enterValues != null : "fx:id=\"enterValues\" was not injected: check your FXML file 'AddStudent.fxml'.";
        assert firstName != null : "fx:id=\"firstName\" was not injected: check your FXML file 'AddStudent.fxml'.";
        assert gender != null : "fx:id=\"gender\" was not injected: check your FXML file 'AddStudent.fxml'.";
        assert grove != null : "fx:id=\"grove\" was not injected: check your FXML file 'AddStudent.fxml'.";
        assert course != null : "fx:id=\"histAvg\" was not injected: check your FXML file 'AddStudent.fxml'.";
        assert lastName != null : "fx:id=\"lastName\" was not injected: check your FXML file 'AddStudent.fxml'.";
        assert major != null : "fx:id=\"major\" was not injected: check your FXML file 'AddStudent.fxml'.";
        assert menu != null : "fx:id=\"menu\" was not injected: check your FXML file 'AddStudent.fxml'.";
        
        classification.getItems().addAll(classes);
        gender.getItems().addAll(genders);
        major.getItems().addAll(groveMajors);
        
        handler = new DBHandler();
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
    public void submitNewStudent(ActionEvent e) {
    	userFirstName = firstName.getText();
    	userLastName = lastName.getText();
    	userClass = classification.getValue();
    	userGender = gender.getValue();
    	userMajor = major.getValue();
    	userCourse = course.getText();
    	
    	try {
    		userCourseGrade = Double.parseDouble(courseGrade.getText());
    	}
    	catch(NumberFormatException ex) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("Please enter a number for the students grade.");
    		alert.show();
    	}
    	
    	boolean isFieldEmpty = userFirstName.isEmpty() || userLastName.isEmpty() || userClass.isEmpty() || userGender.isEmpty() || userMajor.isEmpty() || userCourse.isEmpty();
    	if(isFieldEmpty) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("Please fill all text fields and choose from the choice box.");
    		alert.show();
    	}
    	
    	//String insertQuery = "insert into "
    	
    }
    
}

