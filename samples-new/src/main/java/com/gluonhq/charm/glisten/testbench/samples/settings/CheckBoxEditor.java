package com.gluonhq.charm.glisten.testbench.samples.settings;

import com.gluonhq.charm.glisten.control.settings.Option;
import com.gluonhq.charm.glisten.control.settings.OptionEditor;
import javafx.beans.property.Property;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;

public class CheckBoxEditor implements OptionEditor<Boolean> {

    private final CheckBox checkBox;

    public CheckBoxEditor(Option<Boolean> option) {
        this.checkBox = new CheckBox();
        valueProperty().bindBidirectional(option.valueProperty());
    }
    
    @Override
    public Node getEditor() {
        return checkBox;
    }

    @Override
    public final Property<Boolean> valueProperty() {
        return checkBox.selectedProperty();
    }

    @Override
    public Boolean getValue() {
        return checkBox.isSelected();
    }

    @Override
    public void setValue(Boolean value) {
        checkBox.setSelected(value);
    }

    
}
