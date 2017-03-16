package com.teldir.pbl.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by arvindhn602 on 3/15/2017.
 */
public class Datasource {
    public static final String DB_NAME = "contacts.db";
    public static final String DB_LOCATION = "file:Appdata/contacts.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:"+ DB_LOCATION + DB_NAME;
//    public static final String ;

    public static final String TABLE_CONTACTS = "contacts";
    public static final String COLUMN_CONTACT_ID = "contact_id";
    public static final String COLUMN_FNAME = "fname";
    public static final String COLUMN_LNAME = "lname";
    public static final String COLUMN_BDAY = "bday";
    public static final String INSERT_CONTACTS = "INSERT INTO"+TABLE_CONTACTS+"("+COLUMN_FNAME+","+")" +
            "VALUES(?,?,?,?,?,?,?)";

    private static Datasource instance = new Datasource();

//    private PreparedStatement
    private Datasource() {

    }

    public static Datasource getInstance() {
        return instance;
    }
    //singleton for getting datasource instance
    private Connection conn;
    public boolean open(){
        try{
            conn = DriverManager.getConnection(CONNECTION_STRING);
            return true;
        }
        catch (SQLException e){
            System.out.println("Something went wrong could not connect to database: "+e.getMessage());
            return false;
        }
    }
    public boolean close(){
        try{
            if(conn!=null) {
                conn.close();
            }
            return true;
        }
        catch(SQLException e){
            System.out.println("Connection to the database was not terminated: "+e.getMessage());
            return false;
        }
    }
}
