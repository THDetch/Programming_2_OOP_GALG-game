package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.gameView.GameView;
import thd.gameobjects.base.AutoMovable;
import thd.gameobjects.base.CollidableGameObject;
import thd.gameobjects.unmovable.Ground;

import java.awt.*;

/**
 * a class to create a bullet.
 */
public class Bullet extends CollidableGameObject implements AutoMovable {

    /**
     * standard constructor takes a reference from gameView and gamePlayManager.
     *
     * @param gameView        create a 960*540 pixels window {@link GameView}.
     * @param gamePlayManager controls game events {@link GamePlayManager}.
     */
    public Bullet(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        speedInPixel = 7;
        size = 10;

        hitBoxOffsetX = 8;
        hitBoxOffsetY = 20;
        hitBoxWidth = 8;
        hitBoxHeight = 9;

    }

    @Override
    public void updateStatus() {

    }

    @Override
    public void updatePosition() {
        position.up(speedInPixel);
    }

    @Override
    public void addToCanvas() {
        gameView.addTextToCanvas(".", position.x, position.y, 50, Color.white, 90);
    }

    @Override
    public void reactToCollision(CollidableGameObject other) {
        if (other.getClass() != MainObject.class && other.getClass() != Ground.class) {
            gamePlayManager.points += 100;
            gamePlayManager.destroy(this);
        }
        if (other.getClass() == Ground.class) {
            gamePlayManager.destroy(this);
        }
    }

}
