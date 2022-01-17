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
                    + "'courseId','preferedsessionnumber') VALUES (NULL,?,?,?,?);");
            
            prepst.setString(1, c.getStudentId()!=null?c.getStudentId().toString():null);
            prepst.setString(2, c.getClassroomId()!=null?c.getClassroomId().toString():null);
            prepst.setString(3, c.getCourseId()!=null?c.getCourseId().toString():null);
            prepst.setString(4, c.getPreferedSessionNumber()!=null?c.getPreferedSessionNumber().toString():null);
            
            prepst.executeUpdate();
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
    
    
    public static void main(String[] args) {
        NoteBookFactory sf=new NoteBookFactory();
        sf.setNoteBook(new NoteBook());
        System.out.println(sf.getNoteBooks());
        System.out.println(sf.getNoteBook(3));
        System.out.println(new NoteBook());
    }
}
