/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package vue.main.classes.cl.clshow;

import dao.StudentFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Classroom;
import model.Student;
import vue.main.MainController;

/**
 * FXML Controller class
 *
 * @author loussin
 */
public class ClshowController implements Initializable {

    @FXML
    Label tostr;
    @FXML
    Label desc;
    @FXML
    Label fille;
    @FXML
    Label garcon;
    @FXML
    Label total;
    @FXML
    Label group;
    @FXML
    Label scoolyear;
    @FXML
    Button returnBtn;
    @FXML
    Button studentListBtn;
    @FXML
    Button scolariteBtn;
    @FXML
    Button notebookBtn;
    @FXML
    Button nothingBtn;
    private Classroom classroom;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        MainController.injectShowingClassroom(this);
    }    
    
    public void doReturn(){
        MainController.showClassesG();
    }
    
    public void doStudentList(){
        MainController.showStudentG(classroom);
    }
    
    public void doNotebook(){
        MainController.showNotesG(classroom);
    }
    
    public void doScolarite(){
        System.out.println("vue.main.classes.cl.clshow.ClshowController.doScolarite()");
    }
    
    public void doNothing(){
        System.out.println("vue.main.classes.cl.clshow.ClshowController.doNothing()");
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
        init();
    }
    
    void init(){
        if(classroom!=null){
            ArrayList<Student> students = (new StudentFactory()).getStudentsByClassroom(classroom.getId());
            desc.setText(classroom.desc());
            tostr.setText(classroom.toString());
            fille.setText("Fille :"+StudentFactory.getFilleNbr(students));
            garcon.setText("Garçon :"+StudentFactory.getGarconNbr(students));
            total.setText("Total :"+students.size());
            group.setText("Groupe :"+classroom.getGroup());
            scoolyear.setText("Année scolaire :"+classroom.getScoolYear());
        }
    }
}
