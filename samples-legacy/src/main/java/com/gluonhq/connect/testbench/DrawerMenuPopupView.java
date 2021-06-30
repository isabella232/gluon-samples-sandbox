package com.gluonhq.connect.testbench;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class DrawerMenuPopupView {

    private Parent parent;

    public DrawerMenuPopupView() {
        createNode();
    }

    private void createNode() {
        FXMLLoader loader = new FXMLLoader();
        try {
            this.parent = loader.load(GluonCloudView.class.getResourceAsStream("drawermenu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Parent getParent() {
        return parent;
    }
}
