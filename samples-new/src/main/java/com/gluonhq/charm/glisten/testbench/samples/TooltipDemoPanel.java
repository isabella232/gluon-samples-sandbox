package com.gluonhq.charm.glisten.testbench.samples;

/**
 * Created by abhinay on 07/12/15.
 */

import com.gluonhq.charm.glisten.testbench.Sample;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class TooltipDemoPanel extends BorderPane implements Sample {

    Button button = new Button("Hover Over");
    Tooltip tooltip = new Tooltip("This is a Tooltip");

    StackPane demoPanel = new StackPane();

    public TooltipDemoPanel() {

        demoPanel.setAlignment(Pos.CENTER);
        demoPanel.setPadding(new Insets(10));

        Tooltip.install(button, tooltip);

        demoPanel.getChildren().addAll(button);

        setCenter(demoPanel);
    }

    @Override
    public String toString() {
        return "ToolTips";
    }

    @Override
    public Node getPrimaryGraphic() {
        return MaterialDesignIcon.GAMEPAD.graphic();
    }
}
