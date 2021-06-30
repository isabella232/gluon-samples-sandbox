package com.gluonhq.charm.glisten.testbench.samples;

import com.gluonhq.charm.glisten.control.Avatar;
import com.gluonhq.charm.glisten.layout.layer.PopupView;
import com.gluonhq.charm.glisten.testbench.Sample;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PopupDemoPanel extends BorderPane implements Sample {

    public PopupDemoPanel() {
        Button button = new Button("Click me");

        PopupView popup = new PopupView(button);

        VBox content = new VBox();
        HBox hBox = new HBox(5);
        Avatar avatar = new Avatar(16, new Image(PopupDemoPanel.class.getResource("graphic.png").toExternalForm()));
        Label label = new Label("Pedro Duque Vieira");
        hBox.getChildren().addAll(avatar, label);
        hBox.setOnMousePressed(event -> {
            System.out.println("Pressed: Pedro Duque Vieira");
            popup.hide();
        });
        hBox.setAlignment(Pos.CENTER_LEFT);
        content.getChildren().add(hBox);

        hBox = new HBox(5);
        avatar = new Avatar(16, new Image(PopupDemoPanel.class.getResource("graphic2.jpg").toExternalForm()));
        label = new Label("Pedro Vieira");
        hBox.getChildren().addAll(avatar, label);
        hBox.setOnMousePressed(event -> {
            System.out.println("Pressed: Pedro Vieira");
            popup.hide();
        });
        hBox.setAlignment(Pos.CENTER_LEFT);
        content.getChildren().add(hBox);

        hBox = new HBox(5);
        avatar = new Avatar(16, new Image(PopupDemoPanel.class.getResource("graphic.png").toExternalForm()));
        label = new Label("Pedro");
        hBox.getChildren().addAll(avatar, label);
        hBox.setOnMousePressed(event -> {
            System.out.println("Pressed: Pedro");
            popup.hide();
        });
        hBox.setAlignment(Pos.CENTER_LEFT);
        content.getChildren().add(hBox);

        popup.setContent(content);

        button.setOnAction(event -> popup.show());
        setCenter(button);
    }

    @Override public String toString() {
        return "Popup";
    }

    @Override
    public Node getPrimaryGraphic() {
        return MaterialDesignIcon.SPEAKER_NOTES.graphic();
    }
}
