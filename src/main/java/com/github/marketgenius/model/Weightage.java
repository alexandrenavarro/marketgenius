package com.github.marketgenius.model;

/**
 * Created by Stephane on 30/09/2015.
 */
public class Weightage {

    private double bestPriceWeight;
    private double filledRatioWeight;
    private double latencyWeight;


    public Weightage() {
        this.bestPriceWeight = 0.0;
        this.filledRatioWeight = 0.0;
        this.latencyWeight = 0.0;
    }

    public Weightage(double bestPriceWeight, double filledRatioWeight, double latencyWeight) {
        this.bestPriceWeight = bestPriceWeight;
        this.filledRatioWeight = filledRatioWeight;
        this.latencyWeight = latencyWeight;
    }

    public double getBestPriceWeight() {
        return bestPriceWeight;
    }

    public void setBestPriceWeight(double bestPriceWeight) {
        this.bestPriceWeight = bestPriceWeight;
    }

    public double getFilledRatioWeight() {
        return filledRatioWeight;
    }

    public void setFilledRatioWeight(double filledRatioWeight) {
        this.filledRatioWeight = filledRatioWeight;
    }

    public double getLatencyWeight() {
        return latencyWeight;
    }

    public void setLatencyWeight(double latencyWeight) {
        this.latencyWeight = latencyWeight;
    }




}
