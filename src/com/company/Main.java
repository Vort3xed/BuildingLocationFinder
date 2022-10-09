package com.company;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.application.Application;

import java.io.*;
import java.util.*;

import java.util.concurrent.atomic.AtomicBoolean;

public class Main extends Application {
    Label admin = new Label();
    Label welcome = new Label();
    Label employeePageIdentifier = new Label();

    TextField adminUsername = new TextField();
    TextField adminPassword = new TextField();

    ComboBox eventDropDown = new ComboBox();
    ComboBox locationDropDown = new ComboBox();

    Button adminSubmit = new Button();

    Group groupHomePage = new Group();
    Group groupAdminPage = new Group();
    Group groupEmployeePage = new Group();
    Group groupEmployeePost = new Group();
    Group groupEmployeeClock = new Group();

    Scene signInPage = new Scene(groupHomePage, 375, 600);
    Scene adminPage = new Scene(groupAdminPage, 375, 600);
    Scene employeePage = new Scene(groupEmployeePage, 375, 600);
    Scene employeePost = new Scene(groupEmployeePost, 375, 600);
    Scene employeeClockPage = new Scene(groupEmployeeClock, 375, 600);

    File users = new File("src/com/company/serverInternals/users.txt");
    File rooms = new File("src/com/company/serverInternals/rooms.txt");
    File events = new File("src/com/company/serverInternals/events.txt");
    File postedEvents = new File("src/com/company/serverInternals/postedevents.txt");

    Label addUsers = new Label();
    Label addLocations = new Label();
    Label addEvents = new Label();
    Label currentEventIdentifier = new Label();

    Label eventDropDownIdentifier = new Label();
    Label locDropDownIdentifier = new Label();

    TextField userBox = new TextField();
    TextField locationBox = new TextField();
    TextField eventBox = new TextField();

    Button returnToHome = new Button();
    Button cancelRequest = new Button();
    Button postEvent = new Button();

    Button enterUser = new Button();
    Button enterLocation = new Button();
    Button enterEvent = new Button();

    Label logoName = new Label();

    TextField timeScheduled = new TextField();

    ScrollBar sc = new ScrollBar();
    VBox vb = new VBox();

    Label clockPageIdentifier = new Label();        // new downwards
    Button clockInIdentifier = new Button();
    Button clockOutIdentifier = new Button();

    Button goToClockPage = new Button();
    Button goToPostPage = new Button();
    Button logout = new Button();
    Button cancel = new Button();

    Label timeIdentifier = new Label();

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
            stream = new FileInputStream("src/com/company/resources/logo.png");
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
        AtomicBoolean employeePageGenerated = new AtomicBoolean(false);
        adminSubmit.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            Scanner txt = null;
            try {
                txt = new Scanner(users);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            String userString = "";
            while (true) {
                assert txt != null;
                if (!txt.hasNext()) break;
                String temp = txt.nextLine();
                userString = userString + temp;
            }
            txt.close();

            if (adminUsername.getText().equals("admin") && adminPassword.getText().equals("access")) {       // change this later
                if (!adminPageGenerated.get()) {
                    generateAdminPage();
                    adminPageGenerated.set(true);
                }
                stage.setScene(adminPage);
            } else if (userString.contains(adminUsername.getText())) {
                adminSubmit.addEventHandler(MouseEvent.MOUSE_CLICKED, (p) -> {
                    if (!employeePageGenerated.get()) {
                        try {
                            generateEmployeePage(stage);
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        }
                        employeePageGenerated.set(true);
                    }
                    stage.setScene(employeePage);
                });
            } else {
                admin.setText("Administrator Sign In: (Sign in failed!)");
            }

        });


        returnToHome.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            stage.setScene(signInPage);
        });

        groupHomePage.getChildren().addAll(welcome, admin, adminUsername, adminPassword, adminSubmit, logoName, imageView);
    }

    private void generateEmployeePage(Stage stage) throws FileNotFoundException {
        employeePage.setFill(new LinearGradient(
                0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#5f0086")),
                new Stop(1, Color.web("#010080")))
        );

        InputStream stream = null;
        try {
            stream = new FileInputStream("src/com/company/resources/logo.png");
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

        sc.setLayoutX(employeePage.getWidth() - sc.getWidth());
        sc.setMin(80);
        sc.setOrientation(Orientation.VERTICAL);
        sc.setPrefHeight(150);
        sc.setMax(500);

        Setup.setLabel(logoName, "TeamTrack", "-fx-text-fill: E0FBFC; -fx-font-size: 24px; -fx-font-family: 'Montserrat', sans-serif;", 62, 15);
        Setup.setLabel(currentEventIdentifier, "Posted Events:", "-fx-text-fill: E0FBFC; -fx-font-size: 24px; -fx-font-family: 'Montserrat', sans-serif;", 95, 75);
        Setup.setButton(goToClockPage, "Clock In/Out", false, 145, 425);       // new
        Setup.setButton(goToPostPage, "Post an Event", false, 143.5, 475);      // new
        Setup.setButton(logout, "Logout", false, 161.5, 525);
        vb.setLayoutY(120);      // new
        sc.setLayoutY(120);
        sc.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                vb.setLayoutY(-new_val.doubleValue());
            }
        });

        Scanner userLocator = null;
        try {
            userLocator = new Scanner(postedEvents);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        while (true) {
            assert userLocator != null;
            if (!userLocator.hasNextLine()) break;
            String temp = userLocator.nextLine();
            String info = setEmployeeStatus(temp);
            vb.getChildren().add((new Button("" + info)));
        }
        userLocator.close();

        AtomicBoolean employeeClockPageGenerated = new AtomicBoolean(false);
        goToClockPage.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            if (!employeeClockPageGenerated.get()) {
                generateEmployeeClockPage(stage);
                employeeClockPageGenerated.set(true);
            }
            stage.setScene(employeeClockPage);
        });

        AtomicBoolean employeePostPageGenerated = new AtomicBoolean(false);
        goToPostPage.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            if (!employeePostPageGenerated.get()) {
                generateEmployeePost(stage);
                employeePostPageGenerated.set(true);
            }
            stage.setScene(employeePost);
        });

        logout.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            stage.setScene(signInPage);
        });
        groupEmployeePage.getChildren().addAll(vb, currentEventIdentifier, goToClockPage, goToPostPage, logoName, logout, imageView);      // changed
    }


    private void generateEmployeeClockPage(Stage stage) {   // new
        employeeClockPage.setFill(new LinearGradient(
                0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#5f0086")),
                new Stop(1, Color.web("#010080")))
        );

        InputStream stream = null;
        try {
            stream = new FileInputStream("src/com/company/resources/logo.png");
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

        Setup.setLabel(logoName, "TeamTrack", "-fx-text-fill: E0FBFC; -fx-font-size: 24px; -fx-font-family: 'Montserrat', sans-serif;", 62, 15);
        Setup.setLabel(clockPageIdentifier, "Ready for a break?", "-fx-text-fill: E0FBFC; -fx-font-size: 24px; -fx-font-family: 'Montserrat', sans-serif;", 75, 70);
        Setup.setButton(clockInIdentifier, "Clock In", false, 155, 150);
        Setup.setButton(clockOutIdentifier, "Clock Out", false, 152, 200);
        Setup.setLabel(locDropDownIdentifier, "Select a Location:", "-fx-text-fill: E0FBFC; -fx-font-size: 18px; -fx-font-family: 'Montserrat', sans-serif;", 100, 260);
        Setup.setDropdown(locationDropDown, 137, 300);
        Setup.setButton(cancel, "Cancel", false, 165, 525);

        Scanner locationReader = null;
        try {
            locationReader = new Scanner(rooms);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        while (true) {
            assert locationReader != null;
            if (!locationReader.hasNext()) break;
            String temp = locationReader.nextLine();
            locationDropDown.getItems().add(temp);

        }

        cancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            stage.setScene(employeePage);
        });
        locationReader.close();

        groupEmployeeClock.getChildren().addAll(clockPageIdentifier, clockInIdentifier, clockOutIdentifier, locDropDownIdentifier, locationDropDown, logoName, imageView, cancel);

    }

    private void generateEmployeePost(Stage stage) {
        employeePost.setFill(new LinearGradient(
                0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#5f0086")),
                new Stop(1, Color.web("#010080")))
        );
        Scanner eventReader = null;
        try {
            eventReader = new Scanner(events);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        while (true) {
            assert eventReader != null;
            if (!eventReader.hasNext()) break;
            String temp = eventReader.nextLine();
            eventDropDown.getItems().add(temp);

        }
        eventReader.close();

        InputStream stream = null;
        try {
            stream = new FileInputStream("src/com/company/resources/logo.png");
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

        Scanner locationReader = null;
        try {
            locationReader = new Scanner(rooms);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        while (true) {
            assert locationReader != null;
            if (!locationReader.hasNext()) break;
            String temp = locationReader.nextLine();
            locationDropDown.getItems().add(temp);

        }
        locationReader.close();

        {
            Setup.setLabel(logoName, "TeamTrack", "-fx-text-fill: E0FBFC; -fx-font-size: 24px; -fx-font-family: 'Montserrat', sans-serif;", 62, 15);
            Setup.setLabel(employeePageIdentifier, "Post an Event", "-fx-text-fill: E0FBFC; -fx-font-size: 24px; -fx-font-family: 'Montserrat', sans-serif;", 100, 70);
            Setup.setLabel(eventDropDownIdentifier, "Select an Event:", "-fx-text-fill: E0FBFC; -fx-font-size: 18px; -fx-font-family: 'Montserrat', sans-serif;", 110, 120);
            Setup.setLabel(locDropDownIdentifier, "Select a Location:", "-fx-text-fill: E0FBFC; -fx-font-size: 18px; -fx-font-family: 'Montserrat', sans-serif;", 106, 200);
            Setup.setLabel(timeIdentifier, "Time:", "-fx-text-fill: E0FBFC; -fx-font-size: 18px; -fx-font-family: 'Montserrat', sans-serif;", 160, 280);
            Setup.setDropdown(eventDropDown, 137, 150);
            Setup.setDropdown(locationDropDown, 137, 230);

            Setup.setButton(cancelRequest, "Cancel", false, 155, 525);
            Setup.setTextField(timeScheduled, "Time", false, 113, 310);
            Setup.setButton(postEvent, "Post Event", false, 145, 380);
        }

        postEvent.setOnAction(this::postEvent);

        cancelRequest.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            stage.setScene(employeePage);
        });

        groupEmployeePost.getChildren().addAll(employeePageIdentifier, eventDropDown, locationDropDown, eventDropDownIdentifier, locDropDownIdentifier      // new
                , cancelRequest, timeScheduled, postEvent, logoName, imageView, timeIdentifier);
    }

    public String setEmployeeStatus(String line) throws FileNotFoundException {     // new?
        String[] information = line.split(", ");
        String name = information[0];
        return (name + ": " + "is requesting for a " + information[1] + " at " + information[2] + ". " + "Time: " + information[3]);
    }

    public void postEvent(ActionEvent event) {
        try {
            BufferedWriter userWriter = new BufferedWriter(new FileWriter(postedEvents, true));
            userWriter.write(adminUsername.getText() + ", " + eventDropDown.getValue() + ", " + locationDropDown.getValue() +
                    ", " + timeScheduled.getText());
            userWriter.newLine();
            userWriter.flush();
            userWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        adminUsername.clear();
        userBox.clear();
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
            stream = new FileInputStream("src/com/company/resources/logo.png");
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
                eventBox, enterUser, enterLocation, enterEvent, logoName, imageView);

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
            String name = userBox.getText();
            userWriter.write(name);
            userWriter.newLine();
            userWriter.flush();
            userWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        userBox.clear();
    }
}


