package com.gluonhq.charm.glisten.testbench.samples;

import com.gluonhq.charm.glisten.control.Toast;
import com.gluonhq.charm.glisten.testbench.Sample;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class ToastDemoPanel extends BorderPane implements Sample {

    Button button = new Button("Show toast");

    public ToastDemoPanel() {
        Toast toast = new Toast("This is a toast!");
        button.setOnAction(event -> toast.show());
        setCenter(button);
    }

    @Override
    public String toString() {
        return "Toast";
    }

    @Override
    public Node getPrimaryGraphic() {
        return MaterialDesignIcon.ANNOUNCEMENT.graphic();
    }

}