package com.github.marketgenius;

import com.github.marketgenius.model.MarketBestPrice;
import com.github.marketgenius.model.MarketFilledRatio;
import com.github.marketgenius.model.MarketLatency;
import com.github.marketgenius.services.StoreService;
import junit.framework.Assert;
import org.joda.time.DateTime;
import org.junit.Test;

import java.util.*;

/**
 * Created by Stephane on 30/09/2015.
 */
public class MarketCriteriaFeeder {

    @Test
    public void generateBestPriceCriteriaForMarket()
    {
        List<String> servers = new ArrayList<>();
        servers.add("localhost:9300");
        StoreService service  = new StoreService("marketGenius",servers);
        Random orderAlea = new Random();
        Random priceAlea = new Random();

        List<MarketBestPrice> bestPrices =  new ArrayList<>();
        for ( int index = 0; index < 3600*10; index++)
        {
            generateDataForOneProductAndOneType("2y", "ask", service, orderAlea, priceAlea, bestPrices, index);
            generateDataForOneProductAndOneType("3y", "ask", service, orderAlea, priceAlea, bestPrices, index);
            generateDataForOneProductAndOneType("5y", "ask", service, orderAlea, priceAlea, bestPrices, index);
            generateDataForOneProductAndOneType("7y", "ask", service, orderAlea, priceAlea, bestPrices, index);
            generateDataForOneProductAndOneType("10y", "ask", service, orderAlea, priceAlea, bestPrices, index);
            generateDataForOneProductAndOneType("30y", "ask", service, orderAlea, priceAlea, bestPrices, index);

            generateDataForOneProductAndOneType("2y", "bid", service, orderAlea, priceAlea, bestPrices, index);
            generateDataForOneProductAndOneType("3y", "bid", service, orderAlea, priceAlea, bestPrices, index);
            generateDataForOneProductAndOneType("5y", "bid", service, orderAlea, priceAlea, bestPrices, index);
            generateDataForOneProductAndOneType("7y", "bid", service, orderAlea, priceAlea, bestPrices, index);
            generateDataForOneProductAndOneType("10y", "bid", service, orderAlea, priceAlea, bestPrices, index);
            generateDataForOneProductAndOneType("30y", "bid", service, orderAlea, priceAlea, bestPrices, index);
        }
    }
    @Test
    public void generateBestFilledRatioCriteriaForMarket()
    {
        List<String> servers = new ArrayList<>();
        servers.add("localhost:9300");
        StoreService service  = new StoreService("marketGenius",servers);
        Random ratioAlea = new Random();

        List<MarketFilledRatio> filledRatios =  new ArrayList<>();
        for ( int index = 0; index < 3600*10; index++)
        {
            generateDataForOneOrder(service, ratioAlea, index,filledRatios);
        }
    }

    @Test
    public void generateLatencyCriteriaForMarket()
    {
        List<String> servers = new ArrayList<>();
        servers.add("localhost:9300");
        StoreService service  = new StoreService("marketGenius",servers);
        Random ratioAlea = new Random();

        List<MarketLatency> latencies =  new ArrayList<>();
        for ( int index = 0; index < 3600*10; index++)
        {
            generateDataForLatency(service, ratioAlea, index, latencies);
        }
    }

    @Test
    public void testLatencyRetrieval() throws Exception {
        List<String> servers = new ArrayList<>();
        servers.add("localhost:9300");
        StoreService service  = new StoreService("marketGenius",servers);
        Map<String,Double> data = service.fetchLatencyData(6);
        Assert.assertTrue(data != null );
    }

    @Test
    public void testFilledRatioRetrieval() throws Exception {
        List<String> servers = new ArrayList<>();
        servers.add("localhost:9300");
        StoreService service  = new StoreService("marketGenius",servers);
        Map<String,Double> data = service.fetchFilledRatioData(6);
        Assert.assertTrue(data != null );
    }


    @Test
    public void testBestPriceRetrieval() throws Exception {
        List<String> servers = new ArrayList<>();
        servers.add("localhost:9300");
        StoreService service  = new StoreService("marketGenius",servers);
        Map<String,Double> data = service.fetchBestPriceData(6);
        Assert.assertTrue(data != null );
    }

    private void generateDataForLatency(StoreService service, Random ratioAlea, int index, List<MarketLatency> latencies) {
        DateTime timestamp = DateTime.now().minusSeconds(index);
        MarketLatency latency = new MarketLatency("MarketC",timestamp,ratioAlea.nextInt(5), UUID.randomUUID().toString());
        latencies.add(latency);

        latency = new MarketLatency("MarketA",timestamp,ratioAlea.nextInt(5), UUID.randomUUID().toString());
        latencies.add(latency);

        latency = new MarketLatency("MarketB",timestamp,ratioAlea.nextInt(5), UUID.randomUUID().toString());
        latencies.add(latency);

        latency = new MarketLatency("MarketD",timestamp,ratioAlea.nextInt(5), UUID.randomUUID().toString());
        latencies.add(latency);

        if (latencies.size() == 100 )
        {
            service.storeMultiple(latencies);
            latencies.clear();
        }
    }

    private void generateDataForOneOrder(StoreService service, Random ratioAlea, int index, List<MarketFilledRatio> filledRatios) {
        DateTime timestamp = DateTime.now().minusSeconds(index);
        MarketFilledRatio filledRatio = new MarketFilledRatio("MarketA",timestamp, UUID.randomUUID().toString(),ratioAlea.nextInt(100));
        filledRatios.add(filledRatio);

        filledRatio = new MarketFilledRatio("MarketB",timestamp, UUID.randomUUID().toString(),ratioAlea.nextInt(100));
        filledRatios.add(filledRatio);

        filledRatio = new MarketFilledRatio("MarketC",timestamp, UUID.randomUUID().toString(),ratioAlea.nextInt(100));
        filledRatios.add(filledRatio);

        filledRatio = new MarketFilledRatio("MarketD",timestamp, UUID.randomUUID().toString(),ratioAlea.nextInt(100));
        filledRatios.add(filledRatio);

        if (filledRatios.size() == 100 )
        {
            service.storeMultiple(filledRatios);
            filledRatios.clear();
        }
    }

    private void generateDataForOneProductAndOneType(String product, String side, StoreService service, Random orderAlea, Random priceAlea, List<MarketBestPrice> bestPrices, int index) {
        double price = 99.0;
        List<Integer> orders = new ArrayList<Integer>();
        orders.add(1);
        orders.add(2);
        orders.add(3);
        orders.add(3);

        DateTime timestamp = DateTime.now().minusSeconds(index);
        int orderA = orderAlea.nextInt(3);
        MarketBestPrice bestPriceA = new MarketBestPrice("MarketA",timestamp,product,side, price+priceAlea.nextGaussian(), orders.get(orderA) );
        orders.remove(orderA);
        bestPrices.add(bestPriceA);

        int orderB = orderAlea.nextInt(2);
        MarketBestPrice bestPriceB = new MarketBestPrice("MarketB",timestamp,product,side, price+priceAlea.nextGaussian(), orders.get(orderB) );
        orders.remove(orderB);
        bestPrices.add(bestPriceB);

        int orderC = orderAlea.nextInt(1);
        MarketBestPrice bestPriceC = new MarketBestPrice("MarketC",timestamp,product,side, price+priceAlea.nextGaussian(), orders.get(orderC) );
        orders.remove(orderC);
        bestPrices.add(bestPriceC);

        MarketBestPrice bestPriceD = new MarketBestPrice("MarketD",timestamp,product,side, price+priceAlea.nextGaussian(), orders.get(0) );
        ;

        bestPrices.add(bestPriceD);

        if (bestPrices.size() == 100 )
        {
            service.storeMultiple(bestPrices);
            bestPrices.clear();
        }
    }
}
