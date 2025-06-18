package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.FileWriter;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Clear rentals.txt at startup
        try {
            new FileWriter("rentals.txt", false).close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Pane root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Car Rental Application");
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
