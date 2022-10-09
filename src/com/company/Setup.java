package com.company;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
    
    public static void setDropdown(ComboBox name, double x, double y){
        name.setLayoutX(x);
        name.setLayoutY(y);
    }
    
}
