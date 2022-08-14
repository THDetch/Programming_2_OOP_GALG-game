package thd.game.managers;

import thd.gameView.GameView;

import java.awt.*;

/**
 * control all classes which control all game objects.
 */
public class GameLoopManager {
    private final GameView gameView;
    private final GameObjectManager gameObjectManager;
    private final InputManager inputManager;
    private final GamePlayManager gamePlayManager;


    /**
     * create a game window and decorate it.
     */

    public GameLoopManager() throws NoMoreLevelsAvailableException {
        gameView = new GameView();
        gamePlayManager = new GamePlayManager(gameView);
        gameObjectManager = new GameObjectManager(gameView, gamePlayManager);
        gamePlayManager.setGameObjectManager(gameObjectManager);
        inputManager = new InputManager(gameView, gameObjectManager.getMainObject());
        gameView.setWindowTitle("Galg");
        gameView.setStatusText(" Ahmed Attia - Java Programmierung SS 2022");
        gameView.setWindowIcon("Target.png");
        createColors();
    }

    /**
     * move the objects and print them. for moving objects use the method {@link GameObjectManager#updateGameObjects()}
     */
    public void startGame() throws NoMoreLevelsAvailableException {
        while (!gamePlayManager.gameOver) {
            gamePlayManager.updateGamePlay();
            inputManager.updateUserInputs();
            gameObjectManager.updateGameObjects();
            gameView.printCanvas();
            gameView.addImageToCanvas("BlueGround.png", 0, 0, 1.15, 0);
            gameView.addRectangleToCanvas(0, -1000, 100, 2000, 1, true, Color.decode("#105208"));
            gameView.addRectangleToCanvas(859, -1000, 100, 2000, 1, true, Color.decode("#105208"));
            gameObjectManager.getMainObject().addToCanvasRemainingLives();
            gameView.addTextToCanvas(String.valueOf(gamePlayManager.points), GameView.WIDTH / 2.0 + 100, 0, 25, Color.WHITE, 0);

        }
    }

    private void createColors() {
        gameView.setColorForBlockImage('Z', Color.GRAY);
        gameView.setColorForBlockImage('X', Color.decode("#105208"));
        gameView.setColorForBlockImage('G', Color.decode("#105208"));
        gameView.setColorForBlockImage('D', Color.decode("#BE8400"));
        // Cayenne Orange Color for flowers.
        gameView.setColorForBlockImage('Q', Color.decode("#F0560D"));
        // Dates Brown for flowers.
        gameView.setColorForBlockImage('T', Color.decode("#6B1E15"));
    }
}