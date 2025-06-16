package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private CheckBox checkBox;

    @FXML
    private ImageView emailImageView;

    @FXML
    private Label emailLabel;

    @FXML
    private TextField emailInput;

    @FXML
    private Button loginBtn;

    @FXML
    private Pane mainPane;

    @FXML
    private PasswordField passwordInputPass;

    @FXML
    private TextField passwordInputText;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label recoveryLabel;

    @FXML
    private Label rememberMeLabel;

    @FXML
    private Button resetBtn;

    @FXML
    private Button signUpBtn;

    @FXML
    private Label tipLabel;

    private static final String USERS_FILE = "users.txt";

    @FXML
    void loginBtnClick(MouseEvent event) {
        String username = emailInput.getText();
        String password;

        if (passwordInputPass.isVisible()) {
            password = passwordInputPass.getText();
        } else {
            password = passwordInputText.getText();
        }

        if (validateUserFromFile(username, password)) {
            System.out.println("Login successful!");
            redirectToMain(event);
        } else {
            showAlert("Invalid username or password.");
        }
    }

    private boolean validateUserFromFile(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String storedUsername = parts[0];
                    String storedPassword = parts[1];
                    if (storedUsername.equals(username) && storedPassword.equals(password)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    private void redirectToMain(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/main.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error loading main page.");
        }
    }

    @FXML
    void recoveryPassword(MouseEvent event) {
        System.out.println("Recovery password label clicked!");
    }

    @FXML
    void resetBtnClick(MouseEvent event) {
        System.out.println("Reset password button clicked!");
    }

    @FXML
    void showPasswordIconClick(MouseEvent event) {
        if (passwordInputPass.isVisible()) {
            passwordInputText.setText(passwordInputPass.getText());
            passwordInputPass.setVisible(false);
            passwordInputText.setVisible(true);
        } else {
            passwordInputPass.setText(passwordInputText.getText());
            passwordInputPass.setVisible(true);
            passwordInputText.setVisible(false);
        }
    }

    @FXML
    void signUpBtnClick(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Register.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) signUpBtn.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Could not load registration page", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void initialize() {
        resetBtn.setVisible(false);
        passwordInputText.setVisible(false);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
