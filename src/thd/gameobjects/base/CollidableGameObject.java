package thd.gameobjects.base;

import thd.game.managers.GamePlayManager;
import thd.gameView.GameView;

import java.awt.*;

/**
 * an abstract class for all colliding objects.
 */
public abstract class CollidableGameObject extends GameObject {
    private final Rectangle hitBox;
    protected double hitBoxOffsetX;
    protected double hitBoxOffsetY;
    protected double hitBoxWidth;
    protected double hitBoxHeight;

    /**
     * default constructor takes two references.
     *
     * @param gameView        create a 960*540 pixels window {@link GameView}.
     * @param gamePlayManager game events {@link GamePlayManager}.
     */
    public CollidableGameObject(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        hitBox = new Rectangle(0, 0, 0, 0);
    }

    /**
     * setting the default coordinates for the hitbox.
     */
    private void updateHitBoxPosition() {
        hitBox.x = (int) (position.x + hitBoxOffsetX);
        hitBox.y = (int) (position.y + hitBoxOffsetY);
        hitBox.width = (int) hitBoxWidth;
        hitBox.height = (int) hitBoxHeight;
        //gameView.addRectangleToCanvas(hitBox.x, hitBox.y, hitBox.width, hitBox.height, 1, false, Color.RED);

    }

    /**
     * check whether an object collide with another.
     *
     * @param other another object from typ {@link CollidableGameObject};
     * @return true if collided.
     */
    public final boolean collidesWith(CollidableGameObject other) {
        this.updateHitBoxPosition();
        other.updateHitBoxPosition();
        return hitBox.intersects(other.hitBox);
    }

    /**
     * how objects react after colliding.
     *
     * @param other only react when colliding with objects from this type.
     */
    public abstract void reactToCollision(CollidableGameObject other);

    @Override
    public void worldHasMoved(double shiftX, double shiftY) {
        super.worldHasMoved(shiftX, shiftY);
        hitBox.x -= shiftX;
        hitBox.y -= shiftY;
    }
}

