/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue.main.classes.cl.clshow;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import model.Classroom;

/**
 *
 * @author loussin
 */
public class ClShowG extends AnchorPane {
    
    AnchorPane root;
    Classroom classroom;

    public ClShowG() {
        super();
        init();
    }

    public ClShowG(Classroom classroom) {
        super();
        init();
        this.classroom = classroom;
    }
    
    
    
    void init(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/main/classes/cl/clshow/clshow.fxml"));
            getChildren().add(loader.load());
            AnchorPane.setTopAnchor(this, 0.0);
            AnchorPane.setRightAnchor(this, 0.0);
            AnchorPane.setLeftAnchor(this, 0.0);
            AnchorPane.setBottomAnchor(this, 0.0);
        } catch (IOException ex) {
            Logger.getLogger(ClShowG.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
