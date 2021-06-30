package com.gluonhq.charm.glisten.testbench.samples;

import com.gluonhq.charm.glisten.application.MobileApplicationManager;
import com.gluonhq.charm.glisten.control.FloatingActionButton;
import com.gluonhq.charm.glisten.testbench.Sample;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.Random;
import java.util.function.Function;

import static com.gluonhq.charm.glisten.visual.GlistenStyleClasses.TOGGLE_BUTTON_SWITCH;
import static com.gluonhq.charm.glisten.visual.GlistenStyleClasses.applyStyleClass;

public class ActionButtonDemoPanel extends BorderPane implements Sample {

    private FloatingActionButton floatingActionButton;

    public ActionButtonDemoPanel() {
        floatingActionButton = new FloatingActionButton(null, e -> System.out.println("Original FAB action"));
        
        // Uncomment to add a secondary FAB
        /*FloatingActionButton secondaryFAB = new FloatingActionButton(null, e -> System.out.println("Secondary FAB"));
        secondaryFAB.attachTo(floatingActionButton, Side.TOP);
        secondaryFAB.getStyleClass().add(FloatingActionButton.STYLE_CLASS_MINI);*/

        // row 1
        FlowPane buttonPanel1 = new FlowPane(10,5,
                createButton("Top-Left",      FloatingActionButton.TOP_LEFT),
                createButton("Top-Right",     FloatingActionButton.TOP_RIGHT),
                createButton("Bottom-Left",   FloatingActionButton.BOTTOM_LEFT),
                createButton("Bottom-Center", FloatingActionButton.BOTTOM_CENTER),
                createButton("Bottom-Right",  FloatingActionButton.BOTTOM_RIGHT));
        buttonPanel1.setPadding(new Insets(10));
        buttonPanel1.setMaxHeight(Double.MAX_VALUE);
        buttonPanel1.setAlignment(Pos.CENTER);
        
        // row 2
        final ToggleButton toggleVisibilityButton = new ToggleButton("Toggle Visibility");
        applyStyleClass(toggleVisibilityButton, TOGGLE_BUTTON_SWITCH);
        toggleVisibilityButton.setSelected(true);
        toggleVisibilityButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue) {
                floatingActionButton.show();
            } else {
                floatingActionButton.hide();
            }
        });
        
        // Platform#runLater is required because the constructor is 
        // called by ControlTestBench before the views are created.
        Platform.runLater(() -> {
            MobileApplicationManager.getInstance().retrieveView(toString()).ifPresent(floatingActionButton::showOn);
        });

        FlowPane buttonPanel2 = new FlowPane(10,5, toggleVisibilityButton);
        buttonPanel2.setPadding(new Insets(10));
        buttonPanel2.setMaxHeight(Double.MAX_VALUE);
        buttonPanel2.setAlignment(Pos.CENTER);
        
        VBox vbox = new VBox(10, buttonPanel1, buttonPanel2);
        vbox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        vbox.setAlignment(Pos.CENTER);

        StackPane demo = new StackPane(vbox);
        demo.setAlignment(Pos.CENTER);
        setCenter(demo);
    }

    @Override public String toString() {
        return "Action Button";
    }


    private MaterialDesignIcon[] fabIcons = new MaterialDesignIcon[]{
            MaterialDesignIcon.STAR,
            MaterialDesignIcon.ADD,
            MaterialDesignIcon.SETTINGS,
            MaterialDesignIcon.SEND,
            MaterialDesignIcon.FAVORITE};

    private Random rand = new Random();

    private Button createButton(String text, Function<FloatingActionButton, Point2D> function) {
        Button button = new Button(text); 
        button.setTooltip( new Tooltip(text));
        button.setOnAction(e -> {
            floatingActionButton.setFloatingActionButtonHandler(function);
            floatingActionButton.setOnAction(event -> System.out.println("Random FAB action"));
            floatingActionButton.setText( fabIcons[rand.nextInt(fabIcons.length)].text );
        });
        return button;
    }
    
    @Override public Node getPrimaryGraphic() {
        return MaterialDesignIcon.CONTROL_POINT.graphic();
    }
}
