package view.game;

import view.game.GameViewController;

import java.util.TimerTask;

public class PhaseControl extends TimerTask {
    GameViewController gameViewController;

    public PhaseControl(GameViewController gameViewController) {
        this.gameViewController = gameViewController;
    }

    @Override
    public void run() {
        gameViewController.phaseControl();
    }
}
