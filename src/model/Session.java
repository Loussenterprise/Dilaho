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
public class Session {
    private Integer id;
    private String profAppreciation;
    private String dgAppreciation;
    private Double hightMoyenne;
    private Double lowMoyenne;
    private Double moyenne;
    private Integer range;
    private Boolean isFinal=false;
    
    private Integer noteBookId;
    
    private NoteBook noteBook;
    
    private ArrayList<Note> interros;
    private ArrayList<Note> devoirs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProfAppreciation() {
        return profAppreciation;
    }

    public void setProfAppreciation(String profAppreciation) {
        this.profAppreciation = profAppreciation;
    }

    public String getDgAppreciation() {
        return dgAppreciation;
    }

    public void setDgAppreciation(String dgAppreciation) {
        this.dgAppreciation = dgAppreciation;
    }

    public Double getHightMoyenne() {
        return hightMoyenne;
    }

    public void setHightMoyenne(Double hightMoyenne) {
        this.hightMoyenne = hightMoyenne;
    }

    public Double getLowMoyenne() {
        return lowMoyenne;
    }

    public void setLowMoyenne(Double lowMoyenne) {
        this.lowMoyenne = lowMoyenne;
    }

    public Double getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(Double moyenne) {
        this.moyenne = moyenne;
    }

    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }

    public Boolean isFinal() {
        return isFinal;
    }

    public Boolean getIsFinal() {
        return isFinal;
    }

    public void setIsFinal(Boolean isFinal) {
        this.isFinal = isFinal;
    }

    public Integer getNoteBookId() {
        return noteBookId;
    }

    public void setNoteBookId(Integer noteBookId) {
        this.noteBookId = noteBookId;
    }

    public NoteBook getNoteBook() {
        return noteBook;
    }

    public void setNoteBook(NoteBook noteBook) {
        this.noteBook = noteBook;
    }

    public ArrayList<Note> getInterros() {
        return interros;
    }

    public void setInterros(ArrayList<Note> interros) {
        this.interros = interros;
    }

    public ArrayList<Note> getDevoirs() {
        return devoirs;
    }

    public void setDevoirs(ArrayList<Note> devoirs) {
        this.devoirs = devoirs;
    }

    public Session() {
    }

    @Override
    public String toString() {
        return "Session{" + "id=" + id + ", moyenne=" + moyenne + ", range=" + range + ", isFinal=" + isFinal + ", noteBookId=" + noteBookId + '}';
    }
    
    
}
