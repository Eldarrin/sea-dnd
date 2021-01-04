package io.eldarrin.seadnd.abilities.intelligence.api;

import io.eldarrin.seadnd.abilities.intelligence.IntelligenceService;
import io.eldarrin.seadnd.common.RestAPIVerticle;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

public class RestIntelligenceAPIVerticle extends RestAPIVerticle {

    public static final String SERVICE_NAME = "intelligence-abilities-rest-api";

    private static final String API_RETRIEVE = "/";

    private static final Logger logger = LoggerFactory.getLogger(RestIntelligenceAPIVerticle.class);

    private final IntelligenceService intelligenceService;

    public RestIntelligenceAPIVerticle(IntelligenceService intelligenceService) {
        super();
        this.intelligenceService = intelligenceService;
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
        startRestService(router, promise, SERVICE_NAME, "intelligence.abilities", "sea-dnd", "intelligence.abilities");
    }

    private void apiRetrieve(RoutingContext rc) {
        try {
            String sScore = rc.request().getParam("score");
            if (sScore != null) {
                intelligenceService.getIntelligenceStats(Integer.parseInt(sScore), resultHandlerNonEmpty(rc));
            } else {
                notFound(rc);
            }
        } catch (Exception e) {
            logger.error("retrieve", e);
            badRequest(rc, e);
        }
    }
}
