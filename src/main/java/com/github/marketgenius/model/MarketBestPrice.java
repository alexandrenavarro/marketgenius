package com.github.marketgenius.model;

import org.joda.time.DateTime;

/**
 * Created by Stephane on 30/09/2015.
 */
public class MarketBestPrice extends ModelBase{
    private String marketCode;
    private String side;
    private String feedCode;
    private double price;
    private int rank;

    public MarketBestPrice(String marketCode, DateTime timestamp, String product, String side, double price, int order) {
            super("marketBestPrice",timestamp);
        this.marketCode = marketCode;
        this.side = side;
        this.feedCode = product;
        this.price = price;
        this.rank = order;
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


    public String getFeedCode() {
		return feedCode;
	}

	public void setFeedCode(String feedCode) {
		this.feedCode = feedCode;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
