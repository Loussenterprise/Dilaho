/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package vue;

import dao.Database;
import dao.UserFactory;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.User;

/**
 * FXML Controller class
 *
 * @author loussin
 */
public class LoginController implements Initializable {

    
    
    
    
    public UserFactory userfactory = new UserFactory();
    
    @FXML
    public AnchorPane pane;
    @FXML
    public Label isConnected;
    @FXML
    public Label message;
    @FXML
    public Button connect;
    @FXML
    public TextField email;
    @FXML
    public PasswordField passwd;
            
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if ( userfactory.isConnected()) {
            isConnected.setText("DB connected");
        } else {
            isConnected.setText("DB not connected");
        }
        isConnected.setTextAlignment(TextAlignment.RIGHT);
        isConnected.setWrapText(true);
        message.setVisible(false);
        email.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                passwd.requestFocus();
            }
        });
        passwd.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                login();
            }
        });
        pane.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ESCAPE)) {
                ((Stage)pane.getScene().getWindow()).close();
            }
        });
        requestFocusOrDieTrying(email);
    }    
    
    public void login(){
        User user=userfactory.connect(email.getText(), passwd.getText());
        if(user == null){
            message.setText("Something went wrong !!!");
            email.requestFocus();
        }else{
            message.setText(user.toString());
            message.setTextFill(Color.GREEN);
            connect.requestFocus();
        }
            
        message.setVisible(!message.isVisible());
    }
    
    private void requestFocusOrDieTrying(Node node) {
    Platform.runLater(() -> {
        if (!node.isFocused()) {
            node.requestFocus();
            requestFocusOrDieTrying(node);
        }
    });
}
    
    public static void main(String[] args) {
        System.out.println(Database.getConnection());
    }
}
