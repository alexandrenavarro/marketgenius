package com.github.marketgenius.model;

import org.joda.time.DateTime;

/**
 * Created by Stephane on 30/09/2015.
 */
public class MarketFilledRatio extends ModelBase{
    private String marketCode;
    private DateTime timestamp;
    private String productCode;
    private float ratio;

    public MarketFilledRatio(String marketCode, DateTime timestamp, String productCode, float ratio) {
        super("marketBestPrice",timestamp);
        this.marketCode = marketCode;
        this.timestamp = timestamp;
        this.productCode = productCode;
        this.ratio = ratio;
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

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }
}
