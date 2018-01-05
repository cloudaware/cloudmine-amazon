package com.cloudaware.cloudmine.amazon.cloudwatchlogs;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.Map;

public final class TagsResponse extends AmazonResponse {
    private Map<String, String> tags;

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(final Map<String, String> tags) {
        this.tags = tags;
    }
}
