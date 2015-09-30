package com.github.marketgenius.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.marketgenius.model.Feed;
import com.github.marketgenius.model.ModelBase;
import com.github.marketgenius.services.StoreService;
import com.google.common.collect.Lists;

public class FeedWriter {

	private static final Logger LOGGER = LoggerFactory.getLogger(FeedWriter.class);
	
	private final StoreService storeService = new StoreService("marketgenius", Lists.newArrayList("localhost:9300"));
	
	/**
	 * @param feedList
	 * @param file
	 */
	public void writeToFile(List<? extends ModelBase> feedList, File file) {
		
		LOGGER.info("FeedWriter.writeToFile file is starting ...");
		int nbCount = 0;
        try (final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file)))
        {
        	for (ModelBase feed : feedList) {
        		bufferedWriter.write(feed.toString());
        		bufferedWriter.write("\n");
        		nbCount++;
        	}
        }
        catch (IOException e)
        {
        	LOGGER.warn("Error while writing e={}", e);
            //
        }
        LOGGER.info("FeedWriter.writeToFile file is finished, nbCount={}", nbCount);
        
	}
	
	public void writeToElasticSearch(List<? extends ModelBase> feedList) {
		LOGGER.info("FeedWriter.writeToElasticSearch file is starting ...");
		final long start = System.currentTimeMillis();
		storeService.storeMultiple(feedList);
		final long stop = System.currentTimeMillis();
        LOGGER.info("FeedWriter.writeToElasticSearch file is finished, nbCount={} in {} ms", feedList.size(), stop - start);
		
	}
}
