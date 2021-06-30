package com.gluonhq.glisten.afterburner.testbench.views;

import com.gluonhq.charm.glisten.animation.BounceInRightTransition;
import com.gluonhq.charm.glisten.application.MobileApplicationManager;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.FloatingActionButton;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.fxml.FXML;

public class SecondPresenter  {

    @FXML
    private View second;

    public void initialize() {
        second.setShowTransitionFactory(BounceInRightTransition::new);
        
        FloatingActionButton fab = new FloatingActionButton(MaterialDesignIcon.INFO.text, 
            e -> AppViewManager.FIRST_VIEW.switchView());
        
        fab.showOn(second);
        
        second.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                MobileApplicationManager appManager = MobileApplicationManager.getInstance();

                AppBar appBar = appManager.getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> appManager.getDrawer().open()));
                appBar.setTitleText("Second");
                appBar.getActionItems().add(MaterialDesignIcon.FAVORITE.button(e -> 
                        System.out.println("Favorite")));
            }
        });
    }
}
