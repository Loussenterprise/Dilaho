/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dao.ClassroomFactory;
import dao.CourseFactory;
import dao.NoteBookFactory;
import dao.SessionFactory;
import dao.StudentFactory;
import java.sql.Date;
import java.util.ArrayList;

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
    
    private Classroom cl;
    
    private ArrayList<Add> listeAdd;
    private ArrayList<NoteBook> notebooks;
    
    boolean dopped = false;
    boolean modified = false;

    public Student() {
        listeAdd=new ArrayList<>();
        notebooks=new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    
    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Integer getClassroom() {
        return classroom;
    }

    public void setClassroom(Integer classroom) {
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

    public void setCl(Classroom cl) {
        this.cl = cl;
        if(cl!=null)
            classroom=cl.getId();
        else
            classroom=null;
    }

    public ArrayList<NoteBook> getNotebooks() {
        return notebooks;
    }

    public ArrayList<NoteBook> loadNotebooks() {
        if(id!=null){
            notebooks=new NoteBookFactory().getNoteBooksByStudentId(id);
        }else
            notebooks=new ArrayList<>();
        return notebooks;
    }
    
    public void createNotebooks(){
        if(notebooks==null)
            notebooks=new ArrayList<>();
        if(notebooks.isEmpty()){
            loadCl();
            ArrayList<Course> crs = new CourseFactory().getCoursesByClasslevelId(cl.getClasslevelId());
            for(Course c:crs){
                NoteBook n = new NoteBook();
                n.setStudentId(id);
                n.setCourseId(c.getId());
                n.setClassroomId(classroom);
                n.setPreferedSessionNumber(3);
                new NoteBookFactory().setNoteBook(n);
                n.loadSessions();
            }
        }
    } 

    public NoteBook getNotebook(int courseId) {
        for(NoteBook n : notebooks){
            if(n!=null && n.getCourseId()!=null && courseId==n.getCourseId()){
                return n;
            }
        }
        NoteBook n = new NoteBook();
        n.setStudentId(id);
        n.setCourseId(courseId);
        n.setClassroomId(classroom);
        n.setPreferedSessionNumber(3);
        new NoteBookFactory().setNoteBook(n);
        n.createSessions(3);
        for(Session s:n.getSessions()){
            s.setNoteBookId(n.getId());
            new SessionFactory().setSession(s);
        }
        return n;
    }

    public void setNotebooks(ArrayList<NoteBook> notebooks) {
        this.notebooks = notebooks;
    }

    public boolean isDopped() {
        return dopped;
    }

    public void setDopped(boolean dopped) {
        this.dopped = dopped;
    }

    
    
    public ArrayList<NoteBook> generateNotebooks(){
        ArrayList<NoteBook> list = new ArrayList();
        try {
            for(Course c : cl.getCourses()){
                NoteBook n = new NoteBook();
                n.setClassroomId(classroom);
                n.setCourseId(c.getId());
                n.setStudentId(id);
                n.setPreferedSessionNumber(2);
                System.out.println(n.getSessions().size()+" "+c.getName());
                list.add(n);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        notebooks=list;
        return list;
    }

    public boolean isModified() {
        return modified;
    }

    public void setModified(boolean modified) {
        this.modified = modified;
    }
    
    
    
    
    public static void main(String[] args) {
        Student s=new Student();
        s.setId(Integer.SIZE);
        s.setClassroom(2);
        System.out.println(s.loadCl());
        s.getCl().generateNCourses(10);
        System.out.println(s.generateNotebooks());
        System.out.println(s.getNotebooks());
    }

    
}
