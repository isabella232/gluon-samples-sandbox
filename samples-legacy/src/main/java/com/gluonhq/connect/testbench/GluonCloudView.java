package com.gluonhq.connect.testbench;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class GluonCloudView extends View {

    public GluonCloudView() {
        super();

        createCenter();
    }

    private void createCenter() {
        FXMLLoader loader = new FXMLLoader(GluonCloudView.class.getResource("gluoncloud.fxml"));
        try {
            Parent parent = loader.load();
            setCenter(parent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        super.updateAppBar(appBar);

        appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> MobileApplication.getInstance().showLayer(ConnectTestBench.DRAWER_MENU_POPUP)));
        appBar.setTitleText("GluonCloud");
    }
}
