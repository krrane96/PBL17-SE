package com.teldir.pbl.controller;

import com.teldir.pbl.model.Contacts;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.*;

public class ContactEditController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextField emailAddressField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextField birthdayField;

    private Stage dialogStage;
    private Contacts contact;
    private boolean okClicked = false;
    private String Fname;
    private int phone_no;
    private String Lname;
    private String Street;
    private String city;
    private String pcode;

    public void setContact(Contacts contact) {
        this.contact = contact;

//        firstNameField.setText(contact.getFname());
        Fname=firstNameField.getText();
        Lname=lastNameField.getText();
        phone_no=Integer.parseInt(phoneNumberField.getText());
        Street=streetField.getText();
        pcode=postalCodeField.getText();
        city=cityField.getText();
//        birthdayField.setText(DateUtil.format(contact.getBday()));
//        birthdayField.setPromptText("dd.mm.yyyy");
    }

    public boolean isOkClicked() {return okClicked;}

    public void handleOK(){
        if(isInputValid())
        okClicked = true;

    }

    public void setDialogstage(Stage dialogStage){
        this.dialogStage=dialogStage;
        this.dialogStage.getIcons().add(new Image("file:resource/images/edit-icon.png"));
    }

    public boolean isInputValid(){
        String errorMessage="";
        if(firstNameField.getText()== null || firstNameField.getText().length()==0){
            errorMessage += "First Name is Not valid!\n";
        }
        if(lastNameField.getText()== null || lastNameField.getText().length()==0)
        {
            errorMessage += "Last Name is not valid!\n";
        }
        if(postalCodeField.getText()== null || postalCodeField.getText().length()==0){
            errorMessage += "PinCode is Not valid!\n";
        }
        else {
            try{
                Integer.parseInt(postalCodeField.getText());
            }
            catch (NumberFormatException e){
                errorMessage +="Please enter a valid Pincode";
            }
        }
        if (errorMessage.length()==0){
            return true;
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please Check your Entry");
            alert.showAndWait();

            return false;
        }
    }
}
