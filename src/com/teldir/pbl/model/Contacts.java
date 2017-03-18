package com.teldir.pbl.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by arvindhn602 on 3/15/2017.
 */
public class Contacts {
    private SimpleIntegerProperty id;
    private SimpleStringProperty fname;
    private SimpleStringProperty lname;
    private SimpleIntegerProperty phone;
    private SimpleStringProperty email;
    private SimpleStringProperty street;
    private SimpleStringProperty city;
    private SimpleStringProperty pincode;
    private SimpleStringProperty Bday;

    public Contacts() {
        this(0,null,null,0,null,null,null,null,null);
    }


    public Contacts(int id,String fname, String lname,int phone, String Bday,String email ,String street,String city,String pincode) {
        this.id = new SimpleIntegerProperty(id);
        this.fname = new SimpleStringProperty(fname);
        this.lname = new SimpleStringProperty(lname);
        this.phone = new SimpleIntegerProperty(phone);
        this.Bday = new SimpleStringProperty(Bday);
        this.email = new SimpleStringProperty(email);
        this.street = new SimpleStringProperty(street);
        this.city = new SimpleStringProperty(city);
        this.pincode = new SimpleStringProperty(pincode);

//        // Some initial dummy data, just for convenient testing.
//        this.street = new SimpleStringProperty("some street");
//        this.pincode = new SimpleStringProperty("1234");
//        this.city = new SimpleStringProperty("panvel");
//        this.Bday = new SimpleStringProperty("12.15.1996");
    }
//

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getFname() {
        return fname.get();
    }

    public SimpleStringProperty fnameProperty() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname.set(fname);
    }

    public String getLname() {
        return lname.get();
    }

    public SimpleStringProperty lnameProperty() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname.set(lname);
    }

    public int getPhone() {
        return phone.get();
    }

    public SimpleIntegerProperty phoneProperty() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone.set(phone);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getStreet() {
        return street.get();
    }

    public SimpleStringProperty streetProperty() {
        return street;
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public String getCity() {
        return city.get();
    }

    public SimpleStringProperty cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public String getPincode() {
        return pincode.get();
    }

    public SimpleStringProperty pincodeProperty() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode.set(pincode);
    }

    public String getBday() {
        return Bday.get();
    }

    public SimpleStringProperty bdayProperty() {
        return Bday;
    }

    public void setBday(String bday) {
        this.Bday.set(bday);
    }
}

