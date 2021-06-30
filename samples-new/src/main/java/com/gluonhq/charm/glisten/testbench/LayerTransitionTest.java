/*
 * Copyright (c) 2017, 2018 Gluon - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */
package com.gluonhq.charm.glisten.testbench;

import com.gluonhq.charm.glisten.animation.FadeInUpBigTransition;
import com.gluonhq.charm.glisten.application.GlassPane;
import com.gluonhq.charm.glisten.application.MobileApplicationManager;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.layout.Layer;
import com.gluonhq.charm.glisten.mvc.View;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * Created by pedro_000 on 4/20/2016.
 */
public class LayerTransitionTest extends Application {

    private Button button;
    protected VBox rootNode = new VBox(){
        @Override
        protected double computePrefWidth(double height) {
            return 100;
        }
    };

    private MobileApplicationManager app = MobileApplicationManager.initialize();

    @Override
    public void start(Stage primaryStage) throws Exception {
        app.start(primaryStage);
    }

    @Override
    public void init() {
        rootNode.getChildren().addAll(new Label("label 1"), new Label("label 2"), new Label("label 3"),
                new Label("label 4"));
        Button close = new Button("OK");
        rootNode.getChildren().add(close);
        rootNode.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        Layer layer = new Layer() {
            {
                getChildren().add(rootNode);
                setShowTransitionFactory(layer -> new FadeInUpBigTransition(layer, false));
            }

            @Override public void layoutChildren() {
                GlassPane glassPane = MobileApplicationManager.getInstance().getGlassPane();
                final boolean isShowing = isShowing();

                rootNode.setVisible(isShowing);
                if (!isShowing) {
                    return;
                }

                final double glassPaneWidth = snapSize(glassPane.getWidth());
                final double glassPaneHeight = snapSize(glassPane.getHeight());

                final double dialogPrefWidth = rootNode.prefWidth(-1);

                final double dialogWidth = snapSize(Math.min(glassPaneWidth * .96, dialogPrefWidth));
                final double dialogHeight = snapSize(Math.min(glassPaneHeight * .96, rootNode.prefHeight(dialogWidth)));

                rootNode.resizeRelocate(glassPaneWidth / 2.0 - dialogWidth / 2.0,
                        glassPaneHeight / 2.0 - dialogHeight / 2.0,
                        dialogWidth, dialogHeight);
                super.layoutChildren();
            }

            public void hide() {
                // Prevent dirty region optimization bugs
                rootNode.resize(0,0);
                super.hide();
            }
        };
        app.addViewFactory(MobileApplicationManager.HOME_VIEW, () -> {
            button = new Button("Click");

            close.setOnAction(event -> layer.hide());
            button.setOnAction(event -> layer.show());

            return new View(button) {
                @Override
                protected void updateAppBar(AppBar appBar) {
                    appBar.setTitleText("DialogTransitionTest");
                }
            };
        });

    }
}
