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

/**
 *
 * @author loussin
 */
public class ScoolYearFactory {
    private Connection connection;
    private Statement statement;
    private PreparedStatement prepst;
    private ResultSet rs;

    public ScoolYearFactory() {
        connection = Database.getConnection();
        statement = Database.getStatement();
    }
    
    
    public void addScoolYear(String year){
        if(year!=null && year.matches("[0-9]{4}-[0-9]{4}"))
            try {
                prepst=connection.prepareStatement(""
                        + "INSERT INTO 'scoolyear'('id','year') VALUES (NULL,?) ");
                prepst.setString(1,year);
                prepst.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(AddFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        else
            System.out.println("Year null or not matches");
    }
    
    public void setScoolYear(String year){
        addScoolYear(year);
    }
    
    public ArrayList<String> getScoolYears(){
        ArrayList<String> list = new ArrayList();
        try {
            rs=statement.executeQuery("SELECT year FROM scoolyear");
            while(rs.next()){
                list.add(rs.getString("year"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
       
    }
    
    public static void main(String[] args) {
            Database.seed();
            ScoolYearFactory scf= new ScoolYearFactory();
            scf.addScoolYear("2001-200");
            scf.addScoolYear("2003-2004");
            System.out.println(scf.getScoolYears());
    }
    
}
