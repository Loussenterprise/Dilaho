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
import model.Note;

/**
 *
 * @author loussin
 */
public class NoteFactory {
    
    Connection connection;
    Statement statement;
    PreparedStatement prepst;
    ResultSet rs;

    public NoteFactory() {
        connection = Database.getConnection();
        statement = Database.getStatement();
    }
    
    public void setNote(Note c){
        try {
            prepst=connection.prepareStatement(""
                    + "INSERT INTO 'note'('id','valeur','isdevoir',"
                    + "'sessionId') VALUES (NULL,?,?,?);");
            
            prepst.setString(1, c.getValeur()!=null?c.getValeur().toString():null);
            prepst.setInt(2, c.isDevoir()?1:0);
            prepst.setString(3, c.getSessionId()!=null?c.getSessionId().toString():null);
            
            prepst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Note> getNotes(){
        ArrayList<Note> list = new ArrayList();
        try {
            rs=statement.executeQuery("SELECT * FROM note");
            while(rs.next()){
                Note c= new Note();
                c.setId(rs.getInt("id"));
                c.setValeur(rs.getString("valeur")!=null?Double.parseDouble(rs.getString("valeur")):null);
                c.setIsDevoir(rs.getInt("isDevoir")==1);
                c.setSessionId(rs.getString("sessionId")!=null?Integer.parseInt(rs.getString("sessionId")):null);
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public Note getNote(int id){
        Note c = null;
        try {
            rs=statement.executeQuery("SELECT * FROM note where id="+id);
            if(rs.next()){
                c= new Note();
                c.setId(rs.getInt("id"));
                c.setValeur(rs.getString("valeur")!=null?Double.parseDouble(rs.getString("valeur")):null);
                c.setIsDevoir(rs.getInt("isDevoir")==1);
                c.setSessionId(rs.getString("sessionId")!=null?Integer.parseInt(rs.getString("sessionId")):null);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
    
    
    public static void main(String[] args) {
        NoteFactory sf=new NoteFactory();
        sf.setNote(new Note());
        System.out.println(sf.getNotes());
        System.out.println(sf.getNote(3));
        System.out.println(new Note());
    }
}
