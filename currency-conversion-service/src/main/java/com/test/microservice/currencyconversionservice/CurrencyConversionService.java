package com.test.microservice.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
//@RibbonClient(name="currency-conversion-service", configuration=CurrencyServiceRibbonConfiguration.class )
public class CurrencyConversionService {

	Logger logger = LoggerFactory.getLogger(CurrencyConversionService.class);
	
	@Autowired
	CurrencyExchangeServiceProxy proxy;
	
	@GetMapping(path = "/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	@HystrixCommand(fallbackMethod="defaultretriveExchangeValue")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity){
		
		CurrencyConversionBean response = proxy.retriveExchangeValue(from, to);
		
		logger.info("{}", response);
		
		return new CurrencyConversionBean(response.getId(),from,to,response.getConversionMultiple(),quantity.multiply(response.getConversionMultiple()),quantity,response.getPort());
		
	}

	@GetMapping(path = "/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	@HystrixCommand(fallbackMethod="defaultretriveExchangeValue")
	public CurrencyConversionBean convertCurrency(@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity){
		
		Map<String,String> uriVariables = new HashMap<String,String>();
		
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		
		ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class, uriVariables);
		
		CurrencyConversionBean response = responseEntity.getBody();
		
		return new CurrencyConversionBean(response.getId(),from,to,response.getConversionMultiple(),quantity.multiply(response.getConversionMultiple()),quantity,response.getPort());
		
	}

	public CurrencyConversionBean defaultretriveExchangeValue(@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity) {
		
		
		return new CurrencyConversionBean(1001L,
				"USD",
				"INR",
				BigDecimal.valueOf(65),
				quantity.multiply(new BigDecimal(65.00)),
				quantity,
				8000);
	}	
}
