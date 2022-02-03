/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sqlite.mc.SQLiteMCConfig;

/**
 *
 * @author loussin
 */
public class Database {
    private static final String DBNAME="/home/loussin/Bureau/db.db";
    private static Connection connection;
    private static Statement statement;
    
    private static void Connect(){
        try{
            createDB();
            if( connection == null || connection.isClosed() ){
                Class.forName("org.sqlite.JDBC");
                //connection = DriverManager.getConnection("jdbc:sqlite:"+DBNAME, new  SQLiteMCConfig () . withKey( "A11e2 v0u5 f@174 v8!^" ) . toProperties());
                connection = DriverManager.getConnection("jdbc:sqlite:"+DBNAME);
                if ( connection == null) {
                    System.exit(1);
                }
                statement=connection.createStatement();
                //System.out.println("DB connected");
            }else{
                //System.out.println("DB already connected");
            }
                
        }catch(Exception e){
            e.printStackTrace();
        }  
    }

    public static Connection getConnection() {
        Connect();
        return connection;
    }

    public static String getDBNAME() {
        return DBNAME;
    }

    public static Statement getStatement() {
        Connect();
        return statement;
    }
    
    
    
    
    public static boolean isConnected(){
        try {
            return connection != null && !connection.isClosed();
        } catch (Exception ex) {
            return false;
        }
    }
    
    private static void createDB(){
        Path f = Paths.get(DBNAME);
        if(Files.notExists(f)){
            File targetFile = f.toFile();
            File parent = targetFile.getParentFile();
            if (parent != null && !parent.exists() && !parent.mkdirs()) {
                throw new IllegalStateException("Couldn't create dir: " + parent);
            }
            try {
                targetFile.createNewFile();
                seed();
                //System.out.println("DB created");
            } catch (IOException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            //System.out.println("DB exists");
        }
    }
    
    
    public static void seed(){
            Connect();
        try {
            statement.executeUpdate("drop table if exists user");
            createUserTable();
            statement.executeUpdate("INSERT INTO 'user' ('id','name','email','passwd','role') VALUES (null,'LOUSSIN Andre','loussin.andre@gmail.com','lopplpop',1);");
            
            createAddTable();
            createClassLevelTable();
            createCourseTable();
            createNoteBookTable();
            createNoteTable();
            createPayeTable();
            createScolariteTable();
            createSessionTable();
            createClassRoomTable();
            createStudentTable();
            createScoolYearTable();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void show(){
        try {
            ResultSet rs = statement.executeQuery("select * from user");
            while(rs.next())
            {
                // read the result set
                System.out.print("user.name = " + rs.getString("name") +" ");
                System.out.println("user.id = " + rs.getInt("id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void createUserTable() throws SQLException{
        statement.executeUpdate(""+
                    "CREATE TABLE IF NOT EXISTS user (" +
                    "	id	INTEGER NOT NULL UNIQUE," +
                    "	name	VARCHAR(100)," +
                    "	email	VARCHAR(100) NOT NULL UNIQUE," +
                    "	passwd	VARCHAR(100) NOT NULL," +
                    "	role	INTEGER NOT NULL DEFAULT 0," +
                    "	PRIMARY KEY(id AUTOINCREMENT)" +
                    ");"
            );
    }
    
    public static void createStudentTable() throws SQLException{
        statement.executeUpdate(""+
                    "CREATE TABLE IF NOT EXISTS student (" +
                    "	id              INTEGER NOT NULL UNIQUE," +
                    "	matricule	VARCHAR(100) UNIQUE," +
                    "	name            VARCHAR(100)," +
                    "	firstnames	VARCHAR(100)," +
                    "	passwd          VARCHAR(100)," +
                    "	address         VARCHAR(100)," +
                    "	sexe            VARCHAR(100)," +
                    "	extras          VARCHAR(100)," +
                    "	image           VARCHAR(100)," +
                    "	commingscool	VARCHAR(100)," +
                    "	fathername	VARCHAR(100)," +
                    "	fatherwork	VARCHAR(100)," +
                    "	fatheraddress   VARCHAR(100)," +
                    "	mothername	VARCHAR(100)," +
                    "	motherwork	VARCHAR(100)," +
                    "	motheraddress   VARCHAR(100)," +
                    "	birthday        DATE," +
                    "	inscriptiondate DATE," +
                    "	classroomId	INTEGER," +
                    "	scolariteId	INTEGER," +
                    "	PRIMARY KEY(id AUTOINCREMENT)" +
                    ");"
            );
    }
    
    public static void createScolariteTable() throws SQLException{
        statement.executeUpdate(""+
                    "CREATE TABLE IF NOT EXISTS 'scolarite' (\n" +
                "	'id'	INTEGER NOT NULL UNIQUE,\n" +
                "	'contribution'	REAL,\n" +
                "	'mtpaye'	REAL,\n" +
                "	'studentId'	INTEGER,\n" +
                "	'classroomId'	INTEGER,\n" +
                "	'notebookId'	INTEGER,\n" +
                "	FOREIGN KEY('studentId') REFERENCES 'student'('id') ON DELETE SET NULL,\n" +
                "	FOREIGN KEY('classroomId') REFERENCES 'classroom'('id') ON DELETE SET NULL,\n" +
                "	FOREIGN KEY('notebookId') REFERENCES 'notebook'('id') ON DELETE SET NULL,\n" +
                "	PRIMARY KEY('id' AUTOINCREMENT)\n" +
                ");"
            );
    }
    
    public static void createClassLevelTable() throws SQLException{
        statement.executeUpdate(""+
                "CREATE TABLE IF NOT EXISTS 'classlevel' (" +
                "	'id'	INTEGER NOT NULL UNIQUE," +
                "	'name'	VARCHAR(100)," +
                "	'n'	VARCHAR(10)," +
                "	'niveau'	VARCHAR(100)," +
                "	'option'	VARCHAR(100)," +
                "	'op'	VARCHAR(100)," +
                "	'contribution'	REAL," +
                "	PRIMARY KEY('id' AUTOINCREMENT)" +
                ");"
        );
    }
    
    public static void createClassRoomTable() throws SQLException{
        statement.executeUpdate(""+
                "CREATE TABLE IF NOT EXISTS 'classroom' (" +
                "	'id'	INTEGER NOT NULL UNIQUE," +
                "	'promotion'	VARCHAR(100)," +
                "	'scoolyear'	VARCHAR(100)," +
                "	'groupe'	VARCHAR(100)," +
                "	'contribution'	REAL," +
                "	'classlevelId'	INTEGER," +
                "	PRIMARY KEY('id' AUTOINCREMENT)," +
                "	FOREIGN KEY('classlevelId') REFERENCES 'classlevel'('id') ON DELETE SET NULL" +
                ");"
        );
    }
    
    public static void createCourseTable() throws SQLException{
        statement.executeUpdate(""+
                "CREATE TABLE IF NOT EXISTS 'course' (\n" +
                "	'id'	INTEGER NOT NULL UNIQUE,\n" +
                "	'name'	VARCHAR(100),\n" +
                "	'profname'	VARCHAR(100),\n" +
                "	'classlevelId'	INTEGER,\n" +
                "	'coeff'	INTEGER,\n" +
                "	FOREIGN KEY('classlevelId') REFERENCES 'classlevel'('id') ON DELETE SET NULL,\n" +
                "	PRIMARY KEY('id' AUTOINCREMENT)\n" +
                ");"
        );
    }
    
    public static void createNoteBookTable() throws SQLException{
        statement.executeUpdate(""+
                "CREATE TABLE IF NOT EXISTS 'notebook' (\n" +
                "	'id'	INTEGER NOT NULL UNIQUE,\n" +
                "	'studentId'	INTEGER,\n" +
                "	'classroomId'	INTEGER,\n" +
                "	'courseId'	INTEGER,\n" +
                "	'preferedsessionnumber'	INTEGER,\n" +
                "	FOREIGN KEY('courseId') REFERENCES 'course'('id') ON DELETE SET NULL,\n" +
                "	FOREIGN KEY('studentId') REFERENCES 'student'('id') ON DELETE SET NULL,\n" +
                "	FOREIGN KEY('classroomId') REFERENCES 'classroom'('id') ON DELETE SET NULL,\n" +
                "	PRIMARY KEY('id' AUTOINCREMENT)\n" + 
                "       UNIQUE(studentId,classroomId,courseId) " +
                ");"
        );
    }
    
    public static void createAddTable() throws SQLException{
        statement.executeUpdate(""+
                "CREATE TABLE IF NOT EXISTS 'adds' (\n" +
                "	'id'	INTEGER NOT NULL UNIQUE,\n" +
                "	'studentId'	INTEGER,\n" +
                "	'title'	VARCHAR(255),\n" +
                "	'content'	TEXT,\n" +
                "	FOREIGN KEY('studentId') REFERENCES 'student'('id') ON DELETE SET NULL,\n" +
                "	PRIMARY KEY('id' AUTOINCREMENT)\n" +
                ");"
        );
    }
    
    public static void createPayeTable() throws SQLException{
        statement.executeUpdate(""+
                "CREATE TABLE IF NOT EXISTS 'paye' (\n" +
                "	'id'	INTEGER NOT NULL UNIQUE,\n" +
                "	'scoolyear'	VARCHAR(255),\n" +
                "	'montant'	REAL,\n" +
                "	'montantrst'	REAL,\n" +
                "	'montantenltr'	TEXT,\n" +
                "	'solded'	INTEGER,\n" +
                "	'studentId'	INTEGER,\n" +
                "	'classroomId'	INTEGER,\n" +
                "	FOREIGN KEY('studentId') REFERENCES 'student'('id') on delete set null,\n" +
                "	FOREIGN KEY('classroomId') REFERENCES 'classroom'('id') on delete set null,\n" +
                "	PRIMARY KEY('id' AUTOINCREMENT)\n" +
                ");"
        );
    }
    
    public static void createSessionTable() throws SQLException{
        statement.executeUpdate(""+
                "CREATE TABLE IF NOT EXISTS 'session' (\n" +
                "	'id'	INTEGER NOT NULL UNIQUE,\n" +
                "	'profappreciation'	TEXT,\n" +
                "	'dgappreciation'	TEXT,\n" +
                "	'interromoyenne'	REAL,\n" +
                "	'hightmoyenne'	REAL,\n" +
                "	'lowmoyenne'	REAL,\n" +
                "	'moyenne'	REAL,\n" +
                "	'range'	INTEGER,\n" +
                "	'isfinal'	INTEGER,\n" +
                "	'notebookId'	INTEGER,\n" +
                "	FOREIGN KEY('notebookId') REFERENCES 'notebook'('id') on delete set null,\n" +
                "	PRIMARY KEY('id' AUTOINCREMENT)\n" +
                ");"
        );
    }
    
    public static void createNoteTable() throws SQLException{
        statement.executeUpdate(""+
                "CREATE TABLE IF NOT EXISTS 'note' (\n" +
                "	'id'	INTEGER NOT NULL UNIQUE,\n" +
                "	'valeur'	REAL,\n" +
                "	'isdevoir'	INTEGER,\n" +
                "	'sessionId'	INTEGER,\n" +
                "	PRIMARY KEY('id' AUTOINCREMENT),\n" +
                "	FOREIGN KEY('sessionId') REFERENCES 'session'('id') on delete set null\n" +
                ");"
        );
    }
    
    public static void createScoolYearTable() throws SQLException{
        statement.executeUpdate(""+
                "CREATE TABLE IF NOT EXISTS 'scoolyear' (\n" +
                "	'id'	INTEGER NOT NULL UNIQUE,\n" +
                "	'year'	VARRCHAR(100) UNIQUE ,\n" +
                "	PRIMARY KEY('id' AUTOINCREMENT)\n" +
                ");"
        );
    }
    
    private static boolean ctn(ArrayList<String> l,String s){
        boolean r=false;
        for(String ss:l)
        {
            if(ss.equalsIgnoreCase(s)){
                r=true;
                break;
            }
        }
        return r;
    }
    
    public static ArrayList<String> getGroups(){
        ArrayList<String> l = new ArrayList<>();
        try {
            ResultSet rs= getStatement().executeQuery("SELECT groupe FROM classroom  ORDER BY id DESC");
            while (rs.next()) {
                if(rs.getString("groupe")!=null && !rs.getString("groupe").isEmpty() && !ctn(l, rs.getString("groupe")))
                    l.add(rs.getString("groupe"));                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(String s:l){
            if(s==null)
                l.remove(s);
        }
        return l;
    }
    
    public static ArrayList<String> getScoolyears(){
        ArrayList<String> l = new ArrayList<>();
        try {
            ResultSet rs= getStatement().executeQuery("SELECT scoolyear FROM classroom ORDER BY id DESC");
            while (rs.next()) {
                if(rs.getString("scoolyear")!=null && !ctn(l, rs.getString("scoolyear")))
                    l.add(rs.getString("scoolyear"));                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(String s:l){
            if(s==null)
                l.remove(s);
        }
        return l;
    }

    
    public static void main(String[] args) {
            try {
                getStatement().executeUpdate("drop table if exists paye");
                seed();
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            show();
        
        System.out.println(getGroups());
    }    
}
