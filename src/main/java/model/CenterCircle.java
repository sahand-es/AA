package model;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Set;

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
}
