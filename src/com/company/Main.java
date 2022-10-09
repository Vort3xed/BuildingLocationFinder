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

import java.util.concurrent.atomic.AtomicBoolean;

public class Main extends Application {
    Label admin = new Label();
    Label welcome = new Label();
    Label logoName = new Label();

    TextField adminUsername = new TextField();
    TextField adminPassword = new TextField();

    Button adminSubmit = new Button();

    Group groupHomePage = new Group();
    Scene signInPage = new Scene(groupHomePage, 375, 600);

    Group groupAdminPage = new Group();
    public Scene adminPage = new Scene(groupAdminPage, 375, 600);

    Label adminIdentifier = new Label();

    Label addUsers = new Label();
    Label addLocations = new Label();
    Label addEvents = new Label();

    TextField userBox = new TextField();
    TextField locationBox = new TextField();
    TextField eventBox = new TextField();

    Button returnToHome = new Button();

    Button enterUser = new Button();
    Button enterLocation = new Button("Submit Location");
    Button enterEvent = new Button("Submit Event");

    File users = new File("src/com/company/serverInternals/users.txt");
    File rooms = new File("src/com/company/serverInternals/rooms.txt");
    File events = new File("src/com/company/serverInternals/events.txt");

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void start(Stage stage){
        signInPage.setFill(new LinearGradient(
                0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#5f0086")),
                new Stop(1, Color.web("#010080")))
        );

        signInPage.getStylesheets().add("https://fonts.googleapis.com/css2?family=Montserrat:wght@700&display=swap");       // image importing software, need to simplify

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

        stage.setScene(signInPage);
        stage.setTitle("Team Track");
        stage.setResizable(false);
        stage.show();

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
            if (adminUsername.getText().equals("r") && adminPassword.getText().equals("r")) {
                if (!adminPageGenerated.get()) {
                    generateAdminPage(stage);
                    adminPageGenerated.set(true);
                }
                stage.setScene(adminPage);
            } else {
                admin.setText("Administrator Sign In: (Sign in failed!)");
                admin.setLayoutX(15);
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

        groupHomePage.getChildren().addAll(welcome, admin, adminUsername, adminPassword, adminSubmit, logoName, imageView);
    }
    public void generateAdminPage(Stage stage) {
        adminPage.setFill(new LinearGradient(
                0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#5f0086")),
                new Stop(1, Color.web("#010080")))
        );

        int xOffset = -30;
        {
            Setup.setLabel(logoName, "TeamTrack", "-fx-text-fill: E0FBFC; -fx-font-size: 24px; -fx-font-family: 'Montserrat', sans-serif;", 62, 15);
            Setup.setLabel(adminIdentifier, "Administrator Page", "-fx-text-fill: E0FBFC; -fx-font-size: 24px; -fx-font-family: 'Montserrat', sans-serif;", 115 + xOffset, 50);
            Setup.setButton(returnToHome, "Return to Home Page", false, 360 + xOffset, 150);
        }

        {
            Setup.setLabel(addUsers, "Add Users", "-fx-text-fill: E0FBFC; -fx-font: 18px 'San Francisco';", 130 + xOffset, 100);
            Setup.setTextField(userBox, "Enter Employee Name", false, 100 + xOffset, 130);
            Setup.setButton(enterUser, "Submit User", false, 100 + xOffset, 165);

            Setup.setLabel(addLocations, "Add Locations", "-fx-text-fill: E0FBFC; -fx-font: 18px 'San Francisco';", 100 + xOffset, 180);
            Setup.setTextField(locationBox, "Enter Room/Location", false, 100 + xOffset, 180);
            Setup.setButton(enterLocation, "Submit Location", false, 100 + xOffset, 180);

            Setup.setLabel(addEvents, "Add Events", "-fx-text-fill: E0FBFC; -fx-font: 18px 'San Francisco';", 100 + xOffset, 180);
            Setup.setTextField(eventBox, "Enter Common Events", false, 100 + xOffset, 180);
            Setup.setButton(enterEvent, "Submit Event", false, 100 + xOffset, 180);
        }

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

        {
            enterUser.setOnAction(this::handleWriteToUsers);
            enterLocation.setOnAction(this::handleWriteToLocations);
            enterEvent.setOnAction(this::handleWriteToEvents);
        }

        returnToHome.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            stage.setScene(signInPage);
        });

        groupAdminPage.getChildren().addAll(adminIdentifier, returnToHome, addUsers, addLocations, addEvents, userBox, locationBox,
                eventBox, enterUser, enterLocation, enterEvent, welcome, logoName, imageView);
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
