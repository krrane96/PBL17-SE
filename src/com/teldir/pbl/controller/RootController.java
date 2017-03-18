package com.teldir.pbl.controller;

import com.teldir.pbl.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

/**
 * Created by arvindhn602 on 3/15/2017.
 */
public class RootController {

    private Main main;
//    @FXML
//    private void handleNew() {
//        main.getContactData().clear();
//        main.setcontactFilePath(null);
//    }

    /**
     * Opens a FileChooser to let the user select an address book to load.
     */
//    @FXML
//    private void handleOpen() {
//        FileChooser fileChooser = new FileChooser();
//
//        // Set extension filter
//        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
//                "DB files (*.db)", "*.db");
//        fileChooser.getExtensionFilters().add(extFilter);
//
//        // Show save file dialog
//        File file = fileChooser.showOpenDialog(main.getPrimaryStage());
//
//        if (file != null) {
//            main.loadContactDataFromFile(file);
//        }
//    }


    @FXML
    private void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("PhoneBook");
        alert.setHeaderText("About");
        alert.setContentText("Credits:\nArvind Hariharan Nair\nBhabya Mishra\nKetaki Barde\nKrunal Rane\nMegha Menon" +
                "\n\n Class: SE COMPS A");
        alert.showAndWait();
    }

    @FXML
    private void handleExit(){System.exit(0);}
}
