package com.gluonhq.connect.testbench;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.layout.layer.SidePopupView;

public class ConnectTestBench extends MobileApplication {

    public static final String GLUONCLOUD_VIEW = HOME_VIEW;
    public static final String REST_VIEW = "RestView";
    public static final String FILE_VIEW = "FileView";

    public static final String DRAWER_MENU_POPUP = "DrawerMenuPopupView";

    public ConnectTestBench() {
        addViewFactory(GLUONCLOUD_VIEW, GluonCloudView::new);
        addViewFactory(REST_VIEW, RestView::new);
        addViewFactory(FILE_VIEW, FileView::new);

        addLayerFactory(DRAWER_MENU_POPUP, () -> new SidePopupView(new DrawerMenuPopupView().getParent()));
    }
}
