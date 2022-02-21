/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package vue.main;

import dao.StudentFactory;
import java.io.File;
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
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Classroom;
import model.Scolarite;
import model.Student;
import vue.LoginStage;
import vue.main.butg.ButController;
import vue.main.classes.ClassesController;
import vue.main.classes.ClassesG;
import vue.main.classes.cl.ClController;
import vue.main.classes.cl.clshow.ClShowG;
import vue.main.classes.cl.clshow.ClshowController;
import vue.main.classes.cl.newcl.NewClG;
import vue.main.classes.cl.newcl.NewclController;
import vue.main.dashboard.DashboardG;
import vue.main.notes.NotesController;
import vue.main.notes.NotesG;
import vue.main.scolarite.ScolariteController;
import vue.main.scolarite.ScolariteG;
import vue.main.student.StudentController;
import vue.main.student.StudentG;
import vue.main.student.newstudent.NewstudentController;
import vue.main.student.newstudent.NewstudentG;
import vue.main.student.showstu.ShowStuController;
import vue.main.student.showstu.ShowStuG;

/**
 * FXML Controller class
 *
 * @author loussin
 */
public class MainController implements Initializable {
    
    static ArrayList<ButController> butcs = new ArrayList<>();

    static StudentController sctl; 

    static ClassesController clc; 
    static NotesController nctl; 
    static ShowStuController showstuctl; 
    static ScolariteController scolariteCtl; 
    public static NewstudentController nstuctl;
    public static void setLoading(ImageView aLoading) {
        loading = aLoading;
    }
    
    @FXML
    AnchorPane pan;
    static AnchorPane pane;
    
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
    
    @FXML
    ImageView load;
    private static ImageView loading;
    
    private static ClshowController showingClassroom;
    
    static AnchorPane visible;
    static StudentG studentg ;    
    public static ClassesG classesg ;
    static NotesG notesG ;
    public static ScolariteG scolariteG ;
    public static NewstudentG newstuG;
    public static NewclController cctl;
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
        
        //System.out.println("vue.main.MainController.initialize()");
        setVisible(visibl);
        setPane(pan);
        setLoading(load);
//        studentg = new StudentG();
//        classesg = new ClassesG();
//        notesG = new NotesG();
        Student.loadClassroomForAll(students);
        stl.addListener(new ListChangeListener<Student>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Student> arg0) {
                try {
                    clc.initialize(url, rb);
                } catch (Exception e) {
                }
                
            }
        });
        //System.out.println("vue.main.MainController.initialize()  ##### %%%%% "+butcs.size());
        if(butcs.size()>4){
            butcs.get(0).setText("Eleves");
            butcs.get(0).setImage("img/Student_2.png");
            butcs.get(0).getPane().setOnMouseClicked(event -> showStudentG());
            
            butcs.get(1).setText("Classes");
            butcs.get(1).setImage("img/Student.png");
            butcs.get(1).getPane().setOnMouseClicked(event -> showClassesG());
            
            //butcs.get(2).setText("Salles");
            butcs.get(2).setText("Scolarité");
            butcs.get(2).setImage("img/scolarite.png");
            butcs.get(2).getPane().setOnMouseClicked(event -> showScolariteG());
            
            butcs.get(3).setText("Notes");
            butcs.get(3).setImage("img/notes.png");
            butcs.get(3).getPane().setOnMouseClicked(event -> showNotesG());
            
            butcs.get(4).setText("Dashboard");
            butcs.get(4).setImage("img/dashboard.jpeg");
            butcs.get(4).getPane().setOnMouseClicked(event -> showNode(new DashboardG()));
        }
        setVisible(visibl);
        
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
        showStudentG();
        File file = new File("img/loading.gif");
        //System.out.println(file.exists());
        Image image = new Image(file.toURI().toString());
        loading.setImage(image);
    }   
    
    

    public static void setVisible(AnchorPane visible) {
        MainController.visible = visible;
    }

    public static ArrayList<Student> getStudents() {
        return students;
    }

    public static ObservableList<Student> getStl() {
        return stl;
    }

    public AnchorPane getPane() {
        return pane;
    }

    public void setPane(AnchorPane pane) {
        MainController.pane = pane;
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
        new LoginStage().show();
        butcs=new ArrayList<>();
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
    public static void injectNotesController(NotesController ctl){
        if(ctl!=null)
            nctl = ctl;
    }
    public static void injectNewStuController(NewstudentController ctl){
        if(ctl!=null)
            nstuctl = ctl;
    }
    public static void injectShowStuController(ShowStuController ctl){
        if(ctl!=null)
            showstuctl = ctl;
    }
    public static void injectNewclController(NewclController ctl){
        if(ctl!=null)
            cctl = ctl;
    }
    public static void injectScolariteController(ScolariteController ctl){
        if(ctl!=null)
            scolariteCtl = ctl;
    }
    
    
    public void minOptions(){
        timelinemin.play();
        //leftzone.setPrefWidth(50);
    }
    
    public void wipeOptions(){
        timelinewipe.play();
        //leftzone.setPrefWidth(200);
    }
    
    public static void showStudentG(){
        //loading.setVisible(true);
        if(studentg==null)
            studentg=new StudentG();
        visible.getChildren().removeAll(visible.getChildren());
        visible.getChildren().add(studentg);
        studentg.requestFocus();
        if(butcs.size()>3){
            butcs.get(0).grise();
            butcs.get(1).degrise();
             
            butcs.get(2).degrise();
            butcs.get(3).degrise();
        }
        //loading.setVisible(false);
    } 
    
    public static void reloadStudentG(){
        //loading.setVisible(true);
        studentg=null;
        if(studentg==null)
            studentg=new StudentG();
        visible.getChildren().removeAll(visible.getChildren());
        visible.getChildren().add(studentg);
        studentg.requestFocus();
        if(butcs.size()>3){
            butcs.get(0).grise();
            butcs.get(1).degrise();
             
            butcs.get(2).degrise();
            butcs.get(3).degrise();
        }
        //loading.setVisible(false);
    }  
    
    public static void showStudentG(Classroom classroom){
        //loading.setVisible(true);
        //loading.setVisible(true);
        if(studentg==null)
            studentg=new StudentG();
        if(butcs.size()>3){
            butcs.get(0).grise();
            butcs.get(1).degrise();
             
            butcs.get(2).degrise();
            butcs.get(3).degrise();
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
        //loading.setVisible(false);
    }   
    
    public static void showClassesG(){
        //loading.setVisible(true);
        
        if(classesg==null)
            classesg=new ClassesG();
        if(butcs.size()>3){
            butcs.get(0).degrise();
            butcs.get(1).grise();
             
            butcs.get(2).degrise();
            butcs.get(3).degrise();
        }
        visible.getChildren().removeAll(visible.getChildren());
        visible.getChildren().add(classesg);
        classesg.requestFocus();
        //loading.setVisible(false);
    }
    
    public static void showClassesG(ClassesG g){
        //loading.setVisible(true);
        classesg=g;
        if(classesg==null)
            classesg=new ClassesG();
        if(butcs.size()>3){
            butcs.get(0).degrise();
            butcs.get(1).grise();
             
            butcs.get(2).degrise();
            butcs.get(3).degrise();
        }
        visible.getChildren().removeAll(visible.getChildren());
        visible.getChildren().add(classesg);
        classesg.requestFocus();
        //loading.setVisible(false);
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
        if(butcs.size()>3){
            butcs.get(0).degrise();
            butcs.get(1).degrise();
             
            butcs.get(2).degrise();
            butcs.get(3).degrise();
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
        
        if(butcs.size()>3){
            butcs.get(0).degrise();
            butcs.get(1).degrise();
             
            butcs.get(2).degrise();
            butcs.get(3).degrise();
        }
    }
    
    public static void showNotesG(){
        //loading.setVisible(true);
        if(notesG==null)
            notesG=new NotesG();
        if(butcs.size()>3){
            butcs.get(0).degrise();
            butcs.get(1).degrise();
             
            butcs.get(2).degrise();
            butcs.get(3).grise();
        }
        visible.getChildren().removeAll(visible.getChildren());
        visible.getChildren().add(notesG);
        notesG.requestFocus();
        //loading.setVisible(false);
    }
    
    public static void showNotesG(Classroom classroom){
        //loading.setVisible(true);
        if(notesG==null)
            notesG=new NotesG();
        if(butcs.size()>3){
            butcs.get(0).degrise();
            butcs.get(1).degrise();
             
            butcs.get(2).degrise();
            butcs.get(3).grise();
        }
        visible.getChildren().removeAll(visible.getChildren());
        nctl.setClassroom(classroom);
        visible.getChildren().add(notesG);
        notesG.requestFocus();
        //loading.setVisible(false);
    }
    
    public static void showNode(Node node){
        visible.getChildren().removeAll(visible.getChildren());
        visible.getChildren().add(node);
        node.requestFocus();
        //loading.setVisible(false);
    }
    
    public static void showNewstuG(){
        if(newstuG==null)
            newstuG=new NewstudentG();
        showNode(newstuG);
    }
    
    public static void showStudent(Student s){
        ShowStuG csg=new ShowStuG();
        showstuctl.setStudent(s);
        showNode(csg);
    }
    
    public static void editStudent(Student stu){
        newstuG=new NewstudentG();
        nstuctl.setStudent(stu);
        showNode(newstuG);
    }
    
    public static void editClassroomt(Classroom cl){
        NewClG c = new NewClG();
        cctl.setClassroom(cl);
        showNode(c);
    }
    
    public static void showScolariteG(){
        if(butcs.size()>3){
            butcs.get(0).degrise();
            butcs.get(1).degrise();
             
            butcs.get(2).grise();
            butcs.get(3).degrise();
        }
        if (scolariteG==null) {
            scolariteG=new ScolariteG();
        }
        showNode(scolariteG);
    }
    
    public static void showScolariteG(Scolarite s){
        if(butcs.size()>3){
            butcs.get(0).degrise();
            butcs.get(1).degrise();
             
            butcs.get(2).grise();
            butcs.get(3).degrise();
        }
        if (scolariteG==null) {
            scolariteG=new ScolariteG();
        }
        if(s!=null){
            scolariteCtl.show(s);
        }
        showNode(scolariteG);
    }
}

