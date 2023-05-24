package view;

import javafx.event.Event;
import javafx.scene.input.MouseEvent;
import model.Database;
import view.game.GameMenu;

public class MainViewController {
    public void setDifficulty(Event event) {
    }

    public void exit(MouseEvent mouseEvent) {
        System.exit(1);
    }

    public void profileMenu(MouseEvent mouseEvent) {
    }

    public void startGame(MouseEvent mouseEvent) throws Exception {
        new GameMenu().start(Database.getStage());
    }
}
