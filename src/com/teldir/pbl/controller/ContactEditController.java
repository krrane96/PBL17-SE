package com.teldir.pbl.controller;

import com.teldir.pbl.model.Contacts;
import com.teldir.pbl.model.Datasource;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

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

    private String fname;
    private String lname;
    private int phone_no;
    private String email;
    private String street;
    private String city;
    private String pcode;
    private int insertData=1;

    public void setContact(Contacts contact) {
        this.contact = contact;
        try{
            firstNameField.setText(contact.getFname());
            lastNameField.setText(contact.getLname());
            if(contact.getPhone()!=0){
                phoneNumberField.setText(Integer.toString(contact.getPhone()));
            }
            else{
                phoneNumberField.setPromptText("Enter your Mobile Number..");
            }
            if(contact.getEmail()!=null){
                emailAddressField.setText(contact.getEmail());
            }
            else {
                emailAddressField.setPromptText("abcd@xyz.com");
            }
            streetField.setText(contact.getStreet());
            cityField.setText(contact.getCity());
            postalCodeField.setText(contact.getPincode());
            if(contact.getBday()!= null){
                birthdayField.setText(contact.getBday());
            }
            else {
                birthdayField.setPromptText("dd.mm.yyyy");
            }
//            phone_no= Integer.parseInt(phoneNumberField.getText());
//            email=emailAddressField.getText();
//            street=streetField.getText();
//            pcode=postalCodeField.getText();
//            city=cityField.getText();
//            birthdayField.setText(DateUtil.format(contact.getBday()));
        }

        catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean isOkClicked() {return okClicked;}

    @FXML
    public void handleOK(int insertData){
        if(isInputValid()){
            contact.setFname(firstNameField.getText());
            contact.setLname(lastNameField.getText());
            int phoneNumber = Integer.parseInt(phoneNumberField.getText());
            contact.setPhone(phoneNumber);
            contact.setEmail(emailAddressField.getText());
            contact.setBday(birthdayField.getText());
            contact.setStreet(streetField.getText());
            contact.setPincode(postalCodeField.getText());
            if(insertData!=0){
                Datasource.getInstance().insertData(firstNameField.getText(), lastNameField.getText(), phoneNumber,
                        emailAddressField.getText(), birthdayField.getText(), streetField.getText(),
                        cityField.getText(), postalCodeField.getText());
            }
            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    public void handleCancel(){dialogStage.close();}

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
        if(phoneNumberField.getText()== null || phoneNumberField.getText().length()==0){
            errorMessage += "Please Enter a valid Phone Number\n";
        }
        else {
            try{
                Integer.parseInt(phoneNumberField.getText());
            }
            catch (NumberFormatException e){
                errorMessage +="Please enter a valid Phone Number";
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
