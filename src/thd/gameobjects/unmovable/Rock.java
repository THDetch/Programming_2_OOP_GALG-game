package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.gameView.GameView;
import thd.gameobjects.base.AutoMovable;
import thd.gameobjects.base.CollidableGameObject;

/**
 * class to create a rock.
 */
public class Rock extends CollidableGameObject implements AutoMovable {

    /**
     * standard class takes two references from gameView and gamePlayManager.
     *
     * @param gameView        creates a game window with resolution 960*450
     * @param gamePlayManager controls game events {@link GamePlayManager}.
     */
    public Rock(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        position.x = -10;
        position.y = 430;
        scaleFactor = 0.1;
        rotation = 0;

        hitBoxOffsetX = 0;
        hitBoxOffsetY = 5;
        hitBoxWidth = 74;
        hitBoxHeight = 104;
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("rock.png", position.x, position.y, scaleFactor, rotation);
    }

    @Override
    public void updateStatus() {

    }

    @Override
    public void updatePosition() {

    }

    @Override
    public void reactToCollision(CollidableGameObject other) {

    }
}
