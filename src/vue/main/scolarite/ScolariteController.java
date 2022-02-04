/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package vue.main.scolarite;

import dao.PayeFactory;
import dao.ScolariteFactory;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import model.Classroom;
import model.Paye;
import model.Scolarite;
import vue.main.MainController;

/**
 * FXML Controller class
 *
 * @author loussin
 */
public class ScolariteController implements Initializable {
    
    @FXML
    AnchorPane pane;
    @FXML
    AnchorPane scolpr;
    @FXML
    AnchorPane view;
    @FXML
    ScrollPane general_view;
    @FXML
    AnchorPane ap;
    @FXML
    ComboBox<Classroom> cls_select;
    @FXML
    Label classroom;
    @FXML
    Button all;
    @FXML
    TableView<Scolarite> student_list;
    @FXML
    TableView<Paye> paye_list;
    @FXML
    Button addpaye;
    @FXML
    Button save;
    @FXML
    Button giveup;
    @FXML
    Label student;
    @FXML
    Label classr;
    @FXML
    Label scoolyear;
//    @FXML
//    Label classroom;
    @FXML
    Label cont;
    @FXML
    Label mp;
    @FXML
    Label rt;
    @FXML
    TextField new_montant;
    @FXML
    TextField new_montant_en_ltr;
    @FXML
    FlowPane fp;
    
    
    
    Scolarite scolarite_en_vue;
    ObservableList<Scolarite> list ;
    ObservableList<Scolarite> results;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        MainController.injectScolariteController(this);
        scolpr.setVisible(false);
        fp.setVisible(false);
        addpaye.setOnAction(event->startAddPaye());
        list = FXCollections.observableList(new ScolariteFactory().getScolarites());
        initTable();
        student_list.setItems(list);
        initPayesTable();
    }   
    
    public void initPayesTable(){
        TableColumn<Paye,Date> stu = new TableColumn("Date");
        stu.setCellValueFactory((TableColumn.CellDataFeatures<Paye, Date> p) -> new ObservableValueBase<Date>() {
            @Override
            public Date getValue() {
                return p.getValue().getDateDeCreation();
            }
        });
        TableColumn<Paye,Double> cl = new TableColumn("Montant");
        cl.setCellValueFactory((TableColumn.CellDataFeatures<Paye, Double> p) -> new ObservableValueBase<Double>() {
            @Override
            public Double getValue() {
                return p.getValue().getMontant();
            }
        });
        TableColumn<Paye,String> cll = new TableColumn("Montant en lettre");
        cll.setCellValueFactory((TableColumn.CellDataFeatures<Paye, String> p) -> new ObservableValueBase<String>() {
            @Override
            public String getValue() {
                return p.getValue().getMontantEnLtr();
            }
        });
        cll.setVisible(false);
        TableColumn<Paye,Double> contrib = new TableColumn("Reste à payer");
        contrib.setCellValueFactory((TableColumn.CellDataFeatures<Paye, Double> p) -> new ObservableValueBase<Double>() {
            @Override
            public Double getValue() {
                return p.getValue().getMontantRst();
            }
        });
        TableColumn<Paye,Boolean> ded = new TableColumn("Soldé");
        ded.setCellValueFactory((TableColumn.CellDataFeatures<Paye, Boolean> p) -> new ObservableValueBase<Boolean>() {
            @Override
            public Boolean getValue() {
                return p.getValue().getSolded();
            }
        });
        paye_list.getColumns().removeAll(paye_list.getColumns());
        paye_list.getColumns().addAll(stu,cl,cll,contrib,ded);
    }
    
    public void initTable(){
        TableColumn<Scolarite,String> stu = new TableColumn("Élève");
        stu.setCellValueFactory((TableColumn.CellDataFeatures<Scolarite, String> p) -> new ObservableValueBase<String>() {
            @Override
            public String getValue() {
                return p.getValue().loadStudent()!=null?p.getValue().loadStudent().toString():null;
            }
        });
        TableColumn<Scolarite,String> cl = new TableColumn("Classe");
        cl.setCellValueFactory((TableColumn.CellDataFeatures<Scolarite, String> p) -> new ObservableValueBase<String>() {
            @Override
            public String getValue() {
                return p.getValue().loadClassroom()!=null?p.getValue().loadClassroom().toString():null;
            }
        });
        TableColumn<Scolarite,String> contrib = new TableColumn("À payer");
        contrib.setCellValueFactory((TableColumn.CellDataFeatures<Scolarite, String> p) -> new ObservableValueBase<String>() {
            @Override
            public String getValue() {
                return p.getValue().loadContribution()!=null?p.getValue().loadContribution().toString():null;
            }
        });
        TableColumn<Scolarite,String> mtpay = new TableColumn("Payé");
        mtpay.setCellValueFactory((TableColumn.CellDataFeatures<Scolarite, String> p) -> new ObservableValueBase<String>() {
            @Override
            public String getValue() {
                return p.getValue().loadMtPaye()!=null?p.getValue().loadMtPaye().toString():null;
            }
        });
        TableColumn<Scolarite,String> rst = new TableColumn("Restant");
        rst.setCellValueFactory((TableColumn.CellDataFeatures<Scolarite, String> p) -> new ObservableValueBase<String>() {
            @Override
            public String getValue() {
                return p.getValue().getContribution()!=null?""+(p.getValue().getContribution()-p.getValue().getMtpaye()):null;
            }
        });
        TableColumn<Scolarite,Void> action = new TableColumn("Action");
        action.setCellFactory(col -> new TableCell<Scolarite, Void>() {
            private final Button container;
            {
                container=new Button("Afficher");
                container.setOnAction(evt->{
                    show((Scolarite)getTableRow().getItem());
                });
            }
            @Override
            public void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : container);
            }
        });
        
        student_list.getColumns().removeAll(student_list.getColumns());
        student_list.getColumns().addAll(stu,cl,contrib,mtpay,rst,action);
        blic(student_list);
    }
    
    public void blic(TableView<Scolarite> t){
        t.setEditable(true);
        for(TableColumn c:t.getColumns()){
            c.setOnEditStart(evt -> { 
                show((Scolarite) student_list.getItems().get(
                            student_list.getSelectionModel().getFocusedIndex())
                            );
            });
        }
    }     
    public void showStu(){
        MainController.showStudent(scolarite_en_vue.getStudent());
    }
    
    public void startAddPaye(){
        
        fp.setVisible(true);
        addpaye.setOnAction(event->startAddPaye());
        addpaye.setText("Valider");
        giveup.setVisible(true);
    }
    
    public void gvp(){
        
        new_montant.setText("");
        new_montant_en_ltr.setText("");
    }
    
    public void endAddPaye(){
        
        Paye p=new Paye();
        p.setDateDeCreation(new Date(new java.util.Date().getTime()));
        p.setScolariteId(scolarite_en_vue.getId());
        p.setMontant(Double.valueOf(new_montant.getText()));
        p.setMontantEnLtr((new_montant_en_ltr.getText()));
        p.setMontantRst(scolarite_en_vue.getContribution()-scolarite_en_vue.getMtpaye()-p.getMontant());
        p.setSolded(p.getMontantRst()<=0.0);
        p.setScoolYear(scolarite_en_vue.getClassroom().getScoolYear());
        new PayeFactory().setPaye(p);
        paye_list.getItems().add(p);
        
        new_montant.setText("");
        new_montant_en_ltr.setText("");
        fp.setVisible(false);
        addpaye.setOnAction(event->startAddPaye());
        addpaye.setText("Effectuer un payement");
        giveup.setVisible(false);
    }
    
    public void show(Scolarite sco){
        scolarite_en_vue=sco;
        scolpr.setVisible(true);
        {
            try {
                student.setText("");
                student.setText(sco.getStudent().toString());
            } catch (Exception e) {
            }
            try {
                classr.setText("Classe : ");
                classr.setText(classr.getText()+sco.getClassroom().toString());
            } catch (Exception e) {
            }
            
            try {
                
                scoolyear.setText("Année scolaire : ");
                scoolyear.setText(scoolyear.getText()+sco.getClassroom().getScoolYear());
            } catch (Exception e) {
            }
            try {
                cont.setText("Montant : ");
                cont.setText(cont.getText()+ sco.getContribution().toString());
            } catch (Exception e) {
            }
            try {
                mp.setText("Payé : ");
                mp.setText(mp.getText()+sco.getMtpaye().toString());
            } catch (Exception e) {
            }
            try {
                rt.setText("Restant : ");
                rt.setText(rt.getText()+String.valueOf(sco.getContribution()-sco.getMtpaye()));
            } catch (Exception e) {
            }
            paye_list.setItems(FXCollections.observableList(sco.getPayes()));
        }
        
        general_view.setVisible(false);
    }
    public void deshow(){
        scolpr.setVisible(false);
        general_view.setVisible(true);
    }
    
    public void reload(){
        MainController.scolariteG=null;
        MainController.showScolariteG();
    }
    
}
