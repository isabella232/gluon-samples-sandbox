package com.gluonhq.charm.glisten.testbench.samples;

import com.gluonhq.charm.glisten.control.ToggleButtonGroup;
import com.gluonhq.charm.glisten.testbench.Sample;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ToggleButtonsDemoPanel extends BorderPane implements Sample {

    public ToggleButtonsDemoPanel(){
        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(20, 10, 0, 10));

        ToggleButtonGroup toggleButtonGroup = new ToggleButtonGroup();
        toggleButtonGroup.setSelectionType(SelectionMode.MULTIPLE);
        ToggleButton toggleButton = new ToggleButton();
        toggleButton.setSelected(true);
        toggleButton.setGraphic(MaterialDesignIcon.FORMAT_BOLD.graphic());
        toggleButtonGroup.getToggles().add(toggleButton);
        toggleButton = new ToggleButton();
        toggleButton.setSelected(true);
        toggleButton.setGraphic(MaterialDesignIcon.FORMAT_ITALIC.graphic());
        toggleButtonGroup.getToggles().add(toggleButton);
        toggleButton = new ToggleButton();
        toggleButton.setSelected(true);
        toggleButton.setGraphic(MaterialDesignIcon.FORMAT_UNDERLINED.graphic());
        toggleButtonGroup.getToggles().add(toggleButton);
        toggleButton = new ToggleButton();
        toggleButton.setGraphic(MaterialDesignIcon.FORMAT_COLOR_FILL.graphic());
        toggleButtonGroup.getToggles().add(toggleButton);

        vBox.getChildren().add(toggleButtonGroup);

        toggleButtonGroup = new ToggleButtonGroup();
        toggleButtonGroup.setSelectionType(SelectionMode.SINGLE);
        toggleButton = new ToggleButton();
        toggleButton.setGraphic(MaterialDesignIcon.FORMAT_ALIGN_LEFT.graphic());
        toggleButtonGroup.getToggles().add(toggleButton);
        toggleButton = new ToggleButton();
        toggleButton.setSelected(true);
        toggleButton.setGraphic(MaterialDesignIcon.FORMAT_ALIGN_CENTER.graphic());
        toggleButtonGroup.getToggles().add(toggleButton);
        toggleButton = new ToggleButton();
        toggleButton.setGraphic(MaterialDesignIcon.FORMAT_ALIGN_RIGHT.graphic());
        toggleButtonGroup.getToggles().add(toggleButton);
        toggleButton = new ToggleButton();
        toggleButton.setGraphic(MaterialDesignIcon.FORMAT_ALIGN_JUSTIFY.graphic());
        toggleButtonGroup.getToggles().add(toggleButton);

        vBox.getChildren().add(toggleButtonGroup);

        toggleButtonGroup = new ToggleButtonGroup();
        toggleButtonGroup.setSelectionType(SelectionMode.SINGLE);
        toggleButton = new ToggleButton();
        toggleButton.setGraphic(MaterialDesignIcon.FORMAT_ALIGN_LEFT.graphic());
        toggleButtonGroup.getToggles().add(toggleButton);
        toggleButton = new ToggleButton();
        toggleButton.setGraphic(MaterialDesignIcon.FORMAT_ALIGN_CENTER.graphic());
        toggleButtonGroup.getToggles().add(toggleButton);
        toggleButton = new ToggleButton();
        toggleButton.setGraphic(MaterialDesignIcon.FORMAT_ALIGN_RIGHT.graphic());
        toggleButtonGroup.getToggles().add(toggleButton);
        toggleButton = new ToggleButton();
        toggleButton.setGraphic(MaterialDesignIcon.FORMAT_ALIGN_JUSTIFY.graphic());
        toggleButtonGroup.getToggles().add(toggleButton);

        vBox.getChildren().add(toggleButtonGroup);

        setCenter(vBox);
    }

    @Override
    public String toString() {
        return "Toggle Buttons";
    }

    @Override
    public Node getPrimaryGraphic() {
        return MaterialDesignIcon.CHECK_BOX.graphic();
    }
}
