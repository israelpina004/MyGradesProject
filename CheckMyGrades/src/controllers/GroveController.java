package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dbConnection.DBHandler;
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
    private Button addStudentHelp;

    @FXML
    private Label classification;

    @FXML
    private Label firstName;

    @FXML
    private Label lastName;

    @FXML
    private Label gpa;

    @FXML
    private Label gender;
    
    @FXML
    private Button menu;

    @FXML
    private ComboBox<String> selectStudent;
    
    @FXML
    private Button transcript;
    
    private DBHandler handler;
    private Connection connection;
    private PreparedStatement pst; 

    @FXML
    void initialize() {
        assert addStudentHelp != null : "fx:id=\"addStudent\" was not injected: check your FXML file 'GroveStudentsMenu.fxml'.";
        assert classification != null : "fx:id=\"classification\" was not injected: check your FXML file 'GroveStudentsMenu.fxml'.";
        assert firstName != null : "fx:id=\"firstName\" was not injected: check your FXML file 'GroveStudentsMenu.fxml'.";
        assert lastName != null : "fx:id=\"lastName\" was not injected: check your FXML file 'GroveStudentsMenu.fxml'.";
        assert menu != null : "fx:id=\"menu\" was not injected: check your FXML file 'GroveStudentsMenu.fxml'.";
        assert selectStudent != null : "fx:id=\"selectStudent\" was not injected: check your FXML file 'GroveStudentsMenu.fxml'.";
        
        
        handler = new DBHandler();
        connection = handler.getConnection();
        String q1 = "Select student_id from students_info";
        
        //Populate the combo box with student ids
        try {
            pst = connection.prepareStatement(q1);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                selectStudent.getItems().add(resultSet.getString("student_id"));
            }
            pst.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }

         selectStudent.setOnAction(event -> {
            String selectedStudentId = selectStudent.getValue();
            if (selectedStudentId != null) {
                try {
                    // Gets student info from database
                    pst = connection.prepareStatement("SELECT * FROM students_info WHERE student_id = ?");
                    pst.setString(1, selectedStudentId);
                    ResultSet resultSet = pst.executeQuery();
                    if (resultSet.next()) {
                        firstName.setText("First Name: " + resultSet.getString("first_name"));
                        lastName.setText("Last Name: " + resultSet.getString("last_name"));
                        classification.setText("Classification: " + resultSet.getString("class"));
                        gender.setText("Gender: " + resultSet.getString("gender"));
                    }
                    // Retrieve and calculate average grade from studentgrades table
            pst = connection.prepareStatement("SELECT AVG(course_grade) AS average_grade FROM students_grades WHERE student_id = ?");
            pst.setString(1, selectedStudentId);
            resultSet = pst.executeQuery();
            if (resultSet.next()) {
                double averageGrade = resultSet.getDouble("average_grade");
                pst = connection.prepareStatement("UPDATE students_info SET gpa = ? WHERE student_id = ?");
                pst.setDouble(1, averageGrade);
                pst.setString(2, selectedStudentId);
                gpa.setText("Average Grade: " + String.format("%.2f", averageGrade));
            } else {
                gpa.setText("Average Grade: N/A"); // Handle case where there are no grades
            }
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
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
    
    public void addStudentHelpScreen(ActionEvent e) throws IOException {
    	addStudentHelp.getScene().getWindow().hide();
    	
    	Stage addNotes = new Stage();
    	Parent root = FXMLLoader.load(getClass().getResource("/fxmlFiles/AddStudentHelp.fxml"));
    	Scene scene = new Scene(root);
    	addNotes.setScene(scene);
    	addNotes.show();
    	addNotes.setResizable(false);
    }

}


