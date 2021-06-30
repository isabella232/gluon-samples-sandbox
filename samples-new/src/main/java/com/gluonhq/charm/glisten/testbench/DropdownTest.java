package com.gluonhq.charm.glisten.testbench;

import com.gluonhq.charm.glisten.application.MobileApplicationManager;
import com.gluonhq.charm.glisten.control.Dialog;
import com.gluonhq.charm.glisten.control.DropdownButton;
import com.gluonhq.charm.glisten.mvc.View;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class DropdownTest extends Application {

    private MobileApplicationManager app = MobileApplicationManager.initialize();

    @Override
    public void start(Stage primaryStage) throws Exception {
        app.start(primaryStage);
    }

    @Override
    public void init() {
        app.hideAllLayers(true);
        app.addViewFactory(MobileApplicationManager.HOME_VIEW, BasicView::new);
    }

    public Node createUI() {
        var dialog = new Dialog<Void>();
        var choices = new DropdownButton();
        choices.getItems().addAll(new MenuItem("0"), new MenuItem("1"), new MenuItem("2"), new MenuItem("3"));
        dialog.setGraphic(choices);

        var show = new Button("Show dialog");
        show.setOnAction(e -> dialog.showAndWait());
        return show;
    }

    public static void main(String[] args) {
        launch(args);
    }

    class BasicView extends View {
        public BasicView() {
            setCenter(createUI());
        }
    }
}
