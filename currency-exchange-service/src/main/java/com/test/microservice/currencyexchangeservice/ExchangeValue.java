package com.test.microservice.currencyexchangeservice;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class ExchangeValue {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONVERSION_ID_SEQUENCE")
	@SequenceGenerator(sequenceName="conversion_id_sequence", allocationSize=1, name="CONVERSION_ID_SEQUENCE")
	private Long id;
	
	@Column (name="currency_from")
	private String from;
	@Column (name="currency_to")
	private String to;
	private BigDecimal conversionMultiple;
	private int port;
	
	
	public ExchangeValue() {
		// TODO Auto-generated constructor stub
	}

	public ExchangeValue(String from, String to,
			BigDecimal conversionMultiple) {
		super();
		this.from = from;
		this.to = to;
		this.conversionMultiple = conversionMultiple;
	}

	public ExchangeValue(Long id, String from, String to,
			BigDecimal conversionMultiple) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.conversionMultiple = conversionMultiple;
	}


	public Long getId() {
		return id;
	}


	public String getFrom() {
		return from;
	}


	public String getTo() {
		return to;
	}


	public BigDecimal getConversionMultiple() {
		return conversionMultiple;
	}


	public int getPort() {
		return port;
	}


	public void setPort(int port) {
		this.port = port;
	}

	
}
