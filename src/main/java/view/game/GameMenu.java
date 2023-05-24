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
        updateLabel(gameViewController.getGame());
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
                    return;
                }
//                if (code.equals(KeyCode.S))
//                    gameViewController.indicatorAnimation(shootingIndicator);
//                if (code.equals(KeyCode.A))
//                    shootingIndicator.setAngle(shootingCircle.getAngle() - 1);
                if (code.equals(Setting.getKeyToIceMode()))
                    gameViewController.iceMode();
                if (gameViewController.getGame().getPhase() == 4) {
                    if (code.equals(Setting.getKeyToMoveRight()))
                    {
                        gameViewController.moveRight(shootingCircle, shootingIndicator);
                        if (shootingCircle.getCenterX() < Database.centerX + 100) lastX += 8;
                    }
                    if (code.equals(Setting.getKeyToMoveLeft()))
                    {
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

    private void updateLabel(Game game) {
        label.setText(String.valueOf(game.getPhase()));
    }

    private void setPhaseControl() {
        PhaseControl phaseControl = new PhaseControl(gameViewController);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(phaseControl, 0, 1000);
    }

    public void setShootingIndicator() {
        shootingIndicator = new ShootingIndicator(75 );
        gamePane.getChildren().add(shootingIndicator);
    }
    public void playShootingIndicator() {
        gameViewController.indicatorAnimation(shootingIndicator);
    }

    public void playRotateAnimation() {
        gameViewController.rotate(centerCircle, gameViewController.getGame().getDifficulty().getRoatateSpeed());
    }

    public static void main(String[] args) {
        launch(args);
    }


}
