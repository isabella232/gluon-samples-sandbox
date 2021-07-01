package com.gluonhq.charm.glisten.testbench.samples;

import com.gluonhq.charm.glisten.control.Avatar;
import com.gluonhq.charm.glisten.layout.layer.PopupView;
import com.gluonhq.charm.glisten.testbench.Sample;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.geometry.Insets;
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
        hBox.setPadding(new Insets(2, 10, 2, 10));
        Avatar avatar = new Avatar(16, new Image("/icon.png"));
        Label label = new Label("Label 1");
        hBox.getChildren().addAll(avatar, label);
        hBox.setOnMousePressed(event -> {
            System.out.println("Pressed: Label 1");
            popup.hide();
        });
        hBox.setAlignment(Pos.CENTER_LEFT);
        content.getChildren().add(hBox);

        hBox = new HBox(5);
        hBox.setPadding(new Insets(2, 10, 2, 10));
        avatar = new Avatar(16, new Image("/icon.png"));
        label = new Label("Label 2");
        hBox.getChildren().addAll(avatar, label);
        hBox.setOnMousePressed(event -> {
            System.out.println("Pressed: Label 2");
            popup.hide();
        });
        hBox.setAlignment(Pos.CENTER_LEFT);
        content.getChildren().add(hBox);

        hBox = new HBox(5);
        hBox.setPadding(new Insets(2, 10, 2, 10));
        avatar = new Avatar(16, new Image("/icon.png"));
        label = new Label("Label 3");
        hBox.getChildren().addAll(avatar, label);
        hBox.setOnMousePressed(event -> {
            System.out.println("Pressed: Label 3");
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
