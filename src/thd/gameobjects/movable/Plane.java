package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.gameView.GameView;
import thd.gameobjects.base.AutoMovable;
import thd.gameobjects.base.CollidableGameObject;

import java.util.Random;


/**
 * create a plane object.
 */
public class Plane extends CollidableGameObject implements AutoMovable {
    private static final String DISPLAY =
            "   CC   \n"
                    + "  CCCB  \n"
                    + " CCCCCB \n"
                    + "CBCCCCCB\n"
                    + "CBBCCBCC\n"
                    + "C BCC CB\n"
                    + "C BCB CC\n"
                    + "C BCB CB\n"
                    + "C BCB CB\n"
                    + "C BCB CC\n"
                    + "C BCC CB\n"
                    + "CBBCCBCC\n"
                    + "CBCCCCCB\n"
                    + " CCCCCB \n"
                    + "  CCCB  \n"
                    + "   CC   \n";
    private String display;
    private Status status;
    private boolean flyFromLeftToRight;
    /**
     * number of lives.
     */
    private int numOfLives;
    /**
     * set number of shots per second.
     */
    private double shotsPerSecond;

    private double speedInPixel;


    /**
     * standard constructor create a gameView window and a position.
     * set the default variables.
     *
     * @param gameView        create a 960*540 pixels window {@link GameView}.
     * @param gamePlayManager controls game events {@link GamePlayManager}.
     */
    public Plane(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);

        position.x = GameView.WIDTH / 2.0;
        position.y = 0;

        speedInPixel = 4;
        rotation = 0;
        size = 3;
        numOfLives = 3;
        width = 8;
        height = 16;
        flyFromLeftToRight = true;

        hitBoxOffsetX = 0;
        hitBoxOffsetY = 0;
        hitBoxWidth = width * size;
        hitBoxHeight = height * size;

        shotsPerSecond = 2000;
        status = Status.STANDARD;

    }


    /**
     * update position.
     */
    @Override
    public void updatePosition() {

       /* if (position.x >= 960 - (16 * 5)) {
            flyFromLeftToRight = false;
        }
        if (position.x <= 0) {
            flyFromLeftToRight = true;
        }
        if (flyFromLeftToRight) {
            position.right(speedInPixel);
        } else {
            position.left(speedInPixel);
        }
        position.y = position.y + Math.round(Math.sin(Math.toRadians(position.x * 2.5)));*/
        position.down(speedInPixel);

        Random random = new Random();

        if (random.nextBoolean()) {
            position.left(speedInPixel);

        } else {
            position.right(speedInPixel + 3);
        }
    }


    /**
     * show the object on the window.
     */
    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(DISPLAY, position.x, position.y, size, 0);
    }

    /**
     * if the object touch the bottom it will disappear.
     */
    @Override
    public void updateStatus() {
        shooting();
        switch (status) {
            case STANDARD:
                display = "spacecraft.png";
                break;
            case EXPLODING:
                display = "exploding.png";
                break;
            case DAMAGED:
            case EXPLODED:
                display = "damaged.png";
                break;
            default:
                display = "spacecraft.png";
        }
    }

    @Override
    public void reactToCollision(CollidableGameObject other) {
        if (other.getClass() == MainObject.class || other.getClass() == Bullet.class) {
            gamePlayManager.destroy(this);
        }
    }

    private void shooting() {

        if (!gameView.timerIsActive("planeShooting", this)) {
            double randomTimer = 500 + Math.random() * (shotsPerSecond + 500);
            shoot();
            gameView.activateTimer("planeShooting", this, (long) randomTimer);
        }
    }

    private enum Status {
        STANDARD, DAMAGED, EXPLODING, EXPLODED
    }

    /**
     * set a new value for shots per second.
     *
     * @param shotsPerSecond shots per Second.
     */
    public void setShotsPerSecond(double shotsPerSecond) {
        this.shotsPerSecond = shotsPerSecond;
    }

    /**
     * set a new value for speed in pixel.
     *
     * @param speedInPixel speed in pixels.
     */
    public void setSpeedInPixel(double speedInPixel) {
        this.speedInPixel = speedInPixel;
    }

    private void shoot() {
    }
}
