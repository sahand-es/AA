package controller;

import model.Database;
import model.Game;
import model.User;
import view.game.GameMenu;
import view.game.GameMessages;
import view.game.GameViewController;
import view.main.MainMenu;
import view.shapes.RotatorCircle;

import java.util.ArrayList;
import java.util.List;

public class GameControl {
    static GameViewController gameViewController;
    static Game game = new Game();

    public static void setGameViewController(GameViewController gameViewController) {
       GameControl.gameViewController = gameViewController;
    }

    public static void endGame(Game game) {
        if (game.finished()) {
            game.getUser().addScore(game);
        }
    }

    public static void newGame() throws Exception {
        game = new Game();
        GameMenu.startGame();
    }
    public static void continueGame() throws Exception {
        game = Database.getSavedGame();
        game = game == null ? new Game() : game;
        game.getCenterCircle().setEffect(null);
        game.setStartTime();
        for (RotatorCircle rotatorCircle : game.getCenterCircle().getRotatorCircles()) {
            rotatorCircle.setEffect(null);
            rotatorCircle.getConnectionLine().setEffect(null);
        }
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

    public static void unPauseGame() {
        gameViewController.unPause();
    }


    public static void restart() {

    }

    public static Game getGame() {
        return game;
    }

    public static void saveGame() {
        Database.setSavedGame(game);
    }


    }
