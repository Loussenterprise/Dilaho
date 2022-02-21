/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author loussin
 */
public class General {
    
    
    public static String getSavePath(){
        String s=null;
        try {
            s=Database.getStatement().executeQuery("SELECT savepath FROM general WHERE id==1").getString("savepath");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
    
    public static String getScoolYear(){
        String s=null;
        try {
            s=Database.getStatement().executeQuery("SELECT scoolyear FROM general  WHERE id==1").getString("scoolyear");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
    public static void setSavePath(String path){
        try {
            Database.getStatement().executeUpdate("UPDATE 'general' SET 'savepath'='"+path+"' WHERE id=1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void setScoolYear(String scoolyear){
        if (scoolyear!=null && !scoolyear.isEmpty() && scoolyear.matches("[0-9]{4}-[0-9]{4}")) {
            try {
                Database.getStatement().executeUpdate("UPDATE  'general' SET 'scoolyear'='"+scoolyear+"' WHERE id=1");
            } catch (Exception e) {
                e.printStackTrace();             
            }
        }else
            System.out.println("scoolyear not matches");
    }
}
