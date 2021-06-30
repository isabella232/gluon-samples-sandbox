/*
 * Copyright (c) 2016 Gluon - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */
package com.gluonhq.charm.glisten.testbench.samples;

import com.gluonhq.charm.glisten.control.Chip;
import com.gluonhq.charm.glisten.control.ExpansionPanel;
import com.gluonhq.charm.glisten.control.ExpansionPanelContainer;
import com.gluonhq.charm.glisten.testbench.Sample;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ExpansionPanelDemoPanel extends StackPane implements Sample {

    public ExpansionPanelDemoPanel() {
        ExpansionPanelContainer container = new ExpansionPanelContainer();

        // First ExpansionPanel
        ExpansionPanel expansionPanel = new ExpansionPanel();
        ExpansionPanel.CollapsedPanel collapsedPanel = new ExpansionPanel.CollapsedPanel();
        collapsedPanel.getTitleNodes().addAll(new Label("Trip name"), new Label("Caribbean cruise"));
        expansionPanel.setCollapsedContent(collapsedPanel);

        GridPane content = new GridPane();
        content.setPadding(new Insets(10));
        for (int i = 0; i < 3; ++i) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(33);
            content.getColumnConstraints().add(columnConstraints);
        }
        content.getRowConstraints().add(new RowConstraints());
        content.getRowConstraints().add(new RowConstraints(50));
        content.add(new Label("Trip name"), 0, 0);
        Label centerText = new Label("Select trip");
        centerText.setStyle("-fx-text-fill: #828282;");
        content.add(centerText, 1, 0);
        Chip chip = new Chip("Barbados");
        chip.setDeletable(true);
        content.add(chip, 1, 1);
        HBox hBox = new HBox();
        Separator separator = new Separator(Orientation.VERTICAL);
        Label label = new Label("Select your destination of choice");
        label.setWrapText(true);
        label.setStyle("-fx-font-size: 0.666667em; -fx-text-fill: #8a8a8a;");
        hBox.getChildren().addAll(separator, label);
        content.add(hBox, 2, 1);
        ExpansionPanel.ExpandedPanel expandedPanel = new ExpansionPanel.ExpandedPanel();
        expandedPanel.setContent(content);
        Button saveButton = new Button("SAVE");
        Button cancelButton = new Button("CANCEL");
        expandedPanel.getButtons().addAll(cancelButton, saveButton);

        expansionPanel.setExpandedContent(expandedPanel);

        container.getItems().add(expansionPanel);

        // Second ExpansionPanel
        expansionPanel = new ExpansionPanel();
        collapsedPanel = new ExpansionPanel.CollapsedPanel();
        collapsedPanel.getTitleNodes().addAll(new Label("Location"), new Label("Barbados"));
        expansionPanel.setCollapsedContent(collapsedPanel);

        content = new GridPane();
        content.setPadding(new Insets(10));
        for (int i = 0; i < 3; ++i) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(33);
            content.getColumnConstraints().add(columnConstraints);
        }
        content.getRowConstraints().add(new RowConstraints());
        content.getRowConstraints().add(new RowConstraints(50));
        content.add(new Label("Location"), 0, 0);
        centerText = new Label("Select trip");
        centerText.setStyle("-fx-text-fill: #828282;");
        content.add(centerText, 1, 0);
        chip = new Chip("Barbados");
        chip.setDeletable(true);

        content.add(chip, 1, 1);
        hBox = new HBox();
        separator = new Separator(Orientation.VERTICAL);
        label = new Label("Select your destination of choice");
        label.setWrapText(true);
        label.setStyle("-fx-font-size: 0.666667em; -fx-text-fill: #8a8a8a;");
        hBox.getChildren().addAll(separator, label);
        content.add(hBox, 2, 1);
        expandedPanel = new ExpansionPanel.ExpandedPanel();
        expandedPanel.setContent(content);
        saveButton = new Button("SAVE");
        cancelButton = new Button("CANCEL");
        expandedPanel.getButtons().addAll(cancelButton, saveButton);

        expansionPanel.setExpandedContent(expandedPanel);

        container.getItems().add(expansionPanel);

        // Third ExpansionPanel
        expansionPanel = new ExpansionPanel();
        collapsedPanel = new ExpansionPanel.CollapsedPanel();
        collapsedPanel.getTitleNodes().addAll(new Label("Carrier"), new Label("The best cruise line"));
        expansionPanel.setCollapsedContent(collapsedPanel);

        content = new GridPane();
        content.setPadding(new Insets(10));
        for (int i = 0; i < 3; ++i) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(33);
            content.getColumnConstraints().add(columnConstraints);
        }
        content.getRowConstraints().add(new RowConstraints());
        content.getRowConstraints().add(new RowConstraints(50));
        content.add(new Label("Carrier"), 0, 0);
        centerText = new Label("Select trip");
        centerText.setStyle("-fx-text-fill: #828282;");
        content.add(centerText, 1, 0);
        chip = new Chip("Barbados");
        chip.setDeletable(true);
        content.add(chip, 1, 1);
        hBox = new HBox();
        separator = new Separator(Orientation.VERTICAL);
        label = new Label("Select your destination of choice");
        label.setWrapText(true);
        label.setStyle("-fx-font-size: 0.666667em; -fx-text-fill: #8a8a8a;");
        hBox.getChildren().addAll(separator, label);
        content.add(hBox, 2, 1);
        expandedPanel = new ExpansionPanel.ExpandedPanel();
        expandedPanel.setContent(content);
        saveButton = new Button("SAVE");
        cancelButton = new Button("CANCEL");
        expandedPanel.getButtons().addAll(cancelButton, saveButton);

        expansionPanel.setExpandedContent(expandedPanel);

        container.getItems().add(expansionPanel);
        getChildren().add(container);
    }

    @Override
    public String toString() {
        return "ExpansionPanel";
    }

    @Override
    public Node getPrimaryGraphic() {
        return MaterialDesignIcon.DNS.graphic();
    }



}
