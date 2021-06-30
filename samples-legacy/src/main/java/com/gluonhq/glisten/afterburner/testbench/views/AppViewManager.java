package com.gluonhq.glisten.afterburner.testbench.views;

import com.gluonhq.charm.glisten.afterburner.*;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.Avatar;
import com.gluonhq.charm.glisten.control.NavigationDrawer;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.gluonhq.glisten.afterburner.testbench.GAfTestBench;
import javafx.scene.image.Image;

import java.util.Locale;

import static com.gluonhq.charm.glisten.afterburner.AppView.Flag.*;

public class AppViewManager {

    public static final AppViewRegistry REGISTRY = new AppViewRegistry();

    public static final AppView FIRST_VIEW  = view("First", FirstPresenter.class,   MaterialDesignIcon.HOME,         SHOW_IN_DRAWER, HOME_VIEW, SKIP_VIEW_STACK);
    public static final AppView SECOND_VIEW = view("Second", SecondPresenter.class, MaterialDesignIcon.DASHBOARD,    SHOW_IN_DRAWER);
    
    private static AppView view(String title, Class<? extends GluonPresenter<?>> presenterClass, MaterialDesignIcon menuIcon, AppView.Flag... flags ) {
        return REGISTRY.createView(name(presenterClass), title, presenterClass, menuIcon, flags);
    }

    private static String name(Class<? extends GluonPresenter<?>> presenterClass) {
        return presenterClass.getSimpleName().toUpperCase(Locale.ROOT).replace("PRESENTER", "");
    }
    
    public static void registerViewsAndDrawer(MobileApplication app) {
        for (AppView view : REGISTRY.getViews()) {
            view.registerView(app);
        }
        
        NavigationDrawer.Header header = new NavigationDrawer.Header("Gluon Mobile", "Multi View Project",
                new Avatar(21, new Image(GAfTestBench.class.getResourceAsStream("/icon.png"))));
        Utils.buildDrawer(app.getDrawer(), header, REGISTRY.getViews());
    }
}