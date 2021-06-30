package com.gluonhq.charm.glisten.testbench.samples;

import com.gluonhq.charm.glisten.layout.layer.MenuPopupView;
import com.gluonhq.charm.glisten.layout.layer.MenuSidePopupView;
import com.gluonhq.charm.glisten.testbench.Sample;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;

public class MenuDemoPanel extends BorderPane implements Sample {

    private FlowPane buttonPanel;

    public MenuDemoPanel() {
        buttonPanel = new FlowPane(10,5);
        buttonPanel.setPadding(new Insets(10));
        buttonPanel.setMaxHeight(Double.MAX_VALUE);
        buttonPanel.setAlignment(Pos.CENTER);
        
        buildDemo1();
        buildDemo2();
        buildDemo3();

        StackPane demo = new StackPane(buttonPanel);
        demo.setAlignment(Pos.CENTER);
        setCenter(demo);
    }
    
    private void buildDemo1() {
        // create the menu we will use
        final String[] menuItems = { "Row 1", "Row 2", "Row 3" };
        final Menu menu = new Menu();
        for (String menuItemText : menuItems) {
            MenuItem menuItem = new MenuItem(menuItemText);
            menuItem.setOnAction(e -> System.out.println(menuItemText + " was clicked!"));
            menu.getItems().add(menuItem);
        }
        
        // demo one is a menu that appears where the button is located
        Button inPlaceMenuBtn = new Button("Show Menu");
        final MenuPopupView menuPopupView = new MenuPopupView(inPlaceMenuBtn, menu);
        inPlaceMenuBtn.setOnAction(e -> menuPopupView.show());
        
        buttonPanel.getChildren().add(inPlaceMenuBtn);
    }

    private void buildDemo2() {
        // create the menu we will use
        final String[] menuItems = { "Row 1", "Row 2", "Row 3" };
        final Menu menu = new Menu();
        for (String menuItemText : menuItems) {
            MenuItem menuItem = new MenuItem(menuItemText);
            menuItem.setOnAction(e -> System.out.println(menuItemText + " was clicked!"));
            menu.getItems().add(menuItem);
        }
        
        // demo two is a menu that appears from the bottom of the screen
        Button sideMenuBtn = new Button("Show Side Menu");
        final MenuSidePopupView menuSidePopupView = new MenuSidePopupView(menu);
        sideMenuBtn.setOnAction(e -> menuSidePopupView.show());
        
        buttonPanel.getChildren().add(sideMenuBtn);
    }
    
    private void buildDemo3() {
        // create the menu we will use
        final Menu menu = new Menu();
        
        CheckMenuItem checkMenuItem1 = new CheckMenuItem("Check Item 1");
        checkMenuItem1.setOnAction(e -> System.out.println(checkMenuItem1.getText() + " was clicked!"));
        CheckMenuItem checkMenuItem2 = new CheckMenuItem("Check Item 2");
        checkMenuItem2.setDisable(true);
        checkMenuItem1.setOnAction(e -> System.out.println(checkMenuItem2.getText() + " was clicked!"));
        
        RadioMenuItem radioMenuItem1 = new RadioMenuItem("Radio Item 1");
        radioMenuItem1.setOnAction(e -> System.out.println(radioMenuItem1.getText() + " was clicked!"));
        RadioMenuItem radioMenuItem2 = new RadioMenuItem("Radio Item 2");
        radioMenuItem2.setDisable(true);
        radioMenuItem2.setOnAction(e -> System.out.println(radioMenuItem2.getText() + " was clicked!"));
        
        ToggleGroup toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().addAll(radioMenuItem1, radioMenuItem2);
        
        menu.getItems().addAll(checkMenuItem1, checkMenuItem2, radioMenuItem1, radioMenuItem2);
        
        // demo three is a menu that appears where the button is located, with Check and Radio menu items
        Button inPlaceMenuBtn2 = new Button("Show Check / Radio Menu");
        final MenuPopupView menuPopupView = new MenuPopupView(inPlaceMenuBtn2, menu);
        inPlaceMenuBtn2.setOnAction(e -> menuPopupView.show());
        
        buttonPanel.getChildren().add(inPlaceMenuBtn2);
    }


    @Override public String toString() {
        return "Menu";
    }
    
    @Override public Node getPrimaryGraphic() {
        return MaterialDesignIcon.MENU.graphic();
    }
}
