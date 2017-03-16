package com.teldir.pbl.controller;

import com.teldir.pbl.Main;
import com.teldir.pbl.model.Contacts;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

/**
 * Created by arvindhn602 on 3/15/2017.
 */
public class MainController {
    @FXML
    private TableView <Contacts> contactsTable;
    @FXML
    private Label FirstNameLabel;
    @FXML
    private Label LastNameLabel;
    @FXML
    private Label PhoneNumberLabel;
    @FXML
    private Label EmailAddressLabel;
    @FXML
    private Label StreetLabel;
    @FXML
    private Label CityLabel;
    @FXML
    private Label PostalCodeLabel;
    @FXML
    private Label BirthdayLabel;

    private Main main = new Main();

    public MainController() {
    }

    private void showContactDetails(Contacts contact) {
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
        Contacts selectedPerson = contactsTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = main.showContactEditDialog(selectedPerson);
            if (okClicked) {
                showContactDetails(selectedPerson);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleDeletePerson() {
        int selectedIndex = contactsTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            contactsTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
}
