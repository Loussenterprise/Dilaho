/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package vue.main;

import dao.ClassroomFactory;
import dao.StudentFactory;
import dao.UserFactory;
import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import model.Classroom;
import model.Student;
import model.User;

/**
 * FXML Controller class
 *
 * @author loussin
 */
public class MainController implements Initializable {

    @FXML
    VBox opts;
    
    @FXML
    ScrollPane general_view;
    
    @FXML
    AnchorPane leftzone;
    
    @FXML
    AnchorPane ap;
    
    @FXML
    Label classroom;
    
    @FXML
    Button all;
    @FXML
    Button addstu;
    
    @FXML 
    TableView<Student> student_list_view;
    
    StudentFactory stf=new StudentFactory();
    ClassroomFactory crf= new ClassroomFactory();
    
    ObservableList<Classroom> crl = FXCollections.observableList(crf.getClassrooms());
    ObservableList<Student> stl = FXCollections.observableList(stf.getStudents());
    ObservableList<Student> results = FXCollections.observableList(stl);
    
    @FXML
    ComboBox<Classroom> cls_select;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        classroom.setText("Toutes les clases");
        classroom.setMaxWidth(Double.MAX_VALUE);
        ap.setLeftAnchor(classroom, 0.0);
        ap.setRightAnchor(classroom, 0.0);
        classroom.setAlignment(Pos.CENTER);
        
        cls_select.setItems(crl);
        initStudentTable();
        // TODO
    }   
    
    public void initStudentTable(){
        
        student_list_view.setItems(stl);
        TableColumn<Student,String> mtnoCol = new TableColumn("Matricule");
        mtnoCol.setCellValueFactory((CellDataFeatures<Student, String> p) -> new ObservableValueBase<String>() {
            @Override
            public String getValue() {
                return p.getValue().getMatricule();
            }
        });
        TableColumn<Student,String> NameCol = new TableColumn("Nom");
        NameCol.setCellValueFactory((CellDataFeatures<Student, String> p) -> new ObservableValueBase<String>() {
            @Override
            public String getValue() {
                return p.getValue().getName();
            }
        });
        TableColumn<Student,String> firstNameCol = new TableColumn("Prenoms");
        firstNameCol.setCellValueFactory((CellDataFeatures<Student, String> p) -> new ObservableValueBase<String>() {
            @Override
            public String getValue() {
                return p.getValue().getFirstnames();
            }
        });
        //        firstNameCol.setCellValueFactory(new Callback<CellDataFeatures<Student, String>, ObservableValue<String>>() {
        //            public ObservableValue<String> call(CellDataFeatures<Student, String> p) {
        //                return new ObservableValueBase<String>() {
        //                    @Override
        //                    public String getValue() {
        //                        return p.getValue().getFirstnames();
        //                    }
        //                };
        //            }
        //         });
        
        TableColumn<Student,Date> dobCol = new TableColumn("Date de naissance");
        dobCol.setCellValueFactory((CellDataFeatures<Student, Date> p) -> new ObservableValueBase<Date>() {
            @Override
            public Date getValue() {
                return p.getValue().getBirthday();
            }
        });
        TableColumn<Student,Date> insdCol = new TableColumn("Date d'insscription");
        insdCol.setCellValueFactory((CellDataFeatures<Student, Date> p) -> new ObservableValueBase<Date>() {
            @Override
            public Date getValue() {
                return p.getValue().getInscriptionDate();
            }
        });
        student_list_view.getColumns().addAll(mtnoCol,NameCol,firstNameCol,dobCol,insdCol);
        blic(student_list_view);
    }
    
    
    public void loadData() {
        System.out.println(cls_select.getValue());
        if(cls_select.getValue()!=null){
            Integer searchText = cls_select.getValue().getId();

            classroom.setText(cls_select.getValue().toString());
            Task<ObservableList<Student>> task = new Task() {
                @Override
                protected ObservableList<Student> call() throws Exception {
                    updateMessage("Loading data");
                    System.out.println(".call()");
                    return FXCollections.observableArrayList(stl
                            .stream()
                            .filter(value -> searchText.equals(value.getClassroom()))
                            .collect(Collectors.toList()));
                }
            };
            task.setOnSucceeded(event -> {
                System.out.println("succeeded");
                results = task.getValue();
                student_list_view.setItems(FXCollections.observableList(results));
            });
            task.setOnRunning((event) -> {
                System.out.println("running");
            });
            task.setOnFailed((event) -> {
                System.out.println("failed");
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
    
    public void minOptions(){
        leftzone.setMaxWidth(50);
        System.out.println("vue.main.MainController.minOptions()");
    }
    
    public void wipeOptions(){
        leftzone.setMaxWidth(200);
        System.out.println("vue.main.MainController.wipeOptions() to "+leftzone.getWidth());
    }
    
    public void addStudent(){
        Student s = new Student();
        s.setClassroom(cls_select.getValue()!=null?cls_select.getValue().getId():null);
        s.setInscriptionDate(new Date(new java.util.Date().getTime()));
        System.out.println(s.getInscriptionDate());
        stl.add(s);
        loadData();
        student_list_view.requestFocus();
        student_list_view.getFocusModel().focus(stl.size()-1);
        student_list_view.getSelectionModel().clearAndSelect(stl.size()-1);
        student_list_view.scrollTo(s);
    }
    
    void blic(TableView<Student> table) {
        System.out.println("ressources.SearchController.blic()");
        table.setEditable(true);
        
        ((TableColumn) table.getColumns().get(0)).setCellFactory(TextFieldTableCell.forTableColumn());
        ((TableColumn) table.getColumns().get(0)).setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Student, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Student, String> t) {
                    Student s = ((Student) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        );
                        s.setMatricule(t.getNewValue());
                        stf.setStudent(s);
                    
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
                        stf.setStudent(s);
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
                        stf.setStudent(s);
                }
            }
        );
        
        Callback<TableColumn<Student, Date>, TableCell<Student, Date>> dateCellFactory
                = (TableColumn<Student, Date> param) -> new DateEditingCell();
        
        ((TableColumn) table.getColumns().get(3)).setCellFactory(dateCellFactory);
        ((TableColumn) table.getColumns().get(3)).setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Student, Date>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Student, Date> t) {
                    Student s = ((Student) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        );
                    s.setBirthday(t.getNewValue());
                        stf.setStudent(s);
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
                        stf.setStudent(s);
                }
            }
        );
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
                System.out.println("Committed: " + datePicker.getValue().toString());
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