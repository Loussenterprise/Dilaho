/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

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
    
    private ArrayList<Add> listeAdd;

    public Student() {
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

    public ArrayList getListeAdd() {
        return listeAdd;
    }

    public void setListeAdd(ArrayList listeAdd) {
        this.listeAdd = listeAdd;
    }

    @Override
    public String toString() {
        return name + " " + firstnames;
    }
    
    public static void main(String[] args) {
        System.out.println((new Student()).toString());
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
    
    
    
    
    
}
