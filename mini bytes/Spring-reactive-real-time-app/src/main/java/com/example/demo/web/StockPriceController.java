package com.example.demo.web;

import java.time.Duration;
import java.util.Random;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
public class StockPriceController {

	Random random = new Random();

	// 
	@GetMapping(value = "/stock/{id}/price",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Double> getStockPrice(@PathVariable int id) {
		Flux<Double> flux=Flux
				         .interval(Duration.ofSeconds(3))
				         .map(tick->Math.floor(random.nextDouble()*100));
		return flux;
	}

}
