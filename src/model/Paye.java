/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author loussin
 */
public class Paye {
    private Integer id;
    private String scoolYear;
    private String montantEnLtr;
    private Double montant;
    private Double montantRst;
    private Boolean solded=false;
    
    private Integer studentId;
    private Integer classroomId;
    
    private Student student;
    private Classroom classroom;

    public Paye() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScoolYear() {
        return scoolYear;
    }

    public void setScoolYear(String scoolYear) {
        this.scoolYear = scoolYear;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Double getMontantRst() {
        return montantRst;
    }

    public void setMontantRst(Double montantRst) {
        this.montantRst = montantRst;
    }

    public String getMontantEnLtr() {
        return montantEnLtr;
    }

    public void setMontantEnLtr(String montantEnLtr) {
        this.montantEnLtr = montantEnLtr;
    }

    public Boolean getSolded() {
        return solded;
    }

    public void setSolded(Boolean solded) {
        this.solded = solded;
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

    @Override
    public String toString() {
        return "Paye{" + "id=" + id + ", scoolYear=" + scoolYear + ", montant=" + montant + ", montantRst=" + montantRst + ", montantEnLtr=" + montantEnLtr + ", solded=" + solded + ", studentId=" + studentId + ", classroomId=" + classroomId + '}';
    }
    
}
