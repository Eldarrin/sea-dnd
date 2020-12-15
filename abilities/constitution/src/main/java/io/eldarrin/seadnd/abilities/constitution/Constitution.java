package io.eldarrin.seadnd.abilities.constitution;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class Constitution {

    public Constitution(JsonObject json) {
        ConstitutionConverter.fromJson(json, this);
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        ConstitutionConverter.toJson(this, json);
        return json;
    }

    public String toString() {
        return this.toJson().encodePrettily();
    }

    private int score;
    private int hitPointAdj;
    private int systemShock;
    private int resurrectionSurvival;
    private int poisonSave;

    public String getRegeneration() {
        return regeneration;
    }

    public void setRegeneration(String regeneration) {
        this.regeneration = regeneration;
    }

    private String regeneration;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getHitPointAdj() {
        return hitPointAdj;
    }

    public void setHitPointAdj(int hitPointAdj) {
        this.hitPointAdj = hitPointAdj;
    }

    public int getSystemShock() {
        return systemShock;
    }

    public void setSystemShock(int systemShock) {
        this.systemShock = systemShock;
    }

    public int getResurrectionSurvival() {
        return resurrectionSurvival;
    }

    public void setResurrectionSurvival(int resurrectionSurvival) {
        this.resurrectionSurvival = resurrectionSurvival;
    }

    public int getPoisonSave() {
        return poisonSave;
    }

    public void setPoisonSave(int poisonSave) {
        this.poisonSave = poisonSave;
    }

}
