/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue.main.butg;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author loussin
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        ButG s=new ButG(new Pane());
        ButController c= new ButController();
        stage.setScene(s);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
