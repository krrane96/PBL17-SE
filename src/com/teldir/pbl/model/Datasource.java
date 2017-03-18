package com.teldir.pbl.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arvindhn602 on 3/15/2017.
 */
public class Datasource {
    public static final String DB_NAME = "contacts.db";
    public static final String DB_LOCATION = "file:Appdata/";
    public static final String CONNECTION_STRING = "jdbc:sqlite:"+ DB_LOCATION + DB_NAME;
//    public static final String ;

    public static final String TABLE_CONTACTS = "contacts";
    public static final String COLUMN_CONTACT_ID = "contact_id";
    public static final String COLUMN_FNAME = "fname";
    public static final String COLUMN_LNAME = "lname";
    public static final String COLUMN_PHONE = "phn_no";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_BDAY = "bday";
    public static final String COLUMN_STREET = "street";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_PIN ="pincode";

    public static final int INDEX_CONTACT_ID = 1;
    public static final int INDEX_FNAME = 2;
    public static final int INDEX_LNAME = 3;
    public static final int INDEX_PHONE_NUMBER = 4;
    public static final int INDEX_BIRTHDAY= 5;
    public static final int INDEX_EMAIL = 6;
    public static final int INDEX_STREET = 7;
    public static final int INDEX_CITY = 8;
    public static final int INDEX_PINCODE = 9;

    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;

    public static final String INSERT_CONTACTS = "INSERT INTO "+TABLE_CONTACTS+"("+COLUMN_FNAME+","+COLUMN_LNAME+","
            +COLUMN_PHONE+","+COLUMN_BDAY+","+COLUMN_EMAIL+","+COLUMN_STREET+","+COLUMN_CITY+","+COLUMN_PIN+")" +
            "VALUES(?,?,?,?,?,?,?,?)";

    public static final String DELETE_CONTACTS = "DELETE FROM "+TABLE_CONTACTS+" WHERE "+COLUMN_CONTACT_ID+" = ?";

    public static final String UPDATE_CONTACTS = "UPDATE "+TABLE_CONTACTS+" SET "+COLUMN_FNAME+"= ?, "+COLUMN_LNAME+"= ?,"
            +COLUMN_PHONE+"= ?,"+COLUMN_BDAY+"= ?,"+COLUMN_EMAIL+"= ?,"+COLUMN_STREET+"= ?,"+COLUMN_CITY+"= ?,"+COLUMN_PIN+
            "= ? "+" WHERE "+COLUMN_CONTACT_ID+"= ?";

    public static final String VIEW_CONTACT_TABLEVIEW = "SELECT"+COLUMN_FNAME+","+COLUMN_LNAME+"FROM"+TABLE_CONTACTS;

    private static Datasource instance = new Datasource();

//    private PreparedStatement
    private Datasource() {

    }

    public static Datasource getInstance() {
        return instance;
    }
    //singleton for getting datasource instance

    private Connection conn;
    private PreparedStatement insertIntoContacts;
    private PreparedStatement deleteContact;
    private PreparedStatement updateContact;

    private Contacts contact;

    public boolean open(){
        try{
            conn = DriverManager.getConnection(CONNECTION_STRING);
            insertIntoContacts=conn.prepareStatement(INSERT_CONTACTS);
            deleteContact = conn.prepareStatement(DELETE_CONTACTS);
            updateContact = conn.prepareStatement(UPDATE_CONTACTS);
            return true;
        }
        catch (SQLException e){
            System.out.println("Something went wrong could not connect to database: "+e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    public boolean close(){
        try {
            if (insertIntoContacts != null){
                insertIntoContacts.close();
            }
            if(updateContact!=null){
                updateContact.close();
            }
            if (deleteContact!=null){
                deleteContact.close();
            }
            if(conn!=null) {
                conn.close();
            }
            return true;
        }
        catch(SQLException e){
            System.out.println("Connection to the database was not terminated: "+e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public void insertData(String fName,String lName,int phoneNumber,String Bday,String email,
                           String street, String city, String pincode){
        try {
            contact = new Contacts();
            insertIntoContacts.setString(1,fName);
            insertIntoContacts.setString(2,lName);
            insertIntoContacts.setInt(3,phoneNumber);
            insertIntoContacts.setString(4,Bday);
            insertIntoContacts.setString(5,email);
            insertIntoContacts.setString(6,street);
            insertIntoContacts.setString(7,city);
            insertIntoContacts.setString(8,pincode);
            insertIntoContacts.executeUpdate();
        }
        catch (Exception e){
            try {
                System.out.println("Issues in Inserting data\n Rolling Back");
                conn.rollback();
                e.printStackTrace();
            }
            catch (SQLException e2){
                e2.printStackTrace();
            }
        }

    }

    public void updateData(Contacts contact){
        try {
            updateContact.setString(1,contact.getFname());
            updateContact.setString(2,contact.getLname());
            updateContact.setInt(3,contact.getPhone());
            updateContact.setString(4,contact.getBday());
            updateContact.setString(5,contact.getEmail());
            updateContact.setString(6,contact.getStreet());
            updateContact.setString(7,contact.getCity());
            updateContact.setString(8,contact.getPincode());
            updateContact.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void deleteContact(int contactID) throws SQLException {
        deleteContact.setInt(1,contactID);
        deleteContact.executeUpdate();
    }

//    public void viewTableData(){
//        try {
//            Statement smt = conn.createStatement();
//            smt.execute(VIEW_CONTACT_TABLEVIEW);
//            ResultSet rst = smt.getResultSet();
//            while(rst.next()){
//
//
//            }
//        }
//        catch (SQLException e) {
//            System.out.println("Cannot be queried " + e.getMessage());
//            e.printStackTrace();
//        }
//    }

    public List<Contacts> queryContacts(int sortOrder){
        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_CONTACTS);
        if(sortOrder!=ORDER_BY_NONE){
            sb.append(" ORDER BY ");
            sb.append(COLUMN_FNAME);
            sb.append(" COLLATE NOCASE ");
            if(sortOrder == ORDER_BY_DESC){
                sb.append(" DESC ");
            }
            else {
                sb.append(" ASC ");
            }
        }
        try(Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sb.toString())){
            List<Contacts> contacts = new ArrayList<>();
            while (result.next()){
                Contacts contact = new Contacts();
                contact.setId(result.getInt("contact_id"));
                contact.setFname(result.getString("fname"));
                contact.setLname(result.getString("lname"));
                contact.setPhone(result.getInt("phn_no"));
                contact.setBday(result.getString("bday"));
                contact.setEmail(result.getString("email"));
                contact.setStreet(result.getString("street"));
                contact.setCity(result.getString("city"));
                contact.setPincode(result.getString("pincode"));
                contacts.add(contact);
            }
            return contacts;
        }
        catch (SQLException e){
            System.out.println("QUERY FAILED:");
            e.printStackTrace();
            return null;
        }
    }
}
