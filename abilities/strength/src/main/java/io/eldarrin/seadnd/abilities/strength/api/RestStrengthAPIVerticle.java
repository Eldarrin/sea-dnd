package io.eldarrin.seadnd.abilities.strength.api;

import io.eldarrin.seadnd.abilities.strength.Strength;
import io.eldarrin.seadnd.abilities.strength.StrengthService;
import io.eldarrin.seadnd.common.RestAPIVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestStrengthAPIVerticle extends RestAPIVerticle {

    public static final String SERVICE_NAME = "strength-abilities-rest-api";

    private static final String API_ADD = "/add";
    private static final String API_RETRIEVE = "/";

    private static final Logger logger = LoggerFactory.getLogger(RestStrengthAPIVerticle.class);

    private StrengthService strengthService;

    public RestStrengthAPIVerticle(StrengthService strengthService) {
        super();
        this.strengthService = strengthService;
    }

    @Override
    public void start(Promise<Void> promise) {
        super.start();
        final Router router = Router.router(vertx);
        // body handler
        router.route().handler(BodyHandler.create());
        // API route handler
        addHealthHandler(router, promise);
        router.post(API_ADD).handler(this::apiAdd);
        router.get(API_RETRIEVE).handler(this::apiRetrieve);
        startRestService(router, promise, SERVICE_NAME, "strength.abilities", "sea-dnd", "strength.abilities");
    }

    private void apiAdd(RoutingContext rc) {
/*        try {
            Strength strength = new Strength(); //Strength(new JsonObject(rc.getBodyAsString()));
            strengthService.addStrengthStats(strength, resultHandler(rc, r -> {
                //String result = new JsonObject().put("message", "strength-added")
                  //      .put("stats", strength.toJson()).encodePrettily();
                //rc.response().setStatusCode(201).putHeader("content-type", "application/json").end(result);
            }));

        } catch (DecodeException e) {
            badRequest(rc, e);
        }*/
    }

    private void apiRetrieve(RoutingContext rc) {
        try {
            logger.info("in proc");
            String sScore = rc.request().getParam("score");
            Integer score = 0;
            if (sScore != null) {
                score = Integer.parseInt(sScore);
            }
            logger.info("int parse score");
            String sPercentageScore = rc.request().getParam("percentageScore");
            Integer percentageScore = 0;
            if (sPercentageScore != null) {
                percentageScore = Integer.parseInt(sPercentageScore);
            }
            logger.info("int parse percScore");
            strengthService.getStrengthStats(score, percentageScore, resultHandlerNonEmpty(rc));
        } catch (Exception e) {
            logger.error("retrieve", e);
        }
    }

}
