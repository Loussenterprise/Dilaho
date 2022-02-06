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
import model.Course;

/**
 *
 * @author loussin
 */
public class CourseFactory {
    
    
    Connection connection;
    Statement statement;
    PreparedStatement prepst;
    ResultSet rs;

    public CourseFactory() {
        connection = Database.getConnection();
        statement = Database.getStatement();
    }
    
    public int setCourse(Course c){
        int id=-1;
        try {
            prepst=connection.prepareStatement(""
                    + "INSERT INTO 'course'('id','name','profname',"
                    + "'classlevelId','coeff') VALUES "
                    + "("+c.getId()+",?,?,?,?)"
                    + "ON CONFLICT(id) DO UPDATE SET "
                    + "'name'=?,'profname'=?,"
                    + "'classlevelId'=?,'coeff'=?");
            
            prepst.setString(1, c.getName());
            prepst.setString(2, c.getProfname());
            prepst.setString(3, c.getClasslevelId()!=null?c.getClasslevelId().toString():null);
            prepst.setString(4, c.getCoeff()!=null?c.getCoeff().toString():null);
            
            prepst.setString(5, c.getName());
            prepst.setString(6, c.getProfname());
            prepst.setString(7, c.getClasslevelId()!=null?c.getClasslevelId().toString():null);
            prepst.setString(8, c.getCoeff()!=null?c.getCoeff().toString():null);
            
            prepst.executeUpdate();
            
            
            if(c.getId()==null){
                id=statement.executeQuery("SELECT last_insert_rowid() as id").getInt("id");
                c.setId(id);
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    public ArrayList<Course> getCourses(){
        ArrayList<Course> list = new ArrayList();
        try {
            rs=statement.executeQuery("SELECT * FROM course");
            while(rs.next()){
                Course c= new Course();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setProfname(rs.getString("profname"));
                c.setClasslevelId(rs.getString("classlevelId")!=null?Integer.parseInt(rs.getString("classlevelId")):null);
                c.setCoeff(rs.getString("coeff")!=null?Integer.parseInt(rs.getString("coeff")):null);
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public Course getCourse(int id){
        Course c = null;
        try {
            rs=statement.executeQuery("SELECT * FROM course where id="+id);
            if(rs.next()){
                c= new Course();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setProfname(rs.getString("profname"));
                c.setClasslevelId(rs.getString("classlevelId")!=null?Integer.parseInt(rs.getString("classlevelId")):null);
                c.setCoeff(rs.getString("coeff")!=null?Integer.parseInt(rs.getString("coeff")):null);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
    
    
    public static void main(String[] args) {
        CourseFactory sf=new CourseFactory();
        System.out.println(sf.getCourses());
        System.out.println(new ClasslevelFactory().getClasslevel(37));
        System.out.println(sf.getCoursesByClasslevelId(37));
        System.out.println(sf.getCoursesByClasslevelId(20));
    }

    public ArrayList<Course> getCoursesByClasslevelId(int id) {
        ArrayList<Course> list = new ArrayList();
        try {
            System.out.println("dao.CourseFactory.getCoursesByClasslevelId() ### "+id);
            rs=statement.executeQuery("SELECT * FROM course where classlevelId="+id);
            while(rs.next()){
                Course c= new Course();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setProfname(rs.getString("profname"));
                c.setClasslevelId(rs.getString("classlevelId")!=null?Integer.parseInt(rs.getString("classlevelId")):null);
                c.setCoeff(rs.getString("coeff")!=null?Integer.parseInt(rs.getString("coeff")):null);
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("\n########################################\n"+list+"\n########################################\n");
        return list;
    }
}
