package thd.game.utilities;

/**
 * create an exception when there are too many unused objects in list.
 */
public class TooManyGameObjectsException extends RuntimeException {
    /**
     * standard constructor throwing error message.
     */
    public TooManyGameObjectsException() {
        super("TooManyGameObjectsException: More than 300 GameObjects!");
    }
}
