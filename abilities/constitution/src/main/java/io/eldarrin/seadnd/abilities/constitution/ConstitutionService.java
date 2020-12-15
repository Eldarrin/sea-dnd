package io.eldarrin.seadnd.abilities.constitution;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

@VertxGen
@ProxyGen
public interface ConstitutionService {

    @Fluent
    ConstitutionService initialisePersistence(Handler<AsyncResult<Void>> resultHandler);

    @Fluent
    ConstitutionService getConstitutionStats(Integer score, Boolean isWarrior, Handler<AsyncResult<Constitution>> resultHandler);

}
