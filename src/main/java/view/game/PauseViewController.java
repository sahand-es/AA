package view.game;

import controller.GameControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import model.Database;
import model.Setting;

public class PauseViewController {
    public Text buttonsHelp;
    public ToggleButton muteButton;
    public Button backButton;

    @FXML
    private void initialize() {
        buttonsHelp.setText("Shoot: " + Setting.getKeyToShoot().getName() +
                "\nFreeze: " + Setting.getKeyToIceMode().getName() +
                "\nLeft: " + Setting.getKeyToMoveLeft().getName() +
                "\nRight: " + Setting.getKeyToMoveRight().getName());
    }

    public void changeMusic2(ActionEvent actionEvent) {
        Database.changeMusic(1);
    }public void changeMusic1(ActionEvent actionEvent) {
        Database.changeMusic(0);
    }public void changeMusic3(ActionEvent actionEvent) {
        Database.changeMusic(2);
    }


    public void muteMusic(MouseEvent mouseEvent) {
        if (muteButton.isSelected()) {
            Database.getMainMusic().pause();
        }
        else {
            Database.getMainMusic().play();
        }
    }

    public void unPause(MouseEvent mouseEvent) {
        GameControl.unPauseGame();
    }

    public void restart(MouseEvent mouseEvent) throws Exception {
        GameControl.newGame();
    }
}
