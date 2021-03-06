package io.eldarrin.seadnd.abilities.strength;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

@VertxGen
@ProxyGen
public interface StrengthService {

    @Fluent
    StrengthService initialisePersistence(Handler<AsyncResult<Void>> resultHandler);

    @Fluent
    StrengthService getStrengthStats(Integer score, Integer percentileScore, Boolean isWarrior, Handler<AsyncResult<Strength>> resultHandler);

}
