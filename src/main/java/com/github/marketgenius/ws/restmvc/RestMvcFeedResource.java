package com.github.marketgenius.ws.restmvc;


import java.util.Collection;

import org.elasticsearch.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.marketgenius.model.Feed;
import com.github.marketgenius.model.FeedBuilder;
import com.github.marketgenius.model.Priority;
import com.github.marketgenius.model.PriorityList;

@RestController
@RequestMapping(value = "/feed")
public class RestMvcFeedResource  {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(RestMvcFeedResource.class);
	
    /**
     * @return
     */
    @RequestMapping("/findAll")
    public Collection<Feed> findAll() {
    	LOGGER.info("RestMvcFeedResource.findAll");
    	return Lists.newArrayList(FeedBuilder.create().feedCode("feed1").market("market1").feedId(1).build());
    	//return Lists.newArrayList();
    }

    @RequestMapping("/computePriority")
    public PriorityList computePriority() {
        LOGGER.info("RestMvcFeedResource.findAll");
        return  null;
    }

    @RequestMapping("priorityData")
    public Priority priorityData() {
        LOGGER.info("RestMvcFeedResource.findAll");
        return  null;
    }
    
}
