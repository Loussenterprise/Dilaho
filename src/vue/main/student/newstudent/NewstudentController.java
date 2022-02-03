/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package vue.main.student.newstudent;

import dao.ClassroomFactory;
import dao.StudentFactory;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Classroom;
import model.Student;
import vue.main.MainController;
import vue.main.student.StudentController;

/**
 * FXML Controller class
 *
 * @author loussin
 */
public class NewstudentController implements Initializable {

    @FXML
    Button select_image;
    
    @FXML
    TextField image;
    @FXML
    TextField matricule;
    @FXML
    TextField name;
    @FXML
    TextField firstnames;
    @FXML
    TextField address;
    @FXML
    TextArea extras;
    @FXML
    TextField commingScool;
    @FXML
    TextField fatherName;
    @FXML
    TextField fatherWork;
    @FXML
    TextField fatherAddress;
    @FXML
    TextField motherName;
    @FXML
    TextField motherWork;
    @FXML
    TextField motherAddress;
    
    @FXML
    ComboBox<String> sexe; 
    @FXML
    ComboBox<Classroom> classroom;
    
    @FXML
    DatePicker birthday;
    @FXML
    DatePicker inscriptionDate;
    
    
    @FXML
    AnchorPane pane;
    
    @FXML
    ImageView imageview;
    
    Student s;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        MainController.injectNewStuController(this);
        sexe.getItems().add("F");
        sexe.getItems().add("M");
        classroom.setItems(FXCollections.observableList(new ClassroomFactory().getClassrooms()));
        inscriptionDate.setValue(LocalDate.now());
    }    
    
    public void doSelectImg(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(((Stage)pane.getScene().getWindow()));
        if (selectedFile != null) {
           imageview.setImage(new Image(selectedFile.toURI().toString()));
           image.setText(selectedFile.getPath());
           centerImage();
        }
    } 
    
    public void centerImage() {
        Image img = imageview.getImage();
        if (img != null) {
            double w = 0;
            double h = 0;

            double ratioX = imageview.getFitWidth() / img.getWidth();
            double ratioY = imageview.getFitHeight() / img.getHeight();

            double reducCoeff = 0;
            if(ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;

            imageview.setX((imageview.getFitWidth() - w) / 2);
            imageview.setY((imageview.getFitHeight() - h) / 2);

        }
    }
    
    public void doLoadImg(){
        try {
            File f = new File(image.getText());
            imageview.setImage(new Image(f.toURI().toString()));
            centerImage();
        } catch (Exception e) {
        }
            
    }
    
    public void createStudent(){
        if(s==null)
            s=new Student();
        s.setAddress(address.getText());
        try {
            s.setBirthday(Date.valueOf(birthday.getValue()));
        } catch (Exception e) {
        }
        try {
            s.setInscriptionDate(Date.valueOf(inscriptionDate.getValue()));
        } catch (Exception e) {
        }
        try {
            s.setMatricule(""+Integer.parseInt(matricule.getText()));
        } catch (Exception e) {
        }
        
        s.setCl(classroom.getValue());
        s.setCommingScool(commingScool.getText());
        s.setExtras(extras.getText());
        s.setFatherAddress(fatherAddress.getText());
        s.setFatherName(fatherName.getText());
        s.setFatherWork(fatherWork.getText());
        s.setFirstnames(firstnames.getText());
        s.setImage(image.getText());
        
        
        s.setMotherAddress(motherAddress.getText());
        s.setMotherName(motherName.getText());
        s.setMotherWork(motherWork.getText());
        s.setName(name.getText());
        s.setSexe(sexe.getValue());
        new StudentFactory().setStudent(s);
        if(!StudentController.stl.contains(s))
            StudentController.stl.add(s);
        StudentController.refreshTable();
        MainController.showStudentG();
        MainController.nstuctl=null;
        
    }
    
    
    public void setStudent(Student stu){
        this.s=stu;
        if(s!=null)
        {
            try {
                image.setText(s.getImage());
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
                String H="H";
                String F="F";
                sexe.getItems().removeAll(sexe.getItems());
                sexe.getItems().add(F);
                sexe.getItems().add(H);
                sexe.setValue(s.getSexe().equals("H")?H:F);
            }
            extras.setText(s.getExtras());
            commingScool.setText(s.getCommingScool());
            fatherName.setText(s.getFatherName());
            fatherAddress.setText(s.getFatherAddress());
            fatherWork.setText(s.getFatherWork());
            motherAddress.setText(s.getMotherAddress());
            motherName.setText(s.getMotherName());
            motherWork.setText(s.getMotherWork());
            birthday.setValue(s.getBirthday()!=null?s.getBirthday().toLocalDate():null);
            inscriptionDate.setValue(s.getInscriptionDate()!=null?s.getInscriptionDate().toLocalDate():null);
            try {
                classroom.setValue(s.getClassroom()!=null?new ClassroomFactory().getClassroom(s.getClassroom()):null);
            } catch (Exception e) {
            }
            doLoadImg();
        }
        
    }
    
    
    public void tr(String... s){
        
    }
}
