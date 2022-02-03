/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dao.ClassroomFactory;
import dao.PayeFactory;
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
    private NoteBook notebook;
    
    private ArrayList<Paye> Payes;

    public Scolarite() {
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public NoteBook getNotebook() {
        return notebook;
    }

    public void setNotebook(NoteBook notebook) {
        this.notebook = notebook;
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
        if(classroom==null)
            classroom=new ClassroomFactory().getClassroom(classroomId);
        contribution=classroom.getContribution();
        return contribution;
    }
    
    public Double loadMtPaye(){
        if(Payes==null || Payes.isEmpty())
            Payes = new PayeFactory().getPayes();
        return null;
    }
    
}
