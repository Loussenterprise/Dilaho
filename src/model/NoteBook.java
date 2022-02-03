/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dao.SessionFactory;
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
        //sessions=new ArrayList<>();
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public ArrayList<Session> getSessions() {
        return sessions;
    }

    public ArrayList<Session> loadSessions() {
        if(sessions==null || sessions.isEmpty()){
            if(id!=null){
                sessions=new SessionFactory().getSessionsByNotebookId(id);
            }
            else
                sessions=new ArrayList<>();
            if(sessions.isEmpty()){
                SessionFactory sf= new SessionFactory();
                createSessions(3);
                for(Session s:sessions){
                    s.setNoteBookId(id);
                    sf.setSession(s);
                    s.setModified(true);
                }

            }
        }
        return sessions;
    }
    
    public void setSessions(ArrayList<Session> sessions) {
        this.sessions = sessions;
    }
    
    public void createSessions(int n) {
        preferedSessionNumber=n;
        if(sessions==null)
            sessions=new ArrayList<>();
        if(sessions.size() < preferedSessionNumber)
            for (int i = sessions.size(); i < preferedSessionNumber; i++) {
                sessions.add(new Session());
            }
    }
    
    

    @Override
    public String toString() {
        return "NoteBook{" + "id=" + id + ", studentId=" + studentId + ", classroomId=" + classroomId + ", courseId=" + courseId + ", preferedSessionNumber=" + preferedSessionNumber + '}';
    }
    
    
}
