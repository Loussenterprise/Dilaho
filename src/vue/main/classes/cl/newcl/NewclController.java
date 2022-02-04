/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package vue.main.classes.cl.newcl;

import dao.ClasslevelFactory;
import dao.ClassroomFactory;
import dao.CourseFactory;
import dao.Database;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
import model.Classlevel;
import model.Classroom;
import model.Course;
import model.Course;
import vue.main.MainController;
 

/**
 * FXML Controller class
 *
 * @author loussin
 */
public class NewclController implements Initializable {
    
    @FXML
    AnchorPane pane;
    @FXML
    TableView<Course> courses;
    
    @FXML
    TextField classlevel;
    @FXML
    TextField groupe;
    @FXML
    TextField scoolyear;
    @FXML
    TextField promotion;
    @FXML
    TextField contribution;
    
    @FXML
    Button new_classlevel;
    @FXML
    Button annuler;
    @FXML
    Button retour;
    @FXML
    Button save;
    
    @FXML
    Button add_classlevel;
    @FXML
    Button add_classroom;
    @FXML
    Button add_course;
    
    @FXML
    TextField name;
    @FXML
    TextField n;
    @FXML
    TextField option;
    @FXML
    TextField op;
    @FXML
    TextField niveau;
    @FXML
    TextField ctrb;
    @FXML
    CheckBox is_new_classlevel;
    @FXML
    AnchorPane ne_classlevel_pane;
    
    @FXML
    ComboBox<Classlevel> classlevel_select;
    @FXML
    ComboBox<String> groupe_select;
    @FXML
    ComboBox<String> scoolyear_select;
    
    @FXML
    Label bi;
    
    static ArrayList<Classlevel> classlevels;
    static ArrayList<String> groupes;
    static ArrayList<String> scoolyears;
    static ArrayList<Course> courseArray;
    static ArrayList<Course> temoin;
    
    Classroom c;
    Classlevel cl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        MainController.injectNewclController(this);
        courseArray=new ArrayList<>();
        temoin=courseArray;
        classlevels=new ClasslevelFactory().getClasslevels();
        groupes=Database.getGroups();
        scoolyears=Database.getScoolyears();
        classlevel_select.setItems(FXCollections.observableList(classlevels));
        groupe_select.setItems(FXCollections.observableList(groupes));
        scoolyear_select.setItems(FXCollections.observableList(scoolyears));
        if(!groupes.isEmpty())
            groupe_select.setValue(groupes.get(0));
        if(!scoolyears.isEmpty())
            scoolyear_select.setValue(scoolyears.get(0));
        castGroupe();
        castScoolyear();
        addClassroomAble();
        initCourses();
    }    
    
    public void doRetour(){
        MainController.showClassesG();
    }
    public void doNewClasslevel(){
        is_new_classlevel.setSelected(true);
        ne_classlevel_pane.setVisible(true);
        courses.setVisible(false);
        add_course.setVisible(false);
        addClasslevelAble();
    }
    public void doAnnuler(){
        is_new_classlevel.setSelected(false);
        ne_classlevel_pane.setVisible(false);
        courses.setVisible(true);
        add_course.setVisible(true);
    }
    public void castClasslevel(){
        classlevel.setText(((Classlevel)classlevel_select.getValue()).toString());
        try {
            contribution.setText(((Classlevel)classlevel_select.getValue()).getContribution().toString());
        } catch (Exception e) {
        }
        
        addClassroomAble();
    }
    public void castGroupe(){
        groupe.setText(groupe_select.getValue());
    }
    public void castScoolyear(){
        scoolyear.setText(scoolyear_select.getValue());
        addClassroomAble();
    }
    public void doAddClaslevel(){
        scoolyear.setText(scoolyear_select.getValue());
    }
    
    public void addClasslevelAble(){
        if(n.getText().isEmpty() || name.getText().isEmpty() || niveau.getText().isEmpty())
            add_classlevel.setDisable(true);
        else
            add_classlevel.setDisable(false);
    }
    
    public void addClassroomAble(){
        if(classlevel.getText().isEmpty() || scoolyear.getText().isEmpty() || !ma())
            add_classroom.setDisable(true);
        else
            add_classroom.setDisable(false);
    }
    
    public void setClassroom(Classroom cc){
        System.out.println("vue.main.classes.cl.newcl.NewclController.setClassroom()  =====  "+cc);
        c=cc;
        if(c!=null){
            try {
                classlevel.setText(c.loadClasslevel().toString());
            } catch (Exception e) {
            }
            
            groupe.setText(c.getGroup());
            scoolyear.setText(c.getScoolYear());
            promotion.setText(c.getPromotion());
            System.out.println("vue.main.classes.cl.newcl.NewclController.setClassroom()   "+c.getContribution());
            try {
                contribution.setText(c.getContribution().toString());
            } catch (Exception e) {
            }
            
            classlevel_select.setItems(FXCollections.observableList(classlevels));
            groupe_select.setItems(FXCollections.observableList(groupes));
            scoolyear_select.setItems(FXCollections.observableList(scoolyears));
            classlevel_select.setValue(Classlevel.get(classlevel_select.getItems(), c.getClasslevel()));
            groupe_select.setValue(get(groupe_select.getItems(), c.getGroup()));
            scoolyear_select.setValue(get(scoolyear_select.getItems(), c.getScoolYear()));
            loadCourses();
            setClasslevel(cc.getClasslevel());
        }
    }
    public void setClasslevel(Classlevel cc){
        cl=cc;
        if(c!=null){
            name.setText(cl.getName());
            n.setText(cl.getN());
            niveau.setText(cl.getNiveau());
            option.setText(cl.getOption());
            op.setText(cl.getOp());
            try {
                ctrb.setText(cl.getContribution().toString());
            } catch (Exception e) {
            }
            
        }
        
    }
    
    public void addClasslevel(){
        if(cl==null)
            cl= new Classlevel();
        try {
            cl.setContribution(Double.parseDouble(ctrb.getText()));
        } catch (Exception e) {
        }
        cl.setN(n.getText());
        cl.setName(name.getText());
        cl.setNiveau(niveau.getText());
        cl.setOp(op.getText());
        cl.setOption(option.getText());
        try {
            cl.setContribution(Double.valueOf(ctrb.getText()));
        } catch (Exception e) {
        }
        
        new ClasslevelFactory().setClasslevel(cl);
        classlevel_select.getItems().add(cl);
        classlevel_select.setValue(cl);
        doAnnuler();
    }
    
    public void addClassroom(){
        if(c==null)
            c= new Classroom();
        try {
            c.setContribution(Double.parseDouble(contribution.getText()));
            System.out.println("vue.main.classes.cl.newcl.NewclController.addClassroom() @@@@@@@@@@222 "+contribution.getText());
        } catch (Exception e) {
            try {
                c.setContribution(Double.parseDouble(ctrb.getText()));
            System.out.println("vue.main.classes.cl.newcl.NewclController.addClassroom() @@@@@@@@@@222 "+ctrb.getText());
            } catch (Exception ex) {}
        }
        c.setPromotion(promotion.getText());
        c.setScoolYear(scoolyear.getText());
        c.setGroup(groupe.getText());
        try {
            c.setClasslevel(classlevel_select.getValue());
        } catch (Exception e) {
        }
        new ClassroomFactory().setClassroom(c);
        saveCources();
        MainController.classesg=null;
        MainController.showClassesG();
    }
    
    public void initCourses(){
        courses.setItems(FXCollections.observableList(courseArray));
        courses.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<Course,String> nname = new TableColumn("Nom (Dénomination)");
        nname.setCellValueFactory((TableColumn.CellDataFeatures<Course, String> p) -> new ObservableValueBase<String>() {
            @Override
            public String getValue() {
                return p.getValue().getName();
            }
        });
        TableColumn<Course,String> profname = new TableColumn("Nom du professeur");
        profname.setCellValueFactory((TableColumn.CellDataFeatures<Course, String> p) -> new ObservableValueBase<String>() {
            @Override
            public String getValue() {
                return p.getValue().getProfname();
            }
        });
        TableColumn<Course,Integer> coef = new TableColumn("Coéfficient");
        coef.setCellValueFactory((TableColumn.CellDataFeatures<Course, Integer> p) -> new ObservableValueBase<Integer>() {
            @Override
            public Integer getValue() {
                return p.getValue().getCoeff();
            }
        });
        courses.getColumns().removeAll(courses.getColumns());
        courses.getColumns().addAll(nname,profname,coef);
        blic(courses);
        save.setDisable(true);
    }
    
    void blic(TableView table){
        
        Callback<TableColumn<Course, Integer>, TableCell<Course, Integer>> numberCellFactory = (TableColumn<Course, Integer> param) -> new TextFieldTableCell(new IntegerStringConverter());
        
        table.setEditable(true);
        
        ((TableColumn) table.getColumns().get(2)).setCellFactory(numberCellFactory);
        ((TableColumn) table.getColumns().get(2)).setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Course, Integer>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Course, Integer> t) {
                    try {
                        Course s = ((Course) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            );
                            s.setCoeff(t.getNewValue());
                            new CourseFactory().setCourse(s);
                    } catch (Exception e) {
                    }
                        
                    
                }
            }
        );
        ((TableColumn) table.getColumns().get(0)).setCellFactory(TextFieldTableCell.forTableColumn());
        ((TableColumn) table.getColumns().get(0)).setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Course, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Course, String> t) {
                    Course s = ((Course) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        );
                        s.setName(t.getNewValue());
                        new CourseFactory().setCourse(s);
                    
                }
            }
        );
        ((TableColumn) table.getColumns().get(1)).setCellFactory(TextFieldTableCell.forTableColumn());
        ((TableColumn) table.getColumns().get(1)).setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Course, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Course, String> t) {
                    Course s = ((Course) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        );
                        s.setProfname(t.getNewValue());
                        new CourseFactory().setCourse(s);
                    
                }
            }
        );
        
    }
    
    public void addNewCourse(){
        
        Course s = new Course();
        courses.getItems().add(s);
        courses.requestFocus();
        courses.getFocusModel().focus(courses.getItems().size()-1);
        courses.getSelectionModel().clearAndSelect(courses.getItems().size()-1);
        courses.scrollTo(s);
        save.setDisable(false);
    }
    
    public void saveCources(){
        Classlevel cl= classlevel_select.getValue();
        if(cl!=null){
            CourseFactory f = new CourseFactory();
            for(Course c:courses.getItems()){
                c.setClasslevel(cl);
                f.setCourse(c);
            }
            save.setDisable(true);
        }
    }
    
    public void loadCourses(){
        //saveCources();
        Classlevel cl= classlevel_select.getValue();
        if(cl!=null){
            System.out.println("vue.main.classes.cl.newcl.NewclController.loadCourses() cl!=null ### "+cl.getId());
            courseArray=new CourseFactory().getCoursesByClasslevelId(cl.getId());
            courses.getItems().removeAll(courses.getItems());
            courses.getItems().addAll(courseArray);
            System.out.println(courseArray);
        }else{
            System.out.println("vue.main.classes.cl.newcl.NewclController.loadCourses() cl==null");
            courses.setItems(FXCollections.observableList(temoin));
        }
            
        castClasslevel();
        addClasslevelAble();
    }
    
    public void scoolyearMatch(){
        bi.setVisible(!ma());
        addClassroomAble();
    }
    
    public boolean ma(){
        boolean b=false;
        String s= scoolyear.getText();
        if(s!=null && !s.isEmpty() )
            b = s.matches("[0-9]{4}-[0-9]{4}");
        else
            b=true;
        return b;
    }
    
    
    public static String get(List<String> cl,String c){
        for(String cc:cl){
            if(cc.equals(c))
                return cc;
        }
        return null;
    }
    
    public static void main(String[] args) {
        System.out.println(Double.valueOf(null));
    }
}
