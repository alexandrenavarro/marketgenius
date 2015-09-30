package com.github.marketgenius.messages;

import com.github.marketgenius.model.Weightage;

/**
 * Created by Stephane on 30/09/2015.
 */
public class PriorityRequest {
    private Weightage weightage;
    private int offset;

    public PriorityRequest() {
        weightage = new Weightage();
    }

    public PriorityRequest(Weightage weightage, int offset) {
        this.weightage = weightage;
        this.offset = offset;
    }

    public Weightage getWeightage() {
        return weightage;
    }

    public void setWeightage(Weightage weightage) {
        this.weightage = weightage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
