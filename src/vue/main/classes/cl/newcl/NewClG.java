/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue.main.classes.cl.newcl;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author loussin
 */
public class NewClG extends AnchorPane {
    
    AnchorPane root;

    public NewClG() {
        super();
        init();
    }
    
    
    
    private void init(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/main/classes/cl/newcl/newcl.fxml"));
            getChildren().add(loader.load());
            AnchorPane.setTopAnchor(this, 0.0);
            AnchorPane.setRightAnchor(this, 0.0);
            AnchorPane.setLeftAnchor(this, 0.0);
            AnchorPane.setBottomAnchor(this, 0.0);
        } catch (IOException ex) {
            Logger.getLogger(NewClG.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
