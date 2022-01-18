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
import model.Classroom;

/**
 *
 * @author loussin
 */
public class ClassroomFactory {
    
    Connection connection;
    Statement statement;
    PreparedStatement prepst;
    ResultSet rs;

    public ClassroomFactory() {
        connection = Database.getConnection();
        statement = Database.getStatement();
    }
    
    public void setClassroom(Classroom c){
        try {
            prepst=connection.prepareStatement(""
                    + "INSERT INTO 'classroom'('id','promotion','scoolyear',"
                    + "'groupe','contribution','classlevelId') "
                    + "VALUES (NULL,?,?,?,?,?);");
            
            prepst.setString(1, c.getPromotion());
            prepst.setString(2, c.getScoolYear());
            prepst.setString(3, c.getGroup());
            prepst.setString(4, c.getContribution()!=null?c.getContribution().toString():null);
            prepst.setString(5, c.getClasslevelId()!=null?c.getClasslevelId().toString():null);
            
            prepst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Classroom> getClassrooms(){
        ArrayList<Classroom> list = new ArrayList();
        try {
            rs=statement.executeQuery("SELECT * FROM classroom");
            while(rs.next()){
                Classroom c= new Classroom();
                c.setId(rs.getInt("id"));
                c.setPromotion(rs.getString("promotion"));
                c.setScoolYear(rs.getString("scoolyear"));
                c.setGroup(rs.getString("groupe"));
                c.setContribution(rs.getString("contribution")!=null?Double.parseDouble(rs.getString("contribution")):null);
                c.setClasslevelId(rs.getString("classlevelId")!=null?Integer.parseInt(rs.getString("classlevelId")):null);
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public Classroom getClassroom(int id){
        Classroom c = null;
        try {
            rs=statement.executeQuery("SELECT * FROM classroom where id="+id);
            if(rs.next()){
                c= new Classroom();
                c.setId(rs.getInt("id"));
                c.setPromotion(rs.getString("promotion"));
                c.setScoolYear(rs.getString("scoolyear"));
                c.setGroup(rs.getString("groupe"));
                c.setContribution(rs.getString("contribution")!=null?Double.parseDouble(rs.getString("contribution")):null);
                c.setClasslevelId(rs.getString("classlevelId")!=null?Integer.parseInt(rs.getString("classlevelId")):null);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
    
    
    public static void main(String[] args) {
        ClassroomFactory sf=new ClassroomFactory();
        sf.setClassroom(new Classroom());
        Classroom c= new Classroom();
        c.setGroup("C");
        sf.setClassroom(c);
        System.out.println(sf.getClassrooms());
        System.out.println(sf.getClassroom(3));
        System.out.println(new Classroom());
    }
}
