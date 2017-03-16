package com.teldir.pbl.model;

/**
 * Created by arvindhn602 on 3/15/2017.
 */
public class Contacts {
    private int id;
    private String fname;
    private String lname;
    private int phone;
    private String email;
    private String street;
    private String city;
    private String pincode;
    private String Bday;

    public Contacts (){
    }


    public Contacts(String firstName, String lastName) {
        this.fname = firstName;
        this.lname = lastName;

        // Some initial dummy data, just for convenient testing.
        this.street = "some street";
        this.pincode = "1234";
        this.city = "some city";
        this.Bday = "22.12.1996";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getBday() {
        return Bday;
    }

    public void setBday(String bday) {
        Bday = bday;
    }
}
