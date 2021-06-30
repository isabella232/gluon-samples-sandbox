package com.gluonhq.charm.glisten.testbench.samples;

import com.gluonhq.charm.glisten.control.ProgressBar;
import com.gluonhq.charm.glisten.control.ProgressIndicator;
import com.gluonhq.charm.glisten.testbench.Sample;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class ProgressDemoPanel extends BorderPane implements Sample {

    private final VBox box;

    public ProgressDemoPanel() {
        ProgressBar determinateProgressBar = createDeterminateProgressBar();
        ProgressBar indeterminateProgressBar = createIndeterminateProgress();
        ProgressIndicator determinateProgressIndicator = createDeterminateProgressIndicator();
        instantiateProgressIndicator(determinateProgressIndicator);
        ProgressIndicator indeterminateProgressIndicator = createIndeterminateProgressIndicator();
        Label determinateBarLabel = new Label("Determinate ProgressBar");
        Label indeterminateBarLabel = new Label("Indeterminate ProgressBar");
        Label determinateIndicatorLabel = new Label("Determinate ProgressBar");
        Label indeterminateIndicatorLabel = new Label("Indeterminate ProgressBar");
        box = new VBox(20,
                determinateBarLabel, determinateProgressBar,
                indeterminateBarLabel, indeterminateProgressBar,
                determinateIndicatorLabel, determinateProgressIndicator,
                indeterminateIndicatorLabel, indeterminateProgressIndicator);
        determinateProgressBar.prefWidthProperty().bind(box.widthProperty().subtract(50));
        indeterminateProgressBar.prefWidthProperty().bind(box.widthProperty().subtract(50));
        box.setAlignment(Pos.CENTER);
        setCenter(box);
    }

    private ProgressIndicator createIndeterminateProgressIndicator() {
        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicator.setStyle("-fx-color: DEEPPINK;");
        return progressIndicator;
    }

    private ProgressIndicator createDeterminateProgressIndicator() {
        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicator.setProgress(1);
        progressIndicator.setStyle("-fx-color: AQUA;");
        return progressIndicator;
    }

    private ProgressBar createIndeterminateProgress() {
        ProgressBar indeterminate = new ProgressBar();
        //indeterminate.setPrefWidth(300);
        indeterminate.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
        return indeterminate;
    }

    private ProgressBar createDeterminateProgressBar() {
        ProgressBar determinate = new ProgressBar();
        //determinate.setPrefWidth(300);
        determinate.setStyle("-fx-color: black;");
        progressTimeline(determinate);
        return determinate;
    }

    private Timeline progressTimeline(ProgressBar determinate) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), new KeyValue(determinate.progressProperty(), 0)),
                new KeyFrame(Duration.seconds(5), new KeyValue(determinate.progressProperty(), 1)));
        determinate.sceneProperty().addListener((observable, oldValue, newValue) -> {
            timeline.play();
        });
        timeline.setOnFinished(event -> timeline.playFromStart());
        return timeline;
    }

    private void instantiateProgressIndicator(javafx.scene.control.ProgressIndicator determinate) {
        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(3));
        pauseTransition.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(pauseTransition.getDuration())) {
                box.getChildren().remove(5);
                box.getChildren().add(5, createDeterminateProgressIndicator());
            }
        });

        pauseTransition.setCycleCount(Animation.INDEFINITE);
        determinate.sceneProperty().addListener((observable, oldValue, newValue) -> {
            pauseTransition.play();
        });
    }

    @Override
    public String toString() {
        return "ProgressBar";
    }

    @Override
    public Node getPrimaryGraphic() {
        return MaterialDesignIcon.SYNC.graphic();
    }
}