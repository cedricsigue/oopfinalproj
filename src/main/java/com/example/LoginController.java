package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
    private boolean isRecoveryMode = false;

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
        String username = emailInput.getText();
        if (username.isEmpty()) {
            showAlert("Please enter your username first.");
            return;
        }

        if (userExists(username)) {
            isRecoveryMode = true;
            loginBtn.setVisible(false);
            resetBtn.setVisible(true);
            passwordLabel.setText("New Password");
            showAlert("Info", "Please enter your new password", Alert.AlertType.INFORMATION);
        } else {
            showAlert("Username not found.");
        }
    }

    private boolean userExists(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2 && parts[0].equals(username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @FXML
    void resetBtnClick(MouseEvent event) {
        String username = emailInput.getText();
        String newPassword = passwordInputPass.isVisible() ? passwordInputPass.getText() : passwordInputText.getText();

        if (newPassword.isEmpty()) {
            showAlert("Please enter a new password.");
            return;
        }

        if (updatePassword(username, newPassword)) {
            showAlert("Success", "Password has been reset successfully!", Alert.AlertType.INFORMATION);
            isRecoveryMode = false;
            loginBtn.setVisible(true);
            resetBtn.setVisible(false);
            passwordLabel.setText("Password");
            passwordInputPass.clear();
            passwordInputText.clear();
        } else {
            showAlert("Error resetting password. Please try again.");
        }
    }

    private boolean updatePassword(String username, String newPassword) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(USERS_FILE));
            List<String> newLines = new ArrayList<>();

            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length == 2 && parts[0].equals(username)) {
                    newLines.add(username + "," + newPassword);
                } else {
                    newLines.add(line);
                }
            }

            Files.write(Paths.get(USERS_FILE), newLines);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
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
