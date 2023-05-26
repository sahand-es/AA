package view.game;

import controller.DataManager;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;
import model.*;
import view.shapes.CenterCircle;
import view.shapes.RotatorCircle;
import view.shapes.ShootingIndicator;

import java.util.Timer;


public class GameMenu extends Application {
    public static GameViewController gameViewController = new GameViewController();
    private static Label label;
    private static Pane gamePane;
    private static CenterCircle centerCircle;
    private static double lastX = Database.centerX;

    private static ShootingIndicator shootingIndicator;
    private static Text remainingCount;

    @Override
    public void start(Stage stage) throws Exception {

        stage.setMaximized(true);
        stage.setFullScreen(true);
        Database.setStage(stage);
        gamePane = FXMLLoader.load(this.getClass().getResource(DataManager.GAME_MENU_PATH));
        gameViewController.setGameView(gamePane, this);

        initGameScene();

        Scene scene = new Scene(gamePane);
        stage.setScene(scene);
        RotatorCircle rotatorCircle1 = createShootingCircle(centerCircle);
        stage.show();
    }

    private RotatorCircle createShootingCircle(CenterCircle centerCircle) {
        updatePhaselabel(gameViewController.getGame());
        updateRemainingCount();
        RotatorCircle shootingCircle = new RotatorCircle(centerCircle, lastX);

        gamePane.getChildren().addAll(shootingCircle);
        shootingCircle.requestFocus();

        shootingCircle.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                KeyCode code = keyEvent.getCode();
                if (code.equals(Setting.getKeyToShoot())) {
                    double angle = 0;
                    angle = shootingIndicator == null ? 0 : shootingIndicator.getAngle();
                    gameViewController.shoot(centerCircle, shootingCircle, angle);
                    gamePane.requestFocus();
                    if (!gameViewController.getGame().finished())
                        createShootingCircle(centerCircle);
                    else updateRemainingCount();
                    return;
                }
                if (code.equals(Setting.getKeyToIceMode())) {
                    gameViewController.iceMode();
                    remainingCount.toFront();
                }
                if (gameViewController.getGame().getPhase() == 4) {
                    if (code.equals(Setting.getKeyToMoveRight())) {
                        gameViewController.moveRight(shootingCircle, shootingIndicator);
                        if (shootingCircle.getCenterX() < Database.centerX + 100) lastX += 8;
                    }
                    if (code.equals(Setting.getKeyToMoveLeft())) {
                        gameViewController.moveLeft(shootingCircle, shootingIndicator);
                        if (shootingCircle.getCenterX() > Database.centerX - 100) lastX -= 8;
                    }
                }
            }
        });

        return shootingCircle;
    }

    private void initGameScene() {
        setCenterCircle();
        setPhaseControl();
        setPhaseLabel();
        setShootingIndicator();
        setRemainingCount();
        playRotateAnimation();
    }

    private void setCenterCircle() {
        centerCircle = gameViewController.getGame().getCenterCircle();
        for (RotatorCircle rotatorCircle : centerCircle.getRotatorCircles()) {
            gamePane.getChildren().addAll(rotatorCircle, rotatorCircle.getConnectionLine());
        }
        gamePane.getChildren().add(centerCircle);
    }


    private void setPhaseLabel() {
        label = new Label(String.valueOf(gameViewController.getGame().getPhase()));
        label.setLayoutX(90);
        label.setLayoutY(690);
        label.getStyleClass().add("label1");
        gamePane.getChildren().add(label);
    }

    private void updatePhaselabel(Game game) {
        label.setText(String.valueOf(game.getPhase()));
    }

    private void setPhaseControl() {
        PhaseControl phaseControl = new PhaseControl(gameViewController);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(phaseControl, 0, 1000);
    }

    private void setShootingIndicator() {
        shootingIndicator = new ShootingIndicator(75);
        gamePane.getChildren().add(shootingIndicator);
    }

    private void setRemainingCount() {
        int count = gameViewController.getGame().getShootingCirclesCount();
        remainingCount = new Text(String.valueOf(gameViewController.getGame().getShootingCirclesCount()));
        remainingCount.setFill(Setting.getGameColor().invert());
        if (count < 10) {
            remainingCount.setLayoutX(centerCircle.getCenterX() - 35);
        } else remainingCount.setLayoutX(centerCircle.getCenterX() - 70);
        remainingCount.setLayoutY(centerCircle.getCenterY() + 45);
        remainingCount.getStyleClass().add("number");
        remainingCount.setBoundsType(TextBoundsType.VISUAL);
        remainingCount.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        gamePane.getChildren().add(remainingCount);
    }

    public void updateRemainingCount() {
        int count = gameViewController.getGame().getShootingCirclesCount();
        if (count < 10) {
            remainingCount.setLayoutX(centerCircle.getCenterX() - 35);
        } else remainingCount.setLayoutX(centerCircle.getCenterX() - 70);
        remainingCount.toFront();
        remainingCount.setText(String.valueOf(gameViewController.getGame().getShootingCirclesCount()));
    }

    public void playShootingIndicator() {
        gameViewController.indicatorAnimation(shootingIndicator);
    }

    public void playRotateAnimation() {
        gameViewController.rotate(centerCircle, gameViewController.getGame().getDifficulty().getRoatateSpeed());
    }

    public static void startGame() throws Exception {
        new GameMenu().start(Database.getStage());
    }

    public static void main(String[] args) {
        launch(args);
    }


}
