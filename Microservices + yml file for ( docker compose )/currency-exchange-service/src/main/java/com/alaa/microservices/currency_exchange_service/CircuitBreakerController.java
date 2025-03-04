package com.alaa.microservices.currency_exchange_service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class CircuitBreakerController {
	private Logger logger = 
			LoggerFactory.getLogger(CircuitBreakerController.class);
	
//  to use Retry :@Retry(name = "sample-api" ,fallbackMethod ="hardcodedResponse" ) 
//	add this to app.propertues :
//  resilience4j.retry.instances.sample-api.maxAttempts=5
//	resilience4j.retry.instances.sample-api.waitDuration=1s
//  resilience4j.retry.instances.sample-api.enableExponentialBackoff=true	
	
	
// to use RateLimiter : @RateLimiter(name="default")
// add this to app.propertues :
// resilience4j.ratelimiter.instances.default.limitForPeriod=2
// resilience4j.ratelimiter.instances.default.limitRefreshPeriod=10s
// resilience4j.bulkhead.instances.default.maxConcurrentCalls=10
// resilience4j.bulkhead.instances.sample-api.maxConcurrentCalls=10	
	
   @CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")
	@GetMapping("/sample-api")
	public String sampleApi() {
		logger.info("Sample api call received");
		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/null", 
					String.class);
		return forEntity.getBody();
	}
	public String hardcodedResponse(Exception ex) {
		return "fallback-response";
	}

}