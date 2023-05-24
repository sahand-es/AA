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
import view.PhaseControl;
import view.shapes.CenterCircle;
import view.shapes.RotatorCircle;

import java.util.Timer;


public class GameMenu extends Application {
    public static GameViewController gameViewController = new GameViewController();
    private static Label label;
    private static Pane gamePane;

    @Override
    public void start(Stage stage) throws Exception {

        stage.setMaximized(true);
        stage.setFullScreen(true);
        Database.setStage(stage);
        gamePane = FXMLLoader.load(this.getClass().getResource(DataManager.GAME_MENU_PATH));
        gameViewController.setGameView(gamePane, this);

        CenterCircle centerCircle = gameViewController.getGame().getCenterCircle();
        gamePane.getChildren().add(centerCircle);

        for (RotatorCircle rotatorCircle : centerCircle.getRotatorCircles()) {
            gamePane.getChildren().addAll(rotatorCircle, rotatorCircle.getConnectionLine());
        }

        gameViewController.rotate(centerCircle, gameViewController.getGame().getDifficulty().getRoatateSpeed());

        PhaseControl phaseControl = new PhaseControl(gameViewController);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(phaseControl, 0, 1000);

        setPhaseLabel();

        Scene scene = new Scene(gamePane);
        stage.setScene(scene);

        RotatorCircle rotatorCircle1 = createShootingCircle(centerCircle);
        stage.show();
    }

    private RotatorCircle createShootingCircle(CenterCircle centerCircle) {
        updateLabel(gameViewController.getGame());
        RotatorCircle shootingCircle = new RotatorCircle(centerCircle);
        gamePane.getChildren().addAll(shootingCircle);
        shootingCircle.requestFocus();

        shootingCircle.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                KeyCode code = keyEvent.getCode();
                if (code.equals(Setting.getKeyToShoot())) {
                    gameViewController.shoot(centerCircle, shootingCircle, 0);
                    gamePane.requestFocus();
                    if (!gameViewController.getGame().finished())
                        createShootingCircle(centerCircle);
                    return;
                }
                if (code.equals(Setting.getKeyToIceMode()))
                    gameViewController.iceMode();
                if (gameViewController.getGame().getPhase() == 4) {
                    if (code.equals(Setting.getKeyToMoveRight()))
                        gameViewController.moveRight(shootingCircle);
                    if (code.equals(Setting.getKeyToMoveLeft()))
                        gameViewController.moveLeft(shootingCircle);
                }
            }
        });

        return shootingCircle;
    }

    public void setPhaseLabel() {
        label = new Label(String.valueOf(gameViewController.getGame().getPhase()));
        label.setLayoutX(90);
        label.setLayoutY(690);
        label.getStyleClass().add("label1");
        gamePane.getChildren().add(label);
    }

    public void updateLabel(Game game) {
        label.setText(String.valueOf(game.getPhase()));
    }

    public static void main(String[] args) {
        launch(args);
    }


}
