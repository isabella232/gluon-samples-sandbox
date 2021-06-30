package com.gluonhq.charm.glisten.testbench.samples;

import com.gluonhq.charm.glisten.testbench.Sample;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import com.gluonhq.charm.glisten.control.DatePicker;
import com.gluonhq.charm.glisten.control.TimePicker;
import java.util.Locale;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;


public class DateTimePickerDemoPanel extends BorderPane implements Sample {

    public DateTimePickerDemoPanel() {
        Locale.setDefault(Locale.US);
        Button btnDate = new Button("Show DatePicker", MaterialDesignIcon.DATE_RANGE.graphic());
        btnDate.setOnAction(e -> new DatePicker().showAndWait().ifPresent(System.out::println));
        
        Button btnTimer = new Button("Show TimePicker", MaterialDesignIcon.TIMER.graphic());
        btnTimer.setOnAction(e -> new TimePicker().showAndWait().ifPresent(System.out::println));
        
        final VBox vBox = new VBox(40, btnDate, btnTimer);
        vBox.setAlignment(Pos.CENTER);
        
        setCenter(vBox);
    }
    
    
    @Override
    public String toString() {
        return "Date and Time Picker";
    }

    @Override public Node getPrimaryGraphic() {
        return MaterialDesignIcon.DATE_RANGE.graphic();
    }
}