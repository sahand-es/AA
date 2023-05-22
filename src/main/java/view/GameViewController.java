package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import model.CenterCircle;
import model.RotatorCircle;

public class GameViewController {
    public void shoot(CenterCircle centerCircle, RotatorCircle rotatorCircle, Pane gamePane, double degree) {
        ShootingAnimation shootingAnimation = new ShootingAnimation(gamePane, centerCircle, rotatorCircle, degree);

        shootingAnimation.play();
    }

    public void rotate(CenterCircle centerCircle,  Pane gamePane, RoatateSpeed roatateSpeed) {
        RotateAnimation rotateAnimation = new RotateAnimation(gamePane, centerCircle, roatateSpeed);
        rotateAnimation.play();
    }
}
