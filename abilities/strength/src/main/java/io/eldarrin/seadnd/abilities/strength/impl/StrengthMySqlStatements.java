package io.eldarrin.seadnd.abilities.strength.impl;

public class StrengthMySqlStatements {

    public static final String DROP_CREATE_TABLE = "DROP TABLE strength; CREATE TABLE strength (score INT NOT NULL, " +
            "lowPercentileScore INT, " +
            "highPercentileScore INT, " +
            "hitProb INT, " +
            "damageAdj INT, " +
            "weightAllow INT, " +
            "maxPress INT, " +
            "openDoors INT, " +
            "openLockedDoors INT, " +
            "bendBarsLiftGates INT, " +
            "notes VARCHAR(35) " +
            ");";

    public static final String LOAD_TABLE = "INSERT INTO strength (score, lowPercentileScore, highPercentileScore, " +
                        "hitProb, damageAdj, " +
                        "weightAllow, maxPress, " +
                        "openDoors, openLockedDoors, bendBarsLiftGates, notes) " +
                        "VALUES " +
                        "(1, 0, 0, -5, -4, 1, 3, 1, 0, 0, ''), " +
                        "(2, 0, 0, -3, -2, 1, 5, 1, 0, 0, ''), " +
                        "(3, 0, 0, -3, -1, 5, 10, 2, 0, 0, ''), " +
                        "(4, 0, 0, -2, -1, 10, 25, 3, 0, 0, ''), " +
                        "(5, 0, 0, -2, -1, 10, 25, 3, 0, 0, ''), " +
                        "(6, 0, 0, -1, 0, 20, 55, 4, 0, 0, ''), " +
                        "(7, 0, 0, -1, 0, 20, 55, 4, 0, 0, ''), " +
                        "(8, 0, 0, 0, 0, 35, 90, 5, 0, 1, ''), " +
                        "(9, 0, 0, 0, 0, 35, 90, 5, 0, 1, ''), " +
                        "(10, 0, 0, 0, 0, 40, 115, 6, 0, 2, ''), " +
                        "(11, 0, 0, 0, 0, 40, 115, 6, 0, 2, ''), " +
                        "(12, 0, 0, 0, 0, 45, 140, 7, 0, 4, ''), " +
                        "(13, 0, 0, 0, 0, 45, 140, 7, 0, 4, ''), " +
                        "(14, 0, 0, 0, 0, 55, 170, 8, 0, 7, ''), " +
                        "(15, 0, 0, 0, 0, 55, 170, 8, 0, 7, ''), " +
                        "(16, 0, 0, 0, 1, 70, 195, 9, 0, 10, ''), " +
                        "(17, 0, 0, 1, 1, 85, 220, 10, 0, 13, ''), " +
                        "(18, 0, 0, 1, 2, 110, 255, 11, 0, 16, ''), " +
                        "(18, 0, 50, 1, 3, 135, 280, 12, 0, 20, ''), " +
                        "(18, 51, 75, 2, 3, 160, 305, 13, 0, 25, ''), " +
                        "(18, 76, 90, 2, 4, 185, 330, 14, 0, 30, ''), " +
                        "(18, 91, 99, 2, 5, 235, 380, 15, 3, 35, ''), " +
                        "(18, 100, 100, 3, 6, 335, 480, 16, 6, 40, ''), " +
                        "(19, 0, 0, 3, 7, 485, 640, 16, 8, 50, 'Hill Giant'), " +
                        "(20, 0, 0, 3, 8, 535, 700, 17, 10, 60, 'Stone Giant'), " +
                        "(21, 0, 0, 4, 9, 635, 810, 17, 12, 70, 'Frost Giant'), " +
                        "(22, 0, 0, 4, 10, 785, 970, 18, 14, 80, 'Fire Giant'), " +
                        "(23, 0, 0, 5, 11, 935, 1130, 18, 16, 90, 'Cloud Giant'), " +
                        "(24, 0, 0, 6, 12, 1235, 1440, 19, 17, 95, 'Storm Giant'), " +
                        "(25, 0, 0, 7, 14, 1535, 1750, 19, 18, 99, 'Titan')" +
                        ";";

}
