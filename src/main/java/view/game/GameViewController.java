package view.game;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Difficulty;
import view.RoatateSpeed;
import view.animations.InflationAnimation;
import view.animations.RotateAnimation;
import view.animations.ShootingAnimation;
import view.game.GameMenu;
import view.shapes.CenterCircle;
import model.Database;
import model.Game;
import view.shapes.RotatorCircle;

public class GameViewController {
    GameMenu gameMenu;
    RotateAnimation rotateAnimation;
    // TODO: 5/23/2023 true
    boolean isVisible = false;

    boolean inflated = false;
    boolean iceMode = false;
    private long lastChangedDirTime;
    private long iceModeActivationTime;
    private int nextDirDeltaT;
    private Pane gamePane;

    private Game game;

    public GameViewController() {
        game = new Game();
    }

    public void setGameView(Pane gamePane, GameMenu gameMenu) {
        this.gamePane = gamePane;
        this.gameMenu = gameMenu;
    }

    public void shoot(CenterCircle centerCircle, RotatorCircle rotatorCircle, double degree) {
        ShootingAnimation shootingAnimation = new ShootingAnimation(gamePane, centerCircle, rotatorCircle, degree);

        shootingAnimation.play();
        game.shoot();
    }

    public void moveLeft(RotatorCircle shootingCircle) {
        if (shootingCircle.getCenterX() > Database.centerX - 100)
            shootingCircle.setCenterX(shootingCircle.getCenterX() - 8);
    }

    public void moveRight(RotatorCircle shootingCircle) {
        if (shootingCircle.getCenterX() < Database.centerX + 100)
            shootingCircle.setCenterX(shootingCircle.getCenterX() + 8);
    }

    public void rotate(CenterCircle centerCircle, RoatateSpeed roatateSpeed) {
        RotateAnimation rotateAnimation = new RotateAnimation(gamePane, centerCircle, roatateSpeed);
        rotateAnimation.play();
        this.rotateAnimation = rotateAnimation;
    }

    public void changeRotateDir() {
        rotateAnimation.changeRotateDirection();
        lastChangedDirTime = System.currentTimeMillis();
        nextDirDeltaT = randomNumber(4, 8);
    }

    public void iceMode() {
        rotateAnimation.setSpeed(RoatateSpeed.ICE);
        iceMode = true;
        iceModeActivationTime = System.currentTimeMillis();
    }
    public void deActiveIceMode() {
        rotateAnimation.setSpeed(game.getDifficulty().getRoatateSpeed());
        iceMode = false;
    }

    public void inflate(CenterCircle centerCircle) {
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

    public void invisibleTest(CenterCircle centerCircle) {
        for (RotatorCircle rotatorCircle : centerCircle.getRotatorCircles()) {
            FadeTransition fadeTransition = new FadeTransition();
            fadeTransition.setDuration(Duration.seconds(0.5));
            fadeTransition.setFromValue(5);
            fadeTransition.setToValue(0);

            FadeTransition fadeTransition2 = new FadeTransition();
            fadeTransition2.setDuration(Duration.seconds(0.3));
            fadeTransition2.setFromValue(0.3);
            fadeTransition2.setToValue(0);

            fadeTransition.setNode(rotatorCircle);
            fadeTransition2.setNode(rotatorCircle.getConnectionLine());
            fadeTransition2.play();
            fadeTransition.play();
        }
    }
    public void visibleTest(CenterCircle centerCircle) {
        for (RotatorCircle rotatorCircle : centerCircle.getRotatorCircles()) {
            FadeTransition fadeTransition = new FadeTransition();
            fadeTransition.setDuration(Duration.seconds(0.5));
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(5);

            FadeTransition fadeTransition2 = new FadeTransition();
            fadeTransition2.setDuration(Duration.seconds(0.3));
            fadeTransition2.setFromValue(0);
            fadeTransition2.setToValue(0.3);

            fadeTransition.setNode(rotatorCircle);
            fadeTransition2.setNode(rotatorCircle.getConnectionLine());
            fadeTransition2.play();
            fadeTransition.play();
        }
    }

    public Game getGame() {
        return game;
    }

    public void phaseControl() {
        if (iceMode) {
            if (System.currentTimeMillis() - iceModeActivationTime >
                    game.getDifficulty().getDuration().getSeconds() * 1000L) {
                deActiveIceMode();
            }
        }
        if (game.getPhase() >= 2) {
            if (System.currentTimeMillis() - lastChangedDirTime > nextDirDeltaT * 1000L)
                changeRotateDir();
            if (!inflated) {
                inflate(game.getCenterCircle());
                inflated = true;
            }
        }
        if (game.getPhase() >= 3) {
            if (!isVisible) {
                invisibleTest(game.getCenterCircle());
                isVisible = true;
            }
            else if (isVisible) {
                visibleTest(game.getCenterCircle());
                isVisible = false;
            }
        }
    }
    public static int randomNumber(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }
}
