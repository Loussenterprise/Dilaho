/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue.main;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import vue.LoginStage;

/**
 *
 * @author loussin
 */
public class MainStage extends Stage {

    public MainStage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/main/main.fxml"));
            AnchorPane root = ( AnchorPane ) loader.load();
            Scene scene = new Scene(root);
            
            setMinWidth(600.0);
            setMinHeight(400.0);
            setScene(scene);
            setTitle("Dilaho");
        } catch (IOException ex) {
            Logger.getLogger(LoginStage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public MainStage(StageStyle arg0) {
        super(arg0);
    }
    
}
