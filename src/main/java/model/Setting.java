package model;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Setting {
    private static Color gameColor = Color.BLACK;
    private static KeyCode keyToShoot = KeyCode.SPACE;
    private static Difficulty difficulty = Difficulty.EASY;
    private static int mapNumber = 3;

    private static int shootinCircleCount = 8;

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

    public static Difficulty getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(Difficulty difficulty) {
        Setting.difficulty = difficulty;
    }

    public static int getMapNumber() {
        return mapNumber;
    }

    public static void setMapNumber(int mapNumber) {
        // TODO: 5/23/2023 check it
        Setting.mapNumber = mapNumber;
    }

    public static int getShootinCircleCount() {
        return shootinCircleCount;
    }

    public static void setShootinCircleCount(int shootinCircleCount) {
        Setting.shootinCircleCount = shootinCircleCount;
    }
}
