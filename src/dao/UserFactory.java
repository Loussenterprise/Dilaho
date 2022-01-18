/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author loussin
 */
public class UserFactory {
    
    Connection connection;
    Statement statement;

    public UserFactory() {
        connection = Database.getConnection();
        statement = Database.getStatement();
    }
    
    
    public boolean isConnected(){
        return Database.isConnected();
    }
    
    public User getUser(int id){
        User user = null;
        try {
            ResultSet rs = statement.executeQuery("select * from user where id="+id);
            if(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPasswd("Passwd is private");
                user.setRole(rs.getInt("role"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
    public ArrayList<User> getUsers(){
        ArrayList<User> list = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery("select * from user ");
            while(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPasswd("Passwd is private");
                user.setRole(rs.getInt("role"));
                list.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public ArrayList<String> getUsersNames(){
        ArrayList<String> l=new ArrayList<>();
        for(User u: getUsers()){
            l.add(u.getName());
        }
        return l;
    }
    
    public User connect(String email, String passwd){
        User user = null;
        try {
            ResultSet rs = statement.executeQuery("select * from user where email='"+email+"' and passwd='"+passwd+"'");
            if(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPasswd("Passwd is private");
                user.setRole(rs.getInt("role"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
}
