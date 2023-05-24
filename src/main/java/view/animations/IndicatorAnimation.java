package view.animations;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Setting;
import view.shapes.ShootingIndicator;

public class IndicatorAnimation extends Transition {
    ShootingIndicator shootingIndicator;
    Pane gamePane;
    double rotate = 0.1;

    boolean clockWise = false;

    public IndicatorAnimation(ShootingIndicator shootingIndicator, Pane gamePane) {
        this.shootingIndicator = shootingIndicator;
        this.gamePane = gamePane;

//        this.setRate(0.1);
//        this.setCycleCount(-1);
//        this.setCycleDuration(Duration.millis());
//        this.setRate(Setting.getDifficulty().getWindSpeed());
//        this.setCycleCount();
//        this.setCycleDuration(Duration.seconds(1));
////        this.setAutoReverse(true);

        this.setRate(Setting.getDifficulty().getWindSpeed() * 0.4);
        this.setCycleCount(INDEFINITE);
        this.setCycleDuration(Duration.millis(10));
//        System.out.println(-10 % 360);
    }

    @Override
    protected void interpolate(double v) {
        double angle = shootingIndicator.getAngle();
        if (angle > 20)
            rotate = -0.1;
        if (angle <  -20)
            rotate = 0.1;
        angle = angle + rotate;


//        if (angle < -15 && !clockWise)  {
//            clockWise = true;
//        }
//        if (angle > 15 && clockWise) {
//            clockWise = false;
//        }
//        if (clockWise) {
//            shootingIndicator.setAngle(angle + 0.1);
//        }
//        if (!clockWise) {
//            shootingIndicator.setAngle(angle - 0.1);
//        }

        shootingIndicator.setAngle(angle);
        shootingIndicator.setEndX(shootingIndicator.angleToX(angle));
        shootingIndicator.setEndY(shootingIndicator.angleToY(angle));


    }
}
