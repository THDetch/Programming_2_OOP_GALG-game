package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.gameView.GameView;
import thd.gameobjects.base.AutoMovable;
import thd.gameobjects.base.CollidableGameObject;
import thd.gameobjects.unmovable.Ground;

import java.awt.*;
import java.util.ArrayList;

/**
 * create the main object.
 */
public class MainObject extends CollidableGameObject implements AutoMovable {
    private static final String OBJECT =
            "       WZ       \n"
                    + "       WZ       \n"
                    + "       WZ       \n"
                    + "      WWWZ      \n"
                    + "      WWWZ      \n"
                    + "WZ    WRRZ    WZ\n"
                    + "WZ   ZWRRZZ   WZ\n"
                    + "RR  ZWWRRZWZ  RR\n"
                    + "WZ  WWWRRZWW  WZ\n"
                    + "WZ ZWWWWWWWWw WZ\n"
                    + "WZZWWRWWWWRWWZWZ\n"
                    + "WZZWRWWWWWZRWZWZ\n"
                    + "WZZRRWWZZWZRRZWZ\n"
                    + "WZZWWWWZZWZWWZWZ\n"
                    + "WZ   WWZZWW   WZ";

    /**
     * shoots per Seconds.
     */
    private final double shotsPerSecond;

    /**
     * if it is moving left or not.
     */
    private boolean movingLeft;
    /**
     * if it is moving right or not.
     */
    private boolean movingRight;
    /**
     * if it is moving up or not.
     */
    private boolean movingUp;
    /**
     * JumpState enum variable determine how it is jumping.
     */
    private JumpState jumpState;
    /**
     * DamageState enum variable to determine damage state.
     */
    private DamageState damageState;

    private ArrayList<CollidableGameObject> arrayListToCollidableWith;


    /**
     * standard constructor create a gameView window and a position.
     * set the default right to the instance variables.
     *
     * @param gameView        create a 960*540 pixels window {@link GameView}.
     * @param gamePlayManager controls game events {@link GamePlayManager}.
     */
    public MainObject(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);

        position.x = GameView.WIDTH / 2.0;
        // position.y = GameView.HEIGHT / 2.0;
        position.y = GameView.HEIGHT - (15 * 4) - 1;

        speedInPixel = 4;
        size = 4;
        height = 14;
        rotation = 0;
        shotsPerSecond = 300;
        //rock = new Rock(gameView, gamePlayManager);

        hitBoxOffsetX = 0;
        hitBoxOffsetY = 0;
        hitBoxWidth = 63;
        hitBoxHeight = 60;
        numOfLives = 3;
        visible = true;
        jumpState = JumpState.STANDARD;
        damageState = DamageState.STANDARD;

        arrayListToCollidableWith = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Plane: " + position;
    }

    /**
     * moving the object form left to right and reverse.
     */
    @Override
    public void updatePosition() {

    }

    /**
     * the object shapes itself and be added to our game window.
     */
    @Override
    public void addToCanvas() {
        if (!gameView.timerIsActive("notVisible", this) && visible) {
            gameView.addBlockImageToCanvas(OBJECT, position.x, position.y, size, rotation);
        }


        /*if (damageState != DamageState.DEAD) {
            gameView.addTextToCanvas(damageState.display, position.x - (((damageState.fontSize) - 50) / 2.0), (position.y + jumpState.yOffset) - (((damageState.fontSize) - 50) / 2.0), damageState.fontSize, Color.WHITE, rotation);
        }*/
    }


    @Override
    public void updateStatus() {

        //rollingAnimation();
        //jumpingAnimation();
        //  damageAnimation();
    }

    /**
     * moving left while not colliding.
     */
    public void left() {
        position.left(speedInPixel);
        if (collidableWithGround()) {
            position.right(speedInPixel);
        }
    }

    /**
     * moving right by given number of Pixels.
     */
    public void right() {
        position.right(speedInPixel);
        if (collidableWithGround()) {
            position.left(speedInPixel);
        }
    }

    /**
     * moving up by given number of Pixels.
     */
    public void up() {
        position.up(speedInPixel);
        if (collidableWithGround() && position.y <= 0) {
            position.down(speedInPixel);
        }
        if (position.y <= 0) {
            position.down(speedInPixel);
        }
    }

    /**
     * moving down by given number of Pixels.
     */
    public void down() {
        position.down(speedInPixel);
        if (collidableWithGround()) {
            position.up(speedInPixel);
        }
        if (position.y > GameView.HEIGHT - size * height) {
            position.up(speedInPixel);
        }

    }

    /**
     * shooting.
     */
    public void shoot() {

        if (!gameView.timerIsActive("shooting", this) && visible) {
            int id = gameView.playSound("Pang.wav", false);
            Bullet bullet = new Bullet(gameView, gamePlayManager);
            bullet.assignPosition(this.position.x - 10, this.position.y - 18);
            Bullet bullet1 = new Bullet(gameView, gamePlayManager);
            bullet1.assignPosition(this.position.x + 12 * 4, this.position.y - 18);

            gamePlayManager.spawn(bullet);
            gamePlayManager.spawn(bullet1);
            gameView.activateTimer("shooting", this, (long) shotsPerSecond);
        }
    }

    @Override
    public void reactToCollision(CollidableGameObject other) {
        if (other.getClass() != Bullet.class && other.getClass() != Ground.class) {
            numOfLives -= 1;
            gameView.activateTimer("notVisible", this, 200);
        }
        if (numOfLives <= 0) {
            visible = false;
        }
    }

    /**
     * reset position on the initial position.
     */
    public void resetPosition() {
        position.x = GameView.WIDTH / 2.0;
        position.y = GameView.HEIGHT - (15 * 4) - 1;

    }

    /**
     * set rolling animation.
     */
    private void rollingAnimation() {
        if (movingLeft) {
            movingLeft = false;
            rotation += -7;
        } else {
            rotation = 0;
        }
    }

    /**
     * set jumping animation.
     */
    private void jumpingAnimation() {
        if (!gameView.alarmIsSet("jumpingAnimation", this)) {
            gameView.setAlarm("jumpingAnimation", this, 60);
            if (movingRight) {
                movingRight = false;
                switch (jumpState) {
                    case STANDARD:
                        jumpState = JumpState.HALF_UP;
                        break;
                    case HALF_UP:
                        jumpState = JumpState.UP;
                        break;
                    case UP:
                        jumpState = JumpState.HALF_DOWN;
                        break;
                    case HALF_DOWN:
                        jumpState = JumpState.STANDARD;
                        break;
                    default:
                }
            } else {
                jumpState = JumpState.STANDARD;
            }
        } else if (gameView.alarm("jumpingAnimation", this)) {
            gameView.cancelAlarm("jumpingAnimation", this);
        }
    }

    /**
     * set damage animation.
     */
    private void damageAnimation() {
        if (movingUp) {
            switch (damageState) {
                case STANDARD:
                    damageState = DamageState.DAMAGED1;
                    break;
                case DAMAGED1:
                    if (!gameView.alarmIsSet("damaged1", this)) {
                        gameView.setAlarm("damaged1", this, 100);
                        damageState = DamageState.DAMAGED2;
                    } else if (gameView.alarm("damaged1", this)) {
                        gameView.cancelAlarm("damaged1", this);
                    }
                    break;
                case DAMAGED2:
                    if (!gameView.alarmIsSet("damaged2", this)) {
                        gameView.setAlarm("damaged2", this, 600);
                        damageState = DamageState.DEAD;
                    } else if (gameView.alarm("damaged2", this)) {
                        gameView.cancelAlarm("damaged2", this);
                    }
                    break;
                case DEAD:
                    if (!gameView.alarmIsSet("dead", this)) {
                        gameView.setAlarm("dead", this, 1000);
                        visible = true;
                        damageState = DamageState.STANDARD;
                        movingUp = false;
                    } else if (gameView.alarm("dead", this)) {
                        gameView.cancelAlarm("dead", this);
                    }
                    break;
                default:
            }
        }
    }

    /**
     * enum for jumping states.
     */
    enum JumpState {
        STANDARD(0), HALF_UP(-20), UP(-40), HALF_DOWN(-20);
        private int yOffset;

        JumpState(int yOffset) {
            this.yOffset = yOffset;
        }
    }

    /**
     * enum for damage states.
     */
    public enum DamageState {
        /**
         * assigning display and font size values as constructor.
         */
        STANDARD("X", 50), DAMAGED1("Y", 90), DAMAGED2("Z", 130), DEAD("X", 50);
        /**
         * display text.
         */
        private String display;
        /**
         * font size for display text.
         */
        private int fontSize;

        DamageState(String display, int fontSize) {
            this.display = display;
            this.fontSize = fontSize;
        }
    }

    /**
     * return number of lives.
     *
     * @return int numOfLives.
     */
    public int returnNumOfLives() {
        return numOfLives;
    }

    /**
     * reset number of lives on default.
     */
    public void resetNumOfLives() {
        numOfLives = 3;
    }

    /**
     * make it visible again.
     */
    public void resetVisible() {
        visible = true;
    }

    /**
     * ground objects to collidable.
     *
     * @param arrayListToCollidableWith all ground objects to collidable with.
     */
    public void setArrayListToCollidableWith(ArrayList<CollidableGameObject> arrayListToCollidableWith) {
        this.arrayListToCollidableWith = arrayListToCollidableWith;
    }

    /**
     * detect if collides with ground.
     *
     * @return .
     */
    private boolean collidableWithGround() {
        for (CollidableGameObject gameObject : arrayListToCollidableWith) {
            if (collidesWith(gameObject)) {
                return true;
            }
        }
        return false;
    }

    /**
     * show at canvas remaining lives.
     */
    public void addToCanvasRemainingLives() {
        gameView.addBlockImageToCanvas(OBJECT, GameView.WIDTH - (14 * size), 5, size - 2, 0);
        gameView.addTextToCanvas(String.valueOf(numOfLives), GameView.WIDTH - (20), 15, 20, Color.WHITE, rotation);

    }
}
