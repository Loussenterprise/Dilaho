/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author loussin
 */
public class LoginStage extends Stage{

    public LoginStage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/login.fxml"));
            AnchorPane root = ( AnchorPane ) loader.load();
            Scene scene = new Scene(root);
            
            setScene(scene);
            setTitle("Connect to Dilaho");
        } catch (IOException ex) {
            Logger.getLogger(LoginStage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public LoginStage(StageStyle arg0) {
        super(arg0);
    }
    
    
    
}
