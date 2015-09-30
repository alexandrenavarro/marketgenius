package com.github.marketgenius.model;

import org.joda.time.DateTime;

/**
 * Created by Stephane on 30/09/2015.
 */
public class MarketFilledRatio extends ModelBase{
    private String marketCode;
    private String order;
    private int ratio;

    public MarketFilledRatio(String marketCode, DateTime timestamp, String order, int ratio) {
        super("marketFilledRatio",timestamp);
        this.marketCode = marketCode;
        this.order = order;
        this.ratio = ratio;
    }

    public String getMarketCode() {
        return marketCode;
    }

    public void setMarketCode(String marketCode) {
        this.marketCode = marketCode;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(int ratio) {
        this.ratio = ratio;
    }
}
