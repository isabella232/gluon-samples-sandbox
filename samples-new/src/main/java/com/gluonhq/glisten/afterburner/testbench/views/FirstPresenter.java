package com.gluonhq.glisten.afterburner.testbench.views;

import com.gluonhq.charm.glisten.application.MobileApplicationManager;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;

import java.util.ResourceBundle;

import com.gluonhq.glisten.afterburner.testbench.service.Service;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javax.inject.Inject;

public class FirstPresenter {

    @FXML
    private View first;

    @FXML
    private Label label;

    @FXML
    private ResourceBundle resources;

    @Inject
    private Service service;

    
    public void initialize() {
        first.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                MobileApplicationManager appManager = MobileApplicationManager.getInstance();
                AppBar appBar = appManager.getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e ->
                        appManager.getDrawer().open()));
                appBar.setTitleText("First");
                appBar.getActionItems().add(MaterialDesignIcon.SEARCH.button(e -> 
                        System.out.println("Search")));
            }
        });
        
    }
    
    @FXML
    void buttonClick() {
        label.setText(resources.getString("label.text.2"));
    }
    
}
