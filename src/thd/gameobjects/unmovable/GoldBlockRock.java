package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.gameView.GameView;
import thd.gameobjects.base.CollidableGameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.movable.Bullet;
import thd.gameobjects.movable.MainObject;

/**
 * class for the Edges.
 */
public class GoldBlockRock extends CollidableGameObject {
    /**
     * Y for normal yellow
     * D for gold yellow
     */

    private static final String DISPLAY =
            "  YDDLLLLLLDDLLD\n"
                    + " YYDLLYYDDLLDLLL\n"
                    + "YYDDLYYYDDLLDDLL\n"
                    + "YYDDLYYYDDLLDDLL\n"
                    + "YDDDLYYYDDLLDDDL\n"
                    + "YDDDLYYYDDDLDDDL\n"
                    + "YDLLLYYYDDDLLDLL\n"
                    + "YDLYYLYYDDLDDLDL\n"
                    + "YDLLLLLYDLLLLLDL\n"
                    + "YDDLYYLLLLDDLDDL\n"
                    + "YDDLYYYLLDDDLDDL\n"
                    + "YYDDLYYLLDDLDDDL\n"
                    + " YDDLYYYDDDLDDLL\n"
                    + " YYDLLYYDDLLDDL \n"
                    + "  YYLLLYDLLLDL  \n"
                    + "  LYLLLDDLLLD   \n";

    /**
     * default constructor.
     *
     * @param gameView        create a 960 * 540 pixels window {@link GameView}.
     * @param gamePlayManager controls game events {@link GamePlayManager}.
     * @param position        set objects at right place in gameView window{@link Position}.
     */
    public GoldBlockRock(GameView gameView, GamePlayManager gamePlayManager, Position position) {
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
        //gameView.addOvalToCanvas(position.x, position.y, width, height, 1, true, Color.black);
        gameView.addBlockImageToCanvas(DISPLAY, position.x, position.y, size, rotation);
    }


    @Override
    public void reactToCollision(CollidableGameObject other) {
        if (other.getClass() == MainObject.class || other.getClass() == Bullet.class) {
            gamePlayManager.destroy(this);
        }
    }
}
