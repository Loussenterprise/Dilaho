/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dilaho;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import vue.LoginStage;
import vue.main.MainStage;
import vue.main.student.StudentG;

/**
 *
 * @author loussin
 */
public class Dilaho extends Application{


    @Override
    public void start(Stage stage) throws Exception {
        //stage = new LoginStage();
        stage = new MainStage();
        //stage.setScene(new Scene(new StudentG()));
        stage.show();
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
