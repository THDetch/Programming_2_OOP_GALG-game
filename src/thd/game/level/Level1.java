package thd.game.level;

/**
 * first level.
 */
public class Level1 extends Level {
    /**
     * default constructor.
     *
     * @param difficulty enum from{@link Difficulty}
     */
    public Level1(Difficulty difficulty) {
        super(difficulty);
        levelName = "Level 1";
        levelNumber = 1;
        /*

        G for Ground
        M for BlockRock
        L for BLockObjectLeft
        R for BlockObjectRight
        E for GoldBlockRock
        F for Flower
         */
        levelString1 =
                          "     GGGGGGG                         R GGGGG     n"
                        + "     GGGGGG                     M      GGGGG     n"
                        + "     GGGGG                              GGGG     n"
                        + "     GGGG         M                      GGG     n"
                        + "     GGGG                                 GG     n"
                        + "     GGG          M                       GG     n"
                        + "     GG                                    G     n"
                        + "     G            M                         G    n"
                        + "    G                 M                     G    n"
                        + "     G                                      G    n"
                        + "     GG                                   RG     n"
                        + "     GL                                    G     n"
                        + "     G                                    GG     n"
                        + "     GG                   F               GG     n"
                        + "     GGG                            M   GGGG     n"
                        + "     GGGG                               GGGG     n"
                        + "     GGGGGL      M                     GGGGG     n"
                        + "     GGGGG                          RGGGGGGG     n"
                        + "     GGGGGG              D M          GGGGGG     n"
                        + "     GGGGG                             GGGGG     n"
                        + "     GGG                                GGGG     n"
                        + "     GG                                 RGGG     n"
                        + "     GG                                   GG     n"
                        + "     GGG                                 GGG     n"
                        + "     GGGG                               GGGG     n"
                        + "     GGGGG                               GGG     n"
                        + "     GGGG                                GGG     n";
        levelString2 =
                          "     GGGGG                             GGGGG     n"
                        + "     GGGGGG       D             M       GGGGG     n"
                        + "     GGGGG                              GGGG     n"
                        + "     GGGG         M                      GGG     n"
                        + "     GGG                                   G     n"
                        + "     GG           D                        GG    n"
                        + "     G                                     G     n"
                        + "    G                                      GG    n"
                        + "    G                 M M M                GG    n"
                        + "     G                                     GG    n"
                        + "     GG                                  GGG     n"
                        + "     GGGL                                GGG     n"
                        + "     GGG                               GGGGG     n"
                        + "     GGG                               GGGGG     n"
                        + "     GGG                   D            GGGG     n"
                        + "     GGGG                               GGGG     n"
                        + "     GGGGG                         R GGGGGGG     n"
                        + "     GGGGG L                         GGGGGGG     n"
                        + "     GGGGGG                           GGGGGG     n"
                        + "     GGGGG                             GGGGG     n"
                        + "     GGGG                                GGG     n"
                        + "     GGGGG                             R GGG     n"
                        + "     GGGGGG                               GG     n"
                        + "     GGGGG                               GGG     n"
                        + "     GGGG                                GGG     n"
                        + "     GGG                                 GGG     n"
                        + "     GG                                  GGG     n";

        levelString3 =
                          "    G         GGGG                          G    n"
                        + "    G         GGGGGGGGG                     G    n"
                        + "    G            GGGGGG                     G    n"
                        + "    G               GGG                     G    n"
                        + "    G                  GGG                  G    n"
                        + "    G                    GG                 G    n"
                        + "    G                    GG                 G    n"
                        + "    G                    GG                 G    n"
                        + "    G                    GGG                G    n"
                        + "    G                    GGG                G    n"
                        + "    G                    GGG                G    n"
                        + "    G                    GGG                G    n"
                        + "    G                    GGG                G    n"
                        + "    G                    GGG                G    n"
                        + "    G                    GGG                G    n"
                        + "    G                    GGG                G    n"
                        + "    G                    GGG                G    n"
                        + "    G                    GGG                G    n"
                        + "    G                    GGG                G    n"
                        + "    G                    GGG                G    n"
                        + "    G                    GGG                G    n"
                        + "    G                    GGG                G    n"
                        + "    G                    GGG                G    n"
                        + "    G                    GGG                G    n"
                        + "    G                    GGG                G    n"
                        + "    G                    GGG                G    n"
                        + "    G                    GGG                G    n"
                        + "    G                    GGG                G    n"
                        + "    G                    GGG                G    n"
                        + "    G                    GGG                G    n"
                        + "    G                    GGG                G    n"
                        + "    G                    GGGG               G    n"
                        + "    G                    GGGG               G    n"
                        + "    G                                       G    n"
                        + "    G                                       G    n"
                        + "    G                                       G    n"
                        + "    G                                       G    n"
                        + "    G                                       G    n"
                        + "    G                D D D D D              G    n"
                        + "    G                                       G    n"
                        + "    G                                       G    n"
                        + "    G                                       G    n"
                        + "    G         F             F               G    n"
                        + "    G                                       G    n"
                        + "    G                                       G    n"
                        + "    G                                       G    n"
                        + "    G                                       G    n"
                        + "    G                                       G    n";


        levelString = levelString3 + levelString2 + levelString1 + levelString2;
        speedInPixels = 3;
        parseLevelString(levelString);
    }
}
