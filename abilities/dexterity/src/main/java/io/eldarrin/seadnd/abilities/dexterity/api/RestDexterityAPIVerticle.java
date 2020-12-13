package io.eldarrin.seadnd.abilities.dexterity.api;

import io.eldarrin.seadnd.abilities.dexterity.DexterityService;
import io.eldarrin.seadnd.common.RestAPIVerticle;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

public class RestDexterityAPIVerticle extends RestAPIVerticle {

    public static final String SERVICE_NAME = "dexterity-abilities-rest-api";

    private static final String API_RETRIEVE = "/";

    private static final Logger logger = LoggerFactory.getLogger(RestDexterityAPIVerticle.class);

    private final DexterityService dexterityService;

    public RestDexterityAPIVerticle(DexterityService DexterityService) {
        super();
        this.dexterityService = DexterityService;
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
        startRestService(router, promise, SERVICE_NAME, "dexterity.abilities", "sea-dnd", "dexterity.abilities");
    }

    private void apiRetrieve(RoutingContext rc) {
        try {
            String sScore = rc.request().getParam("score");
            Integer score = 4;
            if (sScore != null) {
                score = Integer.parseInt(sScore);
            }
            dexterityService.getDexterityStats(score, resultHandlerNonEmpty(rc));
        } catch (Exception e) {
            logger.error("retrieve", e);
        }
    }

}
