package model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final int initShootingCirclesCount;
    private int shootingCirclesCount;
    private final CenterCircle centerCircle;
    private Difficulty difficulty;
    private static final ArrayList<Game> defaultMaps = new ArrayList<>();

    private int phase;

    static {
        defaultMaps.add(new Game(new CenterCircle(new ArrayList<Double>(List.of(0.0, 45.0, 90.0, 135.0, 180.0)))));
        defaultMaps.add(new Game(new CenterCircle(new ArrayList<Double>(List.of(0.0, 90.0, 180.0, 270.0, 290.0)))));
        defaultMaps.add(new Game(new CenterCircle(new ArrayList<Double>(List.of(0.0, 30.0, 60.0, 270.0, 200.0, 150.0, 180.0)))));
        defaultMaps.add(new Game(new CenterCircle(new ArrayList<Double>(List.of(0.0, -30.0, 60.0, 270.0, 130.0, 150.0, 180.0)))));
    }

    private Game(CenterCircle centerCircle) {
        this.centerCircle = centerCircle;
        this.initShootingCirclesCount = Setting.getShootinCircleCount();
    }

    public Game() {
        this.shootingCirclesCount = Setting.getShootinCircleCount();
        this.initShootingCirclesCount = Setting.getShootinCircleCount();
        this.difficulty = Setting.getDifficulty();
        this.centerCircle = defaultMaps.get(Setting.getMapNumber()).getCenterCircle();
    }

    public int getShootingCirclesCount() {
        return shootingCirclesCount;
    }

    public CenterCircle getCenterCircle() {
        return centerCircle;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }
}
