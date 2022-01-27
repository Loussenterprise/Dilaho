/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author loussin
 */
public class Course {
    private Integer id;
    private String name;
    private String profname;
    private Classlevel classlevel;
    private Integer classlevelId;
    private Integer coeff;
    
    

    public Course() {
    }

    public Course(String name) {
        this.name=name;
    }

    public Course(Integer id, String name) {
        this.id=id;
        this.name=name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfname() {
        return profname;
    }

    public void setProfname(String profname) {
        this.profname = profname;
    }

    public Classlevel getClasslevel() {
        return classlevel;
    }

    public void setClasslevel(Classlevel classlevel) {
        if(classlevel!=null){
            this.classlevel = classlevel;
            classlevelId=classlevel.getId();
        }
        
    }

    public Integer getClasslevelId() {
        return classlevelId;
    }

    public void setClasslevelId(Integer classlevelId) {
        this.classlevelId = classlevelId;
    }

    public Integer getCoeff() {
        return coeff;
    }

    public void setCoeff(Integer coeff) {
        this.coeff = coeff;
    }

    @Override
    public String toString() {
        return name!=null?name:"(Vide)";
    }
//
//    @Override
//    public String toString() {
//        return "Course{" + "id=" + id + ", name=" + name + ", profname=" + profname + ", classlevel=" + classlevel + ", classlevelId=" + classlevelId + ", coeff=" + coeff + "}\n";
//    }
//    
    
    
}
