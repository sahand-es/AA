package controller;

import model.Database;
import model.Game;
import model.User;
import view.game.GameMenu;
import view.game.GameMessages;
import view.game.GameViewController;
import view.shapes.RotatorCircle;

import java.util.ArrayList;
import java.util.List;

public class GameControl {
    static GameViewController gameViewController;

    public static void setGameViewController(GameViewController gameViewController) {
       GameControl.gameViewController = gameViewController;
    }

    public static void endGame(Game game) {
        if (game.finished()) {
            game.getUser().addScore(game);
        }
    }

    public static void newGame() throws Exception {
        GameMenu.startGame();
    }

    public static ArrayList<RotatorCircle> loseControl(Game game) {
        ArrayList<RotatorCircle> rotatorCircles = new ArrayList<>();
        for (RotatorCircle rotatorCircle : game.getCenterCircle().getRotatorCircles()) {
            if (rotatorCircle.getCenterX() < 0 || rotatorCircle.getCenterX() > 1500 || rotatorCircle.getCenterY() < 0 || rotatorCircle.getCenterY() > 1000) {
                rotatorCircles.add(rotatorCircle);
                return rotatorCircles;
            }
            for (RotatorCircle circle : game.getCenterCircle().getRotatorCircles()) {
                if (rotatorCircle.collided(circle)  && !rotatorCircle.equals(circle)) {
                    rotatorCircles.add(rotatorCircle);
                    rotatorCircles.add(circle);
                    return rotatorCircles;
                }
            }
        }
        return rotatorCircles;
    }

    public static void twoCirclesCollided(RotatorCircle circle1, RotatorCircle circle2) {
        gameViewController.lost(circle1, circle2);
    }

    public static void gameControl(GameMessages gameMessage,
                                   Game game,
                                   GameViewController gameViewController) throws Exception {
        switch (gameMessage) {
            case WIN -> endGame(game);
            case LOST -> newGame();
        }
    }
}
