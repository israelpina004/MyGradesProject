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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private TextField username;
    
    @FXML
    private PasswordField password;
    
    @FXML
    private Button signUp;
    
    @FXML
    private Button goToLogin;

    @FXML
    void initialize() {

    }
    
    @FXML
    public void signUp(ActionEvent e) {
    	PauseTransition pt = new PauseTransition();
    	pt.setDuration(Duration.seconds(2));
    	pt.setOnFinished(event -> {
    		System.out.println("Sign Up Successful.");
    	});
    	
    	pt.play();
    }
    
    public void loginAction(ActionEvent e) throws IOException {
    	signUp.getScene().getWindow().hide();
    	
    	Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxmlFiles/LoginView.fxml"));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.show();
        login.setResizable(false);
    }
    

}
