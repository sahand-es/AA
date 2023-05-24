package view.shapes;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.Database;
import model.Setting;

import java.util.ArrayList;

public class CenterCircle extends Circle {
    ArrayList<RotatorCircle> rotatorCircles = new ArrayList<>();

    Circle collideCircle;

    public CenterCircle() {
        super(90);
        this.setCenterX(Database.centerX);
        this.setCenterY(Database.centerY - 140);

        collideCircle = new Circle(130 + 70);
        collideCircle.setCenterX(this.getCenterX());
        collideCircle.setCenterY(this.getCenterY());
        collideCircle.setFill(new Color(0,0,0,0));
        collideCircle.setStroke(Color.BLACK);

        this.setFill(Setting.getGameColor());
    }

    public CenterCircle(ArrayList<Double> angles) {
        this();

        for (double angle : angles) {
            RotatorCircle rotatorCircle = new RotatorCircle(angle,this);
        }
    }

    public static CenterCircle centerCircleInit(ArrayList<Double> angles) {
        CenterCircle centerCircle = new CenterCircle(angles);

        return centerCircle;
    }

    public Circle getCollideCircle() {
        return collideCircle;
    }

    public ArrayList<RotatorCircle> getRotatorCircles() {
        return rotatorCircles;
    }

    public void addRotatorCircle(RotatorCircle rotatorCircle) {
        rotatorCircles.add(rotatorCircle);
    }

    public boolean collided(RotatorCircle shootingCircle) {
        double d = new Point2D(collideCircle.getCenterX(),
                collideCircle.getCenterY()).distance(shootingCircle.getCenterX(),
                shootingCircle.getCenterY());

        if (d <= collideCircle.getRadius())
            return true;
        return false;
    }

    public void freeze() {
        this.setFill(Color.LIGHTSKYBLUE);
    }
    public void unFreeze() {
        this.setFill(Setting.getGameColor());
    }
}
