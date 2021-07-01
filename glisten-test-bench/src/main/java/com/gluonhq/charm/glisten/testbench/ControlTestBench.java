package com.gluonhq.charm.glisten.testbench;

import com.gluonhq.charm.glisten.animation.FadeInUpBigTransition;
import com.gluonhq.charm.glisten.animation.MobileTransition;
import com.gluonhq.charm.glisten.animation.SwingTransition;
import com.gluonhq.charm.glisten.application.MobileApplicationManager;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.Avatar;
import com.gluonhq.charm.glisten.control.CardPane;
import com.gluonhq.charm.glisten.control.ListTile;
import com.gluonhq.charm.glisten.control.NavigationDrawer;
import com.gluonhq.charm.glisten.control.NavigationDrawer.Header;
import com.gluonhq.charm.glisten.control.NavigationDrawer.Item;
import com.gluonhq.charm.glisten.mvc.SplashView;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.testbench.samples.*;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.gluonhq.charm.glisten.visual.Theme;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class ControlTestBench extends Application {

    private static final Function<View,MobileTransition> VIEW_TRANSITION = view -> new FadeInUpBigTransition(view, false);

    private MobileApplicationManager app = MobileApplicationManager.initialize(this::postInit);

    @Override
    public void start(Stage primaryStage) throws Exception {
        app.start(primaryStage);
    }

    @Override
    public void init() throws Exception {

        // Add the HOME_VIEW
        app.addViewFactory(MobileApplicationManager.HOME_VIEW, () -> new View() {
            {
                CardPane card = new CardPane();
                ListTile tile = new ListTile();
                tile.textProperty().addAll("Welcome to the ControlTestBench",
                        "Click the Menu button to start");
                tile.setPrefHeight(100);
                tile.setPrimaryGraphic(MaterialDesignIcon.DASHBOARD.graphic());
                card.getItems().add(tile);
                setCenter(card);
                setOnShowing(e -> {
                    AppBar appBar = app.getAppBar();
                    appBar.setNavIcon(MaterialDesignIcon.MENU.button(f -> app.getDrawer().open()));
                    appBar.setTitleText("ControlTestBench");
                });
            }
        });

        // Add splash (it should be done in the init method). (never finishes)
        // 1. empty splash view
//        addViewFactory(SPLASH_VIEW, () -> new SplashView());

        // 2.1 Provide content as in any regular view -> no setOnShown (never finishes)
//        addViewFactory(SPLASH_VIEW, () -> {
//            SplashView splashView = new SplashView();
//            splashView.setCenter(new Label("This is the Splash"));
//            return splashView;
//        });

        // 2.2 Provide content as in any regular view -> setOnShown (manual)
//        addViewFactory(SPLASH_VIEW, () -> {
//            SplashView splashView = new SplashView();
//            splashView.setCenter(new Label("This is the Splash"));
//            splashView.setOnShown(e -> splashView.hideSplashView());
//            return splashView;
//        });

        // 3. provide eventHandler
        app.addViewFactory(MobileApplicationManager.SPLASH_VIEW, () -> {
            SplashView splashView = new SplashView("Settings");
            Button button = new Button("Click me!");
            splashView.setMouseTransparent(true);
            splashView.setCenter(button);
            splashView.setOnShown(e -> {
                // it is developer's responsibility to make it work
                SwingTransition shake = new SwingTransition(splashView);
                shake.setOnFinished(f -> {
                    splashView.setMouseTransparent(false);
                    button.setOnAction(a -> splashView.hideSplashView());
                });
                shake.play();
            });
            return splashView;
        });

        // 4. provide duration
//        addViewFactory(SPLASH_VIEW, () -> {
//            SplashView splashView = new SplashView("Settings", new Label("Wait a few"), Duration.seconds(3));
//            return splashView;
//        });
    }

    public void postInit(Scene scene) {

        Header header = new Header("GluonHQ",
                "support@gluonhq.com",
                new Avatar(21, new Image("/icon.png")));
        app.getDrawer().setHeader(header);
        app.setTitle("Glisten TestBench");

        List<Sample> views = new ArrayList<>(Arrays.asList(
                new ActionButtonDemoPanel(),
                new BottomNavigationDemoPanel(),
                new ButtonDemoPanel(),
                new CardPaneDemoPanel(),
                new CharmListViewDemoPanel(),
                new ChipDemoPanel(),
                new DateTimePickerDemoPanel(),
                new DialogsDemoPanel(),
                new DropdownButtonDemoPanel(),
                new ExpansionPanelDemoPanel(),
                new MenuDemoPanel(),
                new NavigationDrawerDemoPanel(),
                new PopupDemoPanel(),
                new ProgressDemoPanel(),
                new RatingDemoPanel(),
                new SidePopupDemoPanel(),
                new SettingsDemoPanel(),
                new SwatchDemoPanel(),
                new TabsDemoPanel(),
                new TextFieldDemoPanel(),
                new ToastDemoPanel(),
                new ToggleButtonsDemoPanel(),
                new TooltipDemoPanel(),
                new ViewTransitionDemoPanel())
        );
        app.getDrawer().getItems().setAll(createItemsFrom(views));
    }

    private List<Item> createItemsFrom(List<Sample> views) {
        List<Item> listOfItems = new ArrayList<>();
        for(Sample view : views) {
            String viewName = view.toString();
            registerViewFactory(viewName, (Node) view);
            listOfItems.add(new NavigationDrawer.ViewItem(view.toString(), view.getPrimaryGraphic(), viewName));
        }
        return listOfItems;
    }

    private void registerViewFactory(String viewId, Node root) {
        app.addViewFactory(viewId, () -> {
            View view = new View(root) {
                private final Button navigationButton;
                private final MenuItem mnDarkTheme, mnLightTheme;

                {
                    navigationButton = MaterialDesignIcon.MENU.button();
                    navigationButton.setOnAction(e -> app.getDrawer().open());
                    mnDarkTheme = new MenuItem("Dark Theme");
                    mnDarkTheme.setOnAction(e -> Theme.DARK.assignTo(app.getAppBar().getScene()));
                    mnLightTheme = new MenuItem("Light Theme");
                    mnLightTheme.setOnAction(e -> Theme.LIGHT.assignTo(app.getAppBar().getScene()));
                }

                @Override
                protected void updateAppBar(AppBar appBar) {
                    appBar.setNavIcon(navigationButton);
                    appBar.getMenuItems().setAll(mnDarkTheme, mnLightTheme);
                    appBar.setTitleText(viewId);
                }
            };
            view.setShowTransitionFactory(VIEW_TRANSITION);
            return view;
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}


