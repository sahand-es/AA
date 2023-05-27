package view.animations;

import controller.DataManager;
import controller.GameControl;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import model.RoatateSpeed;
import view.shapes.CenterCircle;
import view.shapes.RotatorCircle;

import java.io.File;

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

        if (centerCircle.collided(shootingCircle)) {
            MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File(DataManager.CONNECT_SOUND_PATH).toURI().toString()));
            mediaPlayer.setVolume(3);
            mediaPlayer.play();
            shootingCircle.connect();
            pane.getChildren().add(shootingCircle.getConnectionLine());
            shootingCircle.toBack();
            shootingCircle.getConnectionLine().toBack();
            shootingCircle.setAngle(Math.toDegrees(Math.atan(
                    (shootingCircle.getCenterX() - centerCircle.getCenterX()) /
                            (shootingCircle.getCenterY() - centerCircle.getCenterY()))));

            this.stop();
            return;
        }
        for (RotatorCircle rotatorCircle : centerCircle.getRotatorCircles()) {
            if (rotatorCircle.collided(shootingCircle)) {
                this.stop();
                GameControl.twoCirclesCollided(rotatorCircle, shootingCircle);
                return;
            }
        }

        if (shootingCircle.getCenterX() < 10 || shootingCircle.getCenterX() > 1490
                ||
                shootingCircle.getCenterY() < 10 || shootingCircle.getCenterY() > 900) {
            this.stop();
            GameControl.twoCirclesCollided(null, shootingCircle);
            return;
        }
        shootingCircle.setCenterY(y);
        shootingCircle.setCenterX(x);
    }
}
