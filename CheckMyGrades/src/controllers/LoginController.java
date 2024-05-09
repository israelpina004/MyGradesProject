package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private TextField username;
    
    @FXML
    private PasswordField password;
    
    @FXML
    private Button login;
    
    @FXML
    private Label invalidLogin;
    
    @FXML
    private Button signUp;

    @FXML
    void initialize() {

    }
    
    @FXML
    public void loginAction(ActionEvent e) {
    	PauseTransition pt = new PauseTransition();
    	pt.setDuration(Duration.seconds(2));
    	pt.setOnFinished(event -> {
    		System.out.println("Login Successful.");
    	});
    	
    	pt.play();
    }
    
    @FXML
    public void signUp(ActionEvent e) throws IOException {
    	login.getScene().getWindow().hide();
    	
    	Stage signUpStage = new Stage();
    	Parent root = FXMLLoader.load(getClass().getResource("/fxmlFiles/SignUp.fxml"));
    	Scene scene = new Scene(root);
    	signUpStage.setScene(scene);
    	signUpStage.show();
    	signUpStage.setResizable(false);
    }
    
}

