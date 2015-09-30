package com.github.marketgenius.model;

import org.joda.time.DateTime;

/**
 * Created by Stephane on 30/09/2015.
 */
public class MarketFilledRatio extends ModelBase{
    private String marketCode;
    private String productCode;
    private double ratio;

    public MarketFilledRatio(String marketCode, DateTime timestamp, String productCode, double ratio) {
        super("marketFilledRatio",timestamp);
        this.marketCode = marketCode;
        this.productCode = productCode;
        this.ratio = ratio;
    }

    public String getMarketCode() {
        return marketCode;
    }

    public void setMarketCode(String marketCode) {
        this.marketCode = marketCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }
}
