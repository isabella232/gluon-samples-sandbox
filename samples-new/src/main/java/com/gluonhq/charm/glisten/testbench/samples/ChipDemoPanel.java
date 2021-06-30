package com.gluonhq.charm.glisten.testbench.samples;

import com.gluonhq.charm.glisten.control.Chip;
import com.gluonhq.charm.glisten.testbench.Sample;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

public class ChipDemoPanel extends BorderPane implements Sample {

    public ChipDemoPanel() {
        FlowPane flowPane = new FlowPane(10, 10);
        flowPane.setPadding(new Insets(20, 5, 0, 5));
        flowPane.setVgap(20);

        Chip chip = new Chip("Some text");
        chip.setDeletable(true);
        flowPane.getChildren().add(chip);

        chip = new Chip("Some text");
        flowPane.getChildren().add(chip);

        chip = new Chip("Some text");
        Image image = new Image(ChipDemoPanel.class.getResource("graphic.png").toExternalForm());
        chip.setGraphic(image);
        flowPane.getChildren().add(chip);

        chip = new Chip("Some text");
        chip.setDeletable(true);
        chip.setGraphic(image);
        flowPane.getChildren().add(chip);

        chip.setOnDeleteAction(event -> {
            System.out.println("Chip delete pressed");
        });

        chip = new Chip("Some text");
        chip.setStyle("-fx-graphic: url(/com/gluonhq/charm/glisten/testbench/samples/graphic2.jpg);");
        flowPane.getChildren().add(chip);

        setCenter(flowPane);
    }

    @Override
    public String toString() {
        return "Chips";
    }

    @Override
    public Node getPrimaryGraphic() {
        return MaterialDesignIcon.ACCOUNT_CIRCLE.graphic();
    }
}
