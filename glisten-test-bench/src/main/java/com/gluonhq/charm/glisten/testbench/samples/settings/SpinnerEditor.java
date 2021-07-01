package com.gluonhq.charm.glisten.testbench.samples.settings;

import com.gluonhq.charm.glisten.control.settings.OptionEditor;
import javafx.beans.property.Property;
import javafx.scene.Node;
import javafx.scene.control.Spinner;
import com.gluonhq.charm.glisten.control.settings.Option;
import javafx.scene.control.SpinnerValueFactory;

public class SpinnerEditor<T> implements OptionEditor<T> {
        
    private final Spinner<T> spinner;

    public SpinnerEditor(Option<T> option, SpinnerValueFactory spinnerValueFactory) {
        spinner = new Spinner(spinnerValueFactory);
        option.valueProperty().addListener((obs, ov, nv) -> {
            setValue(option.valueProperty().getValue());
        });
        spinner.valueProperty().addListener((obs, ov, nv) -> {
            option.valueProperty().setValue(getValue());
        });
    }

    @Override
    public Node getEditor() {
        return spinner; 
    }

    @Override
    public T getValue() {
        return spinner.getValue();
    }

    @Override
    public void setValue(T value) {
        spinner.getValueFactory().setValue(value);
    }

    @Override
    public Property<T> valueProperty() {
        return (Property<T>) spinner.valueProperty();
    }

}

