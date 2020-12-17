package io.eldarrin.seadnd.abilities.intelligence.impl;

public class IntelligenceMySqlStatements {

    public static final String DROP_CREATE_TABLE = "DROP TABLE IF EXISTS intelligence; CREATE TABLE intelligence (score INT NOT NULL, " +
            "noOfLanguages INT, " +
            "spellLevel INT, " +
            "chanceToLearnSpell INT, " +
            "maxSpellsPerLevel INT, " +
            "allSpellsAllowed BOOLEAN, " +
            "spellIllusionImmunity INT, " +
            ");";

    public static final String LOAD_TABLE = "INSERT INTO intelligence (score, noOfLanguages, spellLevel, " +
            "chanceToLearnSpell, maxSpellsPerLevel, " +
            "allSpellsAllowed, spellIllusionImmunity) " +
            "VALUES " +
            "(1,0,0,0,0,FALSE,0), " +
            "(2,1,0,0,0,FALSE,0), " +
            "(3,1,0,0,0,FALSE,0), " +
            "(4,1,0,0,0,FALSE,0), " +
            "(5,1,0,0,0,FALSE,0), " +
            "(6,1,0,0,0,FALSE,0), " +
            "(7,1,0,0,0,FALSE,0), " +
            "(8,1,0,0,0,FALSE,0), " +
            "(9,2,4,35,6,FALSE,0), " +
            "(10,2,5,40,7,FALSE,0), " +
            "(11,2,5,45,7,FALSE,0), " +
            "(12,3,6,50,7,FALSE,0), " +
            "(13,3,6,55,9,FALSE,0), " +
            "(14,4,7,60,9,FALSE,0), " +
            "(15,4,7,65,11,FALSE,0), " +
            "(16,5,8,70,11,FALSE,0), " +
            "(17,6,8,75,14,FALSE,0), " +
            "(18,7,9,85,18,FALSE,0), " +
            "(19,8,9,95,0,TRUE,1), " +
            "(20,9,9,96,0,TRUE,2), " +
            "(21,10,9,97,0,TRUE,3), " +
            "(22,11,9,98,0,TRUE,4), " +
            "(23,12,9,99,0,TRUE,5), " +
            "(24,15,9,100,0,TRUE,6), " +
            "(25,20,9,100,0,TRUE,7);";

}
