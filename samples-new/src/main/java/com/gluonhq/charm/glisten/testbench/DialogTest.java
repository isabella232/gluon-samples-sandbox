package com.gluonhq.charm.glisten.testbench;

import com.gluonhq.charm.glisten.application.MobileApplicationManager;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.Dialog;
import com.gluonhq.charm.glisten.mvc.View;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Created by pedro_000 on 4/27/2016.
 */
public class DialogTest extends Application {
    private Button button;

    private MobileApplicationManager app = MobileApplicationManager.initialize();

    @Override
    public void start(Stage primaryStage) throws Exception {
        app.start(primaryStage);
    }

    @Override
    public void init() {
        app.addViewFactory(MobileApplicationManager.HOME_VIEW, () -> {
            button = new Button("Click");
            Dialog dialog = new Dialog("Information Alert", "This is an Alert. \n Secondary View");
            button.setOnAction(event -> dialog.showAndWait());
            return new View(button) {
                @Override
                protected void updateAppBar(AppBar appBar) {
                    appBar.setTitleText("DialogTest");
                }
            };
        });

    }
}

