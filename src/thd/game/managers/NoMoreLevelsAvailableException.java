package thd.game.managers;

/**
 * create an exception when no more levels are available.
 */
public class NoMoreLevelsAvailableException extends RuntimeException {
    /**
     * default constructor throw a message "No more Levels available!".
     */
    public NoMoreLevelsAvailableException() {
        super("No more Levels available!");
    }
}
