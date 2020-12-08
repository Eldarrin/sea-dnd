package io.eldarrin.seadnd.abilities.strength.impl;

import io.eldarrin.seadnd.abilities.strength.Strength;
import io.eldarrin.seadnd.abilities.strength.StrengthService;
import io.eldarrin.seadnd.common.wrapper.MySqlRepositoryWrapper;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StrengthMySqlServiceImpl extends MySqlRepositoryWrapper implements StrengthService {

    private static final Logger logger = LoggerFactory.getLogger(StrengthMySqlServiceImpl.class);

    private String TABLE_NAME = "strength";

    public StrengthMySqlServiceImpl(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }

    @Override
    public StrengthService initialisePersistence(Handler<AsyncResult<Void>> resultHandler) {
        String sqlCreate = StrengthMySqlStatements.DROP_CREATE_TABLE + StrengthMySqlStatements.LOAD_TABLE;
        this.execute(sqlCreate).future().onComplete(resultHandler);
        return this;
    }

    @Override
    public StrengthService getStrengthStats(Integer score, Integer percentileScore, Boolean isWarrior,
                                            Handler<AsyncResult<Strength>> resultHandler) {
        Promise<Strength> promise = Promise.promise();
        promise.future().onComplete(resultHandler);
        if (!isWarrior) percentileScore = 0;
        String sql = "select * from strength where score = " + score + " and lowPercentileScore <= " +
                percentileScore + " and highPercentileScore >= " + percentileScore + ";";
        logger.info(sql);
        this.retrieveAll(sql).future().onComplete(res -> {
            // convert and alter to jsonobject
            if (res.succeeded()) {
                for (Row row : res.result()) {
                    JsonObject json = new JsonObject()
                            .put("score", row.getInteger(0))
                            .put("lowPercentileScore", row.getInteger(1))
                            .put("highPercentileScore", row.getInteger(2))
                            .put("hitProb", row.getInteger(3))
                            .put("damageAdj", row.getInteger(4))
                            .put("weightAllow", row.getInteger(5))
                            .put("maxPress", row.getInteger(6))
                            .put("openDoors", row.getInteger(7))
                            .put("openLockedDoors", row.getInteger(8))
                            .put("bendBarsLiftGates", row.getInteger(9))
                            .put("notes", row.getString(10));
                    Strength s = new Strength(json);
                    promise.complete(s);
                }
            } else {
                promise.fail(res.cause());
            }
        });
        return this;

    }

    @Override
    public StrengthService addStrengthStats(Strength strength, Handler<AsyncResult<String>> resultHandler) {

        return this;
    }
}
