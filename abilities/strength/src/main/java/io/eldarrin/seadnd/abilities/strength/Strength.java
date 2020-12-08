package io.eldarrin.seadnd.abilities.strength;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class Strength {

    private Integer score;
    private Integer lowPercentileScore;
    private Integer highPercentileScore;
    private Integer hitProb;
    private Integer damageAdj;
    private Integer weightAllow;
    private Integer maxPress;
    private Integer openDoors;
    private Integer openLockedDoors;
    private Integer bendBarsLiftGates;
    private String notes;

    public Strength(JsonObject json) {
        StrengthConverter.fromJson(json, this);
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        StrengthConverter.toJson(this, json);
        return json;
    }

    public String toString() {
        return this.toJson().encodePrettily();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getLowPercentileScore() {
        return lowPercentileScore;
    }

    public void setLowPercentileScore(Integer lowPercentileScore) {
        this.lowPercentileScore = lowPercentileScore;
    }

    public Integer getHighPercentileScore() {
        return highPercentileScore;
    }

    public void setHighPercentileScore(Integer highPercentileScore) {
        this.highPercentileScore = highPercentileScore;
    }

    public Integer getHitProb() {
        return hitProb;
    }

    public void setHitProb(Integer hitProb) {
        this.hitProb = hitProb;
    }

    public Integer getDamageAdj() {
        return damageAdj;
    }

    public void setDamageAdj(Integer damageAdj) {
        this.damageAdj = damageAdj;
    }

    public Integer getWeightAllow() {
        return weightAllow;
    }

    public void setWeightAllow(Integer weightAllow) {
        this.weightAllow = weightAllow;
    }

    public Integer getMaxPress() {
        return maxPress;
    }

    public void setMaxPress(Integer maxPress) {
        this.maxPress = maxPress;
    }

    public Integer getOpenDoors() {
        return openDoors;
    }

    public void setOpenDoors(Integer openDoors) {
        this.openDoors = openDoors;
    }

    public Integer getOpenLockedDoors() {
        return openLockedDoors;
    }

    public void setOpenLockedDoors(Integer openLockedDoors) {
        this.openLockedDoors = openLockedDoors;
    }

    public Integer getBendBarsLiftGates() {
        return bendBarsLiftGates;
    }

    public void setBendBarsLiftGates(Integer bendBarsLiftGates) {
        this.bendBarsLiftGates = bendBarsLiftGates;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


}
