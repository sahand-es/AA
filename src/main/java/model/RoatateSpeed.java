package model;

public enum RoatateSpeed {
    SLOW(1),
    FAST(2),
    VERY_FAST(3),
    ICE(0.1)
    ;

    public double speedDouble;

    RoatateSpeed(double speedDouble) {
        this.speedDouble = speedDouble;
    }
}
