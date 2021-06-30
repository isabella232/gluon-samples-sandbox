package com.gluonhq.charm.glisten.testbench.samples;

import com.gluonhq.charm.glisten.testbench.Sample;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.gluonhq.charm.glisten.visual.Swatch;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;


public class SwatchDemoPanel extends BorderPane implements Sample {

    public SwatchDemoPanel() {
        
        setPadding(new Insets(5));

        VBox swatchChoicePane = new VBox(5);
        swatchChoicePane.setPadding(new Insets(5));

        GridPane.setHgrow(swatchChoicePane, Priority.ALWAYS);
        for( Swatch swatch: Swatch.values()) {
            Button button = new Button(swatch.name().replace('_', ' '));
            button.setMaxWidth(Double.MAX_VALUE);
            button.setOnAction( e -> swatch.assignTo(getScene()));
            swatchChoicePane.getChildren().add(button);
        }

        setLeft(makeScrollable(swatchChoicePane));


        VBox swatchBox = new VBox();
        swatchBox.setPadding(new Insets(5));
        Label label = new Label("Swatch Sample:");
        swatchBox.getChildren().add(label);

        int[] colors = { 50, 100, 200, 300, 400, 500, 600, 700, 800, 900 };
        for (int color : colors) {
            swatchBox.getChildren().add(createRegion("-primary-swatch-" + color));
        }
        label = new Label("Alternate Colors:");
        label.setPadding(new Insets(10,5,5,5));
        swatchBox.getChildren().add(label);

        int[] altColors = { 100, 200, 400, 700 };
        for (int color : altColors) {
            swatchBox.getChildren().add(createRegion("-alternate-swatch-" + color));
        }

        setCenter(makeScrollable(swatchBox));

    }

    @Override public String toString() {
        return "Swatches";
    }

    @Override
    public Node getPrimaryGraphic() {
        return MaterialDesignIcon.COLOR_LENS.graphic();
    }


    private ScrollPane makeScrollable( Node node ) {
        ScrollPane scroller = new ScrollPane(node);
        scroller.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroller.setFitToWidth(true);
        return scroller;
    }

    private Region createRegion(String backgroundColor) {
        Label r = new Label();
        r.setText(backgroundColor);
        r.setStyle("bkg: " + backgroundColor + "; -fx-padding: 0 0 0 10; -fx-background-color: bkg;"
                 + "-fx-text-fill: ladder(bkg, white 49%, black 50%);");
        r.setMinSize(100, 30);
        r.setMaxWidth(Double.MAX_VALUE);
        return r;
    }


}
