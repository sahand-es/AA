package view;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.CenterCircle;
import model.RotatorCircle;

public class InflationAnimation extends Transition {
    Pane pane;
    CenterCircle centerCircle;
    double initSize;

    public InflationAnimation(Pane pane, CenterCircle centerCircle) {
        this.pane = pane;
        this.centerCircle = centerCircle;
        initSize = centerCircle.getRotatorCircles().get(0).getRadius();

        this.setCycleCount(1);
        this.setCycleDuration(Duration.seconds(1));
    }

    @Override
    protected void interpolate(double v) {
        for (RotatorCircle rotatorCircle : centerCircle.getRotatorCircles()) {
            rotatorCircle.setRadius(rotatorCircle.getRadius() + 0.06);
        }

        for (RotatorCircle rotatorCircle1 : centerCircle.getRotatorCircles()) {
            for (RotatorCircle rotatorCircle2 : centerCircle.getRotatorCircles()) {
                if (rotatorCircle1.collided(rotatorCircle2) && !rotatorCircle1.equals(rotatorCircle2)) {
                    // TODO: 5/23/2023 lose.
                    this.stop();
                    return;
                }
            }
        }
    }

}
