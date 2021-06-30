package com.gluonhq.charm.glisten.testbench.samples;

import com.gluonhq.charm.glisten.control.CardPane;
import com.gluonhq.charm.glisten.testbench.Sample;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class CardPaneDemoPanel extends BorderPane implements Sample {

    private CardPane cardPane = new CardPane();

    public CardPaneDemoPanel() {
        setCenter( cardPane );
        cardPane.getItems().addAll( label("Card 1"), label("Card 2"), label("Card 3"));
    }

    private Label label(String caption ) {
        Label label = new Label( caption );
        label.setStyle("-fx-padding:10;");
        return label;
    }


    @Override
    public String toString() {
        return "Cards";
    }

    @Override public Node getPrimaryGraphic() {
        return MaterialDesignIcon.VIEW_AGENDA.graphic();
    }
}