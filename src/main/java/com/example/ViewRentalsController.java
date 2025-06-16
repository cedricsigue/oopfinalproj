package com.example;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.beans.property.SimpleStringProperty;

public class ViewRentalsController {

    @FXML
    private TableView<Rental> rentalTable;
    @FXML
    private TableColumn<Rental, String> carModelColumn;
    @FXML
    private TableColumn<Rental, Integer> priceColumn;
    @FXML
    private TableColumn<Rental, String> customerNameColumn;
    @FXML
    private TableColumn<Rental, String> contactColumn;
    @FXML
    private TableColumn<Rental, String> rentalDateColumn;
    @FXML
    private TableColumn<Rental, String> returnDateColumn;
    @FXML
    private TableColumn<Rental, String> statusColumn;

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;

    @FXML
    public void initialize() {
        // Set up the columns
        carModelColumn.setCellValueFactory(new PropertyValueFactory<>("carModel"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));

        // Format dates and status
        rentalDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getRentalDate().format(dateFormatter)));

        returnDateColumn.setCellValueFactory(cellData -> {
            String returnDate = cellData.getValue().getReturnDate() != null
                    ? cellData.getValue().getReturnDate().format(dateFormatter)
                    : "Not returned";
            return new SimpleStringProperty(returnDate);
        });

        statusColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().isActive() ? "Active" : "Returned"));

        // Load initial data
        refreshRentals();
    }

    @FXML
    private void refreshRentals() {
        ObservableList<Rental> rentals = FXCollections.observableArrayList(
                RentalManager.getInstance().getAllRentals());
        rentalTable.setItems(rentals);
    }

    @FXML
    private void returnSelectedCar() {
        Rental selectedRental = rentalTable.getSelectionModel().getSelectedItem();

        if (selectedRental == null) {
            showAlert("Error", "Please select a car to return.");
            return;
        }

        if (!selectedRental.isActive()) {
            showAlert("Error", "This car has already been returned.");
            return;
        }

        // Show confirmation dialog
        Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDialog.setTitle("Confirm Return");
        confirmDialog.setHeaderText("Return Car");
        confirmDialog.setContentText("Are you sure you want to return this car?\n" +
                "Car: " + selectedRental.getCarModel() + "\n" +
                "Customer: " + selectedRental.getCustomerName());

        if (confirmDialog.showAndWait().get() == ButtonType.OK) {
            // Return the car
            RentalManager.getInstance().returnCar(selectedRental, LocalDate.now());
            refreshRentals();
            showAlert("Success", "Car has been returned successfully.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void goToCatalog(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("catalog.fxml"));
        Scene scene = new Scene(loader.load());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
}