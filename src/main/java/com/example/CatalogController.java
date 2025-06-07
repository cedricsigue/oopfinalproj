package com.example;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CatalogController {

    @FXML
    private void selectCar1(ActionEvent event) throws IOException {
        loadConfirmation(event, "Car 1: Sedan", 50);
    }

    @FXML
    private void selectCar2(ActionEvent event) throws IOException {
        loadConfirmation(event, "Car 2: SUV", 70);
    }

    @FXML
    private void selectCar3(ActionEvent event) throws IOException {
        loadConfirmation(event, "Car 3: Convertible", 90);
    }

    @FXML
    private void selectCar4(ActionEvent event) throws IOException {
        loadConfirmation(event, "Car 4: Truck", 80);
    }

    private void loadConfirmation(ActionEvent event, String carModel, int pricePerDay) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("confirmation.fxml"));
        Scene scene = new Scene(loader.load());

        ConfirmationController controller = loader.getController();
        controller.setCarInfo(carModel, pricePerDay);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
}
