package com.github.marketgenius.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.marketgenius.model.ModelBase;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import java.util.List;

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

    public  <T extends ModelBase> void storeMultiple(List<T> items) {
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
}
