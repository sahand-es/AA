package view.animations;

import javafx.animation.FillTransition;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import view.shapes.CenterCircle;

public class FreezeAnimation extends Transition {
    CenterCircle centerCircle;
    Pane gamePane;

    FillTransition fillTransition;
    boolean playing = false;

    public FreezeAnimation(CenterCircle centerCircle, Pane gamePane) {
        this.centerCircle = centerCircle;
        this.gamePane = gamePane;

        this.setCycleCount(1);
        this.setCycleDuration(Duration.seconds(2));
        fillTransition= new FillTransition(Duration.millis(2000), centerCircle,
                Color.rgb(179, 225, 255), Color.rgb(76,153,240));
        fillTransition.setCycleCount(1);
    }

    @Override
    protected void interpolate(double v) {
        // TODO: 5/24/2023 delete:
//        int number = 1;
//        if (0 <= v && v <= 0.33) number = 1;
//        else if (0.33 < v && v <= 0.66) number = 2;
//        else if (0.66 < v && v <= 1) number = 3;

//        centerCircle.setFill(new ImagePattern(
//                new Image(
//                        FreezeAnimation.class.getResource(
//                                "/images/freeze/ice" + number + ".jpg").toExternalForm())));

        if (!playing) {
            gamePane.getChildren().remove(centerCircle);
            gamePane.getChildren().add(centerCircle);
            fillTransition.play();
        }
    }
}
