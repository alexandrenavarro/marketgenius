package com.github.marketgenius.ws.restmvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.marketgenius.messages.PriorityRequest;
import org.junit.Test;
import org.springframework.util.Assert;

/**
 * Created by Stephane on 30/09/2015.
 */
public class RestMvcFeedResourceTest{

    @Test
    public void testComputePriority() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        PriorityRequest request = new PriorityRequest();
        request.getWeightage().setBestPriceWeight(0.5);
        request.getWeightage().setFilledRatioWeight(0.5);
        request.getWeightage().setLatencyWeight(0.5);

        String stringRequest = mapper.writeValueAsString(request);

        Assert.isNull(stringRequest);
    }
}