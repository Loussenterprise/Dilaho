/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dao.ScolariteFactory;
import dao.SessionFactory;
import java.util.ArrayList;

/**
 *
 * @author loussin
 */

public class NoteBook {
    
    private Integer id;
//    private Integer studentId;
//    private Integer classroomId;
    private Integer scolariteId;
    private Integer courseId;
    private Integer preferedSessionNumber=3;
    
    private Student student;
    private Classroom classroom;
    private Course course;
    private Scolarite scolarite;
    
    private ArrayList<Session> sessions;
    
    private Stat stat=Stat.NEW;

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
        return scolarite.getStudentId();
    }
//
//    public void setStudentId(Integer studentId) {
//        this.scolarite.setStudentId(studentId);
//    }

    public Integer getClassroomId() {
        return scolarite.getClassroomId();
    }
//
//    public void setClassroomId(Integer classroomId) {
//        this.scolarite.setClassroomId(classroomId);
//    }

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
                createSessions(preferedSessionNumber);
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

    public Stat getStat() {
        return stat;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }
    
    

    public Integer getScolariteId() {
        return scolariteId;
    }

    public void setScolariteId(Integer scolariteId) {
        this.scolariteId = scolariteId;
    }

    public Scolarite getScolarite() {
        return scolarite;
    }

    public void setScolarite(Scolarite scolarite) {
        this.scolarite = scolarite;
    }
    
    public void loadScolarite(){
        if(scolariteId!=null){
            scolarite=new ScolariteFactory().getScolarite(scolariteId);
        }
    }
    
    

    @Override
    public String toString() {
        return "NoteBook{" + "id=" + id + ", studentId=" + getStudentId() + ", classroomId=" + getClassroomId() + ", courseId=" + courseId + ", preferedSessionNumber=" + preferedSessionNumber + '}';
    }
    
    
}
