/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package vue.main.student.showstu;

import dao.ClassroomFactory;
import dao.ScolariteFactory;
import dao.StudentFactory;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import model.Classroom;
import model.Scolarite;
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
    @FXML
    Button scols;
    @FXML
    Button newScol;
    @FXML
    Button valider;
    @FXML
    ComboBox<Classroom> ClSelect;
    @FXML
    TextField scoolyear;
    @FXML
    Accordion scolariteShow;
    ArrayList<Scolarite> scolariteList ;
     
    @FXML
    AnchorPane scolaritePane;
    @FXML
    FlowPane fp;
    @FXML
    VBox actual;
    @FXML
    Label li;
    
    public void showScolarites(){
        scolaritePane.setVisible(true);
        scols.setDisable(true);
        scolariteShow.getPanes().removeAll(scolariteShow.getPanes());
        scolariteList=new ScolariteFactory().getScolaritesByStudentId(s.getId());
        for(Scolarite s : scolariteList){
            s.dopper();
            VBox vb=new VBox();
            try {
                vb.getChildren().add(new Label("Année scolaire : "+s.getClassroom().getScoolYear()));
            } catch (Exception e) {
            }
            try {
                vb.getChildren().add(new Label("Class : "+s.getClassroom().toString()));
            } catch (Exception e) {
            }
            try {
                vb.getChildren().add(new Label("Montant a payer : "+s.getContribution()));
            } catch (Exception e) {
            }
            try {
                vb.getChildren().add(new Label("Payé : "+s.getMtpaye()));
            } catch (Exception e) {
            }
            try {
                vb.getChildren().add(new Label("Soldé : "+((s.getContribution()-s.getMtpaye())>0?"Non":"Oui")));
            } catch (Exception e) {
            }
            TitledPane tp = null;
            
            try {
                tp=new TitledPane(s.getClassroom().toString()+" "+s.getClassroom().getScoolYear(),vb);
            } catch (Exception e) {
                tp=new TitledPane("Classe indefinie ",vb);
            }
            scolariteShow.getPanes().add(tp);
        }
        try {
            if(!scolariteList.isEmpty()){
                setActual(scolariteList.get(0));
            }
        } catch (Exception e) {
        }
        ClSelect.getItems().removeAll(ClSelect.getItems());
        ClSelect.setItems(FXCollections.observableList(new ClassroomFactory().getClassrooms()));
        valider.setDisable(true);
    }
    public void hideScolarites(){
        scolaritePane.setVisible(false);
        scols.setDisable(false);
    }
    
    public void doNewSco(){
        fp.setVisible(true);
        newScol.setDisable(true);
    }
    public void doValider(){
        s.save();
        Scolarite sco=new Scolarite();
        sco.setClassroom(ClSelect.getValue());
        sco.setClassroomId(ClSelect.getValue().getId());
        sco.setStudent(s);
        new ScolariteFactory().setScolarite(sco);
        s.setSco(sco);
        s.save();
        
            sco.dopper();
            VBox vb=new VBox();
            try {
                vb.getChildren().add(new Label("Année scolaire : "+sco.getClassroom().getScoolYear()));
            } catch (Exception e) {
            }
            try {
                vb.getChildren().add(new Label("Class : "+sco.getClassroom().toString()));
            } catch (Exception e) {
            }
            try {
                vb.getChildren().add(new Label("Montant a payer : "+sco.getContribution()));
            } catch (Exception e) {
            }
            try {
                vb.getChildren().add(new Label("Payé : "+sco.getMtpaye()));
            } catch (Exception e) {
            }
            try {
                vb.getChildren().add(new Label("Soldé : "+((sco.getContribution()-sco.getMtpaye())>0?"Non":"Oui")));
            } catch (Exception e) {
            }
            TitledPane tp = null;
            
            try {
                tp=new TitledPane(sco.getClassroom().toString()+" "+sco.getClassroom().getScoolYear(),vb);
            } catch (Exception e) {
                tp=new TitledPane("Classe indefinie ",vb);
            }
            scolariteShow.getPanes().add(0,tp);
            tp.setExpanded(true);
            setActual(sco);
        fp.setVisible(false);
        newScol.setDisable(false);
    }
    public void onClassChanged(){
        if(ClSelect.getValue()!=null){
            scoolyear.setText(ClSelect.getValue().getScoolYear());
            valider.setDisable(false);
        }
    }
    
    public void setActual( Scolarite sco){
        actual.getChildren().removeAll(actual.getChildren());
        actual.getChildren().add(li);
        try {
                actual.getChildren().add(new Label("Année scolaire : "+sco.getClassroom().getScoolYear()));
            } catch (Exception e) {
            }
            try {
                actual.getChildren().add(new Label("Class : "+sco.getClassroom().toString()));
            } catch (Exception e) {
            }
            try {
                actual.getChildren().add(new Label("Montant a payer : "+sco.getContribution()));
            } catch (Exception e) {
            }
            try {
                actual.getChildren().add(new Label("Payé : "+sco.getMtpaye()));
            } catch (Exception e) {
            }
            try {
                actual.getChildren().add(new Label("Soldé : "+((sco.getContribution()-sco.getMtpaye())>0?"Non":"Oui")));
            } catch (Exception e) {
            }
        Button b= new Button("Développer");
        b.setOnAction(evt->MainController.showScolariteG(sco));
        actual.getChildren().add(b);
    }
    
    
    
    Student s;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        MainController.injectShowStuController(this);
        scolaritePane.setVisible(false);
        fp.setVisible(false);
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
            scolariteList=new ScolariteFactory().getScolaritesByStudentId(s.getId());
        }
        
    }
    
    public void edit(){
        MainController.editStudent(s);
    }
    
    
}
