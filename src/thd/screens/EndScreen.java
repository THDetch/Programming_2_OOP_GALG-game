package thd.screens;

import thd.gameView.GameView;

/**
 * End Screen.
 */
public class EndScreen {
    /**
     * The methode shows an end screen as well as the points gained.
     *
     * @param gameView the GameView {@link GameView} .
     * @param score    the current score.
     */
    public static void showEndScreen(GameView gameView, int score) {
        gameView.showEndScreen("You scored " + score + " Points! \n\n\nCongratulation for nothing.");
    }
}
