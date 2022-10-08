package com.company;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.*;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicBoolean;

public class Main extends Application {
    Group groupHomePage = new Group();
    Group groupAdminPage = new Group();

    Label admin = new Label("Administrator Sign In:");
    TextField adminUsername = new TextField();
    TextField adminPassword = new TextField();

    Scene signInPage = new Scene(groupHomePage, 800, 600);
    Scene adminPage = new Scene(groupAdminPage, 800, 600);

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void start(Stage stage) {
        signInPage.setFill(new LinearGradient(
                0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#253237")),
                new Stop(1, Color.web("#5C6B73")))
        );

        stage.setScene(signInPage);
        stage.setTitle("JitHub");
        stage.setResizable(false);
        stage.show();

        Label welcome = new Label("Welcome to JitHub.");
        welcome.setStyle("-fx-text-fill: E0FBFC; -fx-font: 24px 'San Francisco';");
        welcome.setLayoutX(290);
        welcome.setLayoutY(15);

        Button adminSubmit = new Button("Submit");
        {
            admin.setStyle("-fx-text-fill: E0FBFC; -fx-font: 18px 'San Francisco';");
            admin.setLayoutX(80);
            admin.setLayoutY(80);

            adminUsername.setPromptText("Username");
            adminUsername.setFocusTraversable(false);
            adminUsername.setLayoutX(80);
            adminUsername.setLayoutY(115);

            adminPassword.setPromptText("Password");
            adminPassword.setFocusTraversable(false);
            adminPassword.setLayoutX(80);
            adminPassword.setLayoutY(145);

            adminSubmit.setLayoutX(80);
            adminSubmit.setLayoutY(175);
            adminSubmit.setFocusTraversable(false);
        }

        Label employee = new Label("Employee Selection:");
        {
            employee.setStyle("-fx-text-fill: E0FBFC; -fx-font: 18px 'San Francisco';");
            employee.setLayoutX(540);
            employee.setLayoutY(80);
        }


        AtomicBoolean adminPageGenerated = new AtomicBoolean(false);
        adminSubmit.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            if (adminUsername.getText().equals("r") && adminPassword.getText().equals("r")) {
                if (!adminPageGenerated.get()) {
                    generateAdminPage();
                    adminPageGenerated.set(true);
                }
                stage.setScene(adminPage);
            } else {
                admin.setText("Administrator Sign In: (Sign in failed!)");
            }
        });

        returnToHome.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            stage.setScene(signInPage);
        });

        groupHomePage.getChildren().addAll(welcome, admin, adminUsername, adminPassword, adminSubmit, employee);
    }


    Label addUsers = new Label("Add Users");
    Label addLocations = new Label("Add Locations");
    Label addEvents = new Label("Add Events");

    TextField userBox = new TextField();
    TextField locationBox = new TextField();
    TextField eventBox = new TextField();

    Button returnToHome = new Button("Return to Home Page");

    public void generateAdminPage(){
        Label adminIdentifier = new Label("Administrator Page");
        Button enterUser = new Button("Submit User");
        Button enterLocation = new Button("Submit Location");
        Button enterEvent = new Button("Submit Event");

        adminPage.setFill(new LinearGradient(
                0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#253237")),
                new Stop(1, Color.web("#5C6B73")))
        );

        int xOffset = -30;
        {
            adminIdentifier.setStyle("-fx-text-fill: E0FBFC; -fx-font: 24px 'San Francisco';");
            adminIdentifier.setLayoutX(325 + xOffset);
            adminIdentifier.setLayoutY(15);
            returnToHome.setLayoutX(360 + xOffset);
            returnToHome.setLayoutY(50);

            addUsers.setStyle("-fx-text-fill: E0FBFC; -fx-font: 18px 'San Francisco';");
            addUsers.setLayoutX(100 + xOffset);
            addUsers.setLayoutY(100);

            addLocations.setStyle("-fx-text-fill: E0FBFC; -fx-font: 18px 'San Francisco';");
            addLocations.setLayoutX(350 + xOffset);
            addLocations.setLayoutY(100);

            addEvents.setStyle("-fx-text-fill: E0FBFC; -fx-font: 18px 'San Francisco';");
            addEvents.setLayoutX(600 + xOffset);
            addEvents.setLayoutY(100);

            userBox.setLayoutX(100 + xOffset);
            userBox.setLayoutY(130);
            userBox.setFocusTraversable(false);
            userBox.setPromptText("Enter Employee Name");
            enterUser.setLayoutX(100 + xOffset);
            enterUser.setLayoutY(165);


            locationBox.setLayoutX(350 + xOffset);
            locationBox.setLayoutY(130);
            locationBox.setFocusTraversable(false);
            locationBox.setPromptText("Enter Building Locations");
            enterLocation.setLayoutX(350 + xOffset);
            enterLocation.setLayoutY(165);

            eventBox.setLayoutX(600 + xOffset);
            eventBox.setLayoutY(130);
            eventBox.setFocusTraversable(false);
            eventBox.setPromptText("Enter Common Events");
            enterEvent.setLayoutX(600 + xOffset);
            enterEvent.setLayoutY(165);



        }

        groupAdminPage.getChildren().addAll(adminIdentifier, returnToHome, addUsers, addLocations, addEvents, userBox, locationBox,
                eventBox, enterUser, enterLocation, enterEvent);
    }
}