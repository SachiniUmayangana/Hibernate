package lk.ijse.hibernate.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class DashboardController {

    public AnchorPane context;


    public void customerOnAction(ActionEvent event) throws IOException {

        Stage window = (Stage) this.context.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(this.getClass()
                .getResource("/view/Customer.fxml"))));
        window.centerOnScreen();


    }

    public void productOnAction(ActionEvent event) throws IOException {

    }

    public void orderOnAction(ActionEvent event) {
    }

    public void orderDEtailsOnAction(ActionEvent event) {
    }
}
