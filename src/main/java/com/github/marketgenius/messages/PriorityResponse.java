package com.github.marketgenius.messages;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephane on 30/09/2015.
 */
public class PriorityResponse {
    private List<MarketRank> ranks;

    public PriorityResponse() {
        this.ranks = new ArrayList<>();
    }

    public PriorityResponse(List<MarketRank> ranks) {
        this.ranks = ranks;
    }


    public List<MarketRank> getRanks() {
        return ranks;
    }

    public void setRanks(List<MarketRank> ranks) {
        this.ranks = ranks;
    }
}
