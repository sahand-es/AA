package view.animations;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import model.RoatateSpeed;
import view.shapes.CenterCircle;
import view.shapes.RotatorCircle;

public class ShootingAnimation extends Transition {

    Line shootingLine;
    Pane pane;
    RotatorCircle shootingCircle;
    CenterCircle centerCircle;
    double degree;

    public ShootingAnimation(Pane pane, CenterCircle centerCircle, RotatorCircle shootingCircle, double degree) {
        this.pane = pane;
        this.shootingCircle = shootingCircle;
        this.centerCircle = centerCircle;
        this.degree = Math.toRadians(degree);

        this.setCycleDuration(Duration.millis(500));
        this.setCycleCount(INDEFINITE);
        this.setRate(RoatateSpeed.SLOW.speedDouble);
    }

    @Override
    protected void interpolate(double v) {
        double y = shootingCircle.getCenterY() - 7;
        double x = shootingCircle.getCenterX() + 7 * Math.tan(degree);
        boolean collide = false;

        if (centerCircle.collided(shootingCircle)) {
            shootingCircle.connect();
            pane.getChildren().add(shootingCircle.getConnectionLine());
            shootingCircle.setAngle(Math.toDegrees(Math.atan(
                    (shootingCircle.getCenterX() - centerCircle.getCenterX())/
                    (shootingCircle.getCenterY() -   centerCircle.getCenterY()))));

            this.stop();
            collide = true;
        }
        for (RotatorCircle rotatorCircle : centerCircle.getRotatorCircles()) {
            if (rotatorCircle.collided(shootingCircle)) {
                this.stop();
                collide = true;
                // TODO: 5/22/2023 lose.
            }
        }
        if (!collide) {
            shootingCircle.setCenterY(y);
            shootingCircle.setCenterX(x);
        }
    }
}
