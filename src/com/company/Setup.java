package com.company;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.concurrent.atomic.AtomicBoolean;


public class Setup {
    public static void setLabel(Label name, String text, String style, double x, double y) {
        name.setText(text);
        name.setStyle(style);
        name.setLayoutX(x);
        name.setLayoutY(y);
    }

    public static void setTextField(TextField name, String text, boolean traversable, double x, double y) {
        name.setPromptText(text);
        name.setFocusTraversable(traversable);
        name.setLayoutX(x);
        name.setLayoutY(y);
    }

    public static void setButton(Button name, String text, boolean traversable, double x, double y) {
        name.setText(text);
        name.setFocusTraversable(traversable);
        name.setLayoutX(x);
        name.setLayoutY(y);
    }
}
