package com.sebastian.vertx.modular;

import com.sebastian.vertx.modular.HolaHandler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;

/**
 * hola mundo con vertx!.
 *
 * java --module-path target/libs:target/vertx-modular-1.0-SNAPSHOT.jar
        --module com.sebastian.vertx.modular/com.sebastian.vertx.modular.VertxModular
 *
 * @author Sebastián Ávila A.
 */
public class VertxModular {

  public static void main(String[] args) {
    var vertx = Vertx.vertx();
    var router = Router.router(vertx);
    var rc = new HolaHandler();
    router.route(HttpMethod.GET, "/").handler(rc);
    vertx.createHttpServer().requestHandler(router).listen(8080);
  }
}
