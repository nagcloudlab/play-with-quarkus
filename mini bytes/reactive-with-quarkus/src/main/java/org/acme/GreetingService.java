package org.acme;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.vertx.ConsumeEvent;

@ApplicationScoped
public class GreetingService {

	@ConsumeEvent("greeting.addr")
	public String consume1(String name) {
		// ...
		return name.toUpperCase();
	}

}