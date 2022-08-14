package thd.game.bin;

import thd.game.managers.GameLoopManager;
import thd.game.managers.NoMoreLevelsAvailableException;

/**
 * running class.
 */
public class Start {
    /**
     * run the game.
     *
     * @param args standard.
     */
    public static void main(String[] args) throws NoMoreLevelsAvailableException {
        GameLoopManager gameLoopManager = new GameLoopManager();
        gameLoopManager.startGame();
    }
}

