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
        if (!playing) {
            fillTransition.play();
        }
    }
}
