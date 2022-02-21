/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package vue.main.student;

import dao.ClassroomFactory;
import dao.StudentFactory;
import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
import model.Classroom;
import model.Student;
import vue.main.MainController;

/**
 * FXML Controller class
 *
 * @author loussin
 */
public class StudentController implements Initializable {

    
    
    @FXML
    Label classroom;
    
    @FXML 
    TableView<Student> student_list;
    static TableView<Student> student_list_view;
    
    @FXML
    Button all;
    
    @FXML
    Button addstu;   
    
    @FXML
    Button save;
    
    @FXML
    public ComboBox<Classroom> cls_select;
    
    @FXML
    CheckBox fast;
    
    @FXML
    ScrollPane general_view;
    
    @FXML
    AnchorPane ap;
    
    @FXML
    AnchorPane pane;
    
    StudentFactory stf=new StudentFactory();
    ClassroomFactory crf= new ClassroomFactory();
    public ArrayList<Classroom> cl_ls = crf.getClassrooms();
    ObservableList<Classroom> crl = FXCollections.observableList(cl_ls);
    public static ObservableList<Student> stl;
    ObservableList<Student> results;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MainController.injectStudentController(this);
        stl = MainController.getStl();
        results = FXCollections.observableList(stl);
        setStudent_list_view(student_list);
        classroom.setText("Toutes les clases");
        classroom.setMaxWidth(Double.MAX_VALUE);
        ap.setLeftAnchor(classroom, 0.0);
        ap.setRightAnchor(classroom, 0.0);
        classroom.setAlignment(Pos.CENTER);
        cls_select.setItems(crl);
        initStudentTable();
        
        
    }    

    public static void setStudent_list_view(TableView<Student> student_list_view) {
        StudentController.student_list_view = student_list_view;
    }
    
    
    
   public void initStudentTable(){
        
        student_list_view.setItems(stl);
        TableColumn<Student,Integer> mtnoCol = new TableColumn("Matricule");
        mtnoCol.setCellValueFactory((TableColumn.CellDataFeatures<Student, Integer> p) -> new ObservableValueBase<Integer>() {
            @Override
            public Integer getValue() {
                return p.getValue().getMatricule();
            }
        });
        TableColumn<Student,String> NameCol = new TableColumn("Nom");
        NameCol.setCellValueFactory((TableColumn.CellDataFeatures<Student, String> p) -> new ObservableValueBase<String>() {
            @Override
            public String getValue() {
                return p.getValue().getName();
            }
        });
        TableColumn<Student,String> firstNameCol = new TableColumn("Prenoms");
        firstNameCol.setCellValueFactory((TableColumn.CellDataFeatures<Student, String> p) -> new ObservableValueBase<String>() {
            @Override
            public String getValue() {
                return p.getValue().getFirstnames();
            }
        });
        
        TableColumn<Student,Date> dobCol = new TableColumn("Date de naissance");
        dobCol.setCellValueFactory((TableColumn.CellDataFeatures<Student, Date> p) -> new ObservableValueBase<Date>() {
            @Override
            public Date getValue() {
                return p.getValue().getBirthday();
            }
        });
        TableColumn<Student,Date> insdCol = new TableColumn("Date d'insscription");
        insdCol.setCellValueFactory((TableColumn.CellDataFeatures<Student, Date> p) -> new ObservableValueBase<Date>() {
            @Override
            public Date getValue() {
                return p.getValue().getInscriptionDate();
            }
        });
        
        TableColumn<Student, Void> action = new TableColumn("Action");
        action.setCellFactory(col -> new TableCell<Student, Void>() {
            private final Button container;
            {
                container=new Button("Afficher");
                container.setOnAction(evt->{
                    MainController.showStudent((Student)getTableRow().getItem());
                });
            }
            @Override
            public void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : container);
            }
        });
        student_list_view.getColumns().removeAll(student_list_view.getColumns());
        student_list_view.getColumns().addAll(mtnoCol,NameCol,firstNameCol,dobCol,insdCol,action);
        blic(student_list_view);
        
        
    }
        
        
    public void loadData() {
        //System.out.println(cls_select.getValue());
        if(cls_select.getValue()!=null){
            Integer searchText = cls_select.getValue().getId();

            classroom.setText(cls_select.getValue().toString());
            Task<ObservableList<Student>> task = new Task() {
                @Override
                protected ObservableList<Student> call() throws Exception {
                    updateMessage("Loading data");
                    //System.out.println(".call()");
                    return FXCollections.observableArrayList(stl
                            .stream()
                            .filter(value -> searchText.equals(value.getClassroom()))
                            .collect(Collectors.toList()));
                }
            };
            task.setOnSucceeded(event -> {
                //System.out.println("succeeded");
                results = task.getValue();
                student_list_view.setItems(FXCollections.observableList(results));
            });
            task.setOnRunning((event) -> {
                //System.out.println("running");
            });
            task.setOnFailed((event) -> {
                //System.out.println("failed");
            });
            Thread th = new Thread(task);
            th.setDaemon(true);
            th.start();
        }

    }
    
    public void tim(){
        classroom.setText("Toutes les clases");
        student_list_view.setItems(stl);
        cls_select.setValue(null);
        cls_select.setOnAction(event -> loadData());
    }
    
    
    public void addStudent(){
        if(fast.isSelected()){
            Student s = new Student();
//            s.setClassroom(cls_select.getValue()!=null?cls_select.getValue().getId():null);
            s.setInscriptionDate(new Date(new java.util.Date().getTime()));
            //System.out.println(s.getInscriptionDate());
            stl.add(s);
            loadData();
            student_list_view.requestFocus();
            student_list_view.getFocusModel().focus(stl.size()-1);
            student_list_view.getSelectionModel().clearAndSelect(stl.size()-1);
            student_list_view.scrollTo(s);
        }else{
            MainController.showNewstuG();
        }
            
    }
    
    void blic(TableView<Student> table) {
        
        
        Callback<TableColumn<Student, Integer>, TableCell<Student, Integer>> numberCellFactory = new Callback<TableColumn<Student, Integer>, TableCell<Student, Integer>>() {
            @Override
            public TableCell<Student, Integer> call(TableColumn<Student, Integer> param) {
                TextFieldTableCell t =  new TextFieldTableCell(new IntegerStringConverter());
                t.setOnTouchReleased(evt->{
                    
                });
                return t;
            }
        };
        
        table.setEditable(true);
        
        ((TableColumn) table.getColumns().get(0)).setCellFactory(numberCellFactory);
        ((TableColumn) table.getColumns().get(0)).setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Student, Integer>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Student, Integer> t) {
                    Student s = ((Student) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        );
                        s.setMatricule(t.getNewValue().toString());
                        s.setModified(true); save.setVisible(true);
                    
                }
            }
        );
        ((TableColumn) table.getColumns().get(1)).setCellFactory(TextFieldTableCell.forTableColumn());
        ((TableColumn) table.getColumns().get(1)).setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Student, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Student, String> t) {
                    Student s = ((Student) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        );
                    s.setName(t.getNewValue());
                        s.setModified(true); save.setVisible(true);
                }
            }
        );
        ((TableColumn) table.getColumns().get(2)).setCellFactory(TextFieldTableCell.forTableColumn());
        ((TableColumn) table.getColumns().get(2)).setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Student, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Student, String> t) {
                    Student s = ((Student) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        );
                    s.setFirstnames(t.getNewValue());
                        s.setModified(true); save.setVisible(true);
                }
            }
        );
        
        Callback<TableColumn<Student, Date>, TableCell<Student, Date>> dateCellFactory = (TableColumn<Student, Date> param) -> new DateEditingCell();
        
        ((TableColumn) table.getColumns().get(3)).setCellFactory(dateCellFactory);
        ((TableColumn) table.getColumns().get(3)).setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Student, Date>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Student, Date> t) {
                    Student s = ((Student) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        );
                    s.setBirthday(t.getNewValue());
                        s.setModified(true); save.setVisible(true);
                }
            }
        );
        ((TableColumn) table.getColumns().get(4)).setCellFactory(dateCellFactory);
        ((TableColumn) table.getColumns().get(4)).setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Student, Date>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Student, Date> t) {
                    Student s = ((Student) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        );
                    s.setInscriptionDate(t.getNewValue());
                    s.setModified(true); save.setVisible(true);
                }
            }
        );
    }
    
    public void saveModifieds(){
        for(Student s: stl){
            if(s.isModified()){
                stf.setStudent(s);
                s.setModified(false);
            }
                
        }
        save.setVisible(false);
    }
    
    public static void refreshTable(){
        student_list_view.refresh();
    }
    public static void refreshTableAndTim(){
        student_list_view.refresh();
//        tim();
    }
    
    public void reload(){
        MainController.reloadStudentG();
    }
}



    class DateEditingCell extends TableCell<Student, Date> {

        private DatePicker datePicker;

        DateEditingCell() {
        }

        @Override
        public void startEdit() {
            if (!isEmpty()) {
                super.startEdit();
                createDatePicker();
                setText(null);
                setGraphic(datePicker);
            }
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();

                    if (getDate()!=null) {
                        setText(getDate().toString());
                    } else {
                        setText(null);
                    }
            setGraphic(null);
        }

        @Override
        public void updateItem(Date item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (datePicker != null) {
                        datePicker.setValue(getDate());
                    }
                    setText(null);
                    setGraphic(datePicker);
                } else {
                    if (getDate()!=null) {
                        setText(getDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
                    } else {
                        setText(null);
                    }
                    setGraphic(null);
                }
            }
        }

        private void createDatePicker() {
            datePicker = new DatePicker(getDate());
            datePicker.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
            datePicker.setOnAction((e) -> {
                //System.out.println("Committed: " + datePicker.getValue().toString());
                Instant t= datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant();
                ZoneId zoneId = ZoneId.systemDefault();
                ZonedDateTime zdt = ZonedDateTime.ofInstant ( t , zoneId );
                commitEdit(Date.valueOf(zdt.toLocalDate()));
            });
//            datePicker.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
//                if (!newValue) {
//                    commitEdit(Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
//                }
//            });
        }

        private LocalDate getDate() {
            return getItem() == null ? null : getItem().toLocalDate();
        }
    }