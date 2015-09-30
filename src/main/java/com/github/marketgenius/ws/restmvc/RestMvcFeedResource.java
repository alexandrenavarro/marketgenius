package com.github.marketgenius.ws.restmvc;


import com.github.marketgenius.messages.MarketRank;
import com.github.marketgenius.messages.PriorityRequest;
import com.github.marketgenius.messages.PriorityResponse;
import com.github.marketgenius.model.Priority;
import com.github.marketgenius.model.Weightage;
import com.github.marketgenius.services.PriorityProcessor;
import com.github.marketgenius.services.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/feed")
//@Controller
public class RestMvcFeedResource  {

    PriorityProcessor priorityProcessor;
    StoreService storeService;

	private static final Logger LOGGER = LoggerFactory.getLogger(RestMvcFeedResource.class);
    public RestMvcFeedResource()
    {
        List<String> servers = new ArrayList<>();
        servers.add("localhost:9300");
        this.storeService  = new StoreService("marketGenius",servers);
        this.priorityProcessor = new PriorityProcessor(storeService);
    }



    @RequestMapping(value = "/computePriority", method = RequestMethod.POST)
    public PriorityResponse computePriority(@RequestBody PriorityRequest priorityRequest) throws Exception {
        LOGGER.info("RestMvcFeedResource.computePriority");
        Weightage weightage = priorityRequest.getWeightage();


        Map<String, Double> ranks = priorityProcessor.process(weightage.getBestPriceWeight(), weightage.getFilledRatioWeight(), weightage.getFilledRatioWeight(), priorityRequest.getOffset());

        PriorityResponse response = new PriorityResponse();

        int index = 0;
        for (Map.Entry<String,Double> rank : ranks.entrySet()) {
            response.getRanks().add(new MarketRank(rank.getKey(),index++,rank.getValue()));
        }

        return  response;
    }

    @RequestMapping("priorityData")
    public Priority priorityData() {
        LOGGER.info("RestMvcFeedResource.findAll");
        return  null;
    }
    
}
