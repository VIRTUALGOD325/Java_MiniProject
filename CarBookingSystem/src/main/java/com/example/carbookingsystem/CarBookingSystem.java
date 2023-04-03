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
    private TextField pickup_location;
    private TextField drop_location;
    private TextField distanceField;

    private double trip_cost;


    public static void main(String[] args) {
        launch(args);
    }

    private String isRegistered(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to TRP Cab Booking System");
        System.out.println("Enter details to continue.");
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

        System.out.println("Login to start a new session.");

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

        Label pickUp = new Label("Pickup Point:");
        pickup_location = new TextField();

        Label dropOff = new Label("Drop Point:");
        drop_location = new TextField();



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
        window.add(pickUp, 0, 2);
        window.add(pickup_location, 1, 2);
        window.add(dropOff, 0, 3);
        window.add(drop_location, 1, 3);
        window.add(bookButton, 0, 4, 2, 2);

        // Set up primary stage
        primaryStage.setTitle("Car Booking System");
        primaryStage.setScene(new Scene(window, 500, 300));
        primaryStage.show();
    }


    public static int generateCode(){
        Random num = new Random();
        return num.nextInt(1000);
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

        //Price Calc
        Scanner sc = new Scanner(System.in);
        double fare = 0;
        double baseFare = 50;
        double rate = 0.0d;
        String des;
        des = drop_location.getText();

        if(carType == "Mini"){
            rate = 20;
        }
        else if(carType == "Prime Sedan"){
            rate = 25;
        }
        else if(carType == "SUV") {
            rate = 30;
        }


        if(des.equalsIgnoreCase("Borivali")) { fare = 10 * rate + baseFare; }
        else if(des.equalsIgnoreCase("Kandivali")) { fare = 7.5 * rate + baseFare; }
        else if(des.equalsIgnoreCase("Malad")) { fare = 6 * rate + baseFare; }
        else if(des.equalsIgnoreCase("Goregaon")) { fare = 5 * rate + baseFare; }
        else if(des.equalsIgnoreCase("Ram Mandir")) { fare = 3.5 * rate + baseFare; }
        else if(des.equalsIgnoreCase("Jogeshwari")) { fare = 1.5 * rate + baseFare; }
        else if(des.equalsIgnoreCase("Andheri")) { fare = 8.5 * rate + baseFare; }
        else if(des.equalsIgnoreCase("Vile Parle")) { fare = 9.5 * rate + baseFare; }
        else if(des.equalsIgnoreCase("Santacruz")) { fare = 12.5 * rate + baseFare; }
        else if(des.equalsIgnoreCase("Bandra")) { fare = 15 * rate + baseFare; }
        else { System.out.println("Invalid destination entered"); }

        trip_cost = fare;


        // Perform booking logic (e.g. add booking to database)
        System.out.println("Car booked: " + carType);
        System.out.println("Distance: " + dist);
        System.out.println("Cost for the ride: " +  fare);

        // Show confirmation message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Booking confirmed");
        alert.setHeaderText(null);
        alert.setContentText("Your car booking has been confirmed.");
        alert.showAndWait();
    }

}