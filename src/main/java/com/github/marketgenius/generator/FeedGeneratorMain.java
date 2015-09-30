package com.github.marketgenius.generator;

import java.io.File;
import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.marketgenius.model.Feed;

public class FeedGeneratorMain {

	private static final Logger LOGGER = LoggerFactory.getLogger(FeedGenerator.class);

	public static void main(String[] args) {

		final FeedGenerator feedGenerator = new FeedGenerator();

		final int nbMarket = 5;
		final int nbFeedCodeByMarket = 100;
		for (int feedCodeId = 1; feedCodeId <= nbFeedCodeByMarket; feedCodeId++) {
			for (int marketId = 1; marketId <= nbMarket; marketId++) {
				final String feedCode = "feedCode" + feedCodeId;
				final String market = "market" + marketId;

				final FeedGeneratorParameter feedGeneratorParameter = new FeedGeneratorParameter();
				feedGeneratorParameter.setFromDateTime(new DateTime("2015-09-30T09:00:00"));
				feedGeneratorParameter.setToDateTime(new DateTime("2015-09-30T17:00:00"));
				feedGeneratorParameter.setTickEveryNMs(3600000 / marketId);
				feedGeneratorParameter.setFeedCode(feedCode);
				feedGeneratorParameter.setMarket(market);
				feedGeneratorParameter.setDefaultPrice(100 + feedCodeId);
				feedGeneratorParameter.setMaxPriceTickShift(marketId);

				final List<Feed> feedList = feedGenerator.generateFeeds(feedGeneratorParameter);
				

				final FeedWriter feedWriter = new FeedWriter();
				feedWriter.writeToFile(feedList, new File("target/" + market  +"-" + feedCode  + ".txt"));
			}

		}

	}

}
