package com.gluonhq.charm.glisten.testbench.samples;

import com.gluonhq.charm.glisten.control.Rating;
import com.gluonhq.charm.glisten.testbench.Sample;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class RatingDemoPanel extends StackPane implements Sample {

    public RatingDemoPanel() {
        VBox container = new VBox();
        container.setSpacing(20);

        Rating horizontalRating = new Rating();
        Rating verticalRating = new Rating();
        verticalRating.setOrientation(Orientation.VERTICAL);
        Rating partialRating = new Rating();
        partialRating.setPartialRating(true);

        VBox updateOnHoverRatingContainer = new VBox();
        Rating updateOnHoverRating = new Rating();
        updateOnHoverRating.setUpdateOnHover(true);
        Label updateOnHoverRatingLabel = new Label();
        updateOnHoverRatingLabel.textProperty().bind(updateOnHoverRating.ratingProperty().asString());
        updateOnHoverRatingContainer.getChildren().addAll(updateOnHoverRating, updateOnHoverRatingLabel);

        container.getChildren().addAll(horizontalRating, verticalRating, partialRating, updateOnHoverRatingContainer);
        getChildren().add(container);

        setPadding(new Insets(10, 10, 10, 10));
    }

    @Override
    public String toString() {
        return "Rating";
    }

    @Override
    public Node getPrimaryGraphic() {
        return MaterialDesignIcon.STAR.graphic();
    }
}
