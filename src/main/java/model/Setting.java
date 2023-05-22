package model;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Setting {
    public static Color gameColor = Color.BLACK;
    public static KeyCode keyToShoot = KeyCode.SPACE;

    public static Color getGameColor() {
        return gameColor;
    }

    public static void setGameColor(Color gameColor) {
        Setting.gameColor = gameColor;
    }

    public static KeyCode getKeyToShoot() {
        return keyToShoot;
    }

    public static void setKeyToShoot(KeyCode keyToShoot) {
        Setting.keyToShoot = keyToShoot;
    }
}
