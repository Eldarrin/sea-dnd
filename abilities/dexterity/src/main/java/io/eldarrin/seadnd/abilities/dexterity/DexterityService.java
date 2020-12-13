package io.eldarrin.seadnd.abilities.dexterity;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

@VertxGen
@ProxyGen
public interface DexterityService {

    @Fluent
    DexterityService initialisePersistence(Handler<AsyncResult<Void>> resultHandler);

    @Fluent
    DexterityService getDexterityStats(Integer score, Handler<AsyncResult<Dexterity>> resultHandler);
}
