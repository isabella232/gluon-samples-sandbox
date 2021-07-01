package com.gluonhq.charm.glisten.testbench.samples;

import com.gluonhq.charm.glisten.testbench.Sample;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TabsDemoPanel extends BorderPane implements Sample {

    public TabsDemoPanel() {
        setCenter(new VBox(buildTabs(),buildTabs("inverted")));
    }


    private TabPane buildTabs( String... styles) {
        TabPane tabs = new TabPane();
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabs.getStyleClass().addAll(styles);

        HBox pane = new HBox(10);
        pane.setStyle("-fx-padding: 25;");
        pane.setAlignment(Pos.CENTER);
        for ( Side side: Side.values()) {
            pane.getChildren().add( buildButton(side, tabs));
        }

        tabs.getTabs().addAll( new Tab("Tab 1", pane), new Tab("Tab 2"), new Tab("Tab 3"));
        return tabs;
    }

    private Button buildButton( Side side, TabPane tabs ) {
        Button button = new Button(side.toString());
        button.getStyleClass().add("flat");
        button.setOnAction(e -> tabs.setSide(side));
        return button;
    }

    
    @Override
    public String toString() {
        return "Tabs";
    }

    @Override public Node getPrimaryGraphic() {
        return MaterialDesignIcon.FOLDER_OPEN.graphic();
    }
}
