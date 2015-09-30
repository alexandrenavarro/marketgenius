package com.github.marketgenius.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.marketgenius.model.Feed;

public class FeedWriter {

	

	private static final Logger LOGGER = LoggerFactory.getLogger(FeedWriter.class);
	
	/**
	 * @param feedList
	 * @param file
	 */
	public void writeToFile(List<Feed> feedList, File file) {
		
		LOGGER.info("FeedWriter.writeToFile file is starting ...");
		int nbCount = 0;
        try (final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file)))
        {
        	for (Feed feed : feedList) {
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
	
	public void writeToElasticSearch(List<Feed> feedList) {
		LOGGER.info("FeedWriter.writeToElasticSearch file is starting ...");

		//
        LOGGER.info("FeedWriter.writeToElasticSearch file is finished, nbCount={}", feedList.size());
		
	}
}
