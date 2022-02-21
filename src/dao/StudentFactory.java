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
import model.Session;
import model.Student;
import vue.main.notes.NotesController;

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
    
    public int setStudent(Student s){
        int id=-1;
        try {
            prepst=connection.prepareStatement(""
                    + "INSERT INTO 'student' "
                    + "('id','matricule','name','firstnames','passwd'"
                    + ",'address','sexe','extras','image','commingscool'"
                    + ",'fathername','fatherwork','fatheraddress','mothername'"
                    + ",'motherwork','motheraddress','birthday','inscriptiondate'"
                    + ",'classroomId','scolariteId') "
                    + "VALUES ("+s.getId()+",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)  "
                    + "ON CONFLICT(id) DO UPDATE SET "
                    + "'matricule'= ?,'name'= ?,'firstnames'= ?,'passwd'= ? "
                    + ",'address'=?,'sexe'=?,'extras'=?,'image'=?,'commingscool'= ?"
                    + ",'fathername'=?,'fatherwork'=?,'fatheraddress'=?,'mothername'=?"
                    + ",'motherwork'=?,'motheraddress'=?,'birthday'=?,'inscriptiondate'=?"
                    + ",'classroomId'=?,'scolariteId'=? ;");
            prepst.setString(1, s.getMatricule()!=null?s.getMatricule().toString():null);
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
            
            prepst.setString(20, s.getMatricule()!=null?s.getMatricule().toString():null);
            prepst.setString(21, s.getName());
            prepst.setString(22, s.getFirstnames());
            prepst.setString(23, s.getPasswd());
            prepst.setString(24, s.getAddress());
            prepst.setString(25, s.getSexe());
            prepst.setString(26, s.getExtras());
            prepst.setString(27, s.getImage());
            prepst.setString(28, s.getCommingScool());
            prepst.setString(29, s.getFatherName());
            prepst.setString(30, s.getFatherWork());
            prepst.setString(31, s.getFatherAddress());
            prepst.setString(32, s.getMotherName());
            prepst.setString(33, s.getMotherWork());
            prepst.setString(34, s.getMotherAddress());
            prepst.setDate(35, s.getBirthday());
            prepst.setDate(36, s.getInscriptionDate());
            prepst.setString(37, s.getClassroom()!=null?s.getClassroom().toString():null);
            prepst.setString(38, s.getScolarite()!=null?s.getScolarite().toString():null);
            prepst.executeUpdate();
            
            if(s.getId()==null){
                id=statement.executeQuery("SELECT last_insert_rowid() as id").getInt("id");
                s.setId(id);
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
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
                s.setClassroomf(rs.getInt("classroomId"));
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
    
    public ArrayList<Student> getStudentsByClassroom(Integer classroom){
        ArrayList<Student> list = new ArrayList();
        try {
            rs=statement.executeQuery("SELECT * FROM student where classroomId='"+classroom.toString()+"'");
            while(rs.next()){
                Student s= new Student();
                s.setId(rs.getInt("id"));
                s.setAddress(rs.getString("address"));
                s.setBirthday(rs.getDate("birthday"));
                s.setClassroomf(rs.getInt("classroomId"));
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
                s.setClassroomf(rs.getInt("classroomId"));
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
    
    public static int getFilleNbr(ArrayList<Student> students){
        int nbr=0;
        for(Student s:students)
            if("F".equals(s.getSexe()))
                nbr++;
        return nbr;
    }
    public static int getGarconNbr(ArrayList<Student> students){
        int nbr=0;
        for(Student s:students)
            if("H".equals(s.getSexe()))
                nbr++;
        return nbr;
    }
    
    public static void main(String[] args) {
        StudentFactory sf=new StudentFactory();
        for (int i = 0; i < 30; i++) {
            Student s= new Student();
            s.setName("LOUSSIN");
            s.setFirstnames("Andre Ange");
            s.setClassroomf(12);
            sf.setStudent(s);
        }
            
        System.out.println(sf.getStudents());
        System.out.println(sf.getStudent(3));
    }
//    
//    static void dopperStudents(ArrayList<Student> list){
//        try {
//            for(Student s:list){
//                for(NoteBook nb:s.loadNotebooks()){
//                    for(Session se:nb.loadSessions()){
//                        se.loadNotes();
//                    }
//                }
//            }
//        } catch (Exception e) {
//            Logger.getLogger(NotesController.class.getName()).log(Level.SEVERE, null, e);
//        }
//    }
}
