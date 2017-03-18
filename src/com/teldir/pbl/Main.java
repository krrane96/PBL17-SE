package com.teldir.pbl;

import com.teldir.pbl.controller.ContactEditController;
import com.teldir.pbl.controller.MainController;
import com.teldir.pbl.model.Contacts;
import com.teldir.pbl.model.Datasource;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private Contacts getContact;


    private ObservableList<Contacts> contactData = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public Main() {
        // Add some sample data
//        contactData.add(new Contacts("Arvind", "Nair"));
//        contactData.add(new Contacts("ABCD", "PQRST"));
    }
    public ObservableList<Contacts> getContactData() {
        return contactData;
    }

    @Override
    public void init() throws Exception {
        super.init();
        Datasource.getInstance().open();
        contactData.addAll(Datasource.getInstance().queryContacts(Datasource.ORDER_BY_ASC));
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("PhoneBook");
        this.primaryStage.getIcons().add(new Image("file:resource/images/Contacts-icon.png"));

        initRootLayout();
        showContactOverview();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/root.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            Datasource.getInstance().open();
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            System.out.println("Error: "+e.getMessage());
        }

    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showContactOverview() {
        try {
            // Load contact overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/main.fxml"));
            AnchorPane contactOverview = (AnchorPane) loader.load();
            // Set contact overview into the center of root layout.
            rootLayout.setCenter(contactOverview);

            MainController controller = loader.getController();
            controller.setMainApp(this);

        }
        catch (IOException e) {
            System.out.println("hello");
        }
    }
    public boolean showContactEditDialog(Contacts contact,int index) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/contact_edit.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Contacts");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the contact into the controller.
            ContactEditController controller = loader.getController();
            controller.setDialogstage(dialogStage);
            controller.setContact(contact);


            // Set the dialog icon.
            dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            System.out.println("Hello all");
            e.printStackTrace();
            return false;
        }
    }



    @Override
    public void stop() throws Exception {
        super.stop();
        Datasource.getInstance().close();

    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}