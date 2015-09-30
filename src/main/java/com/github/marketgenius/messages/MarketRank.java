package com.github.marketgenius.messages;

/**
 * Created by Stephane on 30/09/2015.
 */
public class MarketRank {
    private String name;
    private int rank;
    private double result;

    public MarketRank() {

    }

    public MarketRank(String name, int rank, double result) {
        this.name = name;
        this.rank = rank;
        this.result = result;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
