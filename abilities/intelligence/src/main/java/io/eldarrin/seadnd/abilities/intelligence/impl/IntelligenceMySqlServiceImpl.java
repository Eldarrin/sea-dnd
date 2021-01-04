package io.eldarrin.seadnd.abilities.intelligence.impl;

import io.eldarrin.seadnd.abilities.intelligence.Intelligence;
import io.eldarrin.seadnd.abilities.intelligence.IntelligenceService;
import io.eldarrin.seadnd.common.wrapper.MySqlRepositoryWrapper;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Row;

public class IntelligenceMySqlServiceImpl extends MySqlRepositoryWrapper implements IntelligenceService {

    public IntelligenceMySqlServiceImpl(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }

    @Override
    public IntelligenceService initialisePersistence(Handler<AsyncResult<Void>> resultHandler) {
        String sqlCreate = IntelligenceMySqlStatements.DROP_CREATE_TABLE + IntelligenceMySqlStatements.LOAD_TABLE;
        this.execute(sqlCreate).future().onComplete(resultHandler);
        return this;
    }

    @Override
    public IntelligenceService getIntelligenceStats(Integer score, Handler<AsyncResult<Intelligence>> resultHandler) {
        Promise<Intelligence> promise = Promise.promise();
        promise.future().onComplete(resultHandler);
        String sql = "select * from intelligence where score = " + score + ";";
        this.retrieveAll(sql).future().onComplete(res -> {
            // convert and alter to jsonobject
            if (res.succeeded()) {
                if (res.result().size() == 0) {
                    promise.complete(null);
                } else {
                    for (Row row : res.result()) {
                        JsonObject json = new JsonObject()
                                .put("score", row.getInteger(0))
                                .put("noOfLanguages", row.getInteger(1))
                                .put("spellLevel", row.getInteger(2))
                                .put("chanceToLearnSpell", row.getInteger(3));

                        if (row.getBoolean(5)) {
                            json.put("maxSpellsPerLevel", "All");
                        } else {
                            json.put("maxSpellsPerLevel", row.getInteger(4).toString());
                        }
                        if (row.getInteger(6) == 0) {
                            json.put("spellIllusionImmunity", "-");
                        } else if (row.getInteger(6) == 1) {
                            json.put("spellIllusionImmunity", "1st level illusions");
                        } else if (row.getInteger(6) == 2) {
                            json.put("spellIllusionImmunity", "1st to 2nd level illusions");
                        } else if (row.getInteger(6) == 3) {
                            json.put("spellIllusionImmunity", "1st to 3rd level illusions");
                        } else {
                            json.put("spellIllusionImmunity", "1st to " + row.getInteger(6) + "th level illusions");
                        }

                        Intelligence i = new Intelligence(json);
                        promise.complete(i);
                    }
                }
            } else {
                promise.fail(res.cause());
            }
        });
        return this;

    }
}
