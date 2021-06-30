/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gluonhq.charm.glisten.testbench.samples.settings;

import com.gluonhq.charm.glisten.control.settings.Option;
import com.gluonhq.charm.glisten.control.settings.OptionEditor;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import java.util.Collection;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class ComboBoxEditor implements OptionEditor<String>{
    
    private final ComboBox<String> comboBox;

    private StringProperty selectedItemProperty;
    
    public ComboBoxEditor(Option<String> option, final Collection<String> items) {
        comboBox = new ComboBox<>(FXCollections.observableArrayList(items));
        
        comboBox.setCellFactory((ListView<String> param) -> {
            return new ListCell<String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null && !empty) {
                        setGraphic(MaterialDesignIcon.valueOf(item).graphic());
                    } else {
                        setGraphic(null);
                    } 
                }
                
            };
        });
        comboBox.setButtonCell(new ListCell<String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null && !empty) {
                        setGraphic(MaterialDesignIcon.valueOf(item).graphic());
                    } else {
                        setGraphic(null);
                    } 
                }
                
            });
        comboBox.setEditable(false);
        
        selectedItemProperty = new SimpleStringProperty();
                
        comboBox.getSelectionModel().selectedItemProperty().addListener(
                (obs, ov, nv) -> selectedItemProperty.set(nv));
        // listener to external changes in option.valueProperty(), 
        // so thouse are reflected back to the ComboBox 
        selectedItemProperty.addListener((obs, ov, nv) -> setValue(nv));
        
        selectedItemProperty.bindBidirectional(option.valueProperty());
    }
    
    @Override
    public Node getEditor() {
        return comboBox;
    }

    @Override
    public Property<String> valueProperty() {
        return selectedItemProperty;
    }

    @Override
    public String getValue() {
        return comboBox.getSelectionModel().getSelectedItem();
    }

    @Override
    public void setValue(String value) {
        comboBox.getSelectionModel().select(value);
    }
    
}
