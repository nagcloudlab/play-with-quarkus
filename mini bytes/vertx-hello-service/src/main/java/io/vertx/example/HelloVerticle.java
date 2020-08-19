package io.vertx.example;

import java.util.concurrent.TimeUnit;

import io.vertx.core.AbstractVerticle;

public class HelloVerticle extends AbstractVerticle {

	@Override
	public void start() throws Exception {
		vertx.eventBus().consumer("hello.vertx.addrs", msg -> {
			System.out.println("HV : "+Thread.currentThread());
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			msg.reply("Hello : vertx");
		});

		vertx.eventBus().consumer("hello.name.vertx.addrs", msg -> {
			String name = (String) msg.body();
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			msg.reply("Hello : vertx :" + name);
		});

	}

}
