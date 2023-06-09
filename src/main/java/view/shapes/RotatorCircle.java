package view.shapes;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import model.Database;
import model.Setting;

public class RotatorCircle extends Circle {
    CenterCircle centerCircle;
    Line connectionLine;
    private boolean isConnected;

    private double angle;

    public RotatorCircle(CenterCircle centerCircle, double x) {
        super(12);
        this.setCenterX(x);
        this.setCenterY(800);
//        this.setFill(Setting.getGameColor());
        this.setFill(new ImagePattern(new Image(RotatorCircle.class.getResource("/images/triple_ezmi_agadmwoaajbi8fe.png").toExternalForm())));

        isConnected = false;
        this.centerCircle = centerCircle;
    }

    public RotatorCircle(double angle, CenterCircle centerCircle) {
        super(12);
        this.centerCircle = centerCircle;

        this.angle = angle;
        this.setCenterX(this.angleToX());
        this.setCenterY(this.angleToY());
        this.setFill(Setting.getGameColor());

        this.connect();
    }

    public void connect() {
        isConnected = true;
        connectionLine = new Line(this.getCenterX(), this.getCenterY(),
                centerCircle.getCenterX(), centerCircle.getCenterY());

        connectionLine.setFill(Setting.getGameColor());
        connectionLine.setStroke(Setting.getGameColor());
        connectionLine.setStrokeWidth(0.8);

        centerCircle.addRotatorCircle(this);

    }

    public Line getConnectionLine() {
        return connectionLine;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public boolean collided(RotatorCircle shootingCircle) {
        double d = new Point2D(this.getCenterX(),
                this.getCenterY()).distance(shootingCircle.getCenterX(),
                shootingCircle.getCenterY());

        if (d <= this.getRadius() * 2)
            return true;
        return false;
    }

    public double angleToY() {
        return centerCircle.getCollideCircle().getRadius() * Math.cos(Math.toRadians(angle)) + centerCircle.getCenterY();
    }
    public double angleToX() {
        return centerCircle.getCollideCircle().getRadius() * Math.sin(Math.toRadians(angle)) + centerCircle.getCenterX();
    }

    public double angleToX(double angle) {
        return centerCircle.getCollideCircle().getRadius() * Math.sin(Math.toRadians(angle)) + centerCircle.getCenterX();
    }

    public double angleToY(double angle) {
        return centerCircle.getCollideCircle().getRadius() * Math.cos(Math.toRadians(angle)) + centerCircle.getCenterY();
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle % 360;
    }
}
