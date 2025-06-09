package com.example;

import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ConfirmationController {

    @FXML private Label summaryLabel;
    @FXML private Label thankYouLabel;

    private String carModel, name, contact;
    private int price;
    private LocalDate rentalDate;

    public void setConfirmationInfo(String carModel, int price, String name, String contact, LocalDate rentalDate) {
        this.carModel = carModel;
        this.price = price;
        this.name = name;
        this.contact = contact;
        this.rentalDate = rentalDate;

        String info = "Car: " + carModel +
                      "\nPrice: $" + price + "/day" +
                      "\nName: " + name +
                      "\nContact: " + contact +
                      "\nRental Date: " + rentalDate;
        summaryLabel.setText(info);
        thankYouLabel.setText("Thank you for your rental!");
    }

    @FXML
    private void confirmRental(ActionEvent event) throws IOException {
        // Return to catalog.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("catalog.fxml"));
        Scene scene = new Scene(loader.load());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
}
