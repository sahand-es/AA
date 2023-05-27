package model;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Setting {
    private static Color gameColor = Color.BLACK;
    private static KeyCode keyToShoot = KeyCode.SPACE;
    private static KeyCode keyToIceMode = KeyCode.TAB;
    private static KeyCode keyToMoveLeft = KeyCode.LEFT;
    private static KeyCode keyToMoveRight = KeyCode.RIGHT;

    private static Difficulty difficulty = Difficulty.EASY;
    private static int mapNumber = 0;

    private static int shootingCircleCount = 8;

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

    public static KeyCode getKeyToIceMode() {
        return keyToIceMode;
    }

    public static void setKeyToIceMode(KeyCode keyToIceMode) {
        Setting.keyToIceMode = keyToIceMode;
    }

    public static KeyCode getKeyToMoveLeft() {
        return keyToMoveLeft;
    }

    public static void setKeyToMoveLeft(KeyCode keyToMoveLeft) {
        Setting.keyToMoveLeft = keyToMoveLeft;
    }

    public static KeyCode getKeyToMoveRight() {
        return keyToMoveRight;
    }

    public static void setKeyToMoveRight(KeyCode keyToMoveRight) {
        Setting.keyToMoveRight = keyToMoveRight;
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

    public static int getShootingCircleCount() {
        return shootingCircleCount;
    }

    public static void setShootingCircleCount(int shootingCircleCount) {
        Setting.shootingCircleCount = shootingCircleCount;
    }
}
