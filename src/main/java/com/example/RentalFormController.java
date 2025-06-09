package com.example;

import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RentalFormController {

    @FXML private Label carLabel;
    @FXML private TextField nameField;
    @FXML private TextField contactField;
    @FXML private DatePicker rentalDatePicker;

    private String carModel;
    private int price;

    public void setCarInfo(String carModel, int price) {
        this.carModel = carModel;
        this.price = price;
        carLabel.setText("You selected: " + carModel);
    }

    @FXML
    private void handleProceed(ActionEvent event) throws IOException {
        String name = nameField.getText();
        String contact = contactField.getText();
        LocalDate date = rentalDatePicker.getValue();

        if (name.isEmpty() || contact.isEmpty() || date == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill in all fields.");
            alert.showAndWait();
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("confirmation.fxml"));
        Scene scene = new Scene(loader.load());

        // Get controller for confirmation
        ConfirmationController controller = loader.getController();
        controller.setConfirmationInfo(carModel, price, name, contact, date);

        // Change scene
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
}
