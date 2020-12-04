package io.eldarrin.seadnd.abilities.strength.impl;

import io.eldarrin.seadnd.abilities.strength.Strength;
import io.eldarrin.seadnd.abilities.strength.StrengthService;
import io.eldarrin.seadnd.abilities.strength.api.RestStrengthAPIVerticle;
import io.eldarrin.seadnd.common.service.RedisWrapper;
import io.eldarrin.seadnd.common.wrapper.MongoRepositoryWrapper;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StrengthMongoServiceImpl extends MongoRepositoryWrapper implements StrengthService {

    //private final MongoClient client;

    private static final Logger logger = LoggerFactory.getLogger(StrengthMongoServiceImpl.class);

    public StrengthMongoServiceImpl(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }

    @Override
    public StrengthService initialisePersistence(Handler<AsyncResult<Void>> resultHandler) {
        return this;
    }

    @Override
    public StrengthService getStrengthStats(Integer score, Integer percentageScore, Handler<AsyncResult<Strength>> resultHandler) {
        /*this.retrieveDocument("strength", score, percentageScore)
                .future().onComplete(r -> {
                    resultHandler.handle(Strength s. = r.result());
        });


        this.selectDocuments(MTA, new JsonObject().put("eventDate", Instant.now()))
                .map(rawList -> rawList.stream().map(MidTermAdjustment::new).collect(Collectors.toList()))
                .setHandler(resultHandler);

*/
        logger.info("in proc get stats");

        Promise<Strength> promise = Promise.promise();
        promise.future().onComplete(resultHandler);
logger.info("future set");
        JsonObject jsonObject = new JsonObject().put("score", 1).put("hitProb", 1);
        Strength s = new Strength(jsonObject);
logger.info("going to complete");
logger.info("service:" + s.toJson().encodePrettily());
        promise.complete(s);
        logger.info("complete");
        return this;

    }

    @Override
    public StrengthService addStrengthStats(Strength strength, Handler<AsyncResult<String>> resultHandler) {
        //this.upsertSingle(strength.toJson(), "strength", resultHandler);
        return this;
    }
}
