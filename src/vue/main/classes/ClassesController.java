/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package vue.main.classes;

import dao.ClassroomFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import model.Classroom;
import vue.main.MainController;
import vue.main.classes.cl.ClController;
import vue.main.classes.cl.ClG;
import vue.main.classes.cl.newcl.NewClG;

/**
 * FXML Controller class
 *
 * @author loussin
 */
public class ClassesController implements Initializable {
    
    static ArrayList<ClController> clcs;
    
    @FXML
    AnchorPane pane;
    
    @FXML
    ScrollPane scrollPane;
    
    @FXML
    FlowPane flowPane;
    
    ClG clg ;
    
    
    ClassroomFactory crf= new ClassroomFactory();
    ArrayList<Classroom> classrooms;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        MainController.injectClassesController(this);
        flowPane.getChildren().removeAll(flowPane.getChildren());
        flowPane.setCenterShape(true);
        clcs = new ArrayList<>();
        classrooms = crf.getClassrooms();
        for (Classroom c:classrooms) {
            new ClG();
        }
        for(int i=0; i<clcs.size(); i++){
            clcs.get(i).setClassroom(classrooms.get(i));
            flowPane.getChildren().add(clcs.get(i).getClg());
        }
        
    }    
    
    public static void injectClController(ClController c){
        clcs.add(c);
    }
    
    public void newClassroom(){
        MainController.showNode(new NewClG());
    }
    
    public void reload(){
        MainController.showClassesG(null);
    }
    
}
