package controller;

import model.Database;
import model.Game;
import model.User;
import view.game.GameMenu;
import view.game.GameMessages;

public class GameControl {
    public static void endGame(Game game) {
        if (game.finished()) {
            game.getUser().addScore(game);
        }
    }

    public static void newGame() throws Exception {
        GameMenu.startGame();
    }

    public static void gameControl(GameMessages gameMessage, Game game) throws Exception {
        switch (gameMessage) {
            case WIN -> endGame(game);
            case LOST -> newGame();
        }
    }
}
