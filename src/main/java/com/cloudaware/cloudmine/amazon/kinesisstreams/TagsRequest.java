package com.cloudaware.cloudmine.amazon.kinesisstreams;

import java.util.Map;

/**
 * User: tolstikov
 * Date: 2/18/16
 */
public final class TagsRequest {

    private String streamName;
    private Map<String, String> tags;

    public TagsRequest(final String streamName, final Map<String, String> tags) {
        this.streamName = streamName;
        this.tags = tags;
    }

    public TagsRequest() {
    }

    public String getStreamName() {
        return streamName;
    }

    public void setStreamName(final String streamName) {
        this.streamName = streamName;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(final Map<String, String> tags) {
        this.tags = tags;
    }
}
