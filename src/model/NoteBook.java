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
class NoteBook {
    private Integer id;
    private Student student;
    private Classroom classroom;
    private Course course;
    private Integer preferedSessionNumber;
    
    private ArrayList<Session> sessions;
}
