/*
 * Copyright (c) 2016 Gluon - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */
package com.gluonhq.charm.glisten.testbench.samples;

//import com.gluonhq.impl.charm.glisten.control.RefreshIndicator;
import com.gluonhq.charm.glisten.testbench.Sample;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * To run it please follow the steps :
 *  - Uncomment the commented lines, including the import
 *  - Make an entry for RefreshIndicatorDemoPanel in ControlTestBench
 *  - Run the code with proguard disabled
 */
public class RefreshIndicatorDemoPanel extends BorderPane implements Sample {

    public RefreshIndicatorDemoPanel() {

        VBox box = new VBox(20);
//        RefreshIndicator refreshIndicator = new RefreshIndicator();

        Button button = new Button("Release");
        button.setVisible(false);
        button.setOnAction(event -> {
//            refreshIndicator.setMode(RefreshIndicator.Mode.REFRESHING);
        });

        Slider slider = new Slider(0.0, 2.0, 0.0);
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
//            refreshIndicator.setPullProgress(newValue.doubleValue());
            if(newValue.doubleValue() >= 1.0) {
                button.setVisible(true);
            } else if (button.isVisible()) {
//                refreshIndicator.setMode(RefreshIndicator.Mode.PULLING);
                button.setVisible(false);
            }
        });

//        box.getChildren().addAll(refreshIndicator, slider, button );
        box.setAlignment(Pos.CENTER);
        setCenter(box);
    }

    @Override
    public String toString() {
        return "RefreshIndicator";
    }

    @Override
    public Node getPrimaryGraphic() {
        return MaterialDesignIcon.REFRESH.graphic();
    }
}