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
        goToRentalForm(event, "You have selected Sport-Car 1: MC-Laren a day.", 5000);
    }

    @FXML
    private void selectCar2(ActionEvent event) throws IOException {
        goToRentalForm(event, "You have selected Sports-Car 2: Buggati a day.", 7000);
    }

    @FXML
    private void selectCar3(ActionEvent event) throws IOException {
        goToRentalForm(event, "You have selected Sports-Car 3: Chevrolet a day.", 4500);
    }

    @FXML
    private void selectCar4(ActionEvent event) throws IOException {
        goToRentalForm(event, "You have selected Sports-Car 4: Honda-Acura a day.", 6000);
    }

    @FXML
    private void selectCar5(ActionEvent event) throws IOException {
        goToRentalForm(event, "You have selected Sports-Car 5: Lotus-Evija a day.", 2000);
    }
    @FXML
    private void selectCar6(ActionEvent event) throws IOException {
        goToRentalForm(event, "You have selected Sports-Car 6: Noble-M500 aday.", 2000);
    }

    @FXML
    private void selectCar7(ActionEvent event) throws IOException {
        goToRentalForm(event, "You have selected Sports-Car 7: Mc-Laren a day..", 4000);
    }

    @FXML
    private void selectCar8(ActionEvent event) throws IOException {
        goToRentalForm(event, "You have selected Sports-Car 8: Aston Martin a day.", 8500);
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
