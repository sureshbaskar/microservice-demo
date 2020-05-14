package com.test.microservice.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeValueRespository extends 
JpaRepository <ExchangeValue,Long>{

	ExchangeValue findByFromAndTo(String from,String to);
}
