package com.github.marketgenius.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.marketgenius.model.Feed;

public class FeedReader {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(FeedReader.class);
	
	/**
	 * @param file
	 * @return
	 */
	public List<Feed> readFromFile(final File file) {
		LOGGER.info("FeedReader.read file={} is starting ...", file);
		final List<Feed> feedList = new ArrayList<Feed>();
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file), 2048)) {
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				feedList.add(Feed.MAPPER.readValue(line, Feed.class));
			}
			bufferedReader.close();
		} catch (IOException e) {
			LOGGER.info("ERROR while reading, e={}", e);
		}
		LOGGER.info("FeedReader.read file={} is finished, nbRows={}", feedList.size());
		return feedList;

	}
	
	
	/**
	 * @return
	 */
	public List<Feed> readFromElasticSearch() {
		LOGGER.info("FeedReader.read file={} is starting ...");
		final List<Feed> feedList = new ArrayList<Feed>();
		// TODO
		
		LOGGER.info("FeedReader.read file={} is finished, nbRows={}", feedList.size());
		return feedList;
	}
	
}
