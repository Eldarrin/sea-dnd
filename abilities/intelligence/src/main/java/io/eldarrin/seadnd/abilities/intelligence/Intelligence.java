package io.eldarrin.seadnd.abilities.intelligence;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class Intelligence {

    public Intelligence(JsonObject json) {
        IntelligenceConverter.fromJson(json, this);
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        IntelligenceConverter.toJson(this, json);
        return json;
    }

    public String toString() {
        return toJson().encodePrettily();
    }

    private int score;
    private int noOfLanguages;
    private int spellLevel;
    private int chanceToLearnSpell;
    private String maxSpellsPerLevel;
    private String spellIllusionImmunity;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNoOfLanguages() {
        return noOfLanguages;
    }

    public void setNoOfLanguages(int noOfLanguages) {
        this.noOfLanguages = noOfLanguages;
    }

    public int getSpellLevel() {
        return spellLevel;
    }

    public void setSpellLevel(int spellLevel) {
        this.spellLevel = spellLevel;
    }

    public int getChanceToLearnSpell() {
        return chanceToLearnSpell;
    }

    public void setChanceToLearnSpell(int chanceToLearnSpell) {
        this.chanceToLearnSpell = chanceToLearnSpell;
    }

    public String getMaxSpellsPerLevel() {
        return maxSpellsPerLevel;
    }

    public void setMaxSpellsPerLevel(String maxSpellsPerLevel) {
        this.maxSpellsPerLevel = maxSpellsPerLevel;
    }

    public String getSpellIllusionImmunity() {
        return spellIllusionImmunity;
    }

    public void setSpellIllusionImmunity(String spellIllusionImmunity) {
        this.spellIllusionImmunity = spellIllusionImmunity;
    }
}
