package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.gameView.GameView;
import thd.gameobjects.base.CollidableGameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.movable.Bullet;
import thd.gameobjects.movable.MainObject;

/**
 * class for rocks in the middle of play field.
 */
public class BlockRock extends CollidableGameObject {
    private static final String DISPLAY =
                      " GGggGGG GgGGGG \n"
                    + "GGGGGGGGGGGgGGGG\n"
                    + "GggggggggggggGGG\n"
                    + "Gggg        ggGG\n"
                    + "gGgLGGGGGGGGLgGG\n"
                    + "GGgLGGggggGGLgGg\n"
                    + "GGgLGggLLggGlggG\n"
                    + "GGgLGgLLLLgGLgGG\n"
                    + "GGgLGgLLLLgGLgGG\n"
                    + "GGgLGggLLggGlggG\n"
                    + "GGgLGGggggGGLgGg\n"
                    + "gGgLGGGGGGGGLgGG\n"
                    + "Gggg        ggGG\n"
                    + "GggggggggggggGGG\n"
                    + "GGGGGGGGGGGgGGGG\n"
                    + " GGggGGG GgGGGG \n";

    /**
     * default constructor.
     *
     * @param gameView        create a 960 * 540 pixels window {@link GameView}.
     * @param gamePlayManager controls game events {@link GamePlayManager}.
     * @param position        set objects at right place in gameView window{@link Position}.
     */
    public BlockRock(GameView gameView, GamePlayManager gamePlayManager, Position position) {
        super(gameView, gamePlayManager);
        this.position.x = position.x;
        this.position.y = position.y;
        size = 2;
        rotation = 0;

        hitBoxOffsetX = 0;
        hitBoxOffsetY = -1;
        hitBoxHeight = 16 * 2;
        hitBoxWidth = 16 * 2;
    }

    @Override
    public void updateStatus() {

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
}
