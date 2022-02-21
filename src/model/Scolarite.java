/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dao.ClassroomFactory;
import dao.CourseFactory;
import dao.NoteBookFactory;
import dao.PayeFactory;
import dao.StudentFactory;
import java.util.ArrayList;

/**
 *
 * @author loussin
 */
public class Scolarite {
    private Integer id;
    private Double contribution;
    private Double mtpaye;
    
    private Integer studentId;
    private Integer classroomId;
    private Integer notebookId;
    
    private Student student;
    private Classroom classroom;
    private ArrayList<NoteBook> notebooks;
    
    private ArrayList<Paye> Payes;
    private boolean dopped = false;

    public Scolarite() {
        mtpaye=0.0;
        notebooks=new ArrayList<>();
    }  
    public ArrayList<NoteBook> createNotebooks(){
        if(notebooks==null)
            notebooks=new ArrayList<>();
        if(notebooks.isEmpty()){
            loadClassroom();
            ArrayList<Course> crs = new CourseFactory().getCoursesByClasslevelId(classroom.getClasslevelId());
            for(Course c:crs){
                NoteBook n = new NoteBook();
                n.setScolariteId(id);
                n.setCourseId(c.getId());
                n.setPreferedSessionNumber(3);
                new NoteBookFactory().setNoteBook(n);
                n.loadSessions();
            }
        }
        return notebooks;
    } 
    
    
    
    public NoteBook getNotebook(int courseId) {
        for(NoteBook n : notebooks){
            if(n!=null && n.getCourseId()!=null && courseId==n.getCourseId()){
                return n;
            }
        }
        return null;
    }

    public boolean isDopped() {
        return dopped;
    }

    public void setDopped(boolean dopped) {
        this.dopped = dopped;
    }

    

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
        if(student!=null)
            studentId=student.getId();
        else
            studentId=null;
    }

    public ArrayList<NoteBook> getNotebooks() {
        return notebooks;
    }
    public ArrayList<NoteBook> loadNotebooks() {
        if(notebooks==null || notebooks.isEmpty())
            notebooks=new NoteBookFactory().getNoteBooksByScolariteId(id);
        return notebooks;
    }

    public void setNotebooks(ArrayList<NoteBook> notebooks) {
        this.notebooks = notebooks;
    }

    

    public ArrayList<Paye> getPayes() {
        return Payes;
    }

    public void setPayes(ArrayList<Paye> Payes) {
        this.Payes = Payes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getContribution() {
        return contribution;
    }

    public void setContribution(Double contribution) {
        this.contribution = contribution;
    }

    public Double getMtpaye() {
        return mtpaye;
    }

    public void setMtpaye(Double mtpaye) {
        if(mtpaye!=null)
            this.mtpaye = mtpaye;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Integer classroomId) {
        this.classroomId = classroomId;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
        if(classroom!=null){
            contribution=classroom.getContribution();
            classroomId=classroom.getId();
        }
        else{
            contribution=null;
            classroomId=null;
        }
    }

    public Integer getNotebookId() {
        return notebookId;
    }

    public void setNotebookId(Integer notebookId) {
        this.notebookId = notebookId;
    }
    
    

    @Override
    public String toString() {
        return "Scolarite{" + "id=" + id + ", contribution=" + contribution + ", mtpaye=" + mtpaye + ", studentId=" + studentId + ", classroomId=" + classroomId + '}';
    }
    
    public Double loadContribution(){
        if(classroom!=null){
            if(contribution==null){
                contribution=classroom.getContribution();
            }
        }else{
            try {
                loadClassroom();
                if(classroom!=null && contribution==null)
                    contribution=classroom.getContribution();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
            
        return contribution;
    }
    public Classroom loadClassroom(){
        if(classroom==null && classroomId!=null){
            classroom=new ClassroomFactory().getClassroom(classroomId);
        }
            
        return classroom;
    }
    public Student loadStudent(){
        if(student==null && studentId!=null){
            student=new StudentFactory().getStudent(studentId);
        }
        return student;
    }
    
    public Double loadMtPaye(){
        mtpaye=0.0;
        if(Payes==null || Payes.isEmpty())
            Payes = new PayeFactory().getPayesByScolariteId(id);
        for(Paye p : Payes){
            if(p.getMontant()!=null)
                mtpaye+=p.getMontant();
        }
        return mtpaye;
    }

    public void dopper() {
        loadStudent();
        loadContribution();
        loadMtPaye();
        setDopped(true);
    }

    public void dopper(Student s) {
        setStudent(s);
        loadContribution();
        loadMtPaye();
        setDopped(dopped);
    }

    public void dopper(Classroom c) {
        loadStudent();
        setClassroom(c);
        loadContribution();
        loadMtPaye();
        setDopped(dopped);
    }
    
}
