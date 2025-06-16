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
        goToRentalForm(event, "Sport-Car 1: MC-Laren", 2000);
    }

    @FXML
    private void selectCar2(ActionEvent event) throws IOException {
        goToRentalForm(event, "Sports-Car 2: Buggati", 4000);
    }

    @FXML
    private void selectCar3(ActionEvent event) throws IOException {
        goToRentalForm(event, "Sports-Car 3: Chevrolet", 4500);
    }

    @FXML
    private void selectCar4(ActionEvent event) throws IOException {
        goToRentalForm(event, "Sports-Car 4: Honda-Acura", 6000);
    }

    @FXML
    private void selectCar5(ActionEvent event) throws IOException {
        goToRentalForm(event, "Sports-Car 5: Lotus-Evija", 2000);
    }

    @FXML
    private void selectCar6(ActionEvent event) throws IOException {
        goToRentalForm(event, "Sports-Car 6: Noble-M500", 2000);
    }

    @FXML
    private void selectCar7(ActionEvent event) throws IOException {
        goToRentalForm(event, "Sports-Car 7: Terzo-Milleno", 2000);
    }

    @FXML
    private void selectCar8(ActionEvent event) throws IOException {
        goToRentalForm(event, "Sports-Car 8: MC-Laren", 4000);
    }

    @FXML
    private void viewRentalHistory(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewRentals.fxml"));
        Scene scene = new Scene(loader.load());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

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
