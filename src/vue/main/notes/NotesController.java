/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package vue.main.notes;

import dao.ClassroomFactory;
import dao.NoteFactory;
import dao.SessionFactory;
import dao.StudentFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import model.Classroom;
import model.Course;
import model.Note;
import model.NoteBook;
import model.Session;
import model.Student;
import vue.main.MainController;

/**
 * FXML Controller class
 *
 * @author loussin
 */
public class NotesController implements Initializable {
    
    @FXML
    AnchorPane pane;
    @FXML
    AnchorPane ap;
    @FXML
    AnchorPane dp;
    @FXML
    VBox vbox;
    @FXML
    Label viewing;
    @FXML 
    TableView<Student> tableview;
    TableView<Student> student_notes;
    TableView<NoteBook> student_buletin;
    ArrayList<String> classe_par_list = new ArrayList<>();
    @FXML
    ComboBox<String> classe_par;
    @FXML
    ComboBox matieres;
    @FXML
    ComboBox<Classroom> Classroom_select;
    @FXML
    Button save;
    @FXML
    Button calculer_moyenne;
    
    ArrayList<Student> students;
    ArrayList<Course> courses;
    ArrayList<Classroom> classrooms;
    Student student;
    Classroom classroom;
    int course;
    int session;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("vue.main.notes.NotesController.initialize()");
        MainController.injectNotesController(this);
        classe_par_list.add("Matière");
        classe_par_list.add("Élève");
        classe_par.setItems(FXCollections.observableList(classe_par_list));
        viewing.setText("Notes ");
        classrooms=new ClassroomFactory().getClassrooms();
        Classroom_select.setItems(FXCollections.observableList(classrooms));
        if(students==null)
            students=new ArrayList<>();
        initStudentNoteTable(students,0,0);
    }    

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        
        Classroom ccc = classroom;
        try {
            for(Classroom c:classrooms){
                if(Objects.equals(c.getId(), classroom.getId())){
                    ccc=c;
                    break;
                }
            }
        } catch (Exception e) {
        }
            
        Classroom_select.setValue(ccc);
        
        this.classroom = classroom;
        students=new StudentFactory().getStudentsByClassroom(classroom.getId());
        courses=classroom.loadCourses();
        System.out.println("vue.main.notes.NotesController.setClassroom() ********** " + students);
        //matieres.getItems().removeAll(matieres.getItems());
        matieres.setItems(FXCollections.observableList(courses));
        matieres.setOnAction(event -> reload());
        classe_par.setValue(classe_par_list.get(0));
        swich();
    }
    
    public void setStudent(Student s){
        classe_par.setValue(classe_par_list.get(1));
        student = s;
        classroom=s.loadCl();
        if(classroom==null)
            classroom=Classroom_select.getValue();
        System.out.println(classroom);
        try {
            for(Classroom c:classrooms){
                if(Objects.equals(c.getId(), classroom.getId())){
                    classroom=c;
                    break;
                }
            }
        } catch (Exception e) {
        }
        Classroom_select.setValue(classroom);
        students=new StudentFactory().getStudentsByClassroom(classroom.getId());
        courses=classroom.loadCourses();
        //matieres.getItems().removeAll(matieres.getItems());
        System.out.println(students);
        matieres.setItems(FXCollections.observableList(students));
        matieres.setOnAction(event -> liLoad());
        liLoad();
    }
    
    public void liLoad(){
        
//        int i=0;
//        try {
//            i=((Course) matieres.getValue()).getId();
//        } catch (Exception e) {}
        matieres.setPromptText("Élèves");
        try {
            viewing.setText(matieres.getValue().toString());
        } catch (Exception e) {
        }
        
        initNoteTable(student , 0);
        System.out.println(students);
    }
    
    public void swich(){
        viewing.setText("");
        if(classe_par.getValue().equals(classe_par_list.get(0))){
            System.out.println("if");
            //matieres.getItems().removeAll(matieres.getItems());
            
            try {
                matieres.setItems(FXCollections.observableList(courses));
            } catch (Exception e) {
            }
            
            matieres.setOnAction(event -> reload());
            reload();
        }else{
            System.out.println("else");
            //matieres.getItems().removeAll(matieres.getItems());
            try {
                matieres.setItems(FXCollections.observableList(students));
            } catch (Exception e) {
            }
            
            matieres.setOnAction(event -> liLoad());
            liLoad();
        }
    }
        
    
    public void changeClassroom(){
        if(Classroom_select.getValue() != null)
            setClassroom(Classroom_select.getValue());
        students=new StudentFactory().getStudentsByClassroom(classroom.getId());
        courses=classroom.loadCourses();
    }
    
    public void reload(){
        matieres.setPromptText("Matières");
        System.out.println("vue.main.notes.NotesController.reload()");
//        if(!courses.isEmpty())
//            matieres.setValue(courses.get(0));
        try {
            viewing.setText(matieres.getValue().toString());
        } catch (Exception e) {
        }
//        
        int i=0;
        try {
            i=((Course) matieres.getValue()).getId();
        } catch (Exception e) {}
        initStudentNoteTable(students,i , 0);
        System.out.println(students);
    }
    
    
    
    public void initStudentNoteTable(ArrayList<Student> stuarray,Integer courseid,Integer sess){
        if(student_notes==null)
            student_notes=new TableView<>();
        System.out.println("vue.main.notes.NotesController.initStudentNoteTable() ******* "+stuarray);
        dopperStudents(stuarray);
        System.out.println("vue.main.notes.NotesController.initStudentNoteTable() student list out dopper = "+stuarray);
        
        student_notes.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        //student_notes.getItems().removeAll(student_notes.getItems());
        
        student_notes.setMinHeight(Region.USE_COMPUTED_SIZE);
        student_notes.setPrefHeight(Region.USE_COMPUTED_SIZE);
        student_notes.setMaxHeight(Double.MAX_VALUE);
        
        session = sess;
        course = courseid;
        
        TableColumn<Student,String> name = new TableColumn("Nom et prénoms");
        name.setCellValueFactory((TableColumn.CellDataFeatures<Student, String> p) -> new ObservableValueBase<String>() {
            @Override
            public String getValue() {
                return p.getValue().toString();
            }
        });
        TableColumn<Student,TableColumn> interros = new TableColumn("Interrogations");
        
        TableColumn<Student,Double> I1 = new TableColumn("1");
        I1.setCellValueFactory((TableColumn.CellDataFeatures<Student, Double> p) -> new ObservableValueBase<Double>() {
            @Override
            public Double getValue() {
                try {
                    return p.getValue().getNotebook(course).getSessions().get(session).getInterros().get(0).getValeur();
                } catch (Exception e) {
                    return null;
                }
                
            }
        });
        TableColumn<Student,Double> I2 = new TableColumn("2");
        I2.setCellValueFactory((TableColumn.CellDataFeatures<Student, Double> p) -> new ObservableValueBase<Double>() {
            @Override
            public Double getValue() {
                try {
                    return p.getValue().getNotebook(course).getSessions().get(session).getInterros().get(1).getValeur();
                } catch (Exception e) {
                    return null;
                }
                
            }
        });
        TableColumn<Student,Double> I3 = new TableColumn("3");
        I3.setCellValueFactory((TableColumn.CellDataFeatures<Student, Double> p) -> new ObservableValueBase<Double>() {
            @Override
            public Double getValue() {
                try {
                    return p.getValue().getNotebook(course).getSessions().get(session).getInterros().get(2).getValeur();
                } catch (Exception e) {
                    return null;
                }
                
            }
        });
        TableColumn<Student,Double> I4 = new TableColumn("4");
        I4.setCellValueFactory((TableColumn.CellDataFeatures<Student, Double> p) -> new ObservableValueBase<Double>() {
            @Override
            public Double getValue() {
                try {
                    return p.getValue().getNotebook(course).getSessions().get(session).getInterros().get(3).getValeur();
                } catch (Exception e) {
                    return null;
                }
                
            }
        });
        TableColumn<Student,Double> I5 = new TableColumn("5");
        I5.setCellValueFactory((TableColumn.CellDataFeatures<Student, Double> p) -> new ObservableValueBase<Double>() {
            @Override
            public Double getValue() {
                try {
                    return p.getValue().getNotebook(course).getSessions().get(session).getInterros().get(4).getValeur();
                } catch (Exception e) {
                    return null;
                }
                
            }
        });
        
        TableColumn<Student,Double> my_interro = new TableColumn("My.intr");
        my_interro.setCellValueFactory((TableColumn.CellDataFeatures<Student, Double> p) -> new ObservableValueBase<Double>() {
            @Override
            public Double getValue() {
                try {
                    System.out.println(".getValue ( moy interro )");
                    return p.getValue().getNotebook(course).getSessions().get(session).getInterroMoyenne();
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
                
            }
        });
        
        
        TableColumn<Student,TableColumn> devoirs = new TableColumn("Devoirs");
        
        TableColumn<Student,Double> D1 = new TableColumn("1");
        D1.setCellValueFactory((TableColumn.CellDataFeatures<Student, Double> p) -> new ObservableValueBase<Double>() {
            @Override
            public Double getValue() {
                try {
                    return p.getValue().getNotebook(course).getSessions().get(session).getDevoirs().get(0).getValeur();
                } catch (Exception e) {
                    return null;
                }
                
            }
        });
        TableColumn<Student,Double> D2 = new TableColumn("2");
        D2.setCellValueFactory((TableColumn.CellDataFeatures<Student, Double> p) -> new ObservableValueBase<Double>() {
            @Override
            public Double getValue() {
                try {
                    return p.getValue().getNotebook(course).getSessions().get(session).getDevoirs().get(1).getValeur();
                } catch (Exception e) {
                    return null;
                }
                
            }
        });
        
        TableColumn<Student,Double> moyenne = new TableColumn("Moyenne");
        moyenne.setCellValueFactory((TableColumn.CellDataFeatures<Student, Double> p) -> new ObservableValueBase<Double>() {
            @Override
            public Double getValue() {
                try {
                    return p.getValue().getNotebook(course).getSessions().get(session).getMoyenne();
                } catch (Exception e) {
                    return null;
                }
                
            }
        });
        
        
        
        TableColumn<Student,Integer> rang = new TableColumn("Rang");
        rang.setCellValueFactory((TableColumn.CellDataFeatures<Student, Integer> p) -> new ObservableValueBase<Integer>() {
            @Override
            public Integer getValue() {
                try {
                    return p.getValue().getNotebook(course).getSessions().get(session).getRange();
                } catch (Exception e) {
                    return null;
                }
                
            }
        });
        interros.getColumns().addAll(I1,I2,I3,I4,I5);
        devoirs.getColumns().addAll(D1,D2);
        student_notes.getColumns().removeAll(student_notes.getColumns());
        
        student_notes.getColumns().addAll(name,interros,my_interro,devoirs,moyenne,rang);
        blic(student_notes);
        System.out.println(stuarray);
        ObservableList<Student> osl=FXCollections.observableList(stuarray);
        System.out.println(osl.sorted());
        student_notes.setItems(osl);
        student_notes.requestFocus();
        
        try {
            vbox.getChildren().removeAll(vbox.getChildren());
        } catch (Exception e) {
            e.printStackTrace();
        }
        vbox.getChildren().addAll(ap,student_notes,dp);
        vbox.setVgrow(student_notes, Priority.ALWAYS);
    }

    
    public void initNoteTable(Student stu,Integer sess){
        if(student_buletin==null)
            student_buletin=new TableView<>();
        ArrayList<NoteBook> nblist;
        if(stu!=null){
            dopperStudent(stu);
            nblist= stu.getNotebooks();
        }else
            nblist=new ArrayList<>();
            
        
        student_buletin.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        //student_buletin.getItems().removeAll(student_buletin.getItems());
        
        student_buletin.setMinHeight(Region.USE_COMPUTED_SIZE);
        student_buletin.setPrefHeight(Region.USE_COMPUTED_SIZE);
        student_buletin.setMaxHeight(Double.MAX_VALUE);
        
        session = sess;
        
        TableColumn<NoteBook,String> name = new TableColumn("Matières");
        name.setCellValueFactory((TableColumn.CellDataFeatures<NoteBook, String> p) -> new ObservableValueBase<String>() {
            @Override
            public String getValue() {
                return p.getValue().toString();
            }
        });
        TableColumn<NoteBook,TableColumn> interros = new TableColumn("Interrogations");
        
        TableColumn<NoteBook,Double> I1 = new TableColumn("1");
        I1.setCellValueFactory((TableColumn.CellDataFeatures<NoteBook, Double> p) -> new ObservableValueBase<Double>() {
            @Override
            public Double getValue() {
                try {
                    return p.getValue().getSessions().get(session).getInterros().get(0).getValeur();
                } catch (Exception e) {
                    return null;
                }
                
            }
        });
        TableColumn<NoteBook,Double> I2 = new TableColumn("2");
        I2.setCellValueFactory((TableColumn.CellDataFeatures<NoteBook, Double> p) -> new ObservableValueBase<Double>() {
            @Override
            public Double getValue() {
                try {
                    return p.getValue().getSessions().get(session).getInterros().get(1).getValeur();
                } catch (Exception e) {
                    return null;
                }
                
            }
        });
        TableColumn<NoteBook,Double> I3 = new TableColumn("3");
        I3.setCellValueFactory((TableColumn.CellDataFeatures<NoteBook, Double> p) -> new ObservableValueBase<Double>() {
            @Override
            public Double getValue() {
                try {
                    return p.getValue().getSessions().get(session).getInterros().get(2).getValeur();
                } catch (Exception e) {
                    return null;
                }
                
            }
        });
        TableColumn<NoteBook,Double> I4 = new TableColumn("4");
        I4.setCellValueFactory((TableColumn.CellDataFeatures<NoteBook, Double> p) -> new ObservableValueBase<Double>() {
            @Override
            public Double getValue() {
                try {
                    return p.getValue().getSessions().get(session).getInterros().get(3).getValeur();
                } catch (Exception e) {
                    return null;
                }
                
            }
        });
        TableColumn<NoteBook,Double> I5 = new TableColumn("5");
        I5.setCellValueFactory((TableColumn.CellDataFeatures<NoteBook, Double> p) -> new ObservableValueBase<Double>() {
            @Override
            public Double getValue() {
                try {
                    return p.getValue().getSessions().get(session).getInterros().get(4).getValeur();
                } catch (Exception e) {
                    return null;
                }
                
            }
        });
        
        TableColumn<NoteBook,Double> my_interro = new TableColumn("My.intr");
        my_interro.setCellValueFactory((TableColumn.CellDataFeatures<NoteBook, Double> p) -> new ObservableValueBase<Double>() {
            @Override
            public Double getValue() {
                try {
                    System.out.println(".getValue ( moy interro )");
                    return p.getValue().getSessions().get(session).getInterroMoyenne();
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
                
            }
        });
        
        
        TableColumn<NoteBook,TableColumn> devoirs = new TableColumn("Devoirs");
        
        TableColumn<NoteBook,Double> D1 = new TableColumn("1");
        D1.setCellValueFactory((TableColumn.CellDataFeatures<NoteBook, Double> p) -> new ObservableValueBase<Double>() {
            @Override
            public Double getValue() {
                try {
                    return p.getValue().getSessions().get(session).getDevoirs().get(0).getValeur();
                } catch (Exception e) {
                    return null;
                }
                
            }
        });
        TableColumn<NoteBook,Double> D2 = new TableColumn("2");
        D2.setCellValueFactory((TableColumn.CellDataFeatures<NoteBook, Double> p) -> new ObservableValueBase<Double>() {
            @Override
            public Double getValue() {
                try {
                    return p.getValue().getSessions().get(session).getDevoirs().get(1).getValeur();
                } catch (Exception e) {
                    return null;
                }
                
            }
        });
        
        TableColumn<NoteBook,Double> moyenne = new TableColumn("Moyenne");
        moyenne.setCellValueFactory((TableColumn.CellDataFeatures<NoteBook, Double> p) -> new ObservableValueBase<Double>() {
            @Override
            public Double getValue() {
                try {
                    return p.getValue().getSessions().get(session).getMoyenne();
                } catch (Exception e) {
                    return null;
                }
                
            }
        });
        
        
        
        TableColumn<NoteBook,Integer> rang = new TableColumn("Rang");
        rang.setCellValueFactory((TableColumn.CellDataFeatures<NoteBook, Integer> p) -> new ObservableValueBase<Integer>() {
            @Override
            public Integer getValue() {
                try {
                    return p.getValue().getSessions().get(session).getRange();
                } catch (Exception e) {
                    return null;
                }
                
            }
        });
        interros.getColumns().addAll(I1,I2,I3,I4,I5);
        devoirs.getColumns().addAll(D1,D2);
        student_buletin.getColumns().removeAll(student_buletin.getColumns());
        
        student_buletin.getColumns().addAll(name,interros,my_interro,devoirs,moyenne,rang);
        bloc(student_buletin);
        ObservableList<NoteBook> osl=FXCollections.observableList(nblist);
        System.out.println(osl.sorted());
        student_buletin.setItems(osl);
        student_buletin.requestFocus();
        
        try {
            vbox.getChildren().removeAll(vbox.getChildren());
        } catch (Exception e) {
            e.printStackTrace();
        }
        vbox.getChildren().addAll(ap,student_buletin,dp);
        vbox.setVgrow(student_buletin, Priority.ALWAYS);
    }

    
    
    
    
    static void dopperStudents(ArrayList<Student> list){
        System.out.println("vue.main.notes.NotesController.dopperStudents() list on start = "+list);
        try {
            for(Student s:list)
                dopperStudent(s);
        } catch (Exception e) {
            Logger.getLogger(NotesController.class.getName()).log(Level.SEVERE, null, e);
        }
        System.out.println("vue.main.notes.NotesController.dopperStudents() list at end = "+list);
    }
    
    static void dopperStudent(Student s){
        try {
            if(!s.isDopped()){
                System.out.println("!s.isDopped() = "+!s.isDopped());
                for(NoteBook nb:s.loadNotebooks()){
                    for(Session se:nb.loadSessions()){
                        System.out.println("vue.main.notes.NotesController.dopperStudents() NoteList :  "+se.loadNotes());
                    }
                }
                s.setDopped(true);
            }else
                System.out.println("!s.isDopped() = "+!s.isDopped());
        } catch (Exception e) {
            Logger.getLogger(NotesController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    void blic(TableView<Student> table){
        Callback<TableColumn<Student, Double>, TableCell<Student, Double>> numberCellFactory = (TableColumn<Student, Double> param) -> { 
            TextFieldTableCell g= new TextFieldTableCell(new DoubleStringConverter(){
                @Override
                public Double fromString(String value) {
                    // If the specified value is null or zero-length, return null
                    if (value == null) {
                        return null;
                    }

                    value = value.trim();

                    if (value.length() < 1) {
                        return null;
                    }
                    try {
                        return Double.valueOf(value);
                    } catch (Exception e) {
                        return null;
                    }
                }
                
                @Override
                public String toString(Double value) {
                    // If the specified value is null, return a zero-length String
                    if (value == null) {
                        return "";
                    }
                    try {
                        return Double.toString(value.doubleValue());
                    } catch (Exception e) {
                        return null;
                    }                    
                }

            });
            return g;
        };
        
        table.setEditable(true);
        
        ((TableColumn)((TableColumn) table.getColumns().get(1)).getColumns().get(0)).setCellFactory(numberCellFactory);
        ((TableColumn)((TableColumn) table.getColumns().get(1)).getColumns().get(0))
                .setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Student, Double>>() {
                        @Override
                        public void handle(TableColumn.CellEditEvent<Student, Double> t) {
                            Student s = ((Student) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                                );
                            try {
                                Session se=s.getNotebook(course).getSessions().size()>0?s.getNotebook(course).getSessions().get(session):null;
                                if(se==null){
                                    se=new Session(true);
                                    se.setNoteBookId(s.getNotebook(course).getId());
                                    new SessionFactory().setSession(se); s.getNotebook(course).getSessions().add(se);
                                }
                                while(se.getInterros().size()<1)
                                    se.getInterros().add(new Note(se.getId()));
                                se.getInterros().get(0).setValeur(t.getNewValue());   
                                save.setDisable(false);                                 
                            } catch (Exception e) {
                            }
                            NoteBook me=s.getNotebook(course);
                            Session de=me.getSessions().get(session);
                            de.setModified(true);
                        }
                    }
                );
        ((TableColumn)((TableColumn) table.getColumns().get(1)).getColumns().get(1)).setCellFactory(numberCellFactory);
        ((TableColumn)((TableColumn) table.getColumns().get(1)).getColumns().get(1))
                .setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Student, Double>>() {
                        @Override
                        public void handle(TableColumn.CellEditEvent<Student, Double> t) {
                            Student s = ((Student) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                                );
                            try {
                                Session se=s.getNotebook(course).getSessions().size()>0?s.getNotebook(course).getSessions().get(session):null;
                                if(se==null){
                                    se=new Session(true);
                                    se.setNoteBookId(s.getNotebook(course).getId());
                                    new SessionFactory().setSession(se); s.getNotebook(course).getSessions().add(se);
                                }
                                while(se.getInterros().size()<2)
                                    se.getInterros().add(new Note(se.getId()));
                                se.getInterros().get(1).setValeur(t.getNewValue());   
                                save.setDisable(false);                                 
                            } catch (Exception e) {
                            }
                            s.getNotebook(course).getSessions().get(session).setModified(true);
                        }
                    }
                );
        ((TableColumn)((TableColumn) table.getColumns().get(1)).getColumns().get(2)).setCellFactory(numberCellFactory);
        ((TableColumn)((TableColumn) table.getColumns().get(1)).getColumns().get(2))
                .setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Student, Double>>() {
                        @Override
                        public void handle(TableColumn.CellEditEvent<Student, Double> t) {
                            Student s = ((Student) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                                );
                            try {
                                Session se=s.getNotebook(course).getSessions().size()>0?s.getNotebook(course).getSessions().get(session):null;
                                if(se==null){
                                    se=new Session(true);
                                    se.setNoteBookId(s.getNotebook(course).getId());
                                    new SessionFactory().setSession(se); s.getNotebook(course).getSessions().add(se);
                                }
                                while(se.getInterros().size()<3)
                                    se.getInterros().add(new Note(se.getId()));
                                se.getInterros().get(2).setValeur(t.getNewValue());    
                                save.setDisable(false);                                
                            } catch (Exception e) {
                            }
                            s.getNotebook(course).getSessions().get(session).setModified(true);
                        }
                    }
                );
        ((TableColumn)((TableColumn) table.getColumns().get(1)).getColumns().get(3)).setCellFactory(numberCellFactory);
        ((TableColumn)((TableColumn) table.getColumns().get(1)).getColumns().get(3))
                .setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Student, Double>>() {
                        @Override
                        public void handle(TableColumn.CellEditEvent<Student, Double> t) {
                            Student s = ((Student) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                                );
                            try {
                                Session se=s.getNotebook(course).getSessions().size()>0?s.getNotebook(course).getSessions().get(session):null;
                                if(se==null){
                                    se=new Session(true);
                                    se.setNoteBookId(s.getNotebook(course).getId());
                                    new SessionFactory().setSession(se); s.getNotebook(course).getSessions().add(se);
                                }
                                while(se.getInterros().size()<4)
                                    se.getInterros().add(new Note(se.getId()));
                                se.getInterros().get(3).setValeur(t.getNewValue()); 
                                save.setDisable(false);                                   
                            } catch (Exception e) {
                            }
                            s.getNotebook(course).getSessions().get(session).setModified(true);
                        }
                    }
                );
        ((TableColumn)((TableColumn) table.getColumns().get(1)).getColumns().get(4)).setCellFactory(numberCellFactory);
        ((TableColumn)((TableColumn) table.getColumns().get(1)).getColumns().get(4))
                .setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Student, Double>>() {
                        @Override
                        public void handle(TableColumn.CellEditEvent<Student, Double> t) {
                            Student s = ((Student) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                                );
                            try {
                                Session se=s.getNotebook(course).getSessions().size()>0?s.getNotebook(course).getSessions().get(session):null;
                                if(se==null){
                                    se=new Session(true);
                                    se.setNoteBookId(s.getNotebook(course).getId());
                                    new SessionFactory().setSession(se); s.getNotebook(course).getSessions().add(se);
                                }
                                while(se.getInterros().size()<5)
                                    se.getInterros().add(new Note(se.getId()));
                                se.getInterros().get(4).setValeur(t.getNewValue());
                                save.setDisable(false);
                            } catch (Exception e) {
                            }
                            s.getNotebook(course).getSessions().get(session).setModified(true);
                        }
                    }
                );
        ((TableColumn)((TableColumn) table.getColumns().get(3)).getColumns().get(0)).setCellFactory(numberCellFactory);
        ((TableColumn)((TableColumn) table.getColumns().get(3)).getColumns().get(0))
                .setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Student, Double>>() {
                        @Override
                        public void handle(TableColumn.CellEditEvent<Student, Double> t) {
                            Student s = ((Student) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                                );
                            try {
                                Session se=s.getNotebook(course).getSessions().size()>0?s.getNotebook(course).getSessions().get(session):null;
                                if(se==null){
                                    se=new Session(true);
                                    se.setNoteBookId(s.getNotebook(course).getId());
                                    new SessionFactory().setSession(se); s.getNotebook(course).getSessions().add(se);
                                }
                                while(se.getDevoirs().size()<1)
                                    se.getDevoirs().add(new Note(se.getId()).setIsDevoir(true));
                                se.getDevoirs().get(0).setValeur(t.getNewValue());
                                save.setDisable(false);
                            } catch (Exception e) {
                            }
                            s.getNotebook(course).getSessions().get(session).setModified(true);
                        }
                    }
                );
        ((TableColumn)((TableColumn) table.getColumns().get(3)).getColumns().get(1)).setCellFactory(numberCellFactory);
        ((TableColumn)((TableColumn) table.getColumns().get(3)).getColumns().get(1))
                .setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Student, Double>>() {
                        @Override
                        public void handle(TableColumn.CellEditEvent<Student, Double> t) {
                            Student s = ((Student) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                                );
                            try {
                                Session se=s.getNotebook(course).getSessions().size()>0?s.getNotebook(course).getSessions().get(session):null;
                                if(se==null){
                                    se=new Session(true);
                                    se.setNoteBookId(s.getNotebook(course).getId());
                                    new SessionFactory().setSession(se); s.getNotebook(course).getSessions().add(se);
                                }
                                while(se.getDevoirs().size()<2)
                                    se.getDevoirs().add(new Note(se.getId()).setIsDevoir(true));
                                se.getDevoirs().get(1).setValeur(t.getNewValue());
                                save.setDisable(false);
                            } catch (Exception e) {
                            }
                            s.getNotebook(course).getSessions().get(session).setModified(true);
                        }
                    }
                );
    } 
    
    void bloc(TableView<NoteBook> table){
        Callback<TableColumn<NoteBook, Double>, TableCell<Student, Double>> numberCellFactory = (TableColumn<NoteBook, Double> param) -> { 
            TextFieldTableCell g= new TextFieldTableCell(new DoubleStringConverter(){
                @Override
                public Double fromString(String value) {
                    // If the specified value is null or zero-length, return null
                    if (value == null) {
                        return null;
                    }

                    value = value.trim();

                    if (value.length() < 1) {
                        return null;
                    }
                    try {
                        return Double.valueOf(value);
                    } catch (Exception e) {
                        return null;
                    }
                }
                
                @Override
                public String toString(Double value) {
                    // If the specified value is null, return a zero-length String
                    if (value == null) {
                        return "";
                    }
                    try {
                        return Double.toString(value.doubleValue());
                    } catch (Exception e) {
                        return null;
                    }                    
                }

            });
            return g;
        };
        
        table.setEditable(true);
        
        ((TableColumn)((TableColumn) table.getColumns().get(1)).getColumns().get(0)).setCellFactory(numberCellFactory);
        ((TableColumn)((TableColumn) table.getColumns().get(1)).getColumns().get(0))
                .setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<NoteBook, Double>>() {
                        @Override
                        public void handle(TableColumn.CellEditEvent<NoteBook, Double> t) {
                            NoteBook s = ((NoteBook) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                                );
                            try {
                                Session se=s.getSessions().size()>0?s.getSessions().get(session):null;
                                if(se==null){
                                    se=new Session(true);
                                    se.setNoteBookId(s.getId());
                                    new SessionFactory().setSession(se); s.getSessions().add(se);
                                }
                                while(se.getInterros().size()<1)
                                    se.getInterros().add(new Note(se.getId()));
                                se.getInterros().get(0).setValeur(t.getNewValue());   
                                save.setDisable(false);                                 
                            } catch (Exception e) {
                            }
                            NoteBook me=s;
                            Session de=me.getSessions().get(session);
                            de.setModified(true);
                        }
                    }
                );
        ((TableColumn)((TableColumn) table.getColumns().get(1)).getColumns().get(1)).setCellFactory(numberCellFactory);
        ((TableColumn)((TableColumn) table.getColumns().get(1)).getColumns().get(1))
                .setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<NoteBook, Double>>() {
                        @Override
                        public void handle(TableColumn.CellEditEvent<NoteBook, Double> t) {
                            NoteBook s = ((NoteBook) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                                );
                            try {
                                Session se=s.getSessions().size()>0?s.getSessions().get(session):null;
                                if(se==null){
                                    se=new Session(true);
                                    se.setNoteBookId(s.getId());
                                    new SessionFactory().setSession(se); s.getSessions().add(se);
                                }
                                while(se.getInterros().size()<2)
                                    se.getInterros().add(new Note(se.getId()));
                                se.getInterros().get(1).setValeur(t.getNewValue());   
                                save.setDisable(false);                                 
                            } catch (Exception e) {
                            }
                            s.getSessions().get(session).setModified(true);
                        }
                    }
                );
        ((TableColumn)((TableColumn) table.getColumns().get(1)).getColumns().get(2)).setCellFactory(numberCellFactory);
        ((TableColumn)((TableColumn) table.getColumns().get(1)).getColumns().get(2))
                .setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<NoteBook, Double>>() {
                        @Override
                        public void handle(TableColumn.CellEditEvent<NoteBook, Double> t) {
                            NoteBook s = ((NoteBook) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                                );
                            try {
                                Session se=s.getSessions().size()>0?s.getSessions().get(session):null;
                                if(se==null){
                                    se=new Session(true);
                                    se.setNoteBookId(s.getId());
                                    new SessionFactory().setSession(se); s.getSessions().add(se);
                                }
                                while(se.getInterros().size()<3)
                                    se.getInterros().add(new Note(se.getId()));
                                se.getInterros().get(2).setValeur(t.getNewValue());    
                                save.setDisable(false);                                
                            } catch (Exception e) {
                            }
                            s.getSessions().get(session).setModified(true);
                        }
                    }
                );
        ((TableColumn)((TableColumn) table.getColumns().get(1)).getColumns().get(3)).setCellFactory(numberCellFactory);
        ((TableColumn)((TableColumn) table.getColumns().get(1)).getColumns().get(3))
                .setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<NoteBook, Double>>() {
                        @Override
                        public void handle(TableColumn.CellEditEvent<NoteBook, Double> t) {
                            NoteBook s = ((NoteBook) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                                );
                            try {
                                Session se=s.getSessions().size()>0?s.getSessions().get(session):null;
                                if(se==null){
                                    se=new Session(true);
                                    se.setNoteBookId(s.getId());
                                    new SessionFactory().setSession(se); s.getSessions().add(se);
                                }
                                while(se.getInterros().size()<4)
                                    se.getInterros().add(new Note(se.getId()));
                                se.getInterros().get(3).setValeur(t.getNewValue()); 
                                save.setDisable(false);                                   
                            } catch (Exception e) {
                            }
                            s.getSessions().get(session).setModified(true);
                        }
                    }
                );
        ((TableColumn)((TableColumn) table.getColumns().get(1)).getColumns().get(4)).setCellFactory(numberCellFactory);
        ((TableColumn)((TableColumn) table.getColumns().get(1)).getColumns().get(4))
                .setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<NoteBook, Double>>() {
                        @Override
                        public void handle(TableColumn.CellEditEvent<NoteBook, Double> t) {
                            NoteBook s = ((NoteBook) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                                );
                            try {
                                Session se=s.getSessions().size()>0?s.getSessions().get(session):null;
                                if(se==null){
                                    se=new Session(true);
                                    se.setNoteBookId(s.getId());
                                    new SessionFactory().setSession(se); s.getSessions().add(se);
                                }
                                while(se.getInterros().size()<5)
                                    se.getInterros().add(new Note(se.getId()));
                                se.getInterros().get(4).setValeur(t.getNewValue());
                                save.setDisable(false);
                            } catch (Exception e) {
                            }
                            s.getSessions().get(session).setModified(true);
                        }
                    }
                );
        ((TableColumn)((TableColumn) table.getColumns().get(3)).getColumns().get(0)).setCellFactory(numberCellFactory);
        ((TableColumn)((TableColumn) table.getColumns().get(3)).getColumns().get(0))
                .setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<NoteBook, Double>>() {
                        @Override
                        public void handle(TableColumn.CellEditEvent<NoteBook, Double> t) {
                            NoteBook s = ((NoteBook) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                                );
                            try {
                                Session se=s.getSessions().size()>0?s.getSessions().get(session):null;
                                if(se==null){
                                    se=new Session(true);
                                    se.setNoteBookId(s.getId());
                                    new SessionFactory().setSession(se); s.getSessions().add(se);
                                }
                                while(se.getDevoirs().size()<1)
                                    se.getDevoirs().add(new Note(se.getId()).setIsDevoir(true));
                                se.getDevoirs().get(0).setValeur(t.getNewValue());
                                save.setDisable(false);
                            } catch (Exception e) {
                            }
                            s.getSessions().get(session).setModified(true);
                        }
                    }
                );
        ((TableColumn)((TableColumn) table.getColumns().get(3)).getColumns().get(1)).setCellFactory(numberCellFactory);
        ((TableColumn)((TableColumn) table.getColumns().get(3)).getColumns().get(1))
                .setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<NoteBook, Double>>() {
                        @Override
                        public void handle(TableColumn.CellEditEvent<NoteBook, Double> t) {
                            NoteBook s = ((NoteBook) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                                );
                            try {
                                Session se=s.getSessions().size()>0?s.getSessions().get(session):null;
                                if(se==null){
                                    se=new Session(true);
                                    se.setNoteBookId(s.getId());
                                    new SessionFactory().setSession(se); s.getSessions().add(se);
                                }
                                while(se.getDevoirs().size()<2)
                                    se.getDevoirs().add(new Note(se.getId()).setIsDevoir(true));
                                se.getDevoirs().get(1).setValeur(t.getNewValue());
                                save.setDisable(false);
                            } catch (Exception e) {
                            }
                            s.getSessions().get(session).setModified(true);
                        }
                    }
                );
    }
    
    /**
     * Sauvegarde les donnees modifies de la table
     */
    
    public void save(){
        System.out.print("vue.main.notes.NotesController.save() : ");
        try {
            for(Student s:students)
                if(s.getNotebook(course).getSessions().get(session).isModified()){
                    System.out.println(s.getNotebook(course).getSessions().get(session).getInterroMoyenne());
                    new SessionFactory().setSession(s.getNotebook(course).getSessions().get(session));
                    NoteFactory nf=new NoteFactory();
                    for(Note n:s.getNotebook(course).getSessions().get(session).getInterros())
                        nf.setNote(n);
                    for(Note n:s.getNotebook(course).getSessions().get(session).getDevoirs())
                        nf.setNote(n);
                }else
                    System.out.println("Nothing to save");
        } catch (Exception e) {
            e.printStackTrace();
        }
        save.setDisable(true);
    }
    public void calculerMoy(){
        save();
        try {
            for(Student s:students)
                calculerMoyG(s);
        } catch (Exception e) {
        }
        save();
    }
    
    Double calculerMoyIntero(Student s){
        System.out.println("vue.main.notes.NotesController.calculerMoyIntero()");
        try {
            Double res=0.0;
            int count=0;
            for (int i = 0; i < s.getNotebook(course).getSessions().get(session).getInterros().size(); i++) {
                Note n=s.getNotebook(course).getSessions().get(session).getInterros().get(i);
                if(n!=null && n.getValeur()!=null){
                    res += n.getValeur();
                    count++;
                }
            }
            res/=count;
            s.getNotebook(course).getSessions().get(session).setInterroMoyenne(res);
            s.getNotebook(course).getSessions().get(session).setModified(true);
            save.setDisable(false);
            return res ;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
            
    }
    Double calculerMoyG(Student s){
        System.out.println("vue.main.notes.NotesController.calculerMoyG()");
        try {
            Double a=calculerMoyIntero(s);
            Double res=0.0;
            int count=0;
            for (int i = 0; i < s.getNotebook(course).getSessions().get(session).getDevoirs().size(); i++) {
                Note n=s.getNotebook(course).getSessions().get(session).getDevoirs().get(i);
                if(n!=null && n.getValeur()!=null){
                    res += n.getValeur();
                    count++;
                }
            }
            if(a!=null){
                res+=a;
                count++;
            }
            res/=count;
            s.getNotebook(course).getSessions().get(session).setMoyenne(res);
            s.getNotebook(course).getSessions().get(session).setModified(true);
            save.setDisable(false);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void main(String[] args) {
        ArrayList<String> st = new ArrayList<>();
        st.add("a");
        st.add("b");
        st.add("c");
        System.out.println(st);
        ObservableList obl= FXCollections.observableList(st);
        System.out.println(obl);
        obl.add("d");
        ObservableList obl2= FXCollections.observableList(st);
        obl2.add("e");
        System.out.println(obl2);
        System.out.println(obl);
    }
}
