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
class Session {
    private Integer id;
    private String profAppreciation;
    private String dgAppreciation;
    private Double hightMoyenne;
    private Double lowMoyenne;
    private Double moyenne;
    private Integer range;
    private Boolean isFinal;
    
    private NoteBook noteBook;
    
    private ArrayList<Note> interros;
    private ArrayList<Note> devoirs;
}
