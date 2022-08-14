package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.gameView.GameView;
import thd.gameobjects.base.AutoMovable;
import thd.gameobjects.base.CollidableGameObject;

/**
 * a class to create a bullet.
 */
public class PlaneBullet extends CollidableGameObject implements AutoMovable {

    private static final String DISPLAY =
            "  W  \n"
                    + " WWW \n"
                    + " RWR \n"
                    + "WWRWW\n"
                    + "WWRWW";
    private boolean moveRight;
    private boolean moveLeft;

    /**
     * standard constructor takes a reference from gameView and gamePlayManager.
     *
     * @param gameView        create a 960*540 pixels window {@link GameView}.
     * @param gamePlayManager controls game events {@link GamePlayManager}.
     */
    public PlaneBullet(GameView gameView, GamePlayManager gamePlayManager) {

        super(gameView, gamePlayManager);
        this.position.x = position.x;
        this.position.y = position.y;
        rotation = 0;
        width = 5;
        height = 5;
        size = 2;
        speedInPixel = 0.5;

        hitBoxOffsetX = 0;
        hitBoxOffsetY = 0;
        hitBoxWidth = 10;
        hitBoxHeight = 10;
        moveRight = false;
        moveLeft = false;
    }

    @Override
    public void updateStatus() {

        if (moveRight) {
            position.right(speedInPixel + 2);
        } else if (moveLeft) {
            position.left(speedInPixel + 2);
        }
        position.down(speedInPixel);
        rotation += -10;
    }

    @Override
    public void updatePosition() {

    }

    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(DISPLAY, position.x, position.y, size, rotation);
    }

    @Override
    public void reactToCollision(CollidableGameObject other) {
        if (other.getClass() == MainObject.class || other.getClass() == Bullet.class) {
            gamePlayManager.destroy(this);
        }
    }

    @Override
    public void assignPosition(double x, double y) {
        this.position.x = x;
        this.position.y = y;
    }

    /**
     * detect if moving right or not.
     */
    public void moveRight() {
        moveRight = true;
    }

    /**
     * detect if moving left or not.
     */
    public void moveLeft() {
        this.moveLeft = true;
    }
}
