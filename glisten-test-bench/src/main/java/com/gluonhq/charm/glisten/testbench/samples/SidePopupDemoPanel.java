package com.gluonhq.charm.glisten.testbench.samples;

import com.gluonhq.charm.glisten.application.MobileApplicationManager;
import com.gluonhq.charm.glisten.layout.layer.SidePopupView;
import com.gluonhq.charm.glisten.testbench.Sample;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import static com.gluonhq.charm.glisten.visual.GlistenStyleClasses.LIGHT;
import static com.gluonhq.charm.glisten.visual.GlistenStyleClasses.applyStyleClass;

public class SidePopupDemoPanel extends BorderPane implements Sample {

    private ListView<String> listView = new ListView<>( FXCollections.observableArrayList("Row 1", "Row 2", "Row 3"));
    private SidePopupView sideView = new SidePopupView(listView);
    
    public SidePopupDemoPanel() {
        
        listView.setStyle("-fx-padding: 1;");
        listView.setPrefHeight(listView.getItems().size() * 24 );
        listView.setPrefHeight(300);
        listView.setOnMouseClicked( e -> {
            sideView.hide();
        });

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        for( Side side: Side.values()) {
            
            Button button = new Button("Show " + side + " Side Popup ");
            button.getStyleClass().addAll("light");
            applyStyleClass(button, LIGHT);
            button.setOnAction(e -> {
                sideView.setSide(side);
                sideView.show();
            });
            
            vbox.getChildren().add(button);
        }

        Button button = new Button("Show Snackbar");
        applyStyleClass(button, LIGHT);
        button.setOnAction(e -> {
            MobileApplicationManager.getInstance().showMessage("This is snackbar!\nThis is snackbar!", "DISMISS", evtHandler -> {
                System.out.println("Dismiss clicked");
            });
            MobileApplicationManager.getInstance().showMessage("And another message!");
        });
        vbox.getChildren().add(button);

        setCenter(vbox);
    }

    @Override
    public String toString() {
        return "Side Popup";
    }

    @Override public Node getPrimaryGraphic() {
        return MaterialDesignIcon.SLIDESHOW.graphic();
    }
}
