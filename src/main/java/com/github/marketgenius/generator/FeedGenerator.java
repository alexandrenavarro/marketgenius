package com.github.marketgenius.generator;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.marketgenius.model.Feed;

public class FeedGenerator {

	/**
	 * @param feedGeneratorParameter
	 * @return
	 */
	public List<Feed> generateFeeds(FeedGeneratorParameter feedGeneratorParameter) {
		final List<Feed> feedList = new ArrayList<>();

		final long startTime = feedGeneratorParameter.getFromDateTime().getMillis();
		final long endTime = feedGeneratorParameter.getToDateTime().getMillis();
		
		for (long currentTime = startTime; currentTime < endTime; currentTime = currentTime + feedGeneratorParameter.getTickEveryNMs()) {
			final Feed feed = new Feed(new DateTime(currentTime));
			feed.setMarket(feedGeneratorParameter.getMarket());
			feed.setFeedCode(feedGeneratorParameter.getFeedCode());
			feed.setBid1(feedGeneratorParameter.getGeneratedBid());
			feed.setAsk1(feedGeneratorParameter.getGeneratedAsk());
			feed.setQtyAsk1(feedGeneratorParameter.getGeneratedQty());
			feed.setQtyBid1(feedGeneratorParameter.getGeneratedQty());
			feedList.add(feed);
		}

		return feedList;
	}
	
}
