package thd.game.level;

import thd.gameView.GameView;
import thd.gameobjects.base.Position;

import java.util.ArrayList;

/**
 * class level to control levels.
 */
public class Level {
    /**
     * level name.
     */
    public String levelName;
    /**
     * level number.
     */
    protected int levelNumber;
    /**
     * difficulty of level.
     */
    private final Difficulty difficulty;
    /**
     * shootsPerSeconds depending on levels.
     */
    public double shootsPerSeconds;
    /**
     * speedInPixels depending on levels.
     */
    public double speedInPixels;
    /*
     * background depending on levels.

    public String background;
    /**
     * scale of background.

    public double scale;
    /**
     * numOfEnemies.

    public int numOfEnemies;*/
    /**
     * ArrayList of positions for BLockObjectLeft {@link thd.gameobjects.unmovable.BlockObjectLeft}.
     */
    public ArrayList<Position> arrayListOfBLockObjectLeft;
    /**
     * ArrayList of positions for BLockObjectRight {@link thd.gameobjects.unmovable.BlockObjectRight}.
     */
    public ArrayList<Position> arrayListOfBLockObjectRight;

    /**
     * ArrayList of positions for Ground {@link thd.gameobjects.unmovable.Ground}.
     */
    public ArrayList<Position> arrayListOfGround;
    /**
     * ArrayList of positions for BLockRock {@link thd.gameobjects.unmovable.BlockRock}.
     */
    public ArrayList<Position> arrayListOfBlockRock;
    /**
     * Arraylist for GoldBlockRock {@link thd.gameobjects.unmovable.GoldBlockRock}.
     */
    public ArrayList<Position> arrayListOfGoldBlockRock;
    /**
     * Arraylist for Flowers {@link thd.gameobjects.unmovable.Flower}.
     */
    public ArrayList<Position> arrayListOfFlower;
    /**
     * final level String to parse.
     */
    protected String levelString;
    /**
     * sub level string 1.
     */
    protected String levelString1;
    /**
     * sub level string 2.
     */
    protected String levelString2;
    /**
     * sub level string 2.
     */
    protected String levelString3;

    /**
     * default constructor to set difficulty of levels.
     *
     * @param difficulty from enum {@link Difficulty}.
     */
    public Level(Difficulty difficulty) {
        this.difficulty = difficulty;

        arrayListOfBLockObjectLeft = new ArrayList<>();
        arrayListOfBLockObjectRight = new ArrayList<>();
        arrayListOfBlockRock = new ArrayList<>();
        arrayListOfGround = new ArrayList<>();
        arrayListOfGoldBlockRock = new ArrayList<>();
        arrayListOfFlower = new ArrayList<>();

    }


    /**
     * enum to set Difficulty.
     */
    public enum Difficulty {
        /**
         * Different difficulties.
         */
        EASY, STANDARD
    }

    /**
     * parse Level string to create objects.
     *
     * @param level String levelString.
     */
    void parseLevelString(String level) {
        StringBuilder sb = new StringBuilder();
        sb.append(level);
        int row = 0;
        int column = 0;
        while (!sb.isEmpty()) {
            if (sb.charAt(0) == 'n') {
                row++;
                column = 0;
            } else {
                Position calculatedPosition = new Position(column * GameView.WIDTH / 48.0, -2500 + (row * GameView.HEIGHT / 27.0));
                switch (sb.charAt(0)) {
                    case 'G':
                        arrayListOfGround.add(calculatedPosition);
                        break;
                    case 'M':
                        arrayListOfBlockRock.add(calculatedPosition);
                        break;
                    case 'L':
                        arrayListOfBLockObjectLeft.add(calculatedPosition);
                        break;
                    case 'R':
                        arrayListOfBLockObjectRight.add(calculatedPosition);
                        break;
                    case 'D':
                        arrayListOfGoldBlockRock.add(calculatedPosition);
                        break;
                    case 'F':
                        arrayListOfFlower.add(calculatedPosition);
                        break;
                    default:

                }
                column++;
            }
            sb.deleteCharAt(0);
        }
    }
}
