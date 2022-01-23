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
import model.Classlevel;

/**
 *
 * @author loussin
 */
public class ClasslevelFactory {
    Connection connection;
    Statement statement;
    PreparedStatement prepst;
    ResultSet rs;

    public ClasslevelFactory() {
        connection = Database.getConnection();
        statement = Database.getStatement();
    }
    
    public int setClasslevel(Classlevel c){
        int id=-1;
        try {
            prepst=connection.prepareStatement(""
                    + "INSERT INTO 'classlevel'('id','name','n',"
                    + "'niveau','option','op','contribution')"
                    + " VALUES ("+c.getId()+",?,?,?,?,?,?)"
                    + "ON CONFLICT(id) DO UPDATE SET "
                    + "'name'=?,'n'=?,"
                    + "'niveau'=?,'option'=?,'op'=?,'contribution'=? ");
            
            prepst.setString(1, c.getName());
            prepst.setString(2, c.getN());
            prepst.setString(3, c.getNiveau());
            prepst.setString(4, c.getOption());
            prepst.setString(5, c.getOp());
            prepst.setString(6, c.getContribution()!=null?c.getContribution().toString():null);
            
            prepst.setString(7, c.getName());
            prepst.setString(8, c.getN());
            prepst.setString(9, c.getNiveau());
            prepst.setString(10, c.getOption());
            prepst.setString(11, c.getOp());
            prepst.setString(12, c.getContribution()!=null?c.getContribution().toString():null);
            
            prepst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    public ArrayList<Classlevel> getClasslevels(){
        ArrayList<Classlevel> list = new ArrayList();
        try {
            rs=statement.executeQuery("SELECT * FROM classlevel");
            while(rs.next()){
                Classlevel c= new Classlevel();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setN(rs.getString("n"));
                c.setNiveau(rs.getString("niveau"));
                c.setOption(rs.getString("option"));
                c.setOp(rs.getString("op"));
                c.setContribution(rs.getString("contribution")!=null?Double.parseDouble(rs.getString("contribution")):null);
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public Classlevel getClasslevel(int id){
        Classlevel c = null;
        try {
            rs=statement.executeQuery("SELECT * FROM classlevel where id="+id);
            if(rs.next()){
                c= new Classlevel();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setN(rs.getString("n"));
                c.setNiveau(rs.getString("niveau"));
                c.setOption(rs.getString("option"));
                c.setOp(rs.getString("op"));
                c.setContribution(rs.getString("contribution")!=null?Double.parseDouble(rs.getString("contribution")):null);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
    
    
    public static void main(String[] args) {
        ClasslevelFactory sf=new ClasslevelFactory();
        ArrayList<Classlevel> cls = Classlevel.getClasses();
        for (Classlevel c : cls){
            sf.setClasslevel(c);
        }
        System.out.println(sf.getClasslevels());
        System.out.println(sf.getClasslevel(3));
    }
}
