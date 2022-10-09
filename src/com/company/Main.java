package com.company;

import com.company.Setup;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.application.Application;
import java.io.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main extends Application {
    Label admin = new Label();
    Label welcome = new Label();

    TextField adminUsername = new TextField();
    TextField adminPassword = new TextField();

    Button adminSubmit = new Button();

    Group groupHomePage = new Group();
    Group groupAdminPage = new Group();
    Group groupEmployeeInt = new Group();

    Scene signInPage = new Scene(groupHomePage, 375, 600);
    Scene adminPage = new Scene(groupAdminPage, 375, 600);
    Scene employeeInterface = new Scene(groupEmployeeInt, 375, 600);

    File users = new File("src/com/company/serverInternals/users.txt");
    File rooms = new File("src/com/company/serverInternals/rooms.txt");
    File events = new File("src/com/company/serverInternals/events.txt");

    Label addUsers = new Label();
    Label addLocations = new Label();
    Label addEvents = new Label();

    TextField userBox = new TextField();
    TextField locationBox = new TextField();
    TextField eventBox = new TextField();

    Button returnToHome = new Button();

    Button enterUser = new Button();
    Button enterLocation = new Button();
    Button enterEvent = new Button();

    Label logoName = new Label();

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void start(Stage stage) {
        signInPage.setFill(new LinearGradient(
                0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#5f0086")),
                new Stop(1, Color.web("#010080")))
        );

        signInPage.getStylesheets().add("https://fonts.googleapis.com/css2?family=Montserrat:wght@700&display=swap");

        stage.setScene(signInPage);
        stage.setTitle("Team Track");
        stage.setResizable(false);
        stage.show();

        InputStream stream = null;
        try {
            stream = new FileInputStream("src/com/company/resources/Logo.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert stream != null;
        Image image = new Image(stream);
        ImageView imageView = new ImageView();
        imageView.setImage(image);

        imageView.setImage(image);
        imageView.setX(-5);
        imageView.setY(-5);
        imageView.setFitWidth(70);
        imageView.setPreserveRatio(true);

        int xSetupShift = 2;
        Setup.setLabel(welcome, "Welcome back.", "-fx-text-fill: E0FBFC; -fx-font-size: 24px; -fx-font-family: 'Montserrat', sans-serif;", 80 + xSetupShift, 100);
        Setup.setLabel(logoName, "TeamTrack", "-fx-text-fill: E0FBFC; -fx-font-size: 24px; -fx-font-family: 'Montserrat', sans-serif;", 60 + xSetupShift, 15);
        {
            Setup.setLabel(admin, "Login", "-fx-text-fill: E0FBFC; -fx-font-size: 18px; -fx-font-family: 'Montserrat', sans-serif;", 150 + xSetupShift, 153);
            Setup.setTextField(adminUsername, "Username", false, 105 + xSetupShift, 185);
            Setup.setTextField(adminPassword, "Password", false, 105 + xSetupShift, 215);
            Setup.setButton(adminSubmit, "Submit", false, 153 + xSetupShift, 250);
        }

        AtomicBoolean adminPageGenerated = new AtomicBoolean(false);
        adminSubmit.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            //String userString = Files.readAllBytes(Paths.get("src/com/company/serverInternals/users.txt"));
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

        /*
        AtomicBoolean employeePageGenerated = new AtomicBoolean(false);
        adminSubmit.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            if (!employeePageGenerated.get()) {
                generateEmployeePage();
                employeePageGenerated.set(true);
            }
            stage.setScene(employeeInterface);
        });
         */
        returnToHome.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            stage.setScene(signInPage);
        });

        groupHomePage.getChildren().addAll(welcome, admin, adminUsername, adminPassword, adminSubmit, imageView, logoName);
    }
    public void generateAdminPage() {
        Label adminIdentifier = new Label();
        adminPage.setFill(new LinearGradient(
                0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#5f0086")),
                new Stop(1, Color.web("#010080")))
        );

        InputStream stream = null;
        try {
            stream = new FileInputStream("src/com/company/resources/Logo.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert stream != null;
        Image image = new Image(stream);
        ImageView imageView = new ImageView();
        imageView.setImage(image);

        imageView.setImage(image);
        imageView.setX(-5);
        imageView.setY(-5);
        imageView.setFitWidth(70);
        imageView.setPreserveRatio(true);


        int xOffset = -30;
        int yOffset = 20;
        {
            Setup.setLabel(logoName, "TeamTrack", "-fx-text-fill: E0FBFC; -fx-font-size: 24px; -fx-font-family: 'Montserrat', sans-serif;", 62, 15);
            Setup.setLabel(adminIdentifier, "Administrator Page", "-fx-text-fill: E0FBFC; -fx-font-size: 24px; -fx-font-family: 'Montserrat', sans-serif;", 90 + xOffset, 50 + yOffset);
            Setup.setButton(returnToHome, "Return to Home Page", false, 151 + xOffset, 130 + yOffset);

            Setup.setLabel(addUsers, "Add Users", "-fx-text-fill: E0FBFC; -fx-font-size: 18px; -fx-font-family: 'Montserrat', sans-serif;", 177 + xOffset, 180 + yOffset);
            Setup.setTextField(userBox, "Enter Employee Name", false, 147 + xOffset, 210 + yOffset);
            Setup.setButton(enterUser, "Submit User", false, 176 + xOffset, 237 + yOffset);

            Setup.setLabel(addLocations, "Add Locations", "-fx-text-fill: E0FBFC; -fx-font-size: 18px; -fx-font-family: 'Montserrat', sans-serif;", 170 + xOffset, 270 + yOffset);
            Setup.setTextField(locationBox, "Enter Room/Location", false, 150 + xOffset, 300 + yOffset);
            Setup.setButton(enterLocation, "Submit Location", false, 179 + xOffset, 327 + yOffset);

            Setup.setLabel(addEvents, "Add Events", "-fx-text-fill: E0FBFC; -fx-font-size: 18px; -fx-font-family: 'Montserrat', sans-serif;", 180 + xOffset, 360 + yOffset);
            Setup.setTextField(eventBox, "Enter Common Events", false, 150 + xOffset, 390 + yOffset);
            Setup.setButton(enterEvent, "Submit Event", false, 179 + xOffset, 417 + yOffset);
        }

        enterUser.setOnAction(this::handleWriteToUsers);
        enterLocation.setOnAction(this::handleWriteToLocations);
        enterEvent.setOnAction(this::handleWriteToEvents);

        groupAdminPage.getChildren().addAll(adminIdentifier, returnToHome, addUsers, addLocations, addEvents, userBox, locationBox,
                eventBox, enterUser, enterLocation, enterEvent, imageView, logoName);

    }

    public void handleWriteToEvents(ActionEvent event) {
        try {
            BufferedWriter eventWriter = new BufferedWriter(new FileWriter(events, true));
            eventWriter.write(eventBox.getText());
            eventWriter.newLine();
            eventWriter.flush();
            eventWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        eventBox.clear();
    }

    public void handleWriteToLocations(ActionEvent event) {
        try {
            BufferedWriter roomWriter = new BufferedWriter(new FileWriter(rooms, true));
            roomWriter.write(locationBox.getText());
            roomWriter.newLine();
            roomWriter.flush();
            roomWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        locationBox.clear();
    }

    public void handleWriteToUsers(ActionEvent event) {
        try {
            BufferedWriter userWriter = new BufferedWriter(new FileWriter(users, true));
            userWriter.write(userBox.getText());
            userWriter.newLine();
            userWriter.flush();
            userWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        userBox.clear();
    }
}


