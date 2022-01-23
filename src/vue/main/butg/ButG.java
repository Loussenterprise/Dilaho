/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue.main.butg;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import vue.LoginStage;

/**
 *
 * @author loussin
 */
public class ButG extends Scene {
    AnchorPane root;
    public ButG(Parent arg0) {
        super(arg0);
        init();
    }
    void init(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/main/butg/but.fxml"));
            root = ( AnchorPane ) loader.load();
            setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(LoginStage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
