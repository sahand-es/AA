package view.game;

import controller.GameControl;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import org.apache.commons.lang3.time.DurationFormatUtils;
import view.main.MainMenu;

public class ResultViewController {

    public Label time;
    public Label score;

    @FXML
    private void initialize() {
        long duration = System.currentTimeMillis() - GameControl.getGame().getStartTime();
        this.setTime(DurationFormatUtils.formatDuration(duration, "mm:ss", true));
        if (GameControl.getGame().finished())
            this.setScore(String.valueOf(GameControl.getGame().getDifficulty().getRoatateSpeed()
                    .speedDouble
                    * (GameControl.getGame().getInitialCount() - GameControl.getGame().getShootingCirclesCount())));
        else
            this.setScore(String.valueOf(GameControl.getGame().getDifficulty().getRoatateSpeed()
                    .speedDouble
                    * (GameControl.getGame().getInitialCount() - GameControl.getGame().getShootingCirclesCount() - 1)));

    }

    public void restart(MouseEvent mouseEvent) throws Exception {
        GameControl.restart();
    }

    public void mainMenu(MouseEvent mouseEvent) throws Exception {
        MainMenu.startMenu();
    }

    public void setTime(String times) {
        time.setText(times);
    }

    public void setScore(String scores) {
        score.setText(scores);
    }
}
