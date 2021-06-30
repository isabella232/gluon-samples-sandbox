package com.gluonhq.connect.testbench;

import com.gluonhq.charm.glisten.application.MobileApplicationManager;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class FileView extends View {

    public FileView() {
        super();

        createCenter();
    }

    private void createCenter() {
        FXMLLoader loader = new FXMLLoader();
        try {
            Parent parent = loader.load(GluonCloudView.class.getResourceAsStream("file.fxml"));
            setCenter(parent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        super.updateAppBar(appBar);

        appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> MobileApplicationManager.getInstance().showLayer(ConnectTestBench.DRAWER_MENU_POPUP)));
        appBar.setTitleText("File");
    }
}
