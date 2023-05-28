package view.main;

import controller.GameControl;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Database;
import model.Difficulty;
import model.Setting;
import view.game.GameMenu;
import view.profile.ProfileMenu;

public class MainViewController {
    public ColorPicker colorPicker;
    public Slider slider;
    public Button shootButton;
    public Button freezeButton;
    public Button leftMoveButton;
    public Button rightMoveButton;
    public AnchorPane pane;


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
        // TODO: 5/28/2023 check
        System.exit(1);
    }

    public void profileMenu(MouseEvent mouseEvent) throws Exception {
        ProfileMenu.startProfileMenu();
    }

    public void startGame(MouseEvent mouseEvent) throws Exception {
        GameControl.newGame();
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

    @FXML
    private void initialize() {
        shootButton.setId("Shoot:   ");
        freezeButton.setId("Freeze:   ");
        leftMoveButton.setId("Left:   ");
        rightMoveButton.setId("Right:   ");
        shootButton.setText("Shoot:   " + Setting.getKeyToShoot().getName());
        freezeButton.setText("Freeze:   " + Setting.getKeyToIceMode().getName());
        leftMoveButton.setText("Left:   " + Setting.getKeyToMoveLeft().getName());
        rightMoveButton.setText("Right:   " + Setting.getKeyToMoveRight().getName());

        setActionKeyChange(shootButton);
        setActionKeyChange(freezeButton);
        setActionKeyChange(leftMoveButton);
        setActionKeyChange(rightMoveButton);
    }

    private void setActionKeyChange(Button button) {
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                button.requestFocus();
                button.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        Setting.setKeyToShoot(keyEvent.getCode());
                        button.setText(button.getId() + keyEvent.getCode().getName());
                        pane.requestFocus();
                    }
                });
            }
        });
    }

    public void continueGame(MouseEvent mouseEvent) throws Exception {
        GameControl.continueGame();
    }
}
