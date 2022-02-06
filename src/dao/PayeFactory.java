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
import model.Paye;

/**
 *
 * @author loussin
 */
public class PayeFactory {
    
    Connection connection;
    Statement statement;
    PreparedStatement prepst;
    ResultSet rs;

    public PayeFactory() {
        connection = Database.getConnection();
        statement = Database.getStatement();
    }
    
    public void setPaye(Paye c){
        try {
            prepst=connection.prepareStatement(""
                    + "INSERT INTO 'paye'('id','scoolyear',"
                    + "'montant','montantrst',"
                    + "'montantenltr','solded',"
                    + "'scolariteId','dateDeCreation') "
                    + "VALUES (NULL,?,?,?,?,?,?,?);");
            
            prepst.setString(1, c.getScoolYear());
            prepst.setString(2, c.getMontant()!=null?c.getMontant().toString():null);
            prepst.setString(3, c.getMontantRst()!=null?c.getMontantRst().toString():null);
            prepst.setString(4, c.getMontantEnLtr());
            prepst.setInt(5, c.getSolded()?1:0);
            prepst.setString(6, c.getScolariteId()!=null?c.getScolariteId().toString():null);
            prepst.setDate(7, c.getDateDeCreation());
            
            prepst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Paye> getPayes(){
        ArrayList<Paye> list = new ArrayList();
        try {
            rs=statement.executeQuery("SELECT * FROM paye");
            while(rs.next()){
                Paye c= new Paye();
                c.setId(rs.getInt("id"));
                c.setScoolYear(rs.getString("scoolyear"));
                c.setMontant(rs.getString("montant")!=null?Double.parseDouble(rs.getString("montant")):null);
                c.setMontantRst(rs.getString("montantrst")!=null?Double.parseDouble(rs.getString("montantrst")):null);
                c.setMontantEnLtr(rs.getString("montantEnLtr"));
                c.setSolded(rs.getInt("solded")==1);
                c.setDateDeCreation(rs.getDate("dateDeCreation"));
                c.setScolariteId(rs.getString("scolariteId")!=null?Integer.parseInt(rs.getString("scolariteId")):null);
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    public ArrayList<Paye> getPayesByScolariteId(Integer scolariteId){
        ArrayList<Paye> list = new ArrayList();
        try {
            rs=statement.executeQuery("SELECT * FROM paye where scolariteId="+scolariteId);
            while(rs.next()){
                Paye c= new Paye();
                c.setId(rs.getInt("id"));
                c.setScoolYear(rs.getString("scoolyear"));
                c.setMontant(rs.getString("montant")!=null?Double.parseDouble(rs.getString("montant")):null);
                c.setMontantRst(rs.getString("montantrst")!=null?Double.parseDouble(rs.getString("montantrst")):null);
                c.setMontantEnLtr(rs.getString("montantEnLtr"));
                c.setSolded(rs.getInt("solded")==1);
                c.setDateDeCreation(rs.getDate("dateDeCreation"));
                c.setScolariteId(scolariteId);
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public Paye getPaye(int id){
        Paye c = null;
        try {
            rs=statement.executeQuery("SELECT * FROM paye where id="+id);
            if(rs.next()){
                c= new Paye();
                c.setId(rs.getInt("id"));
                c.setScoolYear(rs.getString("scoolyear"));
                c.setMontant(rs.getString("montant")!=null?Double.parseDouble(rs.getString("montant")):null);
                c.setMontantRst(rs.getString("montantrst")!=null?Double.parseDouble(rs.getString("montantrst")):null);
                c.setMontantEnLtr(rs.getString("montantEnLtr"));
                c.setSolded(rs.getInt("solded")==1);
                c.setDateDeCreation(rs.getDate("dateDeCreation"));
                c.setScolariteId(rs.getString("scolariteId")!=null?Integer.parseInt(rs.getString("scolariteId")):null);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
    
    
    public static void main(String[] args) {
        PayeFactory sf=new PayeFactory();
        sf.setPaye(new Paye());
        System.out.println(sf.getPayes());
    }
}
