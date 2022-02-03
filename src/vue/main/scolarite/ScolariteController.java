/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package vue.main.scolarite;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import model.Classroom;
import model.Student;

/**
 * FXML Controller class
 *
 * @author loussin
 */
public class ScolariteController implements Initializable {
    
    @FXML
    AnchorPane pane;
    @FXML
    AnchorPane ap;
    @FXML
    ComboBox<Classroom> cls_select;
    @FXML
    Label classroom;
    @FXML
    Button all;
    @FXML
    TableView<Student> student_list;
    @FXML
    Button addpaye;
    @FXML
    Button save;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
