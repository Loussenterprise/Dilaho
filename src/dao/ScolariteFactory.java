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
import model.Scolarite;

/**
 *
 * @author loussin
 */
public class ScolariteFactory {
    

    Connection connection;
    Statement statement;
    PreparedStatement prepst;
    ResultSet rs;

    public ScolariteFactory() {
        connection = Database.getConnection();
        statement = Database.getStatement();
    }
    
    public void setScolarite(Scolarite c){
        try {
            prepst=connection.prepareStatement(""
                    + "INSERT INTO 'main'.'scolarite'('id',"
                    + "'contribution','mtpaye','studentId',"
                    + "'classroomId','notebookId') VALUES "
                    + "(NULL,?,?,?,?,?);");
            
            prepst.setString(1, c.getContribution()!=null?c.getContribution().toString():null);
            prepst.setString(2, c.getMtpaye()!=null?c.getMtpaye().toString():null);
            prepst.setString(3, c.getStudentId()!=null?c.getStudentId().toString():null);
            prepst.setString(4, c.getClassroomId()!=null?c.getClassroomId().toString():null);
            prepst.setString(5, c.getNotebookId()!=null?c.getNotebookId().toString():null);
            
            prepst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Scolarite> getScolarites(){
        ArrayList<Scolarite> list = new ArrayList();
        try {
            rs=statement.executeQuery("SELECT * FROM scolarite");
            while(rs.next()){
                Scolarite c= new Scolarite();
                c.setId(rs.getInt("id"));
                c.setContribution(rs.getString("contribution")!=null?Double.parseDouble(rs.getString("contribution")):null);
                c.setMtpaye(rs.getString("mtpaye")!=null?Double.parseDouble(rs.getString("mtpaye")):null);
                c.setStudentId(rs.getString("studentId")!=null?Integer.parseInt(rs.getString("studentId")):null);
                c.setClassroomId(rs.getString("classroomId")!=null?Integer.parseInt(rs.getString("classroomId")):null);
                c.setClassroomId(rs.getString("notebookId")!=null?Integer.parseInt(rs.getString("notebookId")):null);
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public Scolarite getScolarite(int id){
        Scolarite c = null;
        try {
            rs=statement.executeQuery("SELECT * FROM scolarite where id="+id);
            if(rs.next()){
                c= new Scolarite();
                c.setId(rs.getInt("id"));
                c.setContribution(rs.getString("contribution")!=null?Double.parseDouble(rs.getString("contribution")):null);
                c.setMtpaye(rs.getString("mtpaye")!=null?Double.parseDouble(rs.getString("mtpaye")):null);
                c.setStudentId(rs.getString("studentId")!=null?Integer.parseInt(rs.getString("studentId")):null);
                c.setClassroomId(rs.getString("classroomId")!=null?Integer.parseInt(rs.getString("classroomId")):null);
                c.setClassroomId(rs.getString("notebookId")!=null?Integer.parseInt(rs.getString("notebookId")):null);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
    
    
    public static void main(String[] args) {
        ScolariteFactory sf=new ScolariteFactory();
        sf.setScolarite(new Scolarite());
        System.out.println(sf.getScolarites());
        System.out.println(sf.getScolarite(3));
        System.out.println(new Scolarite());
    }
}