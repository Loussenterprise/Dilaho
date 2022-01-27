/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dao.CourseFactory;
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
    private Integer filleNbr;    
    private Integer garconNbr;
    
    private ArrayList<Scolarite> scolarites;
    private ArrayList<NoteBook> notebooks;
    private ArrayList<Course> courses;

    public Classroom() {
    }

    public Integer getId() {
        return id;
    }

    public Integer getClasslevelId() {
        if(classlevelId==null)
            classlevelId=classlevel!=null?classlevel.getId():null;
        return classlevelId;
    }

    public void setClasslevelId(Integer classlevelId) {
        this.classlevelId = classlevelId;
    }

    @Override
    public String toString() {
        return classlevel!=null? classlevel.toString()+(group!=null?" ["+group+"]":""):"(---)"+(group!=null?" ["+group+"]":"");
    }
    
    public String desc() {
        return classlevel!=null? classlevel.desc()+(group!=null?" groupe "+group+"":""):"(---)"+(group!=null?" groupe "+group+"":"");
    }
//
//    @Override
//    public String toString() {
//        return "Classroom{" + "id=" + id + ", promotion=" + promotion + ", scoolYear=" + scoolYear + ", group=" + group + ", contribution=" + contribution + ", classlevel=" + classlevel + ", classlevelId=" + classlevelId + ", filleNbr=" + filleNbr + ", garconNbr=" + garconNbr + ", scolarites=" + scolarites + ", notebooks=" + notebooks + ", courses=" + courses + '}';
//    }
    
    

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
        if(classlevel!=null){
            this.classlevel = classlevel;
            classlevelId=classlevel.getId();
        }
        
    }

    public Integer getFilleNbr() {
        return filleNbr;
    }

    public void setFilleNbr(Integer filleNbr) {
        this.filleNbr = filleNbr;
    }

    public void generateFilleNbr() {
        Integer filleNbr = 0;
        this.filleNbr = filleNbr;
    }

    public Integer getGarconNbr() {
        return garconNbr;
    }

    public void setGarconNbr(Integer garconNbr) {
        this.garconNbr = garconNbr;
    }

    public void generateNbr() {
        Integer garconNbr = 0;
        this.garconNbr = garconNbr;
    }

    public void generateNCourses(int n) {
        if(courses==null)
            courses=new ArrayList<>();
        for (int i = 0; i < n; i++) {
            courses.add(new Course(i,""+i));
        }
    }

    public ArrayList<Scolarite> getScolarites() {
        return scolarites;
    }

    public void setScolarites(ArrayList<Scolarite> scolarites) {
        this.scolarites = scolarites;
    }

    public ArrayList<NoteBook> getNotebooks() {
        return notebooks;
    }

    public void setNotebooks(ArrayList<NoteBook> notebooks) {
        this.notebooks = notebooks;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public ArrayList<Course> loadCourses() {
        if(classlevelId!=null)
            courses=new CourseFactory().getCoursesByClasslevelId(classlevelId);
        else
            courses=new ArrayList<>();
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    
    public static void main(String[] args) {
        Classroom c=new Classroom();
        c.setClasslevel(Classlevel.PREMIERE_C);
        c.setGroup("A");
        System.out.println(c.desc());
    }
}
