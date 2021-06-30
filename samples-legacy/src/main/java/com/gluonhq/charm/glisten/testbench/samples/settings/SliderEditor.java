package com.gluonhq.charm.glisten.testbench.samples.settings;

import com.gluonhq.charm.glisten.control.settings.OptionEditor;
import javafx.beans.property.Property;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import com.gluonhq.charm.glisten.control.settings.Option;

public class SliderEditor implements OptionEditor<Number> {
        
    private final Slider slider;

    public SliderEditor(Option<Number> option, int min, int max) {
        slider = new Slider(min, max, option.valueProperty().getValue().doubleValue());
        valueProperty().bindBidirectional(option.valueProperty());
    }

    public SliderEditor(Option<Number> option) {
        this(option, 0.0, 100.0);
    }
    
    public SliderEditor(Option<Number> option, double min, double max) {
        slider = new Slider(min, max, option.valueProperty().getValue().doubleValue());
        valueProperty().bindBidirectional(option.valueProperty());
    }
    @Override
    public Node getEditor() {
        return slider; 
    }

    @Override
    public Number getValue() {
        return slider.getValue();
    }

    @Override
    public void setValue(Number value) {
        slider.setValue(value.doubleValue());
    }

    @Override
    public final Property<Number> valueProperty() {
        return (Property<Number>) slider.valueProperty();
    }
}

