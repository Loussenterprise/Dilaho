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
class Scolarite {
    private Integer id;
    private Double contribution;
    private Double mtpaye;
    
    private Integer studentId;
    private Integer classroomId;
    
    private Student student;
    private Classroom classroom;
    private NoteBook notebooks;
    
    private ArrayList<Paye> Payes;
}
