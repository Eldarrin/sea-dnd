package io.eldarrin.seadnd.abilities.constitution.api;

import io.eldarrin.seadnd.abilities.constitution.ConstitutionService;
import io.eldarrin.seadnd.common.RestAPIVerticle;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

public class RestConstitutionAPIVerticle extends RestAPIVerticle {

    public static final String SERVICE_NAME = "constitution-abilities-rest-api";

    private static final String API_RETRIEVE = "/";

    private static final Logger logger = LoggerFactory.getLogger(RestConstitutionAPIVerticle.class);

    private final ConstitutionService constitutionService;

    public RestConstitutionAPIVerticle(ConstitutionService constitutionService) {
        super();
        this.constitutionService = constitutionService;
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
        startRestService(router, promise, SERVICE_NAME, "constitution.abilities", "sea-dnd", "constitution.abilities");
    }

    private void apiRetrieve(RoutingContext rc) {
        try {
            String sScore = rc.request().getParam("score");
            Integer score = 4;
            if (sScore != null) {
                score = Integer.parseInt(sScore);
            }
            Boolean isWarrior = false;
            if (rc.request().getParam("isWarrior") != null) {
                isWarrior = rc.request().getParam("isWarrior").toLowerCase(Locale.ROOT).equals("true");
            }
            constitutionService.getConstitutionStats(score, isWarrior, resultHandlerNonEmpty(rc));
        } catch (Exception e) {
            logger.error("retrieve", e);
        }
    }
}
