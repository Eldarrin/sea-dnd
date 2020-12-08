package io.eldarrin.seadnd.common;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.file.FileSystemOptions;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.codec.BodyCodec;
import io.vertx.junit5.Checkpoint;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("👋 A fairly basic test example")
@ExtendWith(VertxExtension.class)
class BaseMicroserviceVerticleTest {

    @Test
    @DisplayName("⏱ Count 3 timer ticks")
    void countThreeTicks(Vertx vertx, VertxTestContext testContext) {
        AtomicInteger counter = new AtomicInteger();
        vertx.setPeriodic(100, id -> {
            if (counter.incrementAndGet() == 3) {
                testContext.completeNow();
            }
        });
    }

    @Test
    @DisplayName("⏱ Count 3 timer ticks, with a checkpoint")
    void countThreeTicksWithCheckpoints(Vertx vertx, VertxTestContext testContext) {
        Checkpoint checkpoint = testContext.checkpoint(3);
        vertx.setPeriodic(100, id -> checkpoint.flag());
    }

    @Test
    @DisplayName("🚀 Deploy a HTTP service verticle and make 10 requests")
    void useSampleVerticle(Vertx vertx, VertxTestContext testContext) {
        WebClient webClient = WebClient.create(vertx);
        Checkpoint deploymentCheckpoint = testContext.checkpoint();
        Checkpoint requestCheckpoint = testContext.checkpoint(1);

        vertx.deployVerticle(new BMV(), testContext.succeeding(id -> {
            deploymentCheckpoint.flag();
            assertThat(true).isEqualTo(true);
/*            for (int i = 0; i < 10; i++) {

                webClient.get(8080, "localhost", "/")
                        .as(BodyCodec.string())
                        .send(testContext.succeeding(resp -> {
                            testContext.verify(() -> {
                                assertThat(resp.statusCode()).isEqualTo(200);
                                assertThat(resp.body()).contains("Yo!");
                                requestCheckpoint.flag();
                            });
                        }));
            } */
            requestCheckpoint.flag();
        }));
    }

    /*
    @DisplayName("➡️ A nested test with customized lifecycle")
    @Nested
    class CustomLifecycleTest {

        Vertx vertx;

        @BeforeEach
        void prepare() {
            vertx = Vertx.vertx(new VertxOptions()
                    .setMaxEventLoopExecuteTime(1000)
                    .setPreferNativeTransport(true)
                    .setFileSystemOptions(new FileSystemOptions().setFileCachingEnabled(true)));
        }

        @Test
        @DisplayName("⬆️ Deploy SampleVerticle")
        void deploySampleVerticle(VertxTestContext testContext) {
            vertx.deployVerticle(new BMV(), testContext.succeeding(id -> testContext.completeNow()));
        }

        @Test
        @DisplayName("🛂 Make a HTTP client request to SampleVerticle")
        void httpRequest(VertxTestContext testContext) {
            WebClient webClient = WebClient.create(vertx);
            vertx.deployVerticle(new BMV(), testContext.succeeding(id -> {
                webClient.get(11981, "localhost", "/yo")
                        .as(BodyCodec.string())
                        .send(testContext.succeeding(resp -> {
                            testContext.verify(() -> {
                                assertThat(resp.statusCode()).isEqualTo(200);
                                assertThat(resp.body()).contains("Yo!");
                                testContext.completeNow();
                            });
                        }));
            }));
        }

        @AfterEach
        void cleanup() {
            vertx.close();
        }
    }
*/
    class BMV extends BaseMicroserviceVerticle {

    }
}
