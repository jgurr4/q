package com.ple;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import database.verticle.CacherVerticle;
import database.verticle.FileManagerVerticle;
import io.vertx.rxjava3.core.Vertx;

public class QServer {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    vertx.createHttpServer().requestHandler(req -> {
      req.response()
        .putHeader("content-type", "text/plain")
        .end("Hello from Vert.x!");
    }).listen(8888, http -> {
      if (http.succeeded()) {
        startPromise.complete();
        System.out.println("HTTP server started on port 8888");
      } else {
        startPromise.fail(http.cause());
      }
    });
    vertx.deployVerticle(new HyperBaseVerticle());
    vertx.deployVerticle(new DatabaseManagerVerticle());
  }

}
