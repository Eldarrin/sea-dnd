package io.eldarrin.seadnd.abilities.intelligence;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

@VertxGen
@ProxyGen
public interface IntelligenceService {


    @Fluent
    IntelligenceService initialisePersistence(Handler<AsyncResult<Void>> resultHandler);

    @Fluent
    IntelligenceService getIntelligenceStats(Integer score, Handler<AsyncResult<Intelligence>> resultHandler);

}
