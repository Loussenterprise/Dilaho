/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package vue.main.student.showstu;

import dao.ClassroomFactory;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Student;
import vue.main.MainController;

/**
 * FXML Controller class
 *
 * @author loussin
 */
public class ShowStuController implements Initializable {
    
    @FXML
    ImageView image;    
    
    @FXML
    Label matricule;    
    @FXML
    Label name;    
    @FXML
    Label firstnames;    
    @FXML
    Label address;    
    @FXML
    Label sexe;    
    @FXML
    TextArea extras;   
    @FXML
    Label commingScool;    
    @FXML
    Label fatherName;    
    @FXML
    Label fatherWork;    
    @FXML
    Label fatherAddress;    
    @FXML
    Label motherName;    
    @FXML
    Label motherWork;    
    @FXML
    Label motherAddress;    
    @FXML
    Label classe;    
    @FXML
    Label birthday;    
    @FXML
    Label inscriptionDate;
    @FXML
    Label tostring;
    
     
    
    Student s;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        MainController.injectShowStuController(this);
    }    
    
    public void setStudent(Student stu){
        this.s=stu;
        if(s!=null)
        {
            try {
                image.setImage(new Image(new File(s.getImage()).toURI().toString()));
            } catch (Exception e) {
            }
            
            try {
                matricule.setText(s.getMatricule().toString());
            } catch (Exception e) {
            }
            name.setText(s.getName());
            firstnames.setText(s.getFirstnames());
            address.setText(s.getAddress());
            if(s.getSexe()!=null){
                sexe.setText(s.getSexe().equals("H")?"Garçon":"Fille");
            }
            extras.setText(s.getExtras());
            commingScool.setText(s.getCommingScool());
            fatherName.setText(s.getFatherName());
            fatherAddress.setText(s.getFatherAddress());
            fatherWork.setText(s.getFatherWork());
            motherAddress.setText(s.getMotherAddress());
            motherName.setText(s.getMotherName());
            motherWork.setText(s.getMotherWork());
            birthday.setText(s.getBirthday()!=null?s.getBirthday().toString():null);
            inscriptionDate.setText(s.getInscriptionDate()!=null?s.getInscriptionDate().toString():null);
            tostring.setText(tostring.getText()+s.toString());
            try {
                classe.setText(s.getClassroom()!=null?new ClassroomFactory().getClassroom(s.getClassroom()).toString():null);
            } catch (Exception e) {
            }
        }
        
    }
    
    public void edit(){
        MainController.editStudent(s);
    }
    
    
}
