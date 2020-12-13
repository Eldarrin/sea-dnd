package io.eldarrin.seadnd.abilities.dexterity.impl;

public class DexterityMySqlStatements {

    public static final String DROP_CREATE_TABLE = "DROP TABLE IF EXISTS dexterity; CREATE TABLE dexterity (score INT NOT NULL, " +
            "reactionAdj INT, " +
            "missileAttackAdj INT, " +
            "defensiveAdj INT " +
            ");";

    public static final String LOAD_TABLE = "INSERT INTO dexterity (score, reactionAdj, missileAttackAdj, " +
            "defensiveAdj) " +
            "VALUES " +
            "(1, -6, -6, 5), " +
            "(2, -4, -4, 5), " +
            "(3, -3, -3, 4), " +
            "(4, -2, -2, 3), " +
            "(5, -1, -1, 2), " +
            "(6, 0, 0, 1), " +
            "(7, 0, 0, 0), " +
            "(8, 0, 0, 0), " +
            "(9, 0, 0, 0), " +
            "(10, 0, 0, 0), " +
            "(11, 0, 0, 0), " +
            "(12, 0, 0, 0), " +
            "(13, 0, 0, 0), " +
            "(14, 0, 0, 0), " +
            "(15, 0, 0, -1), " +
            "(16, 1, 1, -2), " +
            "(17, 2, 2, -3), " +
            "(18, 2, 2, -4), " +
            "(19, 3, 3, -4), " +
            "(20, 3, 3, -4), " +
            "(21, 4, 4, -5), " +
            "(22, 4, 4, -5), " +
            "(23, 4, 4, -5), " +
            "(24, 5, 5, -6), " +
            "(25, 5, 5, -6) " +
            ";";
}
