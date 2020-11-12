package com.test.microservice.currencyexchangeservice;

import java.math.BigDecimal;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.test.microservice.currencyexchangeservice.exception.ExchangeValueNotFoundException;

@RestController
public class CurrencyExchangeServiceController {

	@Autowired
	Environment environment;
	
	@Autowired
	private ExchangeValueRespository exchangeValueRepository;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	//@HystrixCommand(fallbackMethod="defaultretriveExchangeValue")
	public ExchangeValue retriveExchangeValue(@PathVariable String from,@PathVariable String to){
		ExchangeValue exchangeValue = exchangeValueRepository.findByFromAndTo(from, to);
		if(exchangeValue == null)
			throw new ExchangeValueNotFoundException("Exchange Value not found for "+from+" to "+to+" currency");
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		return exchangeValue;
	}
	
	/*
	 * public ExchangeValue defaultretriveExchangeValue(@PathVariable String
	 * from,@PathVariable String to) { return new
	 * ExchangeValue(1001L,"USD","INR",BigDecimal.valueOf(65)); }
	 */
	
	@PostMapping ("/currency-exchange")
	public ResponseEntity saveExchangeValue(@RequestBody ExchangeValue exchangeValue) {
		ExchangeValue exchangeValueDB = null;
		
		if(exchangeValue.getId() != null) {
			
			exchangeValueDB = exchangeValueRepository.findByFromAndTo(exchangeValue.getFrom(), exchangeValue.getTo());
			if(exchangeValueDB == null) {
				throw new ExchangeValueNotFoundException("Exchange Value not found for "+exchangeValue.getFrom()+" to "+exchangeValue.getTo()+" currency");
			}else if (exchangeValueDB.getId() != exchangeValue.getId()){
				throw new ExchangeValueNotFoundException("Given ID "+exchangeValue.getId()+" Not matching with Database ID "+exchangeValueDB.getId());
			}else {
				exchangeValue = exchangeValueDB;
			}
		}else {
			exchangeValueDB = exchangeValueRepository.findByFromAndTo(exchangeValue.getFrom(), exchangeValue.getTo());
			if(exchangeValueDB != null) {
				throw new ExchangeValueNotFoundException("Exchange Value found for "+exchangeValue.getFrom()+" to "+exchangeValue.getTo()+" currency - ID - "+exchangeValueDB.getId());
			}
		}
			

		
		exchangeValue.setPort(Integer.valueOf(environment.getProperty("local.server.port")));
		exchangeValueRepository.save(exchangeValue);
		
		Map<String,String> uriVariables = new HashMap();
		uriVariables.put("from", exchangeValue.getFrom());
		uriVariables.put("to", exchangeValue.getTo());
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/from/{from}/to/{to}").buildAndExpand(uriVariables).toUri();
		//return exchangeValue;
		
		return new ResponseEntity(location,HttpStatus.CREATED);

	}

}
