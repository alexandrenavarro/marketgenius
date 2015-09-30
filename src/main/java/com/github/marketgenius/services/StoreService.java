package com.github.marketgenius.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.github.marketgenius.model.ModelBase;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.stats.extended.ExtendedStats;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Stephane on 30/09/2015.
 */
public class StoreService {
    private Client client;
    private final ObjectMapper mapper;
    private final Logger logger;

    public StoreService(String cluster, List<String> hosts) {
        Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", cluster).build();
        TransportClient transportClient = new TransportClient(settings);
        List<String> nodes = hosts;
        for (String node : nodes) {
            String[] nodeInfo = node.trim().split(":");
            transportClient.addTransportAddress(new InetSocketTransportAddress(nodeInfo[0], Integer.parseInt(nodeInfo[1])));
        }
        this.client = transportClient;

        this.mapper = new ObjectMapper();
        mapper.registerModule(new JodaModule());
        mapper.configure(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        this.logger = LoggerFactory.getLogger("StoreService");

    }

    public <T extends ModelBase> void storeMultiple(List<T> items) {
        BulkRequestBuilder bulkRequest = client.prepareBulk();

        for (T item : items) {
            String itemType = item.getType();

            try {
                String index = String.format("%s-%s", "logstash", item.getTimestamp().toString("yyyy.MM.dd"));
                String itemAsString = mapper.writeValueAsString(item);
                IndexRequestBuilder saveRequest = client.prepareIndex(index, itemType, item.getId());
                bulkRequest.add(saveRequest.setSource(itemAsString));
                bulkRequest.execute().get();
            } catch (Exception error) {
                logger.error(error.getMessage(), error);
            }
        }

    }

    public void buildLatencyQuery(BoolQueryBuilder elasticQuery, int offsetInHour) {
        DateTime now = DateTime.now();
        elasticQuery.must(QueryBuilders.matchQuery("type", "marketLatency"));
        elasticQuery.must(QueryBuilders.rangeQuery("@timestamp").lt(now).gt(now.minusHours(offsetInHour)));
    }

    public void buildFilledRatioQuery(BoolQueryBuilder elasticQuery, int offsetInHour) {
        DateTime now = DateTime.now();
        elasticQuery.must(QueryBuilders.matchQuery("type", "marketFilledRatio"));
        elasticQuery.must(QueryBuilders.rangeQuery("@timestamp").lt(now).gt(now.minusHours(offsetInHour)));
    }

    public void buildBestPriceQuery(BoolQueryBuilder elasticQuery, int offsetInHour) {
        DateTime now = DateTime.now();
        elasticQuery.must(QueryBuilders.matchQuery("type", "marketBestPrice"));
        elasticQuery.must(QueryBuilders.matchQuery("order", 1));
        elasticQuery.must(QueryBuilders.rangeQuery("@timestamp").lt(now).gt(now.minusHours(offsetInHour)));
    }

    public Map<String, Double> fetchLatencyData(int offsetInHour) throws Exception {
        Client client = this.client;
        if (client == null) return null;
        try {
            BoolQueryBuilder elasticQuery = QueryBuilders.boolQuery();
            buildLatencyQuery(elasticQuery, offsetInHour);

            AggregationBuilder aggregation = AggregationBuilders.terms("marketCode").field("marketCode").subAggregation(AggregationBuilders.extendedStats("latency").field("latency"));

            SearchRequestBuilder searchRequest = client.prepareSearch()
                    .setIndices("logstash-2015.09.30")
                    .setQuery(elasticQuery)
                    .setSearchType(SearchType.COUNT)
                    .addAggregation(aggregation);

            SearchResponse searchResponse = searchRequest.execute().actionGet();

            Map<String, Double> bestPrices = new ConcurrentHashMap<>();
            Terms result = searchResponse.getAggregations().get("marketCode");
            for (Terms.Bucket entry : result.getBuckets()) {
                ExtendedStats statistics = entry.getAggregations().get("latency");
                bestPrices.put(entry.getKey(),statistics.getAvg());
            }
            return bestPrices;

        } catch (Exception error) {
            throw new Exception("Failed to build index list", error);
        }

    }

    public Map<String, Double> fetchFilledRatioData(int offsetInHour) throws Exception {
        Client client = this.client;
        if (client == null) return null;
        try {
            BoolQueryBuilder elasticQuery = QueryBuilders.boolQuery();
            buildFilledRatioQuery(elasticQuery, offsetInHour);

            AggregationBuilder aggregation = AggregationBuilders.terms("marketCode").field("marketCode").subAggregation(AggregationBuilders.extendedStats("ratio").field("ratio"));

            SearchRequestBuilder searchRequest = client.prepareSearch()
                    .setIndices("logstash-2015.09.30")
                    .setQuery(elasticQuery)
                    .setSearchType(SearchType.COUNT)
                    .addAggregation(aggregation);

            SearchResponse searchResponse = searchRequest.execute().actionGet();

            Map<String, Double> bestPrices = new ConcurrentHashMap<>();
            Terms result = searchResponse.getAggregations().get("marketCode");
            for (Terms.Bucket entry : result.getBuckets()) {
                ExtendedStats statistics = entry.getAggregations().get("ratio");
                bestPrices.put(entry.getKey(),statistics.getAvg());
            }
            return bestPrices;

        } catch (Exception error) {
            throw new Exception("Failed to build index list", error);
        }

    }

    public Map<String, Double> fetchBestPriceData(int offsetInHour) throws Exception {
        Client client = this.client;
        if (client == null) return null;
        try {
            BoolQueryBuilder elasticQuery = QueryBuilders.boolQuery();
            buildBestPriceQuery(elasticQuery, offsetInHour);

            AggregationBuilder aggregation = AggregationBuilders.terms("marketCode").field("marketCode");

            SearchRequestBuilder searchRequest = client.prepareSearch()
                    .setIndices("logstash-2015.09.30")
                    .setQuery(elasticQuery)
                    .setSearchType(SearchType.COUNT)
                    .addAggregation(aggregation);

            SearchResponse searchResponse = searchRequest.execute().actionGet();

            Map<String, Double> bestPrices = new ConcurrentHashMap<>();
            Terms result = searchResponse.getAggregations().get("marketCode");
            for (Terms.Bucket entry : result.getBuckets()) {
                bestPrices.put(entry.getKey(),(double) entry.getDocCount());
            }
            return bestPrices;

        } catch (Exception error) {
            throw new Exception("Failed to build index list", error);
        }

    }

}
