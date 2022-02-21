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
import model.Stat;
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
                    + "INSERT INTO 'notebook'('id','scolariteId',"
                    + "'courseId','preferedsessionnumber') VALUES ("+c.getId()+",?,?,?) "
                    + "ON CONFLICT(id) DO UPDATE SET "
                    + "'scolariteId'=?,'courseId'=?,'preferedsessionnumber'=?");
            
            prepst.setString(1, c.getScolariteId()!=null?c.getScolariteId().toString():null);
            prepst.setString(2, c.getCourseId()!=null?c.getCourseId().toString():null);
            prepst.setString(3, c.getPreferedSessionNumber()!=null?c.getPreferedSessionNumber().toString():null);
            
            prepst.setString(4, c.getScolariteId()!=null?c.getScolariteId().toString():null);
            prepst.setString(5, c.getCourseId()!=null?c.getCourseId().toString():null);
            prepst.setString(6, c.getPreferedSessionNumber()!=null?c.getPreferedSessionNumber().toString():null);
            
            prepst.executeUpdate();
            int id = statement.executeQuery("SELECT last_insert_rowid() as id").getInt("id");
            if(c.getId()==null)
                c.setId(id);
            c.setStat(Stat.NONE);
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
                c.setScolariteId(rs.getString("scolariteId")!=null?Integer.parseInt(rs.getString("scolariteId")):null);
                c.setCourseId(rs.getString("courseId")!=null?Integer.parseInt(rs.getString("courseId")):null);
                
                c.setPreferedSessionNumber(rs.getString("preferedsessionnumber")!=null?Integer.parseInt(rs.getString("preferedsessionnumber")):null);
                c.setStat(Stat.NONE);
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
                c.setScolariteId(rs.getString("scolariteId")!=null?Integer.parseInt(rs.getString("scolariteId")):null);
                c.setCourseId(rs.getString("courseId")!=null?Integer.parseInt(rs.getString("courseId")):null);
                
                c.setPreferedSessionNumber(rs.getString("preferedsessionnumber")!=null?Integer.parseInt(rs.getString("preferedsessionnumber")):null);
                c.setStat(Stat.NONE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
    
    
    public ArrayList<NoteBook> getNoteBooksByScolariteId(int scolariteId){
        ArrayList<NoteBook> list = new ArrayList();
        NoteBook c = null;
        try {
            rs=statement.executeQuery("SELECT * FROM notebook where scolariteId="+scolariteId);
            while(rs.next()){
                c= new NoteBook();
                c.setId(rs.getInt("id"));
                c.setScolariteId(scolariteId);
                c.setCourseId(rs.getString("courseId")!=null?Integer.parseInt(rs.getString("courseId")):null);
                c.setPreferedSessionNumber(rs.getString("preferedsessionnumber")!=null?Integer.parseInt(rs.getString("preferedsessionnumber")):null);
                c.setStat(Stat.NONE);
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
//    
//    public ArrayList<NoteBook> getNoteBooksByClassroomId(int classroomId){
//        ArrayList<NoteBook> list = new ArrayList();
//        NoteBook c = null;
//        try {
//            rs=statement.executeQuery("SELECT * FROM notebook where classroomId="+classroomId);
//            while(rs.next()){
//                c= new NoteBook();
//                c.setId(rs.getInt("id"));
//                c.setScolariteId(rs.getString("scolariteId")!=null?Integer.parseInt(rs.getString("scolariteId")):null);
//                c.setCourseId(rs.getString("courseId")!=null?Integer.parseInt(rs.getString("courseId")):null);
//                
//                c.setPreferedSessionNumber(rs.getString("preferedsessionnumber")!=null?Integer.parseInt(rs.getString("preferedsessionnumber")):null);
//                list.add(c);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return list;
//    }
    
    
    public ArrayList<NoteBook> getNoteBooksByCourseId(int courseId){
        ArrayList<NoteBook> list = new ArrayList();
        NoteBook c = null;
        try {
            rs=statement.executeQuery("SELECT * FROM notebook where courseId="+courseId);
            while(rs.next()){
                c= new NoteBook();
                c.setId(rs.getInt("id"));
                c.setScolariteId(rs.getString("scolariteId")!=null?Integer.parseInt(rs.getString("scolariteId")):null);
                c.setCourseId(courseId);
                
                c.setPreferedSessionNumber(rs.getString("preferedsessionnumber")!=null?Integer.parseInt(rs.getString("preferedsessionnumber")):null);
                c.setStat(Stat.NONE);
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    public ArrayList<NoteBook> getNoteBooksByScolariteCourseId(int scolariteId,int courseId){
        ArrayList<NoteBook> list = new ArrayList();
        NoteBook c = null;
        try {
            rs=statement.executeQuery("SELECT * FROM notebook where courseId="+courseId+" and scolariteId="+scolariteId);
            while(rs.next()){
                c= new NoteBook();
                c.setId(rs.getInt("id"));
                c.setScolariteId(rs.getString("scolariteId")!=null?Integer.parseInt(rs.getString("scolariteId")):null);
                c.setCourseId(courseId);
                c.setPreferedSessionNumber(rs.getString("preferedsessionnumber")!=null?Integer.parseInt(rs.getString("preferedsessionnumber")):null);
                c.setStat(Stat.NONE);
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
//    
//    
//    public ArrayList<NoteBook> getNoteBooksByClassroomStudentId(int classroomId,int studentId){
//        ArrayList<NoteBook> list = new ArrayList();
//        NoteBook c = null;
//        try {
//            rs=statement.executeQuery("SELECT * FROM notebook where studentId="+studentId+" and classroomId="+classroomId);
//            while(rs.next()){
//                c= new NoteBook();
//                c.setId(rs.getInt("id"));
//                c.setStudentId(studentId);
//                c.setCourseId(rs.getString("courseId")!=null?Integer.parseInt(rs.getString("courseId")):null);
//                c.setClassroomId(classroomId);
//                c.setPreferedSessionNumber(rs.getString("preferedsessionnumber")!=null?Integer.parseInt(rs.getString("preferedsessionnumber")):null);
//                list.add(c);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return list;
//    }
    
    
    public static void main(String[] args) {
        NoteBookFactory sf=new NoteBookFactory();
//        System.out.println(sf.getNoteBooks());
//        System.out.println(sf.getNoteBook(3));
        NoteBook b= new NoteBook();
        b.setCourseId(1002);
        System.out.println(b);
        sf.setNoteBook(b);
        System.out.println(b);
        sf.setNoteBook(b);
        System.out.println(b);
        
        System.out.println(sf.getNoteBooks());
    }
}
