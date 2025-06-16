package com.example;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RentalFormController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField contactField;

    @FXML
    private DatePicker rentalDatePicker;

    @FXML
    private DatePicker returnDatePicker;

    @FXML
    private Label carLabel;

    private String carModel;
    private int price;

    @FXML
    void handleProceed(ActionEvent event) {
        try {
            // Get the data from the form
            String name = nameField.getText();
            String contact = contactField.getText();
            LocalDate rentalDate = rentalDatePicker.getValue();
            LocalDate returnDate = returnDatePicker.getValue();

            // Validate the data
            if (name == null || name.trim().isEmpty() ||
                    contact == null || contact.trim().isEmpty() ||
                    rentalDate == null || returnDate == null) {
                showAlert("Error", "Please fill in all fields.");
                return;
            }

            if (!isValidContactNumber(contact)) {
                showAlert("Error", "Please enter a valid contact number (numbers only).");
                return;
            }

            // Validate return date is after rental date
            if (returnDate.isBefore(rentalDate)) {
                showAlert("Error", "Return date cannot be before rental date.");
                return;
            }

            // Load the confirmation scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("confirmation.fxml"));
            Scene scene = new Scene(loader.load());

            // Get the controller for the confirmation scene
            ConfirmationController controller = loader.getController();

            // Pass the data to the confirmation controller
            controller.setConfirmationInfo(carModel, price, name, contact, rentalDate, returnDate);

            // Show the confirmation scene
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);

        } catch (DateTimeParseException e) {
            showAlert("Error", "Invalid date format. Please select a date from the date picker.");
        } catch (IOException e) {
            showAlert("Error", "Failed to load confirmation screen: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            showAlert("Error", "An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("catalog.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) nameField.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isValidContactNumber(String contact) {
        return contact.matches("\\d+"); // Check if the contact contains only digits
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void setCarInfo(String carModel, int price) {
        this.carModel = carModel;
        this.price = price;
        carLabel.setText("You have selected: " + carModel + ", Price per day: $" + price);
    }
}
