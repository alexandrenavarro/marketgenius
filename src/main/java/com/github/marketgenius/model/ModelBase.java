package com.github.marketgenius.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import java.util.UUID;

/**
 * Created by Stephane on 30/09/2015.
 */
public class ModelBase {
    private final String id;
    private String type;
    private DateTime timestamp;

    public ModelBase(String type, DateTime timestamp) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("@timestamp")
    public DateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(DateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }
}
