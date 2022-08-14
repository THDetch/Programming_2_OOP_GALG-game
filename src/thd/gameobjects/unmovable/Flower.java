package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.gameView.GameView;
import thd.gameobjects.base.CollidableGameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.movable.Bullet;
import thd.gameobjects.movable.MainObject;
import thd.gameobjects.movable.PlaneBullet;

/**
 * class for shooting flower.
 */
public class Flower extends CollidableGameObject {
    // T for Brown, Q for orange and D for Gold Yellow.
    private static final String DISPLAY =
            "     DDDD     \n"
                    + "   DDDDDDDD   \n"
                    + "  DTTDDDDTTD  \n"
                    + " DDTDDDDDDTDD \n"
                    + " DDTTDDDDTTDD \n"
                    + " DDDDDDDDDDDD \n"
                    + " DDDDDDDDDDDD \n"
                    + " DDDDDDDDDDDD \n"
                    + " DDDDTTTTDDDD \n"
                    + "  DDTTTTTTDD  \n"
                    + "  DDDTTTTDDD  \n"
                    + "   DDDDDDDD   \n"
                    + "  DTTDDDDTTD  \n"
                    + " DQQQ    QQQD \n"
                    + "DQQQ      QQQD\n"
                    + "DQQ        QQD";

    /**
     * default constructor.
     *
     * @param gameView        create a 960 * 540 pixels window {@link GameView}.
     * @param gamePlayManager controls game events {@link GamePlayManager}.
     * @param position        set objects at right place in gameView window{@link Position}.
     */
    public Flower(GameView gameView, GamePlayManager gamePlayManager, Position position) {
        super(gameView, gamePlayManager);
        this.position.x = position.x;
        this.position.y = position.y;
        size = 2;
        rotation = 0;

        hitBoxOffsetX = -1;
        hitBoxOffsetY = -2;
        hitBoxHeight = 16 * 2;
        hitBoxWidth = 15 * 2;

    }

    @Override
    public void reactToCollision(CollidableGameObject other) {
        if (other.getClass() == MainObject.class || other.getClass() == Bullet.class) {
            gamePlayManager.destroy(this);
        }
    }

    @Override
    public void updateStatus() {
        if (!gameView.timerIsActive("shooting", this)) {
            PlaneBullet planeBullet = new PlaneBullet(gameView, gamePlayManager);
            planeBullet.assignPosition(position.x + 10, position.y + 10);
            gamePlayManager.spawn(planeBullet);


            PlaneBullet planeBullet1 = new PlaneBullet(gameView, gamePlayManager);
            planeBullet1.assignPosition(position.x + 20, position.y + 10);
            planeBullet1.moveRight();
            gamePlayManager.spawn(planeBullet1);


            PlaneBullet planeBullet2 = new PlaneBullet(gameView, gamePlayManager);
            planeBullet2.assignPosition(position.x - 10, position.y + 10);
            planeBullet2.moveLeft();
            gamePlayManager.spawn(planeBullet2);

            gameView.activateTimer("shooting", this, 3000L);
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(DISPLAY, position.x, position.y, size, rotation);

    }
}
