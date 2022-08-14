package thd.game.managers;

import thd.game.level.Level;
import thd.gameView.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.movable.Plane;
import thd.gameobjects.unmovable.*;
import thd.screens.EndScreen;
import thd.screens.StartScreen;

import java.util.LinkedList;

/**
 * controls game events.
 */
public class GamePlayManager {
    private final GameView gameView;
    private final LinkedList<GameObject> createdGameObjects;
    private GameObjectManager gameObjectManager;

    /**
     * points gained.
     */
    public int points;
    /**
     * determine if the game is over or not.
     */
    boolean gameOver;
    private LevelManager levelManager;
    private Level currentLevel;
    private Boolean alreadyShow;
    private Level.Difficulty difficulty;


    /**
     * standard constructor takes references of object gameView.
     *
     * @param gameView create a game window with resolution 960*450.
     */
    public GamePlayManager(GameView gameView) throws NoMoreLevelsAvailableException {
        int id = gameView.playSound("BackGroundTrack.wav", true);
        this.gameView = gameView;
        createdGameObjects = new LinkedList<>();
        points = 0;
        gameOver = false;
        levelManager = new LevelManager(Level.Difficulty.STANDARD);
        alreadyShow = false;
    }

    /**
     * update Gameplay.
     */
    void updateGamePlay() {
        if (!alreadyShow) {
            StartScreen.showStartScreen(gameView, difficulty);
            alreadyShow = true;
        }
        if (gameOver()) {
            if (!gameView.alarmIsSet("gameOver", this)) {
                gameView.setAlarm("gameOver", this, 2000);
                gameObjectManager.getOverlay().showMessage("Game Over", 2);

            } else if (gameView.alarm("gameOver", this)) {
                EndScreen.showEndScreen(gameView, points);
                initializeGame();
            }
        } else {
            //initializeGame();
            if (levelIsOver()) {
                try {
                    currentLevel = levelManager.nextLevel();
                    initializeLevel();
                } catch (NoMoreLevelsAvailableException ignored) {
                }
            }
        }
        creatNewPlanes();
    }

    private boolean gameOver() {
        if (gameObjectManager.getMainObject().returnNumOfLives() <= 0) {
            return true;
        } else if (levelIsOver()) {
            return !levelManager.hasNextLevel();
        }
        return false;
    }

    void setGameObjectManager(GameObjectManager gameObjectManager) {
        this.gameObjectManager = gameObjectManager;

    }

    /**
     * help method to spawn new objects.
     *
     * @param gameObject {@link GameObject}.
     */
    public void spawn(GameObject gameObject) {
        gameObjectManager.addGameObject(gameObject);
    }

    /**
     * help method to destroy some object.
     *
     * @param gameObject {@link GameObject}.
     */
    public void destroy(GameObject gameObject) {
        gameObjectManager.removeGameObject(gameObject);
    }

    private void initializeLevel() {
        gameView.activateTimer("levelRuns", this, 15_000);
        gameObjectManager.resetLevelObjects();
        parsingArrayListsOfObjects();
        gameObjectManager.getMainObject().resetPosition();
        gameObjectManager.getOverlay().showMessage(currentLevel.levelName, 2);
    }


    private boolean levelIsOver() {
        return !gameView.timerIsActive("levelRuns", this);
    }

    private void initializeGame() {
        difficulty = FileManager.readDifficultyFromDisc();
        //FileManager.writeDifficultyToDisc(difficulty);

        levelManager = new LevelManager(difficulty);
        points = 0;
        gameOver = false;
        //levelManager = new LevelManager(Level.Difficulty.STANDARD);
        resetMainObjectStatus();
        currentLevel = levelManager.firstLevel();
        initializeLevel();
    }


    private void creatNewPlanes() {
        if (!gameOver()) {
            if (!gameView.alarmIsSet("creatingPlanes", this)) {
                Plane newPlanes = new Plane(gameView, this);
                spawn(newPlanes);
                newPlanes.setShotsPerSecond(currentLevel.shootsPerSeconds);
                newPlanes.setSpeedInPixel(currentLevel.speedInPixels);
                createdGameObjects.add(newPlanes);
                gameView.setAlarm("creatingPlanes", this, 1500L);
            } else if (gameView.alarm("creatingPlanes", this)) {
                gameView.cancelAlarm("creatingPlanes", this);
            }
        }
    }

    private void resetMainObjectStatus() {
        gameObjectManager.getMainObject().resetNumOfLives();
        gameObjectManager.getMainObject().resetVisible();
    }

    private void parsingArrayListsOfObjects() {
        for (Position position : currentLevel.arrayListOfGround) {
            spawn(new Ground(gameView, this, position));
        }
        for (Position position : currentLevel.arrayListOfBlockRock) {
            spawn(new BlockRock(gameView, this, position));
        }
        for (Position position : currentLevel.arrayListOfBLockObjectLeft) {
            spawn(new BlockObjectLeft(gameView, this, position));
        }
        for (Position position : currentLevel.arrayListOfBLockObjectRight) {
            spawn(new BlockObjectRight(gameView, this, position));
        }
        for (Position position : currentLevel.arrayListOfGoldBlockRock) {
            spawn(new GoldBlockRock(gameView, this, position));
        }
        for (Position position : currentLevel.arrayListOfFlower) {
            spawn(new Flower(gameView, this, position));
        }
    }

}
