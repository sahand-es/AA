package view;

public enum RoatateSpeed {
    SLOW(0.2),
    FAST(1),
    VERY_FAST(1.5);

    public double speedDouble;

    RoatateSpeed(double speedDouble) {
        this.speedDouble = speedDouble;
    }
}
