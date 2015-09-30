package com.github.marketgenius.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.marketgenius.model.Feed;
import com.github.marketgenius.model.MarketBestPrice;

public class FeedGeneratorMain {

	private static final Logger LOGGER = LoggerFactory.getLogger(FeedGenerator.class);

	public static void main(String[] args) {

		final FeedGenerator feedGenerator = new FeedGenerator();

		final int nbMarket = 5;
		final int nbFeedCodeByMarket = 4;
		final String fromDate = "2015-09-30T09:00:00";
		final String toDate = "2015-09-30T17:30:00";
		final long startTime = new DateTime(fromDate).getMillis();
		final long endTime = new DateTime(toDate).getMillis();

		
		final FeedWriter feedWriter = new FeedWriter();
		final List<MarketBestPrice> marketBestPriceList = new ArrayList<MarketBestPrice>();
		for (int feedCodeId = 1; feedCodeId <= nbFeedCodeByMarket; feedCodeId++) {
			final Map<String, List<Feed>> marketFeedMap = new HashMap<String, List<Feed>>();
			
			final String feedCode = "feedCode" + feedCodeId;
			for (int marketId = 1; marketId <= nbMarket; marketId++) {
				final String market = "market" + marketId;

				final FeedGeneratorParameter feedGeneratorParameter = new FeedGeneratorParameter();
				feedGeneratorParameter.setFromDateTime(new DateTime(fromDate));
				feedGeneratorParameter.setToDateTime(new DateTime(toDate));
				feedGeneratorParameter.setTickEveryNMs((10 *  60000) / marketId);
				feedGeneratorParameter.setFeedCode(feedCode);
				feedGeneratorParameter.setMarket(market);
				feedGeneratorParameter.setDefaultPrice(100);
				//feedGeneratorParameter.setMaxPriceTickShift( marketId * 2);

				final List<Feed> feedList = feedGenerator.generateFeeds(feedGeneratorParameter);
				

				feedWriter.writeToFile(feedList, new File("target/" + market  +"-" + feedCode  + ".txt"));
				feedWriter.writeToElasticSearch(feedList);
				
				marketFeedMap.put(market, feedList);
			}
			
			
			// Generate For MarketBestPrice, not very beautifull, extract it
			
			
			
			for (long currentTime = startTime; currentTime < endTime; currentTime = currentTime + 10 * 60000) {
				List<Feed> orderedFeedList = new ArrayList<>();
				
				for (int marketId = 1; marketId <= nbMarket; marketId++) {
					final String market = "market" + marketId;
					final List<Feed> feedList = marketFeedMap.get(market);
					final Feed lastFead = findLastValidFeed(feedList, startTime);
					orderedFeedList.add(lastFead);
				}
				
				orderedFeedList.sort(new Comparator<Feed>() {
				@Override
				public int compare(Feed o1, Feed o2) {
					if (o1.getBid1() ==  o2.getBid1()) {
						if (o1.getQtyBid1() == o2.getQtyBid1() ) {
							return 0;
						} else {
							if (o1.getQtyBid1() < o2.getQtyBid1() ) {
								return 1;
							} else {
								return -1;
							}
						}
					} else {
						if (o1.getBid1() <  o2.getBid1()) {
							return 1;
						} else {
							return -1;
						}
					}
				}});
				
				// For Bid
				double previousBid = 0;
				int bidRank = 0;
				for (Feed feed : orderedFeedList) {
					if (previousBid != feed.getBid1()) {
						previousBid = feed.getBid1();
						bidRank++;
					}
					final MarketBestPrice marketBestPrice = new MarketBestPrice(feed.getMarketCode(), new DateTime(currentTime), feedCode, "Bid", feed.getBid1(), bidRank);
					marketBestPriceList.add(marketBestPrice);
					
				}
				
				
				orderedFeedList.sort(new Comparator<Feed>() {
				@Override
				public int compare(Feed o1, Feed o2) {
					if (o1.getBid1() ==  o2.getBid1()) {
						if (o1.getQtyAsk1() == o2.getQtyAsk1() ) {
							return 0;
						} else {
							if (o1.getQtyAsk1() < o2.getQtyAsk1() ) {
								return 1;
							} else {
								return -1;
							}
						}
					} else {
						if (o1.getAsk1() <  o2.getAsk1()) {
							return -1;
						} else {
							return 1;
						}
					}
				}});
				
				// For Ask
				// For Bid
				double previousAsk = 0;
				int askRank = 0;
				for (Feed feed : orderedFeedList) {
					if (previousAsk != feed.getAsk1()) {
						previousAsk = feed.getAsk1();
						askRank++;
					}
					final MarketBestPrice marketBestPrice = new MarketBestPrice(feed.getMarketCode(), new DateTime(currentTime), feedCode, "Ask", feed.getAsk1(), askRank);
					marketBestPriceList.add(marketBestPrice);
				}
				
			}
			marketFeedMap.clear();
			
		}
		LOGGER.info("MarketBestPrice is generated.");
		feedWriter.writeToFile(marketBestPriceList, new File("target/aaMarketBestPrice.txt"));
		feedWriter.writeToElasticSearch(marketBestPriceList);
		
	}
	
	// Refactor it
	public static Feed findLastValidFeed(List<Feed> feedList, long timeStamp) {
		int i = 0;
		while (i < feedList.size() && feedList.get(i).getTimestamp().getMillis() <= timeStamp) {
			i++;
		}
		return (i == 0 || i == feedList.size()) ? null : feedList.get(i - 1);
	}

}
