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
public class NoteBook {
    
    private Integer id;
    private Integer studentId;
    private Integer classroomId;
    private Integer courseId;
    private Integer preferedSessionNumber;
    
    private Student student;
    private Classroom classroom;
    private Course course;
    
    private ArrayList<Session> sessions;

    public NoteBook() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Integer classroomId) {
        this.classroomId = classroomId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getPreferedSessionNumber() {
        return preferedSessionNumber;
    }

    public void setPreferedSessionNumber(Integer preferedSessionNumber) {
        this.preferedSessionNumber = preferedSessionNumber;
    }

    @Override
    public String toString() {
        return "NoteBook{" + "id=" + id + ", studentId=" + studentId + ", classroomId=" + classroomId + ", courseId=" + courseId + ", preferedSessionNumber=" + preferedSessionNumber + '}';
    }
    
    
}
