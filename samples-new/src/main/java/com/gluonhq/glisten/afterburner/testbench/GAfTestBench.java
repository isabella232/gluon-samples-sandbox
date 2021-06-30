package com.gluonhq.glisten.afterburner.testbench;

import com.gluonhq.charm.glisten.application.MobileApplicationManager;
import com.gluonhq.charm.glisten.visual.Swatch;
import com.gluonhq.glisten.afterburner.testbench.views.AppViewManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GAfTestBench extends Application {

    private MobileApplicationManager app = MobileApplicationManager.initialize(this::postInit);

    @Override
    public void start(Stage primaryStage) throws Exception {
        app.start(primaryStage);
    }

    @Override
    public void init() {
        AppViewManager.registerViewsAndDrawer();
    }
    
    public void postInit(Scene scene) {
        Swatch.BLUE.assignTo(scene);

        ((Stage) scene.getWindow()).getIcons().add(new Image(GAfTestBench.class.getResourceAsStream("/icon.png")));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
