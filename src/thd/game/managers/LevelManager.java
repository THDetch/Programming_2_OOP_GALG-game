package thd.game.managers;

import thd.game.level.Level;
import thd.game.level.Level1;
import thd.game.level.Level2;

import java.util.ArrayList;

/**
 * control levels.
 */
class LevelManager {
    private final ArrayList<Level> levels;
    private int levelCounter;

    LevelManager(Level.Difficulty difficulty) {
        levelCounter = 0;
        levels = new ArrayList<>();
        levels.add(new Level1(difficulty));
        levels.add(new Level2(difficulty));
    }

    boolean hasNextLevel() {
        return (levelCounter < levels.size());
    }

    Level nextLevel() throws NoMoreLevelsAvailableException {
        if (hasNextLevel()) {
            return levels.get(levelCounter++);
        } else {
            throw new NoMoreLevelsAvailableException();
        }
    }

    Level firstLevel() {
        levelCounter++;
        return levels.get(0);
    }

    /*void resetLevelCounter() {
        levelCounter = 0;
    }*/

}
