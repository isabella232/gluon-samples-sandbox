/*
 * Copyright (c) 2016 Gluon - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */
package com.gluonhq.charm.glisten.testbench;

import com.gluonhq.charm.glisten.application.MobileApplicationManager;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.layout.layer.PopupView;
import com.gluonhq.charm.glisten.mvc.View;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PopupTest extends Application {
    private Button button;
    private PopupView popupView;

    private MobileApplicationManager app = MobileApplicationManager.initialize();

    @Override
    public void start(Stage primaryStage) throws Exception {
        app.start(primaryStage);
    }

    @Override
    public void init() {
        app.addViewFactory(MobileApplicationManager.HOME_VIEW, () -> {
            button = new Button("Click");
            button.setOnAction(event -> popupView.show());
            popupView = new PopupView(button);
            VBox vBox = new VBox();
            vBox.getChildren().addAll(new Label("Choice 1"), new Label("Choice 2"), new Label("Choice 3"));
            vBox.setSpacing(5);
            popupView.setContent(vBox);
            return new View(button) {
                @Override
                protected void updateAppBar(AppBar appBar) {
                    appBar.setTitleText("PopupView");
                }
            };
        });

    }
}
