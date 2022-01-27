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
import model.Session;

/**
 *
 * @author loussin
 */
public class SessionFactory {
    
    Connection connection;
    Statement statement;
    PreparedStatement prepst;
    ResultSet rs;

    public SessionFactory() {
        connection = Database.getConnection();
        statement = Database.getStatement();
    }
    
    public void setSession(Session c){
        try {
            prepst=connection.prepareStatement(""
                    + "INSERT INTO 'session'('id',"
                    + "'profappreciation','dgappreciation',"
                    + "'interromoyenne','hightmoyenne','lowmoyenne','moyenne',"
                    + "'range','isfinal','notebookId') "
                    + "VALUES ("+c.getId()+",?,?,?,?,?,?,?,?,?)"
                    + "ON CONFLICT(id) DO UPDATE SET "
                    + "'profappreciation'=?,'dgappreciation'=?,"
                    + "'interromoyenne'=?,'hightmoyenne'=?,'lowmoyenne'=?,'moyenne'=?,"
                    + "'range'=?,'isfinal'=?,'notebookId'=?"
            );
            
            prepst.setString(1, c.getProfAppreciation());
            prepst.setString(2, c.getDgAppreciation());
            prepst.setString(3, c.getInterroMoyenne()!=null?c.getInterroMoyenne().toString():null);
            prepst.setString(4, c.getHightMoyenne()!=null?c.getHightMoyenne().toString():null);
            prepst.setString(5, c.getLowMoyenne()!=null?c.getLowMoyenne().toString():null);
            prepst.setString(6, c.getMoyenne()!=null?c.getMoyenne().toString():null);
            prepst.setInt(7, c.isFinal()?1:0);
            prepst.setString(8, c.getRange()!=null?c.getRange().toString():null);
            prepst.setString(9, c.getNoteBookId()!=null?c.getNoteBookId().toString():null);
            
            prepst.setString(10, c.getProfAppreciation());
            prepst.setString(11, c.getDgAppreciation());
            prepst.setString(12, c.getInterroMoyenne()!=null?c.getInterroMoyenne().toString():null);
            prepst.setString(13, c.getHightMoyenne()!=null?c.getHightMoyenne().toString():null);
            prepst.setString(14, c.getLowMoyenne()!=null?c.getLowMoyenne().toString():null);
            prepst.setString(15, c.getMoyenne()!=null?c.getMoyenne().toString():null);
            prepst.setInt(16, c.isFinal()?1:0);
            prepst.setString(17, c.getRange()!=null?c.getRange().toString():null);
            prepst.setString(18, c.getNoteBookId()!=null?c.getNoteBookId().toString():null);
            
            prepst.executeUpdate();
            c.setId(statement.executeQuery("SELECT last_insert_rowid() as id").getInt("id"));
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Session> getSessions(){
        ArrayList<Session> list = new ArrayList();
        try {
            rs=statement.executeQuery("SELECT * FROM session");
            while(rs.next()){
                Session c= new Session();
                c.setId(rs.getInt("id"));
                c.setProfAppreciation(rs.getString("profappreciation"));
                c.setDgAppreciation(rs.getString("dgappreciation"));
                c.setHightMoyenne(rs.getString("hightmoyenne")!=null?Double.parseDouble(rs.getString("hightmoyenne")):null);
                c.setLowMoyenne(rs.getString("lowmoyenne")!=null?Double.parseDouble(rs.getString("lowmoyenne")):null);
                c.setMoyenne(rs.getString("moyenne")!=null?Double.parseDouble(rs.getString("moyenne")):null);
                c.setIsFinal(rs.getInt("isFinal")==1);
                c.setRange(rs.getString("range")!=null?Integer.parseInt(rs.getString("range")):null);
                c.setNoteBookId(rs.getString("notebookId")!=null?Integer.parseInt(rs.getString("notebookId")):null);
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public Session getSession(int id){
        Session c = null;
        try {
            rs=statement.executeQuery("SELECT * FROM session where id="+id);
            if(rs.next()){
                c= new Session();
                c.setId(rs.getInt("id"));
                c.setProfAppreciation(rs.getString("profappreciation"));
                c.setDgAppreciation(rs.getString("dgappreciation"));
                c.setInterroMoyenne(rs.getString("interromoyenne")!=null?Double.parseDouble(rs.getString("interromoyenne")):null);
                c.setHightMoyenne(rs.getString("hightmoyenne")!=null?Double.parseDouble(rs.getString("hightmoyenne")):null);
                c.setLowMoyenne(rs.getString("lowmoyenne")!=null?Double.parseDouble(rs.getString("lowmoyenne")):null);
                c.setMoyenne(rs.getString("moyenne")!=null?Double.parseDouble(rs.getString("moyenne")):null);
                c.setIsFinal(rs.getInt("isFinal")==1);
                c.setRange(rs.getString("range")!=null?Integer.parseInt(rs.getString("range")):null);
                c.setNoteBookId(rs.getString("notebookId")!=null?Integer.parseInt(rs.getString("notebookId")):null);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
    
    
    public static void main(String[] args) {
        SessionFactory sf=new SessionFactory();
        System.out.println(sf.getSessions());
        System.out.println(sf.getSession(3));
        System.out.println(new Session());
    }

    public ArrayList<Session> getSessionsByNotebookId(Integer id) {
        ArrayList<Session> list = new ArrayList();
        try {
            rs=statement.executeQuery("SELECT * FROM session where notebookId="+id);
            while(rs.next()){
                Session c= new Session();
                c.setId(rs.getInt("id"));
                c.setProfAppreciation(rs.getString("profappreciation"));
                c.setDgAppreciation(rs.getString("dgappreciation"));
                c.setInterroMoyenne(rs.getString("interromoyenne")!=null?Double.parseDouble(rs.getString("interromoyenne")):null);
                c.setHightMoyenne(rs.getString("hightmoyenne")!=null?Double.parseDouble(rs.getString("hightmoyenne")):null);
                c.setLowMoyenne(rs.getString("lowmoyenne")!=null?Double.parseDouble(rs.getString("lowmoyenne")):null);
                c.setMoyenne(rs.getString("moyenne")!=null?Double.parseDouble(rs.getString("moyenne")):null);
                c.setIsFinal(rs.getInt("isFinal")==1);
                c.setRange(rs.getString("range")!=null?Integer.parseInt(rs.getString("range")):null);
                c.setNoteBookId(id);
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
