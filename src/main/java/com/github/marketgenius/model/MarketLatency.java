package com.github.marketgenius.model;

import org.joda.time.DateTime;

/**
 * Created by Stephane on 30/09/2015.
 */
public class MarketLatency extends ModelBase{
    private String marketCode;
    private DateTime timestamp;
    private double latency;

    public MarketLatency(String marketCode, DateTime timestamp, double latency) {
        super("marketLatency",timestamp);
        this.marketCode = marketCode;
        this.timestamp = timestamp;
        this.latency = latency;
    }

    public String getMarketCode() {
        return marketCode;
    }

    public void setMarketCode(String marketCode) {
        this.marketCode = marketCode;
    }

    public DateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(DateTime timestamp) {
        this.timestamp = timestamp;
    }

    public double getLatency() {
        return latency;
    }

    public void setLatency(double latency) {
        this.latency = latency;
    }
}
