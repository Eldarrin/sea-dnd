package io.eldarrin.seadnd.abilities.dexterity.impl;

import io.eldarrin.seadnd.abilities.dexterity.Dexterity;
import io.eldarrin.seadnd.abilities.dexterity.DexterityService;
import io.eldarrin.seadnd.abilities.dexterity.api.RestDexterityAPIVerticle;
import io.eldarrin.seadnd.common.wrapper.MySqlRepositoryWrapper;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DexterityMySqlServiceImpl extends MySqlRepositoryWrapper implements DexterityService {

    private static final Logger logger = LoggerFactory.getLogger(DexterityMySqlServiceImpl.class);

    public DexterityMySqlServiceImpl(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }

    @Override
    public DexterityService initialisePersistence(Handler<AsyncResult<Void>> resultHandler) {
        logger.info("creating");
        String sqlCreate = DexterityMySqlStatements.DROP_CREATE_TABLE + DexterityMySqlStatements.LOAD_TABLE;
        logger.info(sqlCreate);
        this.execute(sqlCreate).future().onComplete(resultHandler);
        return this;
    }

    @Override
    public DexterityService getDexterityStats(Integer score, Handler<AsyncResult<Dexterity>> resultHandler) {
        Promise<Dexterity> promise = Promise.promise();
        promise.future().onComplete(resultHandler);
        String sql = "select * from dexterity where score = " + score + ";";
        this.retrieveAll(sql).future().onComplete(res -> {
            if (res.succeeded()) {
                for (Row row : res.result()) {
                    JsonObject json = new JsonObject()
                            .put("score", row.getInteger(0))
                            .put("reactionAdj", row.getInteger(1))
                            .put("missileAttackAdj", row.getInteger(2))
                            .put("defensiveAdj", row.getInteger(3));
                    Dexterity d = new Dexterity(json);
                    logger.info(json.encodePrettily());
                    promise.complete(d);
                }
            } else {
                promise.fail(res.cause());
            }
        });
        return this;
    }
}
