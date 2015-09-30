package com.github.marketgenius.ws.restmvc;


import java.util.Collection;
import java.util.Collections;

import org.elasticsearch.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.marketgenius.Feed;
import com.github.marketgenius.FeedBuilder;
import com.github.marketgenius.ws.FeedResource;

@RestController
@RequestMapping(value = "/feed")
public class RestMvcFeedResource implements FeedResource {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(RestMvcFeedResource.class);
	
    /**
     * @return
     */
    @RequestMapping()
    public Collection<Feed> findAll() {
    	LOGGER.info("RestMvcFeedResource.findAll");
    	return Lists.newArrayList(FeedBuilder.create().feedCode("feed1").market("market1").feedId(1).build());
    }
    
    
    
}
