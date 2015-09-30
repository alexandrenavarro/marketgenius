package com.github.marketgenius.model;

import org.joda.time.DateTime;

/**
 * Created by Stephane on 30/09/2015.
 */
public class MarketBestPrice extends ModelBase{
    private String marketCode;
    private String side;
    private String product;
    private double price;
    private int order;

    public MarketBestPrice(String marketCode, DateTime timestamp, String product, String side, double price, int order) {
        super("marketBestPrice",timestamp);
        this.marketCode = marketCode;
        this.side = side;
        this.product = product;
        this.price = price;
        this.order = order;
    }

    public String getMarketCode() {
        return marketCode;
    }

    public void setMarketCode(String marketCode) {
        this.marketCode = marketCode;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
