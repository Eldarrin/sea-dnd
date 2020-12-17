package io.eldarrin.seadnd.abilities.intelligence;

import io.eldarrin.seadnd.abilities.intelligence.api.RestIntelligenceAPIVerticle;
import io.eldarrin.seadnd.abilities.intelligence.impl.IntelligenceMySqlServiceImpl;
import io.eldarrin.seadnd.common.BaseMicroserviceVerticle;
import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IntelligenceVerticle extends BaseMicroserviceVerticle {

    private IntelligenceService intelligenceService;

    private static final Logger logger = LoggerFactory.getLogger(IntelligenceVerticle.class);

    @Override
    public void start(Promise<Void> startPromise) {
        ConfigStoreOptions store = new ConfigStoreOptions()
                .setType("configmap")
                .setConfig(new JsonObject()
                        .put("namespace", "sea-dnd")
                        .put("name", "intelligence.abilities")
                );

        ConfigRetriever retriever = ConfigRetriever.create(vertx,
                new ConfigRetrieverOptions().addStore(store));

        retriever.getConfig(res -> {
            if (res.succeeded()) {
                JsonObject mysqlConfig = new JsonObject()
                        .put("port", 3306)
                        .put("host", "mysql")
                        .put("username", System.getenv("DB_USERNAME"))
                        .put("password", System.getenv("DB_PASSWORD"))
                        .put("database", System.getenv("DB_NAME"));

                intelligenceService = new IntelligenceMySqlServiceImpl(vertx, mysqlConfig);

                initPersistence();
                deployRestVerticle();
            } else {
                logger.error("no mongo config found");
            }
        });

    }

    private Future<Void> deployRestVerticle() {
        Promise<String> promise = Promise.promise();
        vertx.deployVerticle(new RestIntelligenceAPIVerticle(intelligenceService),
                new DeploymentOptions().setConfig(config()), promise);
        return promise.future().map(r -> null);
    }

    private Promise<Void> initPersistence() {
        Promise<Void> promise = Promise.promise();
        intelligenceService.initialisePersistence(promise);
        return promise;
    }

    @Override
    public void stop() throws Exception {
        super.stop();


    }
}
