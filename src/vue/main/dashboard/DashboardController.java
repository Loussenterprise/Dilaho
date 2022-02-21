/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package vue.main.dashboard;

import dao.ClassroomFactory;
import dao.Database;
import dao.General;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author loussin
 */
public class DashboardController implements Initializable {
    
    @FXML
    Button modify_scoolyear;
    @FXML
    Label actual_scoolyear;
    @FXML
    Label SAVE_PATH;
    @FXML
    ComboBox<String> select_scoolyear;
    @FXML

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        select_scoolyear.setItems(FXCollections.observableList(Database.getScoolyears()));
        actual_scoolyear.setText(General.getScoolYear());
        SAVE_PATH.setText(General.getSavePath());
    }    
    
    public void bibi(){
        select_scoolyear.setVisible(!select_scoolyear.isVisible());
        
        
        if (select_scoolyear.isVisible()) {
            modify_scoolyear.setText("Valider");
            actual_scoolyear.setText("");
        } else {
            modify_scoolyear.setText("Modifier");
            actual_scoolyear.setText(select_scoolyear.getValue());
            General.setScoolYear(select_scoolyear.getValue());
        }
    }
    
    public void black(){
    }
    
    public void chooseDirectory(){
        SAVE_PATH.setText(( new DirectoryChooser().showDialog( ((Stage)select_scoolyear.getScene().getWindow()))).getAbsolutePath());
        General.setSavePath(SAVE_PATH.getText());
    }
}
