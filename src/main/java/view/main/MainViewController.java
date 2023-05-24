package view.main;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import model.Database;
import model.Difficulty;
import model.Setting;
import view.game.GameMenu;

public class MainViewController {
    public ColorPicker colorPicker;
    public Slider slider;


    public void setDifficultyEasy(Event event) {
        Setting.setDifficulty(Difficulty.EASY);
    }
    public void setDifficultyMedium(Event event) {
        Setting.setDifficulty(Difficulty.MEDIUM);
    }

    public void setDifficultyHard(Event event) {
        Setting.setDifficulty(Difficulty.HARD);
    }

    public void exit(MouseEvent mouseEvent) {
        System.exit(1);
    }

    public void profileMenu(MouseEvent mouseEvent) {
    }

    public void startGame(MouseEvent mouseEvent) throws Exception {
        new GameMenu().start(Database.getStage());
    }


    public void setColor(ActionEvent actionEvent) {
        Setting.setGameColor(colorPicker.getValue());
    }

    public void setMap1(ActionEvent actionEvent) {
        Setting.setMapNumber(0);
    }public void setMap2(ActionEvent actionEvent) {
        Setting.setMapNumber(1);
    }public void setMap3(ActionEvent actionEvent) {
        Setting.setMapNumber(2);
    }public void setMap4(ActionEvent actionEvent) {
        Setting.setMapNumber(3);
    }

    public void setBalls(MouseEvent event) {
        Setting.setShootingCircleCount((int) slider.getValue());
    }
}
