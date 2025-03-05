package com.alaa.microservices.currency_exchange_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CurrencyExchangeController {
	@Autowired
	private CurrencyExchangeRepository repository;
	
	@Autowired
	private Environment e1;
		
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(
			@PathVariable String from,
			@PathVariable String to) {

		CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);
		if(currencyExchange ==null) {
			throw new RuntimeException
				("Unable to Find data for " + from + " to " + to);
		}
		String port = e1.getProperty("local.server.port");
		String host = e1.getProperty("HOSTNAME");
		String version = "v11";
		currencyExchange.setEnvironment(port + " " + version + " " + host);
		return currencyExchange;
		
	}

}