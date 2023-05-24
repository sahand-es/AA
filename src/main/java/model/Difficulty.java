package model;

import java.time.Duration;

public enum Difficulty {
    EASY(RoatateSpeed.SLOW, 1.2, Duration.ofSeconds(7)),
    MEDIUM(RoatateSpeed.FAST, 1.5, Duration.ofSeconds(5)),
    HARD(RoatateSpeed.VERY_FAST, 1.8, Duration.ofSeconds(3));

    private RoatateSpeed roatateSpeed;
    private double windSpeed;
    private Duration duration;

    Difficulty(RoatateSpeed roatateSpeed, double windSpeed, Duration duration) {
        this.roatateSpeed = roatateSpeed;
        this.windSpeed = windSpeed;
        this.duration = duration;
    }

    public RoatateSpeed getRoatateSpeed() {
        return roatateSpeed;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public Duration getDuration() {
        return duration;
    }
}
