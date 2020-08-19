package io.vertx.example;

import java.util.concurrent.TimeUnit;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class MainVerticle extends AbstractVerticle {

	@Override
	public void start() {

//    	vertx.createHttpServer().requestHandler(req->{
//    		req.response().end("Hello : vertx");
//    	}).listen(8080);

//		vertx.createHttpServer().requestHandler(req -> {
//			if (req.path().equals("/api/hello")) {
//				req.response().end("Hello : vertx");
//			}
//		}).listen(8080);

		DeploymentOptions opts=new DeploymentOptions().setWorker(true).setInstances(8);
		
		vertx.deployVerticle("io.vertx.example.HelloVerticle",opts);

		// Routing
		Router router = Router.router(vertx);

		router.get("/api/hello").handler(this::hello);
		router.get("/api/hello/:name").handler(this::helloWithName);

		vertx.createHttpServer().requestHandler(router).listen(8080);

	}

	public void hello(RoutingContext ctx) {
		System.out.println("MV : "+Thread.currentThread());
		vertx.eventBus().request("hello.vertx.addrs", "", reply -> {
			ctx.request().response().end((String) reply.result().body());
		});
	}

	public void helloWithName(RoutingContext ctx) {
		String name = ctx.pathParam("name");
		vertx.eventBus().request("hello.vertx.addrs", name, reply -> {
			ctx.request().response().end((String) reply.result().body());
		});
	}

}
