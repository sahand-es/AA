package view.game;

import controller.DataManager;
import controller.GameControl;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.RoatateSpeed;
import model.Setting;
import view.animations.*;
import view.shapes.CenterCircle;
import model.Database;
import model.Game;
import view.shapes.RotatorCircle;
import view.shapes.ShootingIndicator;

import java.io.File;

public class GameViewController {
    public Text buttonsHelp;
    public Button test;
    GameMenu gameMenu;
    RotateAnimation rotateAnimation;
    InflationAnimation inflationAnimation;
    // TODO: 5/23/2023 true
    boolean isVisible = false;

    boolean inflated = false;
    boolean iceMode = false;
    boolean indicator = false;
    private long lastChangedDirTime;
    private long iceModeActivationTime;
    private int nextDirDeltaT;
    private Pane gamePane;

    private Game game;

    public GameViewController() {
        game = GameControl.getGame();
    }

    public void setGameView(Pane gamePane, GameMenu gameMenu) {
        this.gamePane = gamePane;
        this.gameMenu = gameMenu;
        GameControl.setGameViewController(this);
    }

    public void shoot(CenterCircle centerCircle, RotatorCircle rotatorCircle, double degree) {
        ShootingAnimation shootingAnimation = new ShootingAnimation(gamePane, centerCircle, rotatorCircle, degree);

        shootingAnimation.play();
        game.shoot();
        if (game.getPhase() == 4) {
            if (!indicator) {
                gameMenu.playShootingIndicator();
                indicator = true;
            }
        }
    }

    public void moveLeft(RotatorCircle shootingCircle,  ShootingIndicator shootingIndicator) {
        if (shootingCircle.getCenterX() > Database.centerX - 300) {
            shootingCircle.setCenterX(shootingCircle.getCenterX() - 8);
            if (shootingIndicator != null)
                shootingIndicator.setStartX(shootingIndicator.getStartX() - 8);
        }
    }

    public void moveRight(RotatorCircle shootingCircle, ShootingIndicator shootingIndicator) {
        if (shootingCircle.getCenterX() < Database.centerX + 300) {
            shootingCircle.setCenterX(shootingCircle.getCenterX() + 8);
            if (shootingIndicator != null)
                shootingIndicator.setStartX(shootingIndicator.getStartX() + 8);
        }
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

    public void iceMode(ProgressBar progressBar) {
        progressBar.setProgress(0);
        FreezeAnimation freezeAnimation = new FreezeAnimation(game.getCenterCircle(), gamePane);
        freezeAnimation.play();

        rotateAnimation.setSpeed(RoatateSpeed.ICE);
        iceMode = true;
        iceModeActivationTime = System.currentTimeMillis();
    }

    public void deActiveIceMode() {
        rotateAnimation.setSpeed(game.getDifficulty().getRoatateSpeed());
        iceMode = false;
        game.getCenterCircle().unFreeze();
    }

    public void inflate(CenterCircle centerCircle) {
        inflationAnimation = new InflationAnimation(gamePane, centerCircle);
        inflationAnimation.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                for (RotatorCircle rotatorCircle : centerCircle.getRotatorCircles()) {
                    rotatorCircle.setRadius(new RotatorCircle(centerCircle, 0).getRadius());
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

    public void indicatorAnimation(ShootingIndicator shootingIndicator) {
        IndicatorAnimation indicatorAnimation = new IndicatorAnimation(shootingIndicator, gamePane);
        indicatorAnimation.play();
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
            } else if (isVisible) {
                visibleTest(game.getCenterCircle());
                isVisible = false;
            }
        }
    }

    public void collisionZoomAnimation(Circle circle1, Circle circle2) {
        if (circle1 == null || circle2 == null)
            return;
        ScaleTransition st1 = new ScaleTransition(Duration.millis(4000), circle1);
        st1.setByX(0.8f);
        st1.setByY(0.8f);
        st1.setCycleCount(3);
        st1.setAutoReverse(true);

        ScaleTransition st2 = new ScaleTransition(Duration.millis(4000), circle2);
        st2.setByX(0.8f);
        st2.setByY(0.8f);
        st2.setCycleCount(3);
        st2.setAutoReverse(true);

        st1.play();
        st2.play();
    }

    public void lost(Circle circle1, Circle circle2) {
        rotateAnimation.stop();
        if (inflationAnimation != null)
            inflationAnimation.stop();
        if (!isVisible)
            visibleTest(game.getCenterCircle());
        collisionZoomAnimation(circle1,circle2);
        gameMenu.setLostScene();
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File(DataManager.LOSE_SOUND_PATH).toURI().toString()));
        mediaPlayer.play();
    }

    public void pause() {
        rotateAnimation.pause();
        if (inflated)
            inflationAnimation.pause();
    }

    public void unPause() {
        rotateAnimation.play();
        if (inflated)
            inflationAnimation.play();
        gameMenu.removePauseMenu();
    }

    public static int randomNumber(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

}
