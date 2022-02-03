/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue.main.student.showstu;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author loussin
 */
public class ShowStuG extends AnchorPane {
    
    AnchorPane root;

    public ShowStuG() {
        super();
        init();
    }
    
    
    
    void init(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/main/student/showstu/showStu.fxml"));
            getChildren().add(loader.load());
            AnchorPane.setTopAnchor(this, 0.0);
            AnchorPane.setRightAnchor(this, 0.0);
            AnchorPane.setLeftAnchor(this, 0.0);
            AnchorPane.setBottomAnchor(this, 0.0);
        } catch (IOException ex) {
            Logger.getLogger(ShowStuG.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
