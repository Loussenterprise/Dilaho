/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author loussin
 */
public class Classroom {
    private Integer id;
    private String promotion;
    private String scoolYear;
    private String group;
    private Double contribution;
    private Classlevel classlevel;
    
    private Integer classlevelId;
    
    private ArrayList<Scolarite> scolarites;
    private ArrayList<NoteBook> notebooks;
    private ArrayList<Course> courses;

    public Classroom() {
    }

    public Integer getId() {
        return id;
    }

    public Integer getClasslevelId() {
        return classlevelId;
    }

    public void setClasslevelId(Integer classlevelId) {
        this.classlevelId = classlevelId;
    }

    @Override
    public String toString() {
        return classlevel!=null? classlevel.toString()+" "+group:"(---) "+group;
    }
    
    

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public String getScoolYear() {
        return scoolYear;
    }

    public void setScoolYear(String scoolYear) {
        this.scoolYear = scoolYear;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Double getContribution() {
        return contribution;
    }

    public void setContribution(Double contribution) {
        this.contribution = contribution;
    }

    public Classlevel getClasslevel() {
        return classlevel;
    }

    public void setClasslevel(Classlevel classlevel) {
        this.classlevel = classlevel;
    }
    
    
}
