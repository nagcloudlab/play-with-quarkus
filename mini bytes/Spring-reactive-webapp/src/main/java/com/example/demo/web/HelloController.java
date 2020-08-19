package com.example.demo.web;

import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class HelloController {

	@GetMapping("/hello")
	public Mono<String> sayHello() {
		System.out.println(Thread.currentThread().getName());
		return Mono.fromSupplier(() -> {
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			String message = "Hello";
			return message;
		});

	}

}
