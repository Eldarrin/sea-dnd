package io.eldarrin.seadnd.abilities.strength.api;

import io.eldarrin.seadnd.abilities.strength.StrengthService;
import io.eldarrin.seadnd.common.RestAPIVerticle;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

public class RestStrengthAPIVerticle extends RestAPIVerticle {

    public static final String SERVICE_NAME = "strength-abilities-rest-api";

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
        router.get(API_RETRIEVE).handler(this::apiRetrieve);
        startRestService(router, promise, SERVICE_NAME, "strength.abilities", "sea-dnd", "strength.abilities");
    }

    private void apiRetrieve(RoutingContext rc) {
        try {
            String sScore = rc.request().getParam("score");
            Integer score = 7;
            if (sScore != null) {
                score = Integer.parseInt(sScore);
            }
            String sPercentileScore = rc.request().getParam("percentileScore");
            Boolean isWarrior = false;
            if (rc.request().getParam("isWarrior") != null) {
                isWarrior = rc.request().getParam("isWarrior").toLowerCase(Locale.ROOT).equals("true");
            }
            Integer percentileScore = 0;
            if (sPercentileScore != null) {
                percentileScore = Integer.parseInt(sPercentileScore);
            }
            strengthService.getStrengthStats(score, percentileScore, isWarrior, resultHandlerNonEmpty(rc));
        } catch (Exception e) {
            logger.error("retrieve", e);
        }
    }

}
