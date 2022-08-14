package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.gameView.GameView;
import thd.gameobjects.base.GameObject;

import java.awt.*;

/**
 * class for an overlay.
 */
public class Overlay extends GameObject {
    /**
     * message to show in overlay.
     */
    private String message;
    //private int secondsToShow;

    /**
     * default constructor.
     *
     * @param gameView        {@link GameView}.
     * @param gamePlayManager {@link GamePlayManager}.
     */
    public Overlay(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        // message = "Level 1";
        //  secondsToShow = 2;

    }

    @Override
    public void updateStatus() {
    }

    @Override
    public void addToCanvas() {
        if (gameView.timerIsActive("overlay", this)) {
            gameView.addTextToCanvas(message, GameView.WIDTH / 2.0 - 140, GameView.HEIGHT / 2.0 - 20, 40, Color.WHITE, 0);
        }
        //gameView.addTextToCanvas(message, GameView.WIDTH / 2.0 - 140, GameView.HEIGHT / 2.0 - 20, 40, Color.WHITE, 0);

    }

    /**
     * pass the right message to show for the given number of seconds.
     *
     * @param message       message to show.
     * @param secondsToShow number of seconds.
     */
    public void showMessage(String message, int secondsToShow) {
        this.message = message;
        gameView.activateTimer("overlay", this, secondsToShow * 1000L);
    }
}
