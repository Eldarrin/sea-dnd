package io.eldarrin.seadnd.abilities.dexterity;

import io.eldarrin.seadnd.abilities.dexterity.api.RestDexterityAPIVerticle;
import io.eldarrin.seadnd.abilities.dexterity.impl.DexterityMySqlServiceImpl;
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

public class DexterityVerticle extends BaseMicroserviceVerticle {

    private DexterityService dexterityService;

    private static final Logger logger = LoggerFactory.getLogger(DexterityVerticle.class);

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        ConfigStoreOptions store = new ConfigStoreOptions()
                .setType("configmap")
                .setConfig(new JsonObject()
                        .put("namespace", "sea-dnd")
                        .put("name", "dexterity.abilities")
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

                dexterityService = new DexterityMySqlServiceImpl(vertx, mysqlConfig);

                initPersistence();
                deployRestVerticle();
            } else {
                logger.error("no mongo config found");
            }
        });

    }

    private Future<Void> deployRestVerticle() {
        Promise<String> promise = Promise.promise();
        vertx.deployVerticle(new RestDexterityAPIVerticle(dexterityService),
                new DeploymentOptions().setConfig(config()), promise);
        return promise.future().map(r -> null);
    }

    private Promise<Void> initPersistence() {
        Promise<Void> promise = Promise.promise();
        dexterityService.initialisePersistence(promise);
        return promise;
    }

    @Override
    public void stop() throws Exception {
        super.stop();


    }
}
