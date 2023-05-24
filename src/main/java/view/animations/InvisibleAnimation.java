package view.animations;

import javafx.animation.FadeTransition;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import view.shapes.CenterCircle;
import view.shapes.RotatorCircle;

public class InvisibleAnimation extends Transition {
    Pane pane;
    CenterCircle centerCircle;
    boolean isVisible;


    public InvisibleAnimation(Pane pane, CenterCircle centerCircle) {
        this.pane = pane;
        this.centerCircle = centerCircle;
        this.isVisible = false;

        this.setCycleCount(INDEFINITE);
        this.setCycleDuration(Duration.seconds(2));
    }


    @Override
    protected void interpolate(double v) {
      invisible(centerCircle);
    }

    public void invisible(CenterCircle centerCircle) {
        for (RotatorCircle rotatorCircle : centerCircle.getRotatorCircles()) {
            FadeTransition fadeTransition = new FadeTransition();
            fadeTransition.setDuration(Duration.seconds(1));
            fadeTransition.setFromValue(1);
            fadeTransition.setToValue(0);
            fadeTransition.setCycleCount(1);

            fadeTransition.play();
        }
    }

    public void visible(CenterCircle centerCircle) {
        for (RotatorCircle rotatorCircle : centerCircle.getRotatorCircles()) {
            FadeTransition fadeTransition = new FadeTransition();
            fadeTransition.setDuration(Duration.seconds(1));
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.setCycleCount(1);

            fadeTransition.play();
        }
    }

}
