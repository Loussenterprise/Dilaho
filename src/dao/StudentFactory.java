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
import model.Student;

/**
 *
 * @author loussin
 */
public class StudentFactory {
    
    Connection connection;
    Statement statement;
    PreparedStatement prepst;
    ResultSet rs;

    public StudentFactory() {
        connection = Database.getConnection();
        statement = Database.getStatement();
    }
    
    public void setStudent(Student s){
        try {
            prepst=connection.prepareStatement(""
                    + "INSERT INTO 'student' "
                    + "('id','matricule','name','firstnames','passwd'"
                    + ",'address','sexe','extras','image','commingscool'"
                    + ",'fathername','fatherwork','fatheraddress','mothername'"
                    + ",'motherwork','motheraddress','birthday','inscriptiondate'"
                    + ",'classroomId','scolariteId') "
                    + "VALUES (NULL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
            prepst.setString(1, s.getMatricule());
            prepst.setString(2, s.getName());
            prepst.setString(3, s.getFirstnames());
            prepst.setString(4, s.getPasswd());
            prepst.setString(5, s.getAddress());
            prepst.setString(6, s.getSexe());
            prepst.setString(7, s.getExtras());
            prepst.setString(8, s.getImage());
            prepst.setString(9, s.getCommingScool());
            prepst.setString(10, s.getFatherName());
            prepst.setString(11, s.getFatherWork());
            prepst.setString(12, s.getFatherAddress());
            prepst.setString(13, s.getMotherName());
            prepst.setString(14, s.getMotherWork());
            prepst.setString(15, s.getMotherAddress());
            prepst.setDate(16, s.getBirthday());
            prepst.setDate(17, s.getInscriptionDate());
            prepst.setString(18, s.getClassroom()!=null?s.getClassroom().toString():null);
            prepst.setString(19, s.getScolarite()!=null?s.getScolarite().toString():null);
            prepst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Student> getStudents(){
        ArrayList<Student> list = new ArrayList();
        try {
            rs=statement.executeQuery("SELECT * FROM student");
            while(rs.next()){
                Student s= new Student();
                s.setId(rs.getInt("id"));
                s.setAddress(rs.getString("address"));
                s.setBirthday(rs.getDate("birthday"));
                s.setClassroom(rs.getInt("classroomId"));
                s.setCommingScool(rs.getString("commingScool"));
                s.setExtras(rs.getString("extras"));
                s.setFatherAddress(rs.getString("fatherAddress"));
                s.setFatherName(rs.getString("fatherName"));
                s.setFirstnames(rs.getString("firstnames"));
                s.setImage(rs.getString("image"));
                s.setInscriptionDate(rs.getDate("inscriptionDate"));
                s.setMatricule(rs.getString("matricule"));
                s.setMotherAddress(rs.getString("motherAddress"));
                s.setMotherName(rs.getString("motherName"));
                s.setMotherWork(rs.getString("motherWork"));
                s.setName(rs.getString("name"));
                s.setPasswd(rs.getString("passwd"));
                s.setScolarite(rs.getInt("scolariteId"));
                s.setSexe(rs.getString("sexe"));
                list.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public Student getStudent(int id){
        Student s =null ;
        try {
            rs=statement.executeQuery("SELECT * FROM student where id="+id);
            if(rs.next()){
                s= new Student();
                s.setId(rs.getInt("id"));
                s.setAddress(rs.getString("address"));
                s.setBirthday(rs.getDate("birthday"));
                s.setClassroom(rs.getInt("classroomId"));
                s.setCommingScool(rs.getString("commingScool"));
                s.setExtras(rs.getString("extras"));
                s.setFatherAddress(rs.getString("fatherAddress"));
                s.setFatherName(rs.getString("fatherName"));
                s.setFirstnames(rs.getString("firstnames"));
                s.setImage(rs.getString("image"));
                s.setInscriptionDate(rs.getDate("inscriptionDate"));
                s.setMatricule(rs.getString("matricule"));
                s.setMotherAddress(rs.getString("motherAddress"));
                s.setMotherName(rs.getString("motherName"));
                s.setMotherWork(rs.getString("motherWork"));
                s.setName(rs.getString("name"));
                s.setPasswd(rs.getString("passwd"));
                s.setScolarite(rs.getInt("scolariteId"));
                s.setSexe(rs.getString("sexe"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
    
    public static void main(String[] args) {
        StudentFactory sf=new StudentFactory();
        Student s= new Student();
        s.setName("LOUSSIN");
        s.setFirstnames("Andre Ange");
        sf.setStudent(s);
        System.out.println(sf.getStudents());
        System.out.println(sf.getStudent(3));
    }
}