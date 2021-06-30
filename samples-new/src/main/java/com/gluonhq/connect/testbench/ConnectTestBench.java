package com.gluonhq.connect.testbench;

import com.gluonhq.charm.glisten.application.MobileApplicationManager;
import com.gluonhq.charm.glisten.layout.layer.SidePopupView;
import javafx.application.Application;
import javafx.stage.Stage;

public class ConnectTestBench extends Application {

    public static final String GLUONCLOUD_VIEW = MobileApplicationManager.HOME_VIEW;
    public static final String REST_VIEW = "RestView";
    public static final String FILE_VIEW = "FileView";

    public static final String DRAWER_MENU_POPUP = "DrawerMenuPopupView";

    private MobileApplicationManager app = MobileApplicationManager.initialize();

    @Override
    public void start(Stage primaryStage) throws Exception {
        app.start(primaryStage);
    }

    public ConnectTestBench() {
        app.addViewFactory(GLUONCLOUD_VIEW, GluonCloudView::new);
        app.addViewFactory(REST_VIEW, RestView::new);
        app.addViewFactory(FILE_VIEW, FileView::new);

        app.addLayerFactory(DRAWER_MENU_POPUP, () -> new SidePopupView(new DrawerMenuPopupView().getParent()));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
