package com.gluonhq.charm.glisten.testbench.samples;

import com.gluonhq.charm.glisten.control.DropdownButton;
import com.gluonhq.charm.glisten.testbench.Sample;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class DropdownButtonDemoPanel extends BorderPane implements Sample {

    public DropdownButtonDemoPanel(){
        DropdownButton dropdownButton = new DropdownButton();
        MenuItem menuItem = new MenuItem("Choice 1");
        dropdownButton.getItems().addAll(menuItem, new MenuItem("Choice 2"), new MenuItem("Choice 3"), new MenuItem("Choice 4"));
        dropdownButton.setSelectedItem(menuItem);
        setCenter(dropdownButton);

    }

    @Override
    public Node getPrimaryGraphic() {
        return MaterialDesignIcon.ARROW_DROP_DOWN.graphic();
    }

    @Override
    public String toString() {
        return "Dropdown Button";
    }

}
