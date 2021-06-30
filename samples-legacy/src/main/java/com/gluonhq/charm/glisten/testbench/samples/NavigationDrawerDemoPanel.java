package com.gluonhq.charm.glisten.testbench.samples;

import com.gluonhq.charm.glisten.control.Avatar;
import com.gluonhq.charm.glisten.control.NavigationDrawer;
import com.gluonhq.charm.glisten.control.NavigationDrawer.Footer;
import com.gluonhq.charm.glisten.control.NavigationDrawer.Header;
import com.gluonhq.charm.glisten.control.NavigationDrawer.Item;
import com.gluonhq.charm.glisten.testbench.Sample;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class NavigationDrawerDemoPanel extends BorderPane implements Sample {

    public NavigationDrawerDemoPanel() {

        // Create Header
        Header header = new Header("Abhinay Agarwal",
                "abhinay.agarwal@gluonhq.com",
                new Avatar(42)); //, new Image("com/gluonhq/charm/glisten/G_Blue_500_58x58.png")));

        // Create List Items
        final Node footer = new Footer("Settings", MaterialDesignIcon.SETTINGS.graphic());

        ObservableList<Node> items = FXCollections.observableArrayList(
                header,
                new Separator(Orientation.HORIZONTAL),
                new Item("Inbox", MaterialDesignIcon.INBOX.graphic()),
                new Item("Starred", MaterialDesignIcon.STAR.graphic()),
                new Item("Sent Mail", MaterialDesignIcon.SEND.graphic()),
                new Item("Drafts", MaterialDesignIcon.DRAFTS.graphic()),
                new Separator(Orientation.HORIZONTAL),
                new Item("SubHeader"),
                new Item("All Mail", MaterialDesignIcon.MAIL.graphic()),
                new Item("Trash", MaterialDesignIcon.DELETE.graphic()),
                new Item("Spam", MaterialDesignIcon.DO_NOT_DISTURB_ALT.graphic()),
                new Separator(Orientation.HORIZONTAL));


        NavigationDrawer navigationDrawer = new NavigationDrawer();
        //navigationDrawer.setHeader(header);
        navigationDrawer.getItems().setAll(items);
        navigationDrawer.setFooter(footer);
        setCenter(navigationDrawer);
    }

    @Override
    public String toString() {
        return "NavigationDrawer";
    }

    @Override
    public Node getPrimaryGraphic() {
        return MaterialDesignIcon.NAVIGATION.graphic();
    }
}

