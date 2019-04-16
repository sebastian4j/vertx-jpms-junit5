package com.sebastian.vertx.modular;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

/**
 *
 * @author Sebastián Ávila A.
 */
public class HolaHandler implements Handler<RoutingContext> {

    @Override
    public void handle(RoutingContext rc) {
        rc.response().putHeader("content-type", "text/plain").end("hola vertx");
    }
}
