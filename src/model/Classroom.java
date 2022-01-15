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
class Classroom {
    private Integer id;
    private String promotion;
    private String scoolYear;
    private String group;
    private Double contribution;
    private Classlevel classlevel;
    
    private ArrayList<Scolarite> scolarites;
    private ArrayList<NoteBook> notebooks;
    private ArrayList<Course> courses;
}
