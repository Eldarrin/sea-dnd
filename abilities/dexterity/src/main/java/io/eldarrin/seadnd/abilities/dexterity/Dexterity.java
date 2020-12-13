package io.eldarrin.seadnd.abilities.dexterity;

import io.eldarrin.seadnd.abilities.dexterity.api.RestDexterityAPIVerticle;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DataObject(generateConverter = true)
public class Dexterity {

    private static final Logger logger = LoggerFactory.getLogger(Dexterity.class);

    private int score;
    private int reactionAdj;
    private int missileAttackAdj;
    private int defensiveAdj;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getReactionAdj() {
        return reactionAdj;
    }

    public void setReactionAdj(int reactionAdj) {
        this.reactionAdj = reactionAdj;
    }

    public int getMissileAttackAdj() {
        return missileAttackAdj;
    }

    public void setMissileAttackAdj(int missileAttackAdj) {
        this.missileAttackAdj = missileAttackAdj;
    }

    public int getDefensiveAdj() {
        return defensiveAdj;
    }

    public void setDefensiveAdj(int defensiveAdj) {
        this.defensiveAdj = defensiveAdj;
    }


    public Dexterity (JsonObject json) {
        DexterityConverter.fromJson(json, this);

    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        DexterityConverter.toJson(this, json);
        logger.info(json.encodePrettily());
        return json;
    }

    public String toString() {
        return this.toJson().encodePrettily();
    }
}
