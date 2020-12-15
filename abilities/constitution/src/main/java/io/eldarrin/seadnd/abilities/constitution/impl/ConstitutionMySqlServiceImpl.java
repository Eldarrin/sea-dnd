package io.eldarrin.seadnd.abilities.constitution.impl;

import io.eldarrin.seadnd.abilities.constitution.Constitution;
import io.eldarrin.seadnd.abilities.constitution.ConstitutionService;
import io.eldarrin.seadnd.common.wrapper.MySqlRepositoryWrapper;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConstitutionMySqlServiceImpl extends MySqlRepositoryWrapper implements ConstitutionService {

    private static final Logger logger = LoggerFactory.getLogger(ConstitutionMySqlServiceImpl.class);

    public ConstitutionMySqlServiceImpl(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }

    @Override
    public ConstitutionService initialisePersistence(Handler<AsyncResult<Void>> resultHandler) {
        String sqlCreate = ConstitutionMySqlStatements.DROP_CREATE_TABLE + ConstitutionMySqlStatements.LOAD_TABLE;
        this.execute(sqlCreate).future().onComplete(resultHandler);
        return this;
    }

    @Override
    public ConstitutionService getConstitutionStats(Integer score, Boolean isWarrior, Handler<AsyncResult<Constitution>> resultHandler) {
        Promise<Constitution> promise = Promise.promise();
        promise.future().onComplete(resultHandler);
        String sql = "select * from constitution where score = " + score + ";";
        this.retrieveAll(sql).future().onComplete(res -> {
            if (res.succeeded()) {
                for (Row row : res.result()) {
                    String regen = row.getInteger(6).toString() + "/" + row.getInteger(7).toString();
                    if (score == 25) {
                        regen = regen + " turn";
                    } else if (score > 19) {
                        regen = regen + " turns";
                    } else {
                        regen = "nil";
                    }
                    JsonObject json = new JsonObject()
                            .put("score", row.getInteger(0))
                            .put("systemShock", row.getInteger(3))
                            .put("resurrectionSurvival", row.getInteger(4))
                            .put("poisonSave", row.getInteger(5))
                            .put("regeneration", regen);

                    if (isWarrior) {
                        json.put("hitPointAdj", row.getInteger(2));
                    } else {
                        json.put("hitPointAdj", row.getInteger(1));
                    }

                    Constitution c = new Constitution(json);
                    promise.complete(c);
                }
            } else {
                promise.fail(res.cause());
            }
        });
        return this;
    }
}

