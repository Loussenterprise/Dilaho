/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package vue.main;

import dao.StudentFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Classroom;
import model.Student;
import vue.LoginStage;
import vue.main.butg.ButController;
import vue.main.classes.ClassesController;
import vue.main.classes.ClassesG;
import vue.main.classes.cl.ClController;
import vue.main.classes.cl.clshow.ClShowG;
import vue.main.classes.cl.clshow.ClshowController;
import vue.main.student.StudentController;
import vue.main.student.StudentG;

/**
 * FXML Controller class
 *
 * @author loussin
 */
public class MainController implements Initializable {
    
    static ArrayList<ButController> butcs = new ArrayList<>();

    static StudentController sctl; 

    static ClassesController clc; 
    
    @FXML
    AnchorPane pane;
    
    @FXML
    VBox opts;
    
    
    @FXML
    AnchorPane leftzone;
    
    @FXML
    AnchorPane visibl;
    
    
    @FXML
    Button expend;
    
    @FXML
    Button logout;
    
    private static ClshowController showingClassroom;
    
    static AnchorPane visible;
    static StudentG studentg ;    
    static ClassesG classesg ;
    Timeline timelinemin;
    Timeline timelinewipe;
    
    
    static StudentFactory stf=new StudentFactory();
    static ArrayList<Student> students=stf.getStudents();
    static ObservableList<Student> stl = FXCollections.observableList(students);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        System.out.println("vue.main.MainController.initialize()");
        setVisible(visibl);
        studentg = new StudentG();
                classesg = new ClassesG();
        
        stl.addListener(new ListChangeListener<Student>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Student> arg0) {
                clc.initialize(url, rb);
            }
        });
//        Thread t=new Thread(new Runnable() {
//            @Override
//            public void run() {
//            }
//        });
//        t.start();
        if(butcs.size()>4){
            butcs.get(0).setText("Eleves");
            butcs.get(0).setImage("./img/Student_2.png");
            butcs.get(0).getPane().setOnMouseClicked(event -> showStudentG());
            
            butcs.get(1).setText("Classes");
            butcs.get(1).setImage("img/Student.png");
            butcs.get(1).getPane().setOnMouseClicked(event -> showClassesG());
            butcs.get(2).setText("Salles");
            butcs.get(3).setText("Scolarite");
            butcs.get(4).setText("Notes");
        }
        setVisible(visibl);
        showStudentG();
        
        Duration cycleDuration = Duration.millis(250);
        timelinemin = new Timeline(
                new KeyFrame(cycleDuration,
                        new KeyValue(leftzone.prefWidthProperty(),50.0,Interpolator.EASE_BOTH))
                );
        timelinewipe = new Timeline(
                new KeyFrame(cycleDuration,
                        new KeyValue(leftzone.prefWidthProperty(),200.0,Interpolator.EASE_BOTH))
                );
        
        pane.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ESCAPE)) {
                logout();
            }
        });
    }   

    public static void setVisible(AnchorPane visible) {
        System.out.println("vue.main.MainController.setVisible() ###"+visible);
        MainController.visible = visible;
    }

    public static ArrayList<Student> getStudents() {
        return students;
    }

    public static ObservableList<Student> getStl() {
        return stl;
    }
    
    
    
    public void expend(){
        if(leftzone.getWidth()>60){
            minOptions();
            expend.setText(">");
        }else{
            expend.setText("<");
            wipeOptions();
        }
    }
    
    public void logout(){
        LoginStage ls=new LoginStage();
        ls.show();
        ((Stage)pane.getScene().getWindow()).close();
    }
    
    public static void injectButController(ButController ctl){
        if(ctl!=null)
            butcs.add(ctl);
    }
    public static void injectStudentController(StudentController ctl){
        if(ctl!=null)
            sctl = ctl;
    }
    public static void injectClassesController(ClassesController ctl){
        if(ctl!=null)
            clc = ctl;
    }
    
    
    public void minOptions(){
        timelinemin.play();
        //leftzone.setPrefWidth(50);
    }
    
    public void wipeOptions(){
        timelinewipe.play();
        //leftzone.setPrefWidth(200);
    }
    
    public void showStudentG(){
        visible.getChildren().removeAll(visible.getChildren());
        visible.getChildren().add(studentg);
        studentg.requestFocus();
        if(butcs.size()>4){
            butcs.get(0).grisec();
            butcs.get(1).degrisec();
            butcs.get(2).degrisec();
            butcs.get(3).degrisec();
            butcs.get(4).degrisec();
        }
    }  
    
    public static void showStudentG(Classroom classroom){
        if(butcs.size()>4){
            butcs.get(0).grisec();
            butcs.get(1).degrisec();
            butcs.get(2).degrisec();
            butcs.get(3).degrisec();
            butcs.get(4).degrisec();
        }
        visible.getChildren().removeAll(visible.getChildren());
        visible.getChildren().add(studentg);
        Classroom ccc = classroom;
        for(Classroom c:sctl.cl_ls){
            if(Objects.equals(c.getId(), classroom.getId())){
                ccc=c;
                break;
            }
        }
        sctl.cls_select.setValue(ccc);
        studentg.requestFocus();
    }   
    
    public static void showClassesG(){
        if(butcs.size()>4){
            butcs.get(0).degrisec();
            butcs.get(1).grisec();
            butcs.get(2).degrisec();
            butcs.get(3).degrisec();
            butcs.get(4).degrisec();
        }
        visible.getChildren().removeAll(visible.getChildren());
        visible.getChildren().add(classesg);
        classesg.requestFocus();
    }
    
    public static void injectShowingClassroom(ClshowController clsc){
        showingClassroom=clsc;
    }
    
    public static void showClasse(ClController clsc){
        ClShowG clShow=new ClShowG();
        showingClassroom.setClassroom(clsc.getClassroom());
        
        visible.getChildren().removeAll(visible.getChildren());
        visible.getChildren().add(clShow);
        clShow.requestFocus();
        clShow.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ESCAPE)) {
                showingClassroom.doReturn();
            }
        });
        if(butcs.size()>4){
            butcs.get(0).degrisec();
            butcs.get(1).degrisec();
            butcs.get(2).degrisec();
            butcs.get(3).degrisec();
            butcs.get(4).degrisec();
        }
    }
    
    public static void showClasse(){
        ClShowG clShow=new ClShowG();
        
        visible.getChildren().removeAll(visible.getChildren());
        visible.getChildren().add(clShow);
        clShow.requestFocus();
        clShow.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ESCAPE)) {
                showClassesG();
            }
        });
        
        if(butcs.size()>4){
            butcs.get(0).degrisec();
            butcs.get(1).degrisec();
            butcs.get(2).degrisec();
            butcs.get(3).degrisec();
            butcs.get(4).degrisec();
        }
    }
}

