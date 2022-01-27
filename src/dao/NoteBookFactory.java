/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.NoteBook;

/**
 *
 * @author loussin
 */
public class NoteBookFactory {
       
    Connection connection;
    Statement statement;
    PreparedStatement prepst;
    ResultSet rs;

    public NoteBookFactory() {
        connection = Database.getConnection();
        statement = Database.getStatement();
    }
    
    public void setNoteBook(NoteBook c){
        try {
            prepst=connection.prepareStatement(""
                    + "INSERT INTO 'notebook'('id','studentId',"
                    + "'classroomId',"
                    + "'courseId','preferedsessionnumber') VALUES ("+c.getId()+",?,?,?,?) "
                    + "ON CONFLICT(id) DO UPDATE SET "
                    + "'studentId'=?,'classroomId'=?,'courseId'=?,'preferedsessionnumber'=?");
            
            prepst.setString(1, c.getStudentId()!=null?c.getStudentId().toString():null);
            prepst.setString(2, c.getClassroomId()!=null?c.getClassroomId().toString():null);
            prepst.setString(3, c.getCourseId()!=null?c.getCourseId().toString():null);
            prepst.setString(4, c.getPreferedSessionNumber()!=null?c.getPreferedSessionNumber().toString():null);
            
            prepst.setString(5, c.getStudentId()!=null?c.getStudentId().toString():null);
            prepst.setString(6, c.getClassroomId()!=null?c.getClassroomId().toString():null);
            prepst.setString(7, c.getCourseId()!=null?c.getCourseId().toString():null);
            prepst.setString(8, c.getPreferedSessionNumber()!=null?c.getPreferedSessionNumber().toString():null);
            
            prepst.executeUpdate();
            c.setId(statement.executeQuery("SELECT last_insert_rowid() as id").getInt("id"));
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<NoteBook> getNoteBooks(){
        ArrayList<NoteBook> list = new ArrayList();
        try {
            rs=statement.executeQuery("SELECT * FROM notebook");
            while(rs.next()){
                NoteBook c= new NoteBook();
                c.setId(rs.getInt("id"));
                c.setStudentId(rs.getString("studentId")!=null?Integer.parseInt(rs.getString("studentId")):null);
                c.setCourseId(rs.getString("courseId")!=null?Integer.parseInt(rs.getString("courseId")):null);
                c.setClassroomId(rs.getString("classroomId")!=null?Integer.parseInt(rs.getString("classroomId")):null);
                c.setPreferedSessionNumber(rs.getString("preferedsessionnumber")!=null?Integer.parseInt(rs.getString("preferedsessionnumber")):null);
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public NoteBook getNoteBook(int id){
        NoteBook c = null;
        try {
            rs=statement.executeQuery("SELECT * FROM notebook where id="+id);
            if(rs.next()){
                c= new NoteBook();
                c.setId(rs.getInt("id"));
                c.setStudentId(rs.getString("studentId")!=null?Integer.parseInt(rs.getString("studentId")):null);
                c.setCourseId(rs.getString("courseId")!=null?Integer.parseInt(rs.getString("courseId")):null);
                c.setClassroomId(rs.getString("classroomId")!=null?Integer.parseInt(rs.getString("classroomId")):null);
                c.setPreferedSessionNumber(rs.getString("preferedsessionnumber")!=null?Integer.parseInt(rs.getString("preferedsessionnumber")):null);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
    
    
    public ArrayList<NoteBook> getNoteBooksByStudentId(int studentId){
        ArrayList<NoteBook> list = new ArrayList();
        NoteBook c = null;
        try {
            rs=statement.executeQuery("SELECT * FROM notebook where studentId="+studentId);
            while(rs.next()){
                c= new NoteBook();
                c.setId(rs.getInt("id"));
                c.setStudentId(studentId);
                c.setCourseId(rs.getString("courseId")!=null?Integer.parseInt(rs.getString("courseId")):null);
                c.setClassroomId(rs.getString("classroomId")!=null?Integer.parseInt(rs.getString("classroomId")):null);
                c.setPreferedSessionNumber(rs.getString("preferedsessionnumber")!=null?Integer.parseInt(rs.getString("preferedsessionnumber")):null);
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    public ArrayList<NoteBook> getNoteBooksByClassroomId(int classroomId){
        ArrayList<NoteBook> list = new ArrayList();
        NoteBook c = null;
        try {
            rs=statement.executeQuery("SELECT * FROM notebook where classroomId="+classroomId);
            while(rs.next()){
                c= new NoteBook();
                c.setId(rs.getInt("id"));
                c.setStudentId(rs.getString("studentId")!=null?Integer.parseInt(rs.getString("studentId")):null);
                c.setCourseId(rs.getString("courseId")!=null?Integer.parseInt(rs.getString("courseId")):null);
                c.setClassroomId(classroomId);
                c.setPreferedSessionNumber(rs.getString("preferedsessionnumber")!=null?Integer.parseInt(rs.getString("preferedsessionnumber")):null);
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    public ArrayList<NoteBook> getNoteBooksByCourseId(int courseId){
        ArrayList<NoteBook> list = new ArrayList();
        NoteBook c = null;
        try {
            rs=statement.executeQuery("SELECT * FROM notebook where courseId="+courseId);
            while(rs.next()){
                c= new NoteBook();
                c.setId(rs.getInt("id"));
                c.setStudentId(rs.getString("studentId")!=null?Integer.parseInt(rs.getString("studentId")):null);
                c.setCourseId(courseId);
                c.setClassroomId(rs.getString("classroomId")!=null?Integer.parseInt(rs.getString("classroomId")):null);
                c.setPreferedSessionNumber(rs.getString("preferedsessionnumber")!=null?Integer.parseInt(rs.getString("preferedsessionnumber")):null);
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    public ArrayList<NoteBook> getNoteBooksByClassroomCourseId(int classroomId,int courseId){
        ArrayList<NoteBook> list = new ArrayList();
        NoteBook c = null;
        try {
            rs=statement.executeQuery("SELECT * FROM notebook where courseId="+courseId+" and classroomId="+classroomId);
            while(rs.next()){
                c= new NoteBook();
                c.setId(rs.getInt("id"));
                c.setStudentId(rs.getString("studentId")!=null?Integer.parseInt(rs.getString("studentId")):null);
                c.setCourseId(courseId);
                c.setClassroomId(classroomId);
                c.setPreferedSessionNumber(rs.getString("preferedsessionnumber")!=null?Integer.parseInt(rs.getString("preferedsessionnumber")):null);
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    public ArrayList<NoteBook> getNoteBooksByClassroomStudentId(int classroomId,int studentId){
        ArrayList<NoteBook> list = new ArrayList();
        NoteBook c = null;
        try {
            rs=statement.executeQuery("SELECT * FROM notebook where studentId="+studentId+" and classroomId="+classroomId);
            while(rs.next()){
                c= new NoteBook();
                c.setId(rs.getInt("id"));
                c.setStudentId(studentId);
                c.setCourseId(rs.getString("courseId")!=null?Integer.parseInt(rs.getString("courseId")):null);
                c.setClassroomId(classroomId);
                c.setPreferedSessionNumber(rs.getString("preferedsessionnumber")!=null?Integer.parseInt(rs.getString("preferedsessionnumber")):null);
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    public static void main(String[] args) {
        NoteBookFactory sf=new NoteBookFactory();
//        System.out.println(sf.getNoteBooks());
//        System.out.println(sf.getNoteBook(3));
        NoteBook b= new NoteBook();
        b.setClassroomId(1000);
        b.setStudentId(1001);
        b.setCourseId(1002);
        System.out.println(b);
        sf.setNoteBook(b);
        System.out.println(b);
        sf.setNoteBook(b);
        System.out.println(b);
        
        System.out.println(sf.getNoteBooks());
    }
}
