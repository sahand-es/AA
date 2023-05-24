package view.shapes;

import javafx.scene.shape.Line;
import model.Database;
import model.Setting;
import view.animations.IndicatorAnimation;

public class ShootingIndicator extends Line {
    double angle;
    double length;
    IndicatorAnimation indicatorAnimation;

    public ShootingIndicator(double length) {
        super(Database.centerX,
                new RotatorCircle(null, 0).getCenterY(),
                Database.centerX,
                new RotatorCircle(null, 0).getCenterY() - length);
        this.setStroke(Setting.getGameColor());
        this.setOpacity(0.8);
        this.getStrokeDashArray().addAll(2d, 4d);
        this.length = length;
        angle = 0;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle % 360;
    }

    public double angleToX(double angle) {
        return this.getStartX() + length * Math.sin(Math.toRadians(angle));
    }
    public double angleToY(double angle) {
        return this.getStartY() - length * Math.cos(Math.toRadians(angle));
    }
}
