package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
    private TextField emplid;

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
    
    @FXML
    private Button delete;
    
    private String userFirstName;
    private String userLastName;
    private String userEMPLID;
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
    private PreparedStatement pst;
    private Statement statement;

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
    	userFirstName = firstName.getText().toUpperCase();
    	userLastName = lastName.getText().toUpperCase();
    	userEMPLID = emplid.getText();
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
    	
    	try {
    		Integer.parseInt(userEMPLID);
    	}
    	catch(NumberFormatException ex) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("Please enter an 8 digit number for the students EMPLID.");
    		alert.show();
    	}
    	
    	boolean isFieldEmpty = userFirstName == null || userLastName == null || userEMPLID == null || userClass == null || userGender == null || userMajor == null || userCourse == null;
    	if(isFieldEmpty) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("Please fill all text fields and choose from the choice boxes.");
    		alert.show();
    	}
    	else if(userEMPLID.length() != 8) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("Please enter an 8 digit number for the students EMPLID.");
    		alert.show();
    	}
    	
    	connection = handler.getConnection();
    	
    	String insert_into_majors = "replace into students_majors(student_id, department, major)" +
    	"values(?,?,?)";
    	try {
    		pst = connection.prepareStatement(insert_into_majors);
    		
    	}
    	catch(SQLException ex) {
    		ex.printStackTrace();
    	}
    	try {
    		pst.setString(1, userEMPLID);
    		pst.setString(2, "Grove School of Engineering");
    		pst.setString(3, userMajor);
    		
    		pst.executeUpdate();
    	}
    	catch(SQLException ex) {
    		ex.printStackTrace();
    	}
    	try {
	    	statement = connection.createStatement();
	    	String sort_table = "select * from students_majors order by student_id";
	    	statement.execute(sort_table);
    	}
    	catch(SQLException ex) {
    		ex.printStackTrace();
    	}
    	
    	String insert_into_info = "replace into students_info(student_id, first_name, last_name, class, gender, gpa)" + 
    	"values(?,?,?,?,?,?)";
    	try {
    		pst = connection.prepareStatement(insert_into_info);
    		
    	}
    	catch(SQLException ex) {
    		ex.printStackTrace();
    	}
    	try {
    		pst.setString(1, userEMPLID);
    		pst.setString(2, userFirstName);
    		pst.setString(3, userLastName);
    		pst.setString(4, userClass);
    		pst.setString(5, userGender);
    		pst.setDouble(6, 0.0);
    		
    		pst.executeUpdate();
    	}
    	catch(SQLException ex) {
    		ex.printStackTrace();
    	}
    	try {
	    	statement = connection.createStatement();
	    	String sort_table = "select * from students_info order by student_id";
	    	statement.execute(sort_table);
    	}
    	catch(SQLException ex) {
    		ex.printStackTrace();
    	}
    	
    	String insert_into_grades = "replace into students_grades(student_id, course, course_grade)" + 
    	"values(?, ?, ?)";
    	try {
    		pst = connection.prepareStatement(insert_into_grades);
    		
    	}
    	catch(SQLException ex) {
    		ex.printStackTrace();
    	}
    	try {
    		pst.setString(1, userEMPLID);
    		pst.setString(2, userCourse);
    		pst.setDouble(3, userCourseGrade);;
    		
    		pst.executeUpdate();
    	}
    	catch(SQLException ex) {
    		ex.printStackTrace();
    	}
    	try {
	    	statement = connection.createStatement();
	    	String sort_table = "select * from students_grades order by student_id";
	    	statement.execute(sort_table);
    	}
    	catch(SQLException ex) {
    		ex.printStackTrace();
    	}
    	try {
    		pst.close();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Student has been successfully added.");
        alert.showAndWait();
        clearFields();
    	
    	
    	clearFields();
    }
    
    @FXML
    private void deleteStudent() {
    	userEMPLID = emplid.getText();
    	try {
    		Integer.parseInt(userEMPLID);
    	}
    	catch(NumberFormatException ex) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("Please enter an 8 digit number for the students EMPLID.");
    		alert.show();
    	}
    	
    	if(userEMPLID.length() != 8) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("Please enter an 8 digit number for the students EMPLID.");
    		alert.show();
    	}
    	else if(userEMPLID == null) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("Please enter the student ID you wish to delete from the database.");
    		alert.show();
    	}
    	else {
    		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    		alert.setTitle("WARNING!");
    		alert.setContentText("This will delete ALL information associated with the student ID, including "
    				+ "the student's grades. Do you wish to proceed?");
    		
    		Optional<ButtonType> result = alert.showAndWait();
    		if(result.get() == ButtonType.OK) {
    	    	connection = handler.getConnection();
    	    	
    	    	try {
    	    		String noSafeMode = "set SQL_SAFE_UPDATES=0";
    	    		statement = connection.createStatement();
    	    		statement.execute(noSafeMode);
    	    	}
    	    	catch(SQLException ex) {
    	    		ex.printStackTrace();
    	    	}
    	    	
    	    	try {
    	    		String deleteStudent = "delete from students_info where student_id=" + userEMPLID;
        	    	statement = connection.createStatement();
        	    	statement.execute(deleteStudent);
    	    	}
    	    	catch(SQLException ex) {
    	    		ex.printStackTrace();
    	    	}
    	    	try {
    	    		String deleteStudent = "delete from students_majors where student_id=" + userEMPLID;
        	    	statement = connection.createStatement();
        	    	statement.execute(deleteStudent);
    	    	}
    	    	catch(SQLException ex) {
    	    		ex.printStackTrace();
    	    	}
    	    	try {
    	    		String deleteStudent = "delete from students_grades where student_id=" + userEMPLID;
        	    	statement = connection.createStatement();
        	    	statement.execute(deleteStudent);
    	    	}
    	    	catch(SQLException ex) {
    	    		ex.printStackTrace();
    	    	}
    	    	alert = new Alert(Alert.AlertType.INFORMATION);
    	        alert.setTitle("Information Dialog");
    	        alert.setHeaderText(null);
    	        alert.setContentText("Student has been successfully deleted.");
    	        alert.show();
    		}
    	}
    }
    
    public void clearFields(){
        firstName.clear();
        lastName.clear();
        emplid.clear();
        classification.setValue(null);
        gender.setValue(null);
        major.setValue(null);
        course.clear();
        courseGrade.clear();
    }
    
}

