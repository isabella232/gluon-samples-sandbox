package com.gluonhq.charm.glisten.testbench.samples;

import com.gluonhq.charm.glisten.control.Alert;
import com.gluonhq.charm.glisten.control.Dialog;
import com.gluonhq.charm.glisten.control.ExceptionDialog;
import com.gluonhq.charm.glisten.control.LifecycleEvent;
import com.gluonhq.charm.glisten.testbench.Sample;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import java.util.Optional;

public class DialogsDemoPanel extends BorderPane implements Sample {

    public DialogsDemoPanel() {
        FlowPane flowPane = new FlowPane(10, 10);
        flowPane.setPadding(new Insets(20, 5, 0, 5));
        flowPane.setVgap(20);

        Button errorButton = new Button("Show Error Alert");
        errorButton.setOnAction(event -> {
            Alert alert = new Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alert.setTitleText("This is an error");
            alert.setContentText("This is the error information. Do something with it.");
            alert.showAndWait();
        });
        Button warningButton = new Button("Show Warning Alert");
        warningButton.setOnAction(event -> {
            Alert alert = new Alert(javafx.scene.control.Alert.AlertType.WARNING);
            alert.setTitleText("This is a warning");
            alert.setContentText("This is the warning information. Do something with it.");
            alert.showAndWait();
        });
        Button informationButton = new Button("Show Information Alert");
        informationButton.setOnAction(event -> {
            Alert alert = new Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
            alert.setTitleText("This is an information");
            alert.setContentText("This is the information. Do something with it");
            alert.showAndWait();
        });
        Button confirmationButton = new Button("Show Confimation Alert");
        confirmationButton.setOnAction(event -> {
            Alert alert = new Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
            alert.setTitleText("This is a confirmation");
            alert.setContentText("This is some confirmation question, you'll have to either accept it or reject it");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent()) {
                ButtonType buttonType = result.get();
                if (buttonType == ButtonType.OK) {
                    System.out.println("OK button pressed!");
                } else if (buttonType == ButtonType.CANCEL) {
                    System.out.println("Cancel button pressed");
                }
            }
        });

        Button regularDialogButton = new Button("Show Regular Dialog");
        regularDialogButton.setOnAction(event -> {
            Dialog dialog = new Dialog();
            dialog.setTitleText("This is a regular dialog");
            dialog.setContent(new Label("Just a regular dialog, plain and simple"));
            Button okButton = new Button("OK");
            okButton.setOnAction(e -> {
                dialog.hide();
            });
            dialog.getButtons().add(okButton);
            dialog.showAndWait();
        });

        Button fullscreenDialogButton = new Button("Show Fullscreen Dialog");
        fullscreenDialogButton.setOnAction(event -> {
            Dialog dialog = new Dialog(true);
            dialog.setTitleText("");
            dialog.setContent(new Label("Some dialog content..."));
            Button saveButton = new Button("SAVE");
            dialog.getButtons().addAll(saveButton);

            dialog.setOnCloseRequest(closeRequestEvent -> {
                Dialog areYouSureDialog = new Dialog(false);
                areYouSureDialog.setContent(new Label("Are you sure you want to discard all changes?"));
                Button yesButton = new Button("DISCARD");
                Button noButton = new Button("KEEP EDITING");
                yesButton.setOnAction(event2 -> {
                    areYouSureDialog.hide();
                });
                noButton.setOnAction(event2 -> {
                    closeRequestEvent.consume();
                    areYouSureDialog.hide();
                });
                areYouSureDialog.getButtons().addAll(yesButton, noButton);
                areYouSureDialog.showAndWait();
            });

            dialog.showAndWait();
        });

        Button exceptionButton = new Button("Show Exception Dialog");
        exceptionButton.setOnAction(event -> {
            ExceptionDialog dialog = new ExceptionDialog();
            NullPointerException nullPointerException = new NullPointerException("Object reference not set to an instance of an object");
            dialog.setException(nullPointerException);
            dialog.showAndWait();
        });

        flowPane.getChildren().addAll(errorButton, warningButton, informationButton, confirmationButton,
                regularDialogButton, fullscreenDialogButton, exceptionButton);

        setCenter(flowPane);

    }

    @Override
    public String toString() {
        return "Dialogs";
    }

    @Override
    public Node getPrimaryGraphic() {
        return MaterialDesignIcon.WEB_ASSET.graphic();
    }
}
