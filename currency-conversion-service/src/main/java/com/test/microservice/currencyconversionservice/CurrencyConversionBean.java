package com.test.microservice.currencyconversionservice;

import java.math.BigDecimal;

public class CurrencyConversionBean {

	private Long id;
	private String from;
	private String to;
	private BigDecimal conversionMultiple;
	private BigDecimal totalCalulateValue;
	private BigDecimal quantity;
	private int port;
	
	
	
	public CurrencyConversionBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CurrencyConversionBean(Long id, String from, String to,
			BigDecimal conversionMultiple, BigDecimal totalCalulateValue,
			BigDecimal quantity, int port) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.conversionMultiple = conversionMultiple;
		this.totalCalulateValue = totalCalulateValue;
		this.quantity = quantity;
		this.port = port;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public BigDecimal getConversionMultiple() {
		return conversionMultiple;
	}
	public void setConversionMultiple(BigDecimal conversionMultiple) {
		this.conversionMultiple = conversionMultiple;
	}
	public BigDecimal getTotalCalulateValue() {
		return totalCalulateValue;
	}
	public void setTotalCalulateValue(BigDecimal totalCalulateValue) {
		this.totalCalulateValue = totalCalulateValue;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	
	
	
}
