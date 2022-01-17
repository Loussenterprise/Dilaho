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
import model.Add;

/**
 *
 * @author loussin
 */
public class AddFactory {
    Connection connection;
    Statement statement;
    PreparedStatement prepst;
    ResultSet rs;

    public AddFactory() {
        connection = Database.getConnection();
        statement = Database.getStatement();
    }
    
    public void addAdd(Add add){
        try {
            prepst=connection.prepareStatement(""
                    + "INSERT INTO 'adds'('id','studentId','title','content') VALUES (NULL,?,?,?);");
            prepst.setString(1, add.getStudentId()!=null?add.getStudentId().toString():null);
            prepst.setString(2, add.getTitle());
            prepst.setString(3, add.getContent());
            System.out.println(prepst.executeUpdate());
        } catch (SQLException ex) {
            Logger.getLogger(AddFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setAdd( Add add){
        addAdd(add);
    }
    
    public ArrayList<Add> getAdds(){
        ArrayList<Add> list = new ArrayList();
        try {
            rs=statement.executeQuery("SELECT * FROM adds");
            while(rs.next()){
                Add a= new Add();
                a.setId(rs.getInt("id"));
                a.setStudentId(rs.getString("studentId")!=null?Integer.parseInt(rs.getString("studentId")):null);
                a.setTitle(rs.getString("title"));
                a.setContent(rs.getString("content"));
                list.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
       
    }
    
    
    public Add getAdd(int id){
        Add a = null;
        try {
            rs=statement.executeQuery("SELECT * FROM adds where id="+id);
            if(rs.next()){
                a= new Add();
                a.setId(rs.getInt("id"));
                a.setStudentId(rs.getString("studentId")!=null?Integer.parseInt(rs.getString("studentId")):null);
                a.setTitle(rs.getString("title"));
                a.setContent(rs.getString("content"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
    
    public static void main(String[] args) {
        AddFactory sf=new AddFactory();
        Add s= new Add();
        s.setTitle("LOUSSIN");
        s.setContent("Andre Ange");
        sf.setAdd(s);
        System.out.println(sf.getAdds());
        System.out.println(sf.getAdd(3));
    }
    
}
