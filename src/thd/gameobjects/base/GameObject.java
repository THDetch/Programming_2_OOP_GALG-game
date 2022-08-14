package thd.gameobjects.base;

import thd.game.managers.GamePlayManager;
import thd.gameView.GameView;

/**
 * the main class for all game objects.
 */
public abstract class GameObject {
    /**
     * a position to describe the current position on the x- y Axes {@link Position}.
     */
    protected final Position position;
    /**
     * reference from gamePlayManager {@link GamePlayManager}.
     */
    protected final GamePlayManager gamePlayManager;
    /**
     * a game window with 960*540 pixels {@link GameView}.
     */
    protected GameView gameView;
    /**
     * size of the object.
     */
    protected double size;
    /**
     * the moving speed in pixels.
     */
    protected double speedInPixel;
    /**
     * the rotation of the object pieces.
     */
    protected double rotation;
    /**
     * the width of objects.
     */
    protected double width;
    /**
     * the height of the objects.
     */
    protected double height;
    /**
     * to determine whether object should be showed or not.
     */
    protected boolean visible;
    /**
     * set a scale factor.
     */
    protected double scaleFactor;
    /**
     * num of Lives.
     */
    protected int numOfLives;

    /**
     * the default constructor.
     *
     * @param gameView        create a 960*540 pixels window {@link GameView}.
     * @param gamePlayManager controls game events {@link GamePlayManager}.
     */

    public GameObject(GameView gameView, GamePlayManager gamePlayManager) {
        this.gameView = gameView;
        this.gamePlayManager = gamePlayManager;
        position = new Position(0, 0);
        visible = true;
    }

    /**
     * updating status.
     */
    public abstract void updateStatus();

    /**
     * object shapes itself and be added to our game window.
     */
    public abstract void addToCanvas();

    /**
     * return own position.
     *
     * @return position from class Position {@link Position}.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * set new position.
     *
     * @param x for x-coordinate.
     * @param y for y-coordinate.
     */
    public void assignPosition(double x, double y) {
        this.position.x = x;
        this.position.y = y;
    }

    /**
     * carry out the shifting for an object.
     *
     * @param shiftX shift in x-axe.
     * @param shiftY shift in y-axe.
     */
    public void worldHasMoved(double shiftX, double shiftY) {
        position.x -= shiftX;
        position.y -= shiftY;
    }
}
