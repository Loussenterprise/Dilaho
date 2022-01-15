/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dilaho;

import javafx.application.Application;
import javafx.stage.Stage;
import vue.LoginStage;

/**
 *
 * @author loussin
 */
public class Dilaho extends Application{


    @Override
    public void start(Stage stage) throws Exception {
        stage = new LoginStage();
        stage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
