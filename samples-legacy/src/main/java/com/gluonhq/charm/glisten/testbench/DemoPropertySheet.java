package com.gluonhq.charm.glisten.testbench;

import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

public class DemoPropertySheet extends VBox{
    
    public final CheckBox cbLightTheme = new CheckBox("Light Theme");
    
    public DemoPropertySheet() {
        setPadding( new Insets(15));
        getChildren().add(cbLightTheme);
        setStyle("-fx-background-color: #EEEEEE; -fx-border-color: grey transparent transparent transparent;");
        cbLightTheme.getStyleClass().add("light");
    }

}
