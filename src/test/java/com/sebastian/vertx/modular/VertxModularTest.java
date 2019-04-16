package com.sebastian.vertx.modular;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.codec.BodyCodec;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(VertxExtension.class)
public class VertxModularTest {

    private static Vertx vertx;

    @BeforeAll
    public static void init(Vertx vertx) {
        var router = Router.router(vertx);
        var rc = new HolaHandler();
        VertxModularTest.vertx = vertx;
        router.route(HttpMethod.GET, "/").handler(rc);
        vertx.createHttpServer().requestHandler(router).listen(8080);
    }

    @AfterAll
    public static void fin() {
        vertx.close();
    }

    @Test
    public void test(VertxTestContext testContext) {
        WebClient client = WebClient.create(vertx);
        client.get(8080, "localhost", "/")
                .as(BodyCodec.string())
                .send(ar -> {
                    if (ar.failed()) {
                        testContext.failNow(ar.cause());
                    } else {
                        testContext.verify(() -> Assertions.assertThat(ar.result().body()).isEqualTo("hola vertx"));
                        testContext.completeNow();
                    }
                });;
    }
}
