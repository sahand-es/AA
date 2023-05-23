package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import model.CenterCircle;
import model.Game;
import model.RotatorCircle;

public class GameViewController {
    RotateAnimation rotateAnimation;
    // TODO: 5/23/2023 true
    boolean isVisible = false;

    Game game;

    public GameViewController() {
        game = new Game();
    }

    public void shoot(CenterCircle centerCircle, RotatorCircle rotatorCircle, Pane gamePane, double degree) {
        ShootingAnimation shootingAnimation = new ShootingAnimation(gamePane, centerCircle, rotatorCircle, degree);

        shootingAnimation.play();
    }

    public void rotate(CenterCircle centerCircle, Pane gamePane, RoatateSpeed roatateSpeed) {
        RotateAnimation rotateAnimation = new RotateAnimation(gamePane, centerCircle, roatateSpeed);
        rotateAnimation.play();
        this.rotateAnimation = rotateAnimation;
    }

    public void changeRotateDir() {
        rotateAnimation.changeRotateDirection();
    }

    public void inflate(CenterCircle centerCircle, Pane gamePane) {
        InflationAnimation inflationAnimation = new InflationAnimation(gamePane, centerCircle);
        inflationAnimation.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                for (RotatorCircle rotatorCircle : centerCircle.getRotatorCircles()) {
                    rotatorCircle.setRadius(new RotatorCircle(centerCircle).getRadius());
                    inflationAnimation.play();
                }
            }
        });
        inflationAnimation.play();
    }

    public void iceMode() {
        rotateAnimation.setSpeed(RoatateSpeed.ICE);
    }

    public void invisible(CenterCircle centerCircle, Pane gamePane) {
        if (isVisible) {
            for (RotatorCircle rotatorCircle : centerCircle.getRotatorCircles()) {
                gamePane.getChildren().removeAll(rotatorCircle, rotatorCircle.getConnectionLine());
            }
            isVisible = false;
        }
    }

    public void visible(CenterCircle centerCircle, Pane gamePane) {
        if (!isVisible) {
            for (RotatorCircle rotatorCircle : centerCircle.getRotatorCircles()) {
                gamePane.getChildren().addAll(rotatorCircle, rotatorCircle.getConnectionLine());
            }
            isVisible = true;
        }
    }

    public Game getGame() {
        return game;
    }
}