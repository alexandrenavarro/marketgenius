package com.github.marketgenius.services;

import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Stephane on 30/09/2015.
 */
public class PriorityProcessorTest {

    @Test
    public void testPriority() throws Exception {
        List<String> servers = new ArrayList<>();
        servers.add("localhost:9300");
        StoreService service  = new StoreService("marketGenius",servers);
        PriorityProcessor processor = new PriorityProcessor(service);

        Map<String, Double> data =  processor.process(0.5, 0.2, 0.3, 6);
        Assert.assertTrue(data != null);
     }

}