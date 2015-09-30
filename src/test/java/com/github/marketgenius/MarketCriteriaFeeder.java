package com.github.marketgenius;

import com.github.marketgenius.model.MarketBestPrice;
import com.github.marketgenius.services.StoreService;
import org.joda.time.DateTime;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Stephane on 30/09/2015.
 */
public class MarketCriteriaFeeder {

    @Test
    public void generateCriteriaForMarket()
    {
        List<String> servers = new ArrayList<>();
        servers.add("localhost:9300");
        StoreService service  = new StoreService("marketGenius",servers);
        Random orderAlea = new Random();
        Random priceAlea = new Random();

        List<MarketBestPrice> bestPrices =  new ArrayList<>();
        for ( int index = 0; index < 100; index++)
        {
            double price = 99.0;
            List<Integer> orders = new ArrayList<Integer>();
            orders.add(1);
            orders.add(2);
            orders.add(3);
            orders.add(3);

            DateTime timestamp = DateTime.now().minusSeconds(index);
            int orderA = orderAlea.nextInt(3);
            MarketBestPrice bestPriceA = new MarketBestPrice("MarketA",timestamp,"bid","1y", price+priceAlea.nextGaussian(), orders.get(orderA) );
            orders.remove(orderA);
            bestPrices.add(bestPriceA);

            int orderB = orderAlea.nextInt(2);
            MarketBestPrice bestPriceB = new MarketBestPrice("MarketB",timestamp,"bid","1y", price+priceAlea.nextGaussian(), orders.get(orderB) );
            orders.remove(orderB);
            bestPrices.add(bestPriceB);

            int orderC = orderAlea.nextInt(1);
            MarketBestPrice bestPriceC = new MarketBestPrice("MarketC",timestamp,"bid","1y", price+priceAlea.nextGaussian(), orders.get(orderC) );
            orders.remove(orderC);
            bestPrices.add(bestPriceC);

            MarketBestPrice bestPriceD = new MarketBestPrice("MarketD",timestamp,"bid","1y", price+priceAlea.nextGaussian(), orders.get(0) );;

            bestPrices.add(bestPriceD);

            if (bestPrices.size() == 100 )
            {
                service.storeMultiple(bestPrices);
                bestPrices.clear();
            }
        }
    }
}
