package com.gluonhq.connect.testbench;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.ListTile;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

public class DrawerMenuPopupPresenter {

    private ObservableList<DrawerMenuItem> menuItems = FXCollections.observableArrayList(
            new DrawerMenuItem(MaterialDesignIcon.CLOUD, "Gluon Cloud", ConnectTestBench.GLUONCLOUD_VIEW),
            new DrawerMenuItem(MaterialDesignIcon.WEB, "REST", ConnectTestBench.REST_VIEW),
            new DrawerMenuItem(MaterialDesignIcon.ATTACH_FILE, "File", ConnectTestBench.FILE_VIEW)
    );

    @FXML
    private ListView<DrawerMenuItem> drawerMenu;

    public void initialize() {
        drawerMenu.setCellFactory(lv -> new DrawerMenuItemCell());
        drawerMenu.setItems(menuItems);
    }

    private static class DrawerMenuItemCell extends ListCell<DrawerMenuItem> {

        ListTile tile = new ListTile();

        @Override
        protected void updateItem(DrawerMenuItem item, boolean empty) {
            super.updateItem(item, empty);
            setText(null);
            if (empty) {
                setGraphic(null);
            } else {
                tile.textProperty().setAll(item.caption);
                tile.setPrimaryGraphic(item.icon.graphic());
                tile.setOnMouseClicked( e -> item.select() );
                setGraphic(tile);
            }
        }
    }

    private static class DrawerMenuItem {

        public final MaterialDesignIcon icon;
        public final String caption;
        public final String viewId;

        public DrawerMenuItem(MaterialDesignIcon icon, String caption, String viewId) {
            this.icon = icon;
            this.caption = caption;
            this.viewId = viewId;
        }

        public void select() {
            MobileApplication.getInstance().hideLayer(ConnectTestBench.DRAWER_MENU_POPUP);
            MobileApplication.getInstance().switchView(viewId);
        }
    }
}
