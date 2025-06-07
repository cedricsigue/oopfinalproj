package com.example;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class mainPageController {

    @FXML
    private Button openCatalogBtn1;

    // This is for the "Open catalog" button
    @FXML
    private void openCatalogBtnClick() {
        try {
            Pane catalogPage = FXMLLoader.load(getClass().getResource("catalog.fxml"));
            Scene scene = new Scene(catalogPage);
            Stage stage = (Stage) openCatalogBtn1.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // This is for the "Contact Us" button
    @FXML
    private void showContactAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Contact Us");
        alert.setHeaderText("Get in touch");
        alert.setContentText("📧 Email: support@example.com\n📞 Phone: +1-800-123-4567\n⏰ Mon–Fri: 9:00 AM to 6:00 PM");
        alert.showAndWait();
    }
}
