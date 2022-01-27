/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package vue.main.butg;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import vue.main.MainController;

/**
 * FXML Controller class
 *
 * @author loussin
 */
public class ButController implements Initializable {
    
    @FXML
    Label label;
     

    @FXML
    public AnchorPane pane;
    
    @FXML
    ImageView imageView;

    public ButController() {
    }
    
    
    boolean gr=false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        MainController.injectButController(this);
    }   
    
    public Scene getScene(){
        return (Scene)pane.getScene();
    }    
    
    public void setText(String text){
        label.setText(text);
    }
    
    public void grise(){
        if(gr){
            pane.setStyle("-fx-background-color:gray");
            label.setTextFill(Color.WHITE);
        }
            
    }
    
    public void degrise(){
        if(!gr){
            pane.setStyle("-fx-background-color:white");
            label.setTextFill(Color.rgb(35, 90, 90, 0.9));
        }
            
    }
    
    public void grisec(){
        pane.setStyle("-fx-background-color:gray");
        label.setTextFill(Color.WHITE);
        gr=true;
    }
    
    public void degrisec(){
        pane.setStyle("-fx-background-color:white");
        label.setTextFill(Color.rgb(35, 90, 90, 0.9));
        gr=false;
    }
    
    public void setImage(String ImaLocation){
        
        File file = new File(ImaLocation);
        //System.out.println(file.exists());
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
    }

    public AnchorPane getPane() {
        return pane;
    }
    
    
}
