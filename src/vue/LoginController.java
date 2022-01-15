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
        if (emailMatches(email.getText())) {
            User user=userfactory.connect(email.getText(), passwd.getText());
            if(user == null){
                message.setText("Something went wrong !!!");
                email.requestFocus();
            }else{
                message.setText(user.toString());
                message.setTextFill(Color.GREEN);
                connect.requestFocus();
            }
        } else {
            message.setText("Email not macthes");
            email.requestFocus();
        }
        message.setVisible(true);
    }
    
    private void requestFocusOrDieTrying(Node node) {
        Platform.runLater(() -> {
            if (!node.isFocused()) {
                node.requestFocus();
                requestFocusOrDieTrying(node);
            }
        });
    }
    
    public boolean emailMatches(String email){
        return email.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    }
    
    public void dbSeed(){
        Database.seed();
    }
    
    public static void main(String[] args) {
        System.out.println(Database.getConnection());
    }
}
