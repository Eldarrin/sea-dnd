package io.eldarrin.seadnd.abilities.constitution.impl;

public class ConstitutionMySqlStatements {

    public static final String DROP_CREATE_TABLE = "DROP TABLE IF EXISTS constitution; CREATE TABLE constitution (score INT NOT NULL, " +
            "hitPointAdj INT NOT NULL, " +
            "hitPointAdjWar INT NOT NULL, " +
            "systemShock INT NOT NULL, " +
            "resurrectionSurvival INT NOT NULL, " +
            "poisonSave INT NOT NULL, " +
            "regenerationAmount INT NOT NULL, " +
            "regenerationTurns INT NOT NULL);";

    public static final String LOAD_TABLE = "INSERT INTO constitution (score, hitPointAdj, hitPointAdjWar, " +
            "systemShock, resurrectionSurvival, poisonSave, regenerationAmount, regenerationTurns) " +
            "VALUES " +
            "(1,-3,-3,25,30,-2,0,0),"+
            "(2,-2,-2,30,35,-1,0,0),"+
            "(3,-2,-2,35,40,0,0,0),"+
            "(4,-1,-1,40,45,0,0,0),"+
            "(5,-1,-1,45,50,0,0,0),"+
            "(6,-1,-1,50,55,0,0,0),"+
            "(7,0,0,55,60,0,0,0),"+
            "(8,0,0,60,65,0,0,0),"+
            "(9,0,0,65,70,0,0,0),"+
            "(10,0,0,70,75,0,0,0),"+
            "(11,0,0,75,80,0,0,0),"+
            "(12,0,0,80,85,0,0,0),"+
            "(13,0,0,85,90,0,0,0),"+
            "(14,0,0,88,92,0,0,0),"+
            "(15,1,1,90,94,0,0,0),"+
            "(16,2,2,95,96,0,0,0),"+
            "(17,2,3,97,98,0,0,0),"+
            "(18,2,4,99,100,0,0,0),"+
            "(19,2,5,99,100,1,0,0),"+
            "(20,2,5,99,100,1,1,6),"+
            "(21,2,6,99,100,2,1,5),"+
            "(22,2,6,99,100,2,1,4),"+
            "(23,2,6,99,100,3,1,3),"+
            "(24,2,7,99,100,3,1,2),"+
            "(25,2,7,100,100,4,1,1);";


}
