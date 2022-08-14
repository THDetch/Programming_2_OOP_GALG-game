package thd.screens;

import thd.game.level.Level;
import thd.gameView.GameView;

/**
 * Start screen.
 */
public class StartScreen {
    /**
     * method returns the current difficulty of the Game.
     *
     * @param gameView   the GameView {@link GameView} .
     * @param difficulty the current difficulty.
     * @return the current difficulty of the game.
     */
    public static Level.Difficulty showStartScreen(GameView gameView, Level.Difficulty difficulty) {
        String title = "GALG";
        String description = "\n\n\n try not to DIE!";
        gameView.showSimpleStartScreen(title, description, true);
        return difficulty;
    }
}
