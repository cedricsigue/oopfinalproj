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
        goToRentalForm(event, "Car 1: Sedan", 50);
    }

    @FXML
    private void selectCar2(ActionEvent event) throws IOException {
        goToRentalForm(event, "Car 2: SUV", 70);
    }

    @FXML
    private void selectCar3(ActionEvent event) throws IOException {
        goToRentalForm(event, "Car 3: Convertible", 90);
    }

    @FXML
    private void selectCar4(ActionEvent event) throws IOException {
        goToRentalForm(event, "Car 4: Truck", 80);
    }

    /**
     * Loads the RentalForm.fxml file and passes car info to it.
     *
     * @param event      The button event that triggered this method.
     * @param carModel   The selected car model.
     * @param price      The daily rental price of the car.
     * @throws IOException if the FXML file cannot be loaded.
     */
    private void goToRentalForm(ActionEvent event, String carModel, int price) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RentalForm.fxml"));
        Scene scene = new Scene(loader.load());

        // Pass car info to RentalFormController
        RentalFormController controller = loader.getController();
        controller.setCarInfo(carModel, price);

        // Change scene
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
}
