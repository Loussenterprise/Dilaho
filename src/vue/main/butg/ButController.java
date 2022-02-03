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
        pane.setStyle("-fx-background-color:gray");
        label.setTextFill(Color.WHITE);
            
    }
    
    public void degrise(){
        pane.setStyle("-fx-background-color:white");
        label.setTextFill(Color.rgb(35, 90, 90, 0.9));
            
    }
    
    public void grisec(){
        pane.setStyle("-fx-background-color:gray");
        label.setTextFill(Color.WHITE);
    }
    
    public void degrisec(){
        pane.setStyle("-fx-background-color:white");
        label.setTextFill(Color.rgb(35, 90, 90, 0.9));
    }
    
    public void setImage(String ImaLocation){
        Image image = new Image(ImaLocation);
        imageView.setImage(image);
    }
    
    public Image getImage(){
        return imageView.getImage();
    }

    public AnchorPane getPane() {
        return pane;
    }
    
    
}
