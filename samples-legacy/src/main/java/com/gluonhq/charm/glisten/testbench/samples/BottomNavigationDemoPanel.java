package com.gluonhq.charm.glisten.testbench.samples;

import com.gluonhq.charm.glisten.control.BottomNavigation;
import com.gluonhq.charm.glisten.control.BottomNavigationButton;
import com.gluonhq.charm.glisten.control.Icon;
import com.gluonhq.charm.glisten.testbench.Sample;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class BottomNavigationDemoPanel extends BorderPane implements Sample {

    public BottomNavigationDemoPanel() {
        Label title = new Label("Something");
        BottomNavigation bottomNavigation = new BottomNavigation(BottomNavigation.Type.FIXED);
        BottomNavigationButton defaultItem = new BottomNavigationButton("Recent", MaterialDesignIcon.RECENT_ACTORS.graphic());
        defaultItem.setOnAction(e -> title.setText("Recent"));
        BottomNavigationButton nearby = new BottomNavigationButton("Nearby", new Icon(MaterialDesignIcon.NEAR_ME));
        nearby.setOnAction(e -> title.setText("Nearby"));
        BottomNavigationButton music = new BottomNavigationButton("Music", new Icon(MaterialDesignIcon.MUSIC_NOTE));
        music.setOnAction(e -> title.setText("Music"));
        bottomNavigation.getActionItems().addAll(
                defaultItem,
                new BottomNavigationButton("Favourite", MaterialDesignIcon.FAVORITE.graphic(), e -> title.setText("Favourite")),
                nearby,
                music);

        final CheckBox shiftingCheckBox = new CheckBox("Shifting");
        shiftingCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                bottomNavigation.setType(BottomNavigation.Type.SHIFTING);
            } else {
                bottomNavigation.setType(BottomNavigation.Type.FIXED);
            }
        });

        final VBox root = new VBox(10, title, shiftingCheckBox);
        root.setAlignment(Pos.CENTER);
        setCenter(root);
        setBottom(bottomNavigation);
    }

    @Override
    public String toString() {
        return "BottomNavigation";
    }

    @Override
    public Node getPrimaryGraphic() {
        return MaterialDesignIcon.BORDER_BOTTOM.graphic();
    }
}