package view.animations;

import controller.GameControl;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import view.shapes.CenterCircle;
import view.shapes.RotatorCircle;

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
            rotatorCircle.setRadius(rotatorCircle.getRadius() + 0.04);
        }

        for (RotatorCircle rotatorCircle1 : centerCircle.getRotatorCircles()) {
            for (RotatorCircle rotatorCircle2 : centerCircle.getRotatorCircles()) {
                if (rotatorCircle1.collided(rotatorCircle2) && !rotatorCircle1.equals(rotatorCircle2)) {
                    GameControl.twoCirclesCollided(rotatorCircle1, rotatorCircle2);
                    this.stop();
                    return;
                }
            }
        }
    }

}
