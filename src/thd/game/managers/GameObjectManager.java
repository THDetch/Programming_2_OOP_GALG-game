package thd.game.managers;

import thd.game.utilities.TooManyGameObjectsException;
import thd.gameView.GameView;
import thd.gameobjects.base.AutoMovable;
import thd.gameobjects.base.CollidableGameObject;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.movable.MainObject;
import thd.gameobjects.unmovable.Ground;
import thd.gameobjects.unmovable.Overlay;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * control game objects.
 */
class GameObjectManager {
    private final MainObject mainObject;

    private final LinkedList<GameObject> gameObjects;
    private final ArrayList<GameObject> toAdd;
    private final ArrayList<GameObject> toRemove;
    private final Overlay overlay;


    /**
     * default constructor.
     *
     * @param gameView        creates a game window with resolution 960*450.
     * @param gamePlayManager controls game events {@link GamePlayManager}.
     */
    GameObjectManager(GameView gameView, GamePlayManager gamePlayManager) {
        mainObject = new MainObject(gameView, gamePlayManager);
        overlay = new Overlay(gameView, gamePlayManager);
        gameObjects = new LinkedList<>();
        gameObjects.add(mainObject);
        gameObjects.add(overlay);
        toAdd = new ArrayList<>();
        toRemove = new ArrayList<>();
    }

    /**
     * move objects and add them to game window
     */
    void updateGameObjects() {
        collidableWithMainObject();
        modifyGameObjectsList();
        ArrayList<CollidableGameObject> collidables = new ArrayList<>(gameObjects.size());
        for (GameObject gameObject : gameObjects) {
            gameObject.updateStatus();
            if (gameObject instanceof AutoMovable) {
                ((AutoMovable) gameObject).updatePosition();
            }
            if (gameObject instanceof CollidableGameObject) {
                collidables.add((CollidableGameObject) gameObject);
            }
            gameObject.addToCanvas();
        }
        moveWorld(0, -2);
        detectCollisionsAndNotifyGameObjects(collidables);
    }

    void addGameObject(GameObject gameObject) {
        toAdd.add(gameObject);
    }

    void removeGameObject(GameObject gameObject) {
        toRemove.add(gameObject);
    }

    private void modifyGameObjectsList() throws TooManyGameObjectsException {
        gameObjects.addAll(toAdd);
        gameObjects.removeAll(toRemove);
        toRemove.clear();
        toAdd.clear();
        if (gameObjects.size() > 1000) {
            throw new TooManyGameObjectsException();
        }
    }

    private void detectCollisionsAndNotifyGameObjects(ArrayList<CollidableGameObject> collidables) {
        for (int index = 0; index < collidables.size(); index++) {
            for (int other = index + 1; other < collidables.size(); other++) {
                if (collidables.get(index).collidesWith(collidables.get(other))) {
                    collidables.get(index).reactToCollision(collidables.get(other));
                    collidables.get(other).reactToCollision(collidables.get(index));
                }
            }
        }
    }

    private void moveWorld(double shiftX, double shiftY) {
        for (GameObject gameObject : gameObjects) {
            if (!(gameObject.getClass() == MainObject.class)) {
                gameObject.worldHasMoved(shiftX, shiftY);
            }
        }
    }

    void resetLevelObjects() {
        for (GameObject gameObject : gameObjects) {
            if (!(gameObject.getClass() == MainObject.class) && !(gameObject.getClass() == Overlay.class)) {
                removeGameObject(gameObject);
            }
        }
    }

    private void collidableWithMainObject() {
        ArrayList<CollidableGameObject> arrayListForMainObject = new ArrayList<>();
        for (GameObject gameObject : gameObjects) {
            if (gameObject instanceof Ground) {
                arrayListForMainObject.add((CollidableGameObject) gameObject);
            }
        }
        mainObject.setArrayListToCollidableWith(arrayListForMainObject);
    }

    MainObject getMainObject() {
        return mainObject;
    }

    Overlay getOverlay() {
        return overlay;
    }
}
