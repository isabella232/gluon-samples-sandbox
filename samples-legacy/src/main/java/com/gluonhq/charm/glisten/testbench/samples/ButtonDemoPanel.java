package com.gluonhq.charm.glisten.testbench.samples;

import com.gluonhq.charm.glisten.control.Dialog;
import com.gluonhq.charm.glisten.testbench.Sample;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import static com.gluonhq.charm.glisten.visual.GlistenStyleClasses.*;

public class ButtonDemoPanel extends BorderPane implements Sample {

    private CheckBox cbEnabled = new CheckBox("Disabled");
    
    private FlowPane buttonPanel;
    private Control[] controls;
    
    private Dialog<String> dialog = new Dialog<>("Dialog title", "This first dialog in Glisten");
    
    public ButtonDemoPanel() {

       VBox demo = new VBox(10);
       demo.setPadding(new Insets(10));

       ToggleButton switchButton = new ToggleButton("SWITCH");
       applyStyleClass(switchButton, TOGGLE_BUTTON_SWITCH);

       Slider slider = new Slider();

       controls = new Control[]{
                    createButton("RAISED"),
                    createButton("FLAT", BUTTON_FLAT), 
                    createButton(MaterialDesignIcon.SEARCH.text, BUTTON_ROUND, ICON_TEXT),
                    new CheckBox("CheckBox"),
                    new RadioButton("RadioButton"),
                    switchButton,
                    slider
                };
       
        for ( Control btn: controls ) {
           btn.disableProperty().bindBidirectional(cbEnabled.selectedProperty());
        }
       
        buttonPanel = new FlowPane(10,5, controls );
        buttonPanel.setPadding(new Insets(10));
        
        applyStyleClass(cbEnabled, LIGHT);
            
        demo.getChildren().addAll(buttonPanel, cbEnabled);

        setCenter(demo);

        final Button btn = new Button("CLOSE");
        btn.setOnAction( e -> dialog.hide());
        dialog.getButtons().add(btn);

    }
    

    @Override
    public String toString() {
        return "Buttons";
    }

    
    private Button createButton( String text, String... classes) {
        Button button = new Button(text); 
        applyStyleClass(button, classes);
        button.setOnAction(e -> dialog.showAndWait());
        return button;
    }
    
    @Override public Node getPrimaryGraphic() {
        return MaterialDesignIcon.ADD_BOX.graphic();
    }
}
