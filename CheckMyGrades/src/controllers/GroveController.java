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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

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
    private Label major;
    
    @FXML
    private Button menu;

    @FXML
    private ComboBox<String> selectStudent;
    
    @FXML
    private Button transcript;
    
    private DBHandler handler;
    private Connection connection;
    private PreparedStatement pst; 
    
    String first = "";
    String last = "";
    String id = "";
    String gen = "";
    String year = "";
    String maj = "";
    String grade = "N/A";

    @FXML
    void initialize() {
        assert addStudentHelp != null : "fx:id=\"addStudent\" was not injected: check your FXML file 'GroveStudentsMenu.fxml'.";
        assert classification != null : "fx:id=\"classification\" was not injected: check your FXML file 'GroveStudentsMenu.fxml'.";
        assert firstName != null : "fx:id=\"firstName\" was not injected: check your FXML file 'GroveStudentsMenu.fxml'.";
        assert lastName != null : "fx:id=\"lastName\" was not injected: check your FXML file 'GroveStudentsMenu.fxml'.";
        assert menu != null : "fx:id=\"menu\" was not injected: check your FXML file 'GroveStudentsMenu.fxml'.";
        assert selectStudent != null : "fx:id=\"selectStudent\" was not injected: check your FXML file 'GroveStudentsMenu.fxml'.";
        assert transcript != null : "fx:id=\"transcript\" was not injected: check your FXML file 'GroveStudentsMenu.fxml'.";
        
        
        handler = new DBHandler();
        connection = handler.getConnection();
        
        selectStudentInfo();
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
    
    public void selectStudentInfo(){
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
            id = selectedStudentId;
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
                        
                        first = resultSet.getString("first_name");
                        last = resultSet.getString("last_name");
                        year = resultSet.getString("class");
                        gen = resultSet.getString("gender");
                    }
                    //Gets students major
                    pst = connection.prepareStatement("Select * FROM students_majors WHERE student_id = ?");
                    pst.setString(1, selectedStudentId);
                    resultSet = pst.executeQuery();
                    if(resultSet.next()){
                        major.setText("Major: " + resultSet.getString("major"));
                        maj = resultSet.getString("major");
                    }

                    // Retrieve and calculate gpa from studentgrades table
            pst = connection.prepareStatement("SELECT AVG(course_grade) AS average_grade FROM students_grades WHERE student_id = ?");
            pst.setString(1, selectedStudentId);
            resultSet = pst.executeQuery();
            if (resultSet.next()) {
                double averageGrade = resultSet.getDouble("average_grade");
                pst = connection.prepareStatement("UPDATE students_info SET gpa = ? WHERE student_id = ?");
                pst.setDouble(1, averageGrade);
                pst.setString(2, selectedStudentId);
                gpa.setText("Average Grade: " + String.format("%.2f", averageGrade));
                grade = String.format("%.2f", averageGrade);
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
    public void generatePDF(ActionEvent e) throws IOException{
    	try {
    		String file = "C:\\Users\\orell\\eclipse-workspace\\CheckMyGrades\\transcript.pdf";
    		Document doc = new Document();
			PdfWriter.getInstance(doc, new FileOutputStream(file));
			
			doc.open();
			
			Paragraph p1 = new Paragraph("Student Transcript\n\nFirst Name: " + first + "\n");
			Paragraph p2 = new Paragraph("Last Name: " + last + "\n");
			Paragraph p3 = new Paragraph("EMPLID: " + id + "\n");
			Paragraph p4 = new Paragraph("Gender: " + gen + "\n");
			Paragraph p5 = new Paragraph("Classification: " + year + "\n");
			Paragraph p6 = new Paragraph("Major: " + maj + "\n");
			Paragraph p7 = new Paragraph("GPA: " + grade + "\n");
			doc.add(p1);
			doc.add(p2);
			doc.add(p3);
			doc.add(p4);
			doc.add(p5);
			doc.add(p6);
			doc.add(p7);
			
			doc.close();
			System.out.println("PDF was made!");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			System.err.println(e1);
		}
    }

}


