/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package vue.main.classes.cl;

import dao.StudentFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.Classroom;
import model.Student;
import vue.main.MainController;
import vue.main.classes.ClassesController;

/**
 * FXML Controller class
 *
 * @author loussin
 */
public class ClController implements Initializable {
    
    @FXML
    AnchorPane clg;
    
    @FXML
    public Label tostr;
    
    @FXML
    public Label fille;
    
    @FXML
    public Label garcon;
    
    @FXML
    public Label total;
    
    private Classroom classroom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ClassesController.injectClController(this);
    }    
    
    public void init(){
        try {
            ArrayList<Student> students = (new StudentFactory()).getStudentsByClassroom(classroom.getId());
            tostr.setText(classroom.toString());
            fille.setText(""+StudentFactory.getFilleNbr(students));
            garcon.setText(""+StudentFactory.getGarconNbr(students));
            total.setText(""+students.size());
        } catch (Exception e) {
        }
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
        init();
    }

    public AnchorPane getClg() {
        return clg;
    }
    
    public void show(){
        MainController.showClasse(this);
    }
}
