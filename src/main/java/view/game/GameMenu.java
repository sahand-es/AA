package view.game;

import controller.DataManager;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;
import model.*;
import org.apache.commons.lang3.time.DurationFormatUtils;
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
    private static ProgressBar progressBar;
    private static Text remainingCount;
    private static PhaseControl phaseControl;
    private static AnimationTimer timer;

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
        updatePhaseLabel(gameViewController.getGame());
        updateRemainingCount();
        updateProgressBar();

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
                    else {
                        updateRemainingCount();
                        setWinScene();
                    }
                    return;
                }
                if (code.equals(Setting.getKeyToIceMode()) && progressBar.getProgress() >= 0.99) {
                    gameViewController.iceMode(progressBar);
                    remainingCount.toFront();
                }
                if (gameViewController.getGame().getPhase() == 4) {
                    if (code.equals(Setting.getKeyToMoveRight())) {
                        gameViewController.moveRight(shootingCircle, shootingIndicator);
                        if (shootingCircle.getCenterX() < Database.centerX + 300) lastX += 8;
                    }
                    if (code.equals(Setting.getKeyToMoveLeft())) {
                        gameViewController.moveLeft(shootingCircle, shootingIndicator);
                        if (shootingCircle.getCenterX() > Database.centerX - 300) lastX -= 8;
                    }
                }
                if (code.equals(KeyCode.L))
                    setWinScene();
            }
        });

        return shootingCircle;
    }

    private void initGameScene() {
        setCenterCircle();
        setPhaseControl();
        setPhaseLabel();
        setShootingIndicator();
        setProgressBar();
        setGameTimer();
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

    private void updatePhaseLabel(Game game) {
        label.setText(String.valueOf(game.getPhase()));
    }

    private void setPhaseControl() {
        phaseControl = new PhaseControl(gameViewController);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(phaseControl, 0, 1000);
    }

    private void setShootingIndicator() {
        shootingIndicator = new ShootingIndicator(60);
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

    public void setProgressBar() {
        progressBar = new ProgressBar();
        progressBar.setProgress(0);
        progressBar.setLayoutX(100);
        progressBar.setLayoutY(100);
        progressBar.getStyleClass().add("black-bar");
        progressBar.setBackground(gamePane.getBackground());
        gamePane.getChildren().add(progressBar);
    }

    public void setPauseButton() {
        Button pauseButton = new Button("pause");
        pauseButton.setLayoutX(100);
        pauseButton.setLayoutY(100);
        pauseButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // TODO: 5/26/2023 pause
            }
        });

        gamePane.getChildren().add(pauseButton);

    }

    public void updateProgressBar() {
        double progress = progressBar.getProgress();
        if (progress + 0.25 < 100)
            progress += 0.25;
        progressBar.setProgress(progress);
    }

    public void setGameTimer() {
        Text text = new Text();
        text.setLayoutX(1120);
        text.setLayoutY(820);
        text.getStyleClass().add("number");
        gamePane.getChildren().add(text);
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                long duration = System.currentTimeMillis() - gameViewController.getGame().getStartTime();
                if (duration > 91 * 1000){
                    gameViewController.lost(null, null);
                    return;
                }
                text.setText(DurationFormatUtils.formatDuration(duration, "mm:ss", true));
            }
        };

        timer.start();
    }


    public void playShootingIndicator() {
        gameViewController.indicatorAnimation(shootingIndicator);
    }

    public void playRotateAnimation() {
        gameViewController.rotate(centerCircle, gameViewController.getGame().getDifficulty().getRoatateSpeed());
    }

    public void setLostScene() {
        phaseControl.cancel();
        gamePane.requestFocus();
        timer.stop();
        gamePane.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(0), new Insets(0))));
        progressBar.setBackground(gamePane.getBackground());
    }

    public void setWinScene() {
        gamePane.setBackground(new Background(new BackgroundFill(Color.SPRINGGREEN, new CornerRadii(0), new Insets(0))));
        progressBar.setBackground(gamePane.getBackground());
    }

    public static void startGame() throws Exception {
        new GameMenu().start(Database.getStage());
    }

    public static void main(String[] args) {
        launch(args);
    }


}
