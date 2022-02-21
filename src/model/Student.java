/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dao.ClassroomFactory;
import dao.CourseFactory;
import dao.NoteBookFactory;
import dao.ScolariteFactory;
import dao.SessionFactory;
import dao.StudentFactory;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author loussin
 */
public class Student {
        
    private Integer id;
    private String matricule;
    private String name;
    private String firstnames;
    private String passwd;
    private String address;
    private String sexe;
    private String extras;
    private String image;
    private String commingScool;
    private String fatherName;
    private String fatherWork;
    private String fatherAddress;
    private String motherName;
    private String motherWork;
    private String motherAddress;
    private Date birthday;
    private Date inscriptionDate;
    private Integer classroom;
    private Integer scolarite;
    
    private Scolarite sco;
    private Classroom cl;
    
    private ArrayList<Add> listeAdd;
    private ArrayList<Scolarite> scols;
    
    private ArrayList<NoteBook> notebooks;
    
    boolean dopped = false;
    boolean modified = false;

    public Student() {
        listeAdd=new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    public static void loadClassroomForAll(Collection<Student> c){
        for (Student student : c) {
            student.loadClassroom();
        }
    }
    
//    public String getMatricule() {
//        return matricule;
//    }
    public Integer getMatricule() {
        Integer i= null;
        try {
            i=Integer.parseInt(matricule);
        } catch (Exception e) {
        }
        return i;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstnames() {
        return firstnames;
    }

    public void setFirstnames(String firstnames) {
        this.firstnames = firstnames;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCommingScool() {
        return commingScool;
    }

    public void setCommingScool(String commingScool) {
        this.commingScool = commingScool;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherWork() {
        return fatherWork;
    }

    public void setFatherWork(String fatherWork) {
        this.fatherWork = fatherWork;
    }

    public String getFatherAddress() {
        return fatherAddress;
    }

    public void setFatherAddress(String fatherAddress) {
        this.fatherAddress = fatherAddress;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getMotherWork() {
        return motherWork;
    }

    public void setMotherWork(String motherWork) {
        this.motherWork = motherWork;
    }

    public String getMotherAddress() {
        return motherAddress;
    }

    public void setMotherAddress(String motherAddress) {
        this.motherAddress = motherAddress;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getInscriptionDate() {
        return inscriptionDate;
    }

    public void setInscriptionDate(Date inscriptionDate) {
        this.inscriptionDate = inscriptionDate;
    }

    public ArrayList<Add> getListeAdd() {
        return listeAdd;
    }

    public void setListeAdd(ArrayList<Add> listeAdd) {
        this.listeAdd = listeAdd;
    }

    @Override
    public String toString() {
        return name + " " + firstnames;
    }
    
    
    public String tolString() {
        return "Student{" + "id=" + id + ", matricule=" + matricule + ", name=" + name + ", firstnames=" + firstnames + ", passwd=" + passwd + ", address=" + address + ", sexe=" + sexe + ", extras=" + extras + ", image=" + image + ", commingScool=" + commingScool + ", fatherName=" + fatherName + ", fatherWork=" + fatherWork + ", fatherAddress=" + fatherAddress + ", motherName=" + motherName + ", motherWork=" + motherWork + ", motherAddress=" + motherAddress + ", birthday=" + birthday + ", inscriptionDate=" + inscriptionDate + ", classroom=" + classroom + ", scolarite=" + scolarite + ", sco=" + sco + ", cl=" + cl + ", listeAdd=" + listeAdd + ", scols=" + scols + ", notebooks=" + notebooks + ", dopped=" + dopped + ", modified=" + modified + '}';
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Integer getClassroom() {
        return classroom;
    }

    private void setClassroom(Integer classroom) {
        this.classroom = classroom;
    }

    public void setClassroomf(Integer classroom) {
        this.classroom = classroom;
    }

    public Integer getScolarite() {
        return scolarite;
    }

    public void setScolarite(Integer scolarite) {
        this.scolarite = scolarite;
    }

    public Classroom getCl() {
        return cl;
    }
    
    public void show(){
        System.out.println(this.tolString());
    }
    
    public void save(){
        new StudentFactory().setStudent(this);
        
    }

    public Classroom loadCl() {
        if(cl==null)
            cl=new ClassroomFactory().getClassroom(classroom);
        if(cl!=null)
            classroom=cl.getId();
        return cl;
    }
    
    public Classroom loadClassroom(){
        try {
            classroom=new ScolariteFactory().getScolarite(scolarite).getClassroomId();
        } catch (Exception e) {
        }
        return cl;
    }

    private void setCl(Classroom cl) {
        this.cl = cl;
        if(cl!=null)
            classroom=cl.getId();
        else
            classroom=null;
    }

    public void setClf(Classroom cl) {
        this.cl = cl;
        if(cl!=null)
            classroom=cl.getId();
        else
            classroom=null;
    }

    public Scolarite loadSco() {
        if(scolarite!=null){
            if(sco==null)
                sco=new ScolariteFactory().getScolarite(scolarite);
            return sco;
        }
        return null;
    }

    public void setSco(Scolarite s) {
        this.sco = s;
        if(sco!=null){
            setCl(sco.getClassroom());
            scolarite=sco.getId();
            classroom=sco.getClassroomId();          
        }else{
            setCl(null);
            scolarite=null;
        }
    }

    public ArrayList<NoteBook> getNotebooks() {
        return notebooks==null?notebooks=new ArrayList<>():notebooks;
    }

    public ArrayList<NoteBook> loadNotebooks() {
        if(id!=null){
            try {
                notebooks=sco.loadNotebooks();
            } catch (Exception e) {
                notebooks=loadSco().loadNotebooks();
            }
                
        }else
            notebooks=new ArrayList<>();
        return notebooks;
    }

    
    public boolean isDopped() {
        return dopped;
    }

    public void setDopped(boolean dopped) {
        this.dopped = dopped;
    }

    public Scolarite getSco() {
        return sco;
    }

    public ArrayList<Scolarite> loadScols() {
        scols=new ScolariteFactory().getScolaritesByStudentId(id);
        return scols;
    }

    public ArrayList<Scolarite> getScols() {
        return scols;
    }

    public void setScols(ArrayList<Scolarite> scols) {
        this.scols = scols;
    }
    
    

    public boolean isModified() {
        return modified;
    }

    public void setModified(boolean modified) {
        this.modified = modified;
    }
    
    
    public void dopper(){
        try {
            if(!isDopped()){
                System.out.println("!s.isDopped() = "+!isDopped());
                for(NoteBook nb:loadNotebooks()){
                    for(Session se:nb.loadSessions()){
                        System.out.println("vue.main.notes.NotesController.dopperStudents() NoteList :  "+se.loadNotes());
                    }
                }
                setDopped(true);
            }else
                System.out.println("!s.isDopped() = "+!isDopped());
        } catch (Exception e) {
        }
            
    }
    
    
    
    public static void main(String[] args) {
        Student s=new Student();
        s.setId(Integer.SIZE);
        System.out.println(s.getSco().createNotebooks());
        System.out.println(s.getSco().getNotebooks());
    }

    public NoteBook getNotebook(int course) {
        return loadSco().getNotebook(course);
    }

    
}
