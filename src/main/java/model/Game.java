package model;

import view.shapes.CenterCircle;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final int initialCount;
    private int shootingCirclesCount;
    private final CenterCircle centerCircle;
    private int initialConnectedCount;
    private Difficulty difficulty;
    private static final ArrayList<Game> defaultMaps = new ArrayList<>();
    private double percentage = 0;
    private User user;
    private long startTime;

    private int phase = 1;

    static {
        defaultMaps.add(new Game(new CenterCircle(new ArrayList<Double>(List.of(0.0, 45.0, 90.0, 135.0, 180.0)))));
        defaultMaps.add(new Game(new CenterCircle(new ArrayList<Double>(List.of(0.0, 90.0, 180.0, 270.0, 290.0)))));
        defaultMaps.add(new Game(new CenterCircle(new ArrayList<Double>(List.of(0.0, 30.0, 60.0, 270.0, 200.0, 150.0, 180.0)))));
        defaultMaps.add(new Game(new CenterCircle(new ArrayList<Double>(List.of(0.0, -30.0, 60.0, 270.0, 130.0, 150.0, 180.0)))));
    }

    private Game(CenterCircle centerCircle) {
        this.centerCircle = centerCircle;
        this.initialCount = Setting.getShootingCircleCount();
    }

    public Game() {
        this.shootingCirclesCount = Setting.getShootingCircleCount();
        this.initialCount = Setting.getShootingCircleCount();
        this.difficulty = Setting.getDifficulty();
        this.centerCircle = defaultMaps.get(Setting.getMapNumber()).getCenterCircle();
        this.initialConnectedCount = centerCircle.getRotatorCircles().size();
        this.user = Database.getCurrentUser();
        this.startTime = System.currentTimeMillis();
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

    public void shoot() {
        shootingCirclesCount--;

        percentage = (((double) initialCount + 1 - (double) shootingCirclesCount)/(double) initialCount) * 100;

        if (percentage <= 25)
            phase = 1;
        else if (percentage <= 50)
            phase = 2;
        else if (percentage <= 75)
            phase = 3;
        else if (percentage <= 100)
            phase = 4;
    }

    public boolean finished() {
        return centerCircle.getRotatorCircles().size() - initialConnectedCount + 1 == initialCount;
    }
    public int getInitialCount() {
        return initialCount;
    }

    public User getUser() {
        return user;
    }

    public long getStartTime() {
        return startTime;
    }
}
