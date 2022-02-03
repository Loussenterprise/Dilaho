/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dilaho;

import java.io.File;
import java.util.Scanner;
import javafx.application.Application;
import javafx.application.Platform;
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
//        
//        String e= new Scanner(System.in).nextLine();
//        
//        File f=new File(e);
//        System.out.println(f.exists());
//        System.out.println(f.getAbsolutePath());
//        System.out.println(f.getPath());
//        System.out.println(f.getParent());
//        String[] s=f.list();
//        for (int i = 0; i < s.length; i++) {
//            System.out.println(s[i]);
//        }
//        System.out.println(f.list());
//        Platform.exit();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("BECALAV12022");
        launch(args);
        
    }
}
