package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class ConfirmationController {

    @FXML
    private Label carLabel;

    @FXML
    private Label priceLabel;

    private String carModel;
    private int price;

    public void setCarInfo(String carModel, int price) {
        this.carModel = carModel;
        this.price = price;
        carLabel.setText("You selected: " + carModel);
        priceLabel.setText("Rental Price: $" + price + "/day");
    }

    @FXML
    private void confirmRental() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Rental Confirmed");
        alert.setHeaderText(null);
        alert.setContentText("Thank you! You have rented " + carModel + " for $" + price + "/day.");
        alert.showAndWait();
    }
}
