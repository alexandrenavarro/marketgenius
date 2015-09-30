package com.github.marketgenius.model;

import org.joda.time.DateTime;

/**
 * Created by Stephane on 30/09/2015.
 */
public class MarketBestPrice extends ModelBase{
    private String marketCode;
    private String productCode;
    private String type;
    private double price;
    private int order;

    public MarketBestPrice(String marketCode, DateTime timestamp, String productCode, String type, double price, int order) {
        super("marketBestPrice",timestamp);
        this.marketCode = marketCode;
        this.productCode = productCode;
        this.type = type;
        this.price = price;
        this.order = order;
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

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
