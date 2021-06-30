package com.gluonhq.charm.glisten.testbench;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.Dialog;
import com.gluonhq.charm.glisten.layout.layer.PopupView;
import com.gluonhq.charm.glisten.mvc.View;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Created by pedro_000 on 4/27/2016.
 */
public class DialogTest extends MobileApplication {
    private Button button;

    @Override
    public void init() {
        addViewFactory(HOME_VIEW, () -> {
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

