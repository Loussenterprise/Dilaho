/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue.main.dashboard;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author loussin
 */
public class DashboardG extends AnchorPane{
    
    AnchorPane root;

    public DashboardG() {
        super();
        init();
    }
    
    
    
    void init(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/main/dashboard/dashboard.fxml"));
            System.out.println(loader);
            getChildren().add(loader.load());
            AnchorPane.setTopAnchor(this, 0.0);
            AnchorPane.setRightAnchor(this, 0.0);
            AnchorPane.setLeftAnchor(this, 0.0);
            AnchorPane.setBottomAnchor(this, 0.0);
        } catch (IOException ex) {
            Logger.getLogger(DashboardG.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
