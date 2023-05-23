package view;

import controller.DataManager;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.CenterCircle;
import model.Database;
import model.RotatorCircle;
import model.Setting;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class GameMenu extends Application {
    public static GameViewController gameViewController = new GameViewController();

    @Override
    public void start(Stage stage) throws Exception {

        stage.setMaximized(true);
        stage.setFullScreen(true);
        Database.setStage(stage);
        Pane gamePane = FXMLLoader.load(this.getClass().getResource(DataManager.GAME_MENU_PATH));

        CenterCircle centerCircle = gameViewController.getGame().getCenterCircle();
        gameViewController.visible(centerCircle, gamePane);

        Group group = new Group();
        group.getChildren().addAll(centerCircle, centerCircle.getCollideCircle());
        gamePane.getChildren().addAll(group);

        gameViewController.rotate(centerCircle, gamePane, RoatateSpeed.VERY_FAST);
        gameViewController.inflate(centerCircle, gamePane);


        Scene scene = new Scene(gamePane);
        stage.setScene(scene);

        RotatorCircle rotatorCircle1 = createShootingCircle(gamePane, centerCircle);
        stage.show();
    }

    private RotatorCircle createShootingCircle(Pane gamePane, CenterCircle centerCircle) {
        RotatorCircle shootingCircle = new RotatorCircle(centerCircle);
        gamePane.getChildren().addAll(shootingCircle);
        shootingCircle.requestFocus();

        shootingCircle.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                KeyCode code = keyEvent.getCode();
                if (code.equals(Setting.getKeyToShoot())) {
                    gameViewController.shoot(centerCircle, shootingCircle, gamePane, 0);
                    gamePane.requestFocus();
//                    todo: check empty felan
                    createShootingCircle(gamePane, centerCircle);
                }
                if (code.equals(KeyCode.I))
                    gameViewController.invisible(centerCircle, gamePane);
                if (code.equals(KeyCode.V))
                    gameViewController.visible(centerCircle, gamePane);
                if (code.equals(KeyCode.TAB))
                    gameViewController.iceMode();
            }
        });

        return shootingCircle;
    }

    public static void main(String[] args) {
        launch(args);
    }


}
