package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.gameView.GameView;
import thd.gameobjects.base.AutoMovable;
import thd.gameobjects.base.CollidableGameObject;
import thd.gameobjects.base.Position;

import java.awt.*;

/**
 * class for the round.
 */
public class Ground extends CollidableGameObject implements AutoMovable {
    /**
     * default constructor.
     *
     * @param gameView        create a 960 * 540 pixels window {@link GameView}.
     * @param gamePlayManager controls game events {@link GamePlayManager}.
     * @param position        set objects at right place in gameView window{@link Position}.
     */
    public Ground(GameView gameView, GamePlayManager gamePlayManager, Position position) {
        super(gameView, gamePlayManager);

        this.position.x = position.x;
        this.position.y = position.y;
        width = 40;
        height = 40;
        size = 1;
        hitBoxOffsetX = -20;
        hitBoxOffsetY = -20;
        hitBoxWidth = 40;
        hitBoxHeight = 40;

    }

    @Override
    public void updateStatus() {

    }

    @Override
    public void addToCanvas() {
        gameView.addOvalToCanvas(position.x, position.y, width, height, size, true, Color.decode("#105208"));
        // gameView.addOvalToCanvas(position.x, position.y, width, height, size, true, Color.black);
    }

    @Override
    public void updatePosition() {

    }

    @Override
    public void reactToCollision(CollidableGameObject other) {

    }
}
