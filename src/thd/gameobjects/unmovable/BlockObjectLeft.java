package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.gameView.GameView;
import thd.gameobjects.base.CollidableGameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.movable.Bullet;
import thd.gameobjects.movable.MainObject;

/**
 * unmovable gameObject to draw at left side.
 */
public class BlockObjectLeft extends CollidableGameObject {
    private static final String DISPLAY =
                      "  YO        YO  \n"
                    + "  YO   YY   OY  \n"
                    + "  YO   OO   OY  \n"
                    + "  YO  Y YY  OY  \n"
                    + " YOO  OOOY  OOY \n"
                    + " YOO     O  OOY \n"
                    + " YOO OY YYY OOY \n"
                    + " OO OYYYYYYO OO \n"
                    + "YYYO        OYYY\n"
                    + "YYOOOOOOOOOOOOYY\n"
                    + "OYYYYYYYYYYYYYYO\n"
                    + "O    O    O    O\n"
                    + "Y OOOYBBBBBOOO Y\n"
                    + "YOBBOOBOOOBBBBOY\n"
                    + "BBOOBBBBBBBOOOBB";

    /**
     * default constructor.
     *
     * @param gameView        create a 960 * 540 pixels window {@link GameView}.
     * @param gamePlayManager controls game events {@link GamePlayManager}.
     * @param position        set objects at right place in gameView window{@link Position}.
     */
    public BlockObjectLeft(GameView gameView, GamePlayManager gamePlayManager, Position position) {
        super(gameView, gamePlayManager);
        this.position.x = position.x;
        this.position.y = position.y;
        size = 2;
        rotation = 90;

        hitBoxOffsetX = 0;
        hitBoxOffsetY = -1;
        hitBoxHeight = 16 * 2;
        hitBoxWidth = 15 * 2;
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
