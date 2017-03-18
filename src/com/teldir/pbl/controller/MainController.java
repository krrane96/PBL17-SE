package com.teldir.pbl.controller;

import com.teldir.pbl.Main;
import com.teldir.pbl.model.Contacts;
import com.teldir.pbl.model.Datasource;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.SQLException;

/**
 * Created by arvindhn602 on 3/15/2017.
 */
public class MainController {
    @FXML
    private TableView<Contacts> contactsTable;
    @FXML
    private TableColumn<Contacts, String> firstNameColumn;
    @FXML
    private TableColumn<Contacts, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label phoneNumberLabel;
    @FXML
    private Label emailAddressLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label birthdayLabel;

    private Main main = new Main();

    public MainController() {
    }

    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().fnameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lnameProperty());

        // Clear person details.
        showContactDetails(null);

        // Listen for selection changes and show the person details when changed.
        contactsTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showContactDetails(newValue));
    }

    public void setMainApp(Main main) {
        this.main = main;

        // Add observable list data to the table
        contactsTable.setItems(main.getContactData());
    }

    private void showContactDetails(Contacts contact) {
        if(contact!=null){
            firstNameLabel.setText(contact.getFname());
            lastNameLabel.setText(contact.getLname());
            phoneNumberLabel.setText(Integer.toString(contact.getPhone()));
            emailAddressLabel.setText(contact.getEmail());
            streetLabel.setText(contact.getStreet());
            cityLabel.setText(contact.getCity());
            postalCodeLabel.setText(contact.getPincode());
            birthdayLabel.setText(contact.getBday());
        }
        else{
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            phoneNumberLabel.setText("");
            emailAddressLabel.setText("");
            streetLabel.setText("");
            cityLabel.setText("");
            postalCodeLabel.setText("");
            birthdayLabel.setText("");

        }
    }


    @FXML
    private void handleNewPerson() {
        Contacts tempPerson = new Contacts();
        boolean okClicked = main.showContactEditDialog(tempPerson);
        if (okClicked) {
            main.getContactData().add(tempPerson);
        }
    }

    @FXML
    private void handleEditPerson() {
        Contacts selectedContact = contactsTable.getSelectionModel().getSelectedItem();
        if (selectedContact != null) {
            boolean okClicked = main.showContactEditDialog(selectedContact);
            if (okClicked) {
                showContactDetails(selectedContact);
                Datasource.getInstance().updateData(selectedContact);
            }

        } else {
            noSelectAlert();
        }
    }

    @FXML
    private void handleDeletePerson() throws SQLException {
        int selectedIndex = contactsTable.getSelectionModel().getSelectedIndex();
        Contacts selectedContact = contactsTable.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0 && selectedContact.getId()!=0) {
            contactsTable.getItems().remove(selectedIndex);
            Datasource.getInstance().deleteContact(selectedContact.getId());

        } else {
            noSelectAlert();
        }
    }


    private void noSelectAlert(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(main.getPrimaryStage());
        alert.setTitle("No Selection");
        alert.setHeaderText("No contacts selected");
        alert.setContentText("Please Select a contact !");
        alert.showAndWait();
    }
}
