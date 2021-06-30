/*
 * Copyright (c) 2016, 2017 Gluon - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */
package com.gluonhq.charm.glisten.testbench.samples;

import com.gluonhq.charm.glisten.control.AutoCompleteTextField;
import com.gluonhq.charm.glisten.control.TextArea;
import com.gluonhq.charm.glisten.control.TextField;
import com.gluonhq.charm.glisten.testbench.Sample;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.gluonhq.charm.glisten.visual.GlistenStyleClasses.TEXT_INPUT_FULL_WIDTH;
import static com.gluonhq.charm.glisten.visual.GlistenStyleClasses.applyStyleClass;

public class TextFieldDemoPanel extends BorderPane implements Sample {

    private final List<String> names = Arrays.asList("Jonathan", "Jose", "Johan", "Erwin", "Joeri", "Abhinay");

    private TextField simpleTextField = new TextField();
    private TextField disabledTextField = new TextField();
    private TextField errorTextField = new TextField();
    private TextField fullWidthTextField = new TextField("Glisten");
    private TextField counterTextField = new TextField();
    private TextField floatingTextField = new TextField();
    private TextField floatingCounterTextField = new TextField();
    private TextArea textArea = new TextArea();
    private AutoCompleteTextField<Person> autoCompleteTextField = new AutoCompleteTextField<>();
    private VBox demoPanel = new VBox(10);
    private ScrollPane root = new ScrollPane(demoPanel);

    public TextFieldDemoPanel() {

        root.setFitToWidth(true);
        demoPanel.setPadding(new Insets(10));

        simpleTextField.setPromptText("Name");
        disabledTextField.setPromptText("Disabled");
        disabledTextField.setDisable(true);
        fullWidthTextField.setPromptText("Full Width Text Field");
        counterTextField.setMaxLength(20);
        floatingTextField.setFloatText("Glisten");
        floatingCounterTextField.setMaxLength(10);
        floatingCounterTextField.setFloatText("Username");
        autoCompleteTextField.setCompleter(s -> {
            List<Person> persons = new ArrayList<>();
            for (String name : names) {
                if(name.startsWith(s)) {
                    persons.add(new Person(name));
                }
            }
            return persons;
        });
        autoCompleteTextField.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("New value selected from the auto-complete popup: " + newValue);
        });
        errorTextField.setFloatText("Email");
        errorTextField.setErrorValidator(s -> {
            if (s.isEmpty()) return "";
            if (!s.matches("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")) {
                return "Email Address Invalid!";
            }
            return "";
        });
        textArea.setFloatText("Message");

        applyStyleClass(fullWidthTextField, TEXT_INPUT_FULL_WIDTH);
        demoPanel.getChildren().addAll(
                autoCompleteTextField,
                counterTextField,
                floatingTextField,
                floatingCounterTextField,
                simpleTextField,
                disabledTextField,
                fullWidthTextField,
                errorTextField,
                textArea
        );
        
        setCenter(root);
        
    }


    @Override
    public String toString() {
        return "TextFields";
    }

    @Override public Node getPrimaryGraphic() {
        return MaterialDesignIcon.MODE_EDIT.graphic();
    }

    private static class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }

        public void setName(String value) { name = value; }
        public String getName() { return name; }

        @Override
        public String toString() {
            return name;
        }
    }
}
