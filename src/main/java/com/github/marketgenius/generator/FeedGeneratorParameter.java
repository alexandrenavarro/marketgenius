package com.github.marketgenius.generator;

import org.joda.time.DateTime;

public class FeedGeneratorParameter {

	

	// Bid Ask
	private double defaultPrice = 100.0;
	private double priceTick = 1.0 / 64;
	private int maxPriceTickShift = 5;
	
	public double getGeneratedBid() {
		return defaultPrice - priceTick - ((int)(Math.random()  * maxPriceTickShift)) * priceTick;
	}
	
	public double getGeneratedAsk() {
		return defaultPrice + priceTick + ((int)(Math.random()  * maxPriceTickShift)) * priceTick; 
	}
	
	private int minQty = 1_000_000;
	private double qtyTick = 1_000_000; 
	private double maxQtyTickShift = 8;
	
	public int getGeneratedQty() {
		return minQty + (int) ((int) (Math.random() * maxQtyTickShift) * qtyTick);
	}
	
	
	// Timestamp
	private DateTime fromDateTime;
	private DateTime toDateTime;
	private long tickEveryNMs;
	
	
	private String market;
	private String feedCode;
	
	
	
	
	public double getDefaultPrice() {
		return defaultPrice;
	}

	public void setDefaultPrice(double defaultPrice) {
		this.defaultPrice = defaultPrice;
	}

	public double getPriceTick() {
		return priceTick;
	}

	public void setPriceTick(double priceTick) {
		this.priceTick = priceTick;
	}

	public int getMaxPriceTickShift() {
		return maxPriceTickShift;
	}

	public void setMaxPriceTickShift(int maxPriceTickShift) {
		this.maxPriceTickShift = maxPriceTickShift;
	}

	public int getMinQty() {
		return minQty;
	}

	public void setMinQty(int minQty) {
		this.minQty = minQty;
	}

	public double getQtyTick() {
		return qtyTick;
	}

	public void setQtyTick(double qtyTick) {
		this.qtyTick = qtyTick;
	}

	public double getMaxQtyTickShift() {
		return maxQtyTickShift;
	}

	public void setMaxQtyTickShift(double maxQtyTickShift) {
		this.maxQtyTickShift = maxQtyTickShift;
	}

	public DateTime getFromDateTime() {
		return fromDateTime;
	}
	
	public void setFromDateTime(DateTime fromDateTime) {
		this.fromDateTime = fromDateTime;
	}
	public DateTime getToDateTime() {
		return toDateTime;
	}
	public void setToDateTime(DateTime toDateTime) {
		this.toDateTime = toDateTime;
	}
	public long getTickEveryNMs() {
		return tickEveryNMs;
	}
	public void setTickEveryNMs(long tickEveryNMs) {
		this.tickEveryNMs = tickEveryNMs;
	}
	public String getMarket() {
		return market;
	}
	public void setMarket(String market) {
		this.market = market;
	}
	public String getFeedCode() {
		return feedCode;
	}
	public void setFeedCode(String feedCode) {
		this.feedCode = feedCode;
	}
	
	
	
	
	
	
}
