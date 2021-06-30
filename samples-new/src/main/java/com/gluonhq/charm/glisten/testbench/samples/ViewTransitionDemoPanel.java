package com.gluonhq.charm.glisten.testbench.samples;

import com.gluonhq.charm.glisten.animation.MobileTransition;
import com.gluonhq.charm.glisten.application.MobileApplicationManager;
import com.gluonhq.charm.glisten.control.FloatingActionButton;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.testbench.Sample;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

import java.util.Arrays;
import java.util.List;

public class ViewTransitionDemoPanel extends BorderPane implements Sample {

    private static List<String> transitions = Arrays.asList(
            "BounceInDownTransition",
            "BounceInLeftTransition",
            "BounceInRightTransition",
            "BounceInTransition",
            "BounceInUpTransition",
            "BounceOutDownTransition",
            "BounceOutLeftTransition",
            "BounceOutRightTransition",
            "BounceOutTransition",
            "BounceOutUpTransition",
            "BounceTransition",
            "FadeInDownBigTransition",
            "FadeInDownTransition",
            "FadeInLeftBigTransition",
            "FadeInLeftTransition",
            "FadeInRightBigTransition",
            "FadeInRightTransition",
            "FadeInTransition",
            "FadeInUpBigTransition",
            "FadeInUpTransition",
            "FadeOutDownBigTransition",
            "FadeOutDownTransition",
            "FadeOutLeftBigTransition",
            "FadeOutLeftTransition",
            "FadeOutRightBigTransition",
            "FadeOutRightTransition",
            "FadeOutTransition",
            "FadeOutUpBigTransition",
            "FadeOutUpTransition",
            "FlashTransition",
            "FlipInXTransition",
            "FlipInYTransition",
            "FlipOutXTransition",
            "FlipOutYTransition",
            "FlipTransition",
            "HingeTransition",
            "PulseTransition",
            "RollInTransition",
            "RollOutTransition",
            "RotateInDownLeftTransition",
            "RotateInDownRightTransition",
            "RotateInTransition",
            "RotateInUpLeftTransition",
            "RotateInUpRightTransition",
            "RotateOutDownLeftTransition",
            "RotateOutDownRightTransition",
            "RotateOutTransition",
            "RotateOutUpLeftTransition",
            "RotateOutUpRightTransition",
            "ShakeTransition",
            "SwingTransition",
            "TadaTransition",
            "WobbleTransition"
    );

    private View parentView;
    private ListView<String> listView = new ListView<>();

    public ViewTransitionDemoPanel() {

        setCenter(listView);
        listView.getItems().addAll(transitions);

        FloatingActionButton floatingActionButton = new FloatingActionButton(
                MaterialDesignIcon.PLAY_ARROW.text, e -> playSelected());

        // Platform#runLater is required because the constructor is 
        // called by ControlTestBench before the views are created.
        Platform.runLater(() -> {
            MobileApplicationManager.getInstance().retrieveView(toString()).ifPresent(floatingActionButton::showOn);
        }); 
    }


    private void playSelected() {
        String name = listView.getSelectionModel().getSelectedItem();
        if ( name == null ) return;

        if (parentView == null) {
            Node node = ViewTransitionDemoPanel.this;
            while (!(node instanceof View)) {
                node = node.getParent();
            }
            parentView = (View) node;
            parentView.setOnShowing(event -> {
                listView.getSelectionModel().select(0);
            });
        }

        try {
            final Class cls = Class.forName("com.gluonhq.charm.glisten.animation." + name);
            parentView.setShowTransitionFactory(view -> {
                try {
                    return (MobileTransition) cls.getConstructor(Node.class).newInstance(view);
                } catch (Throwable e) {
                    e.printStackTrace();
                    return null;
                }
            });
            parentView.getShowTransition().play();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }


    @Override
    public String toString() {
        return "View Transitions";
    }

    @Override
    public Node getPrimaryGraphic() {
        return MaterialDesignIcon.FLIP.graphic();
    }
}
