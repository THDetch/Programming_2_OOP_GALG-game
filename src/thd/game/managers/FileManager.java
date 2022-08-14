package thd.game.managers;

import thd.game.level.Level;

import java.io.*;

/**
 * File manager.
 */
public class FileManager {
    /*
     * write Difficulty To Disc .
     *
     * @param difficulty {@link Level.Difficulty}.


    static void writeDifficultyToDisc(Level.Difficulty difficulty) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\aa03907\\Desktop\\user\\home\\wichtelgame.txt"));
            bufferedWriter.write(difficulty.name());
            bufferedWriter.close();
        } catch (IOException ignored) {

        }
    }*/

    /**
     * read Difficulty From Disc.
     *
     * @return {@link Level.Difficulty}.
     */
    static Level.Difficulty readDifficultyFromDisc() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\aa03907\\Desktop\\user\\home\\wichtelgame.txt"));
            return Level.Difficulty.valueOf(bufferedReader.readLine());
        } catch (IOException e) {
            System.err.println("Die Datei wichtelgame.txt konnte nicht gelesen werden.");
        }
        return Level.Difficulty.STANDARD;
    }
}