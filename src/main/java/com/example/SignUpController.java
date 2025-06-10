package com.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SignUpController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button createAccountBtn;

    @FXML
    private Label btnLogin;

    private static final String USERS_FILE = "users.txt";

    @FXML
    public void loginClicked(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/login.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error loading login page.");
        }
    }

    @FXML
    void btnCreateClicked(ActionEvent event) {
        String usernameText = username.getText();
        String passwordText = password.getText();

        if (usernameText.isEmpty() || passwordText.isEmpty()) {
            showAlert("Please fill in all fields.");
            return;
        }

        if (saveUserToFile(usernameText, passwordText)) {
            System.out.println("User registered successfully!");
            triggerLogin();
        } else {
            showAlert("Error saving user data.");
        }
    }

    private boolean saveUserToFile(String username, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE, true))) {
            writer.write(username + "," + password);
            writer.newLine();
            System.out.println("User data written to file: " + username + "," + password);
            return true;
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void triggerLogin() {
        if (btnLogin != null) {
            btnLogin.fireEvent(new MouseEvent(MouseEvent.MOUSE_CLICKED,
                    0, 0, 0, 0, MouseButton.PRIMARY, 1,
                    false, false, false, false, false, false, false, true, false, false, null));
        }
    }
}
