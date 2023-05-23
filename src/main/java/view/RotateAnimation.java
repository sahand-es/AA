package view;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.CenterCircle;
import model.RotatorCircle;

public class RotateAnimation extends Transition {
    Pane pane;
    CenterCircle centerCircle;
    RoatateSpeed speed;

    double rotateDirection = 0.1;

    public RotateAnimation(Pane pane, CenterCircle centerCircle, RoatateSpeed roatateSpeed) {
        this.pane = pane;
        this.centerCircle = centerCircle;
        this.speed = roatateSpeed;

        this.setRate(roatateSpeed.speedDouble);
        this.setCycleCount(INDEFINITE);
        this.setCycleDuration(Duration.millis(10));
    }

    @Override
    protected void interpolate(double v) {
        for (RotatorCircle rotatorCircle : centerCircle.getRotatorCircles()) {
            double angle = rotatorCircle.getAngle() + rotateDirection;
            rotatorCircle.setAngle(angle);
            rotatorCircle.setCenterX(rotatorCircle.angleToX(angle));
            rotatorCircle.setCenterY(rotatorCircle.angleToY(angle));

            rotatorCircle.getConnectionLine().setStartX(rotatorCircle.getCenterX());
            rotatorCircle.getConnectionLine().setStartY(rotatorCircle.getCenterY());
        }
    }

    public void setSpeed(RoatateSpeed speed) {
        this.speed = speed;
        this.setRate(speed.speedDouble);
    }

    public void changeRotateDirection() {
        rotateDirection = rotateDirection == 0.1? -0.1 : 0.1;
    }
}
