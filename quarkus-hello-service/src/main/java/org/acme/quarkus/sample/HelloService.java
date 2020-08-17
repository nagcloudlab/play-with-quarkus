package org.acme.quarkus.sample;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HelloService {

	public String getMessage() {
		return "Hello";
	}

}
