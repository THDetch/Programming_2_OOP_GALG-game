package thd.game.managers;

import thd.gameView.GameView;
import thd.gameobjects.movable.MainObject;

import java.awt.event.KeyEvent;

/**
 * a class to control inputs from user.
 */

class InputManager {
    private static final boolean DIAGONAL_MOVEMENT_ALLOWED = true;
    private final GameView gameView;
    private final MainObject mainObject;

    /**
     * take references of object gameView and mainObject.
     *
     * @param gameView   create a game window with resolution 960*450.
     * @param mainObject the main Object
     */
    InputManager(GameView gameView, MainObject mainObject) {
        this.gameView = gameView;
        this.mainObject = mainObject;
    }

    /**
     * moving objects depending on user inputs.
     */
    void updateUserInputs() {
        Integer[] pressedKeys = gameView.getKeyCodesOfCurrentlyPressedKeys();
        for (int keyCode : pressedKeys) {
            if (keyCode == KeyEvent.VK_RIGHT) {
                mainObject.right();
            } else if (keyCode == KeyEvent.VK_LEFT) {
                mainObject.left();
            } else if (keyCode == KeyEvent.VK_UP) {
                mainObject.up();
            } else if (keyCode == KeyEvent.VK_DOWN) {
                mainObject.down();
            } else if (keyCode == KeyEvent.VK_SPACE) {
                mainObject.shoot();
            }
            if (!(DIAGONAL_MOVEMENT_ALLOWED)) {
                break;
            }
        }
    }
}
