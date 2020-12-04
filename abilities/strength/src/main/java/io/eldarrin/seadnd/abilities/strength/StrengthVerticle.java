package io.eldarrin.seadnd.abilities.strength;

import io.eldarrin.seadnd.abilities.strength.api.RestStrengthAPIVerticle;
import io.eldarrin.seadnd.abilities.strength.impl.StrengthMongoServiceImpl;
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

public class StrengthVerticle extends BaseMicroserviceVerticle {

    private StrengthService strengthService;

    private static final Logger logger = LoggerFactory.getLogger(StrengthVerticle.class);

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        ConfigStoreOptions store = new ConfigStoreOptions()
                .setType("configmap")
                .setConfig(new JsonObject()
                        .put("namespace", "sea-dnd")
                        .put("name", "strength.abilities")
                );

        ConfigRetriever retriever = ConfigRetriever.create(vertx,
                new ConfigRetrieverOptions().addStore(store));

        retriever.getConfig(res -> {
            if (res.succeeded()) {
                JsonObject mongoConfig = new JsonObject()
                        .put("host", res.result().getString("database.host"))
                        .put("port", res.result().getInteger("database.port"))
                        .put("db_name", "abilities");
                        //.put("username", System.getenv("DB_USERNAME"))
                        //.put("password", System.getenv("DB_PASSWORD"))

                strengthService = new StrengthMongoServiceImpl(vertx, mongoConfig);
                initPersistence(strengthService);
                deployRestVerticle();
            } else {
                logger.error("no mongo config found");
            }
        });

    }

    private Future<Void> deployRestVerticle() {
        Promise<String> promise = Promise.promise();
        vertx.deployVerticle(new RestStrengthAPIVerticle(strengthService),
                new DeploymentOptions().setConfig(config()), promise);
        return promise.future().map(r -> null);
    }

    private Promise<Void> initPersistence(StrengthService service) {
        Promise<Void> promise = Promise.promise();
        service.initialisePersistence(promise);
        return promise;
    }

    @Override
    public void stop() throws Exception {
        super.stop();


    }
}
