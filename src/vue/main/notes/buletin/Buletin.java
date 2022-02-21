/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue.main.notes.buletin;

import java.io.File;
import java.util.ArrayList;
import model.Classroom;
import model.NoteBook;
import model.Scolarite;
import model.Student;

/**
 *
 * @author loussin
 */
public class Buletin {
    Scolarite scolarite;
    Student student;
    Classroom classroom;
    String scoolyear;
    ArrayList<NoteBook> notes;
    File pdf;

    public Buletin() {
    }

    public Buletin(Scolarite scolarite) {
        this.scolarite = scolarite;
        if(scolarite!=null){
            student=scolarite.loadStudent();
            classroom=scolarite.loadClassroom();
            notes=scolarite.loadNotebooks();
        }
    }
    
    
    
}
