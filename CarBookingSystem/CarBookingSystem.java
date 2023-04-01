package com.example.carbookingsystem;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Random;
import java.util.Scanner;

public class CarBookingSystem extends Application {
    private ComboBox<String> carTypeComboBox;
    private TextField distanceField;

    public static void main(String[] args) {
        launch(args);
    }

    private String isRegistered(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String rUsername = scanner.nextLine();
        System.out.print("Enter password: ");
        String rPassword = scanner.nextLine();
        String creds = rUsername + " " + rPassword;
        return creds;
    }

    private boolean isLoggedIn(String creds) {
        // Get username and password from user
        String[] splitString = creds.split("\\s+");
        String regUsername = splitString[0];
        String regPassword = splitString[1];

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Check if username and password are valid
        return username.equals(regUsername) && password.equals(regPassword);
    }

    @Override
    public void start(Stage primaryStage) {
        // Check if user is registered and logged in
        String creds = isRegistered();
        if (!isLoggedIn(creds)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login required");
            alert.setHeaderText(null);
            alert.setContentText("Please login to access the car booking system.");
            alert.showAndWait();
            return;
        }

        // Creating GUI controls
        Label carTypeLabel = new Label("Car Type:");
        carTypeComboBox = new ComboBox<>();
        carTypeComboBox.getItems().addAll("Mini", "Prime Sedan", "SUV");

        Label distanceLabel =  new Label("Distance to travel(KM):");
        distanceField = new TextField();
        Label rateConv = new Label("*PRICE DEPENDS ON CAR TYPE*");

        Button bookButton = new Button("Book Car");
        bookButton.setOnAction(e -> bookCar());

        // Creating GUI layout
        GridPane window = new GridPane();
        window.setPadding(new Insets(10));
        window.setHgap(10);
        window.setVgap(10);

        //Adding Components to the GUI Window "Window"
        window.add(carTypeLabel, 0, 0);
        window.add(carTypeComboBox, 1, 0);
        window.add(distanceLabel, 0, 1);
        window.add(distanceField,1,1);
        window.add(rateConv, 0, 2);
        window.add(bookButton, 0, 3, 2, 1);

        // Set up primary stage
        primaryStage.setTitle("Car Booking System");
        primaryStage.setScene(new Scene(window, 500, 300));
        primaryStage.show();
    }

    public static int generateCode(){
        Random num = new Random();
        return num.nextInt(10);
    }

    private void bookCar() {
        // Captcha System using Random function
        boolean flag = false;

        while(!flag) {
            int captcha = generateCode();
            System.out.println(captcha);
            System.out.println("ENTER THE CAPTCHA TO CONFIRM BOOKING");
            Scanner sc = new Scanner(System.in);
            int ans = sc.nextInt();
            if (ans == captcha) {
                System.out.println("VERIFIED");
                flag = true;
            } else {
                System.out.println("Wring Captcha");
            }
        }

        // Get input values
        String carType = carTypeComboBox.getValue();
        int dist = Integer.parseInt(distanceField.getText());

        // Perform booking logic (e.g. add booking to database)
        System.out.println("Car booked: " + carType);
        System.out.println("Distance: " + dist);

        // Show confirmation message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Booking confirmed");
        alert.setHeaderText(null);
        alert.setContentText("Your car booking has been confirmed.");
        alert.showAndWait();
    }

}