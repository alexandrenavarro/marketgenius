package com.github.marketgenius.services;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Stephane on 30/09/2015.
 */
public class PriorityProcessor {
    private StoreService storeService;


    public PriorityProcessor(StoreService storeService) {
        this.storeService = storeService;
    }

    public Map<String, Double> process(double bestPriceWeight, double filledRatioWeight, double latencyWeight, int offset) throws Exception {
        Map<String, Double> ranks = new ConcurrentHashMap<>();

        Map<String, Double> bestPrices = storeService.fetchBestPriceData(offset);

        for (Map.Entry<String, Double> bestPrice : bestPrices.entrySet()) {
            String market = bestPrice.getKey();
            if (!ranks.containsKey(market)) {
                ranks.put(market, 0.0);
            }

            Double value = ranks.get(market);
            value = value + bestPrice.getValue() * bestPriceWeight / (bestPriceWeight + filledRatioWeight + latencyWeight);
            ranks.put(market, value);
        }

        Map<String, Double> filledRatios = storeService.fetchFilledRatioData(offset);
        for (Map.Entry<String, Double> filledRatio : filledRatios.entrySet()) {
            String market = filledRatio.getKey();
            if (!ranks.containsKey(market)) {
                ranks.put(market, 0.0);
            }

            Double value = ranks.get(market);
            value = value + filledRatio.getValue() * filledRatioWeight / (bestPriceWeight + filledRatioWeight + latencyWeight);
            ranks.put(market, value);
        }

        Map<String, Double> latencies = storeService.fetchLatencyData(offset);
        for (Map.Entry<String, Double> latency : latencies.entrySet()) {
            String market = latency.getKey();
            if (!ranks.containsKey(market)) {
                ranks.put(market, 0.0);
            }

            Double value = ranks.get(market);
            value = value + latency.getValue() * latencyWeight / (bestPriceWeight + filledRatioWeight + latencyWeight);
            ranks.put(market, value);
        }

        sortByValues(ranks);

        return ranks;
    }

    public static <K extends Comparable,V extends Comparable> Map<K,V> sortByValues(Map<K,V> map){
        List<Map.Entry<K,V>> entries = new LinkedList<Map.Entry<K,V>>(map.entrySet());

        Collections.sort(entries, new Comparator<Map.Entry<K,V>>() {

            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        //LinkedHashMap will keep the keys in the order they are inserted
        //which is currently sorted on natural ordering
        Map<K,V> sortedMap = new LinkedHashMap<K,V>();

        for(Map.Entry<K,V> entry: entries){
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }


}
