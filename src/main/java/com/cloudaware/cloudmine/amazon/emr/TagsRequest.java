package com.cloudaware.cloudmine.amazon.emr;

import java.util.Map;

/**
 * User: tolstikov
 * Date: 2/18/16
 */
public final class TagsRequest {

    private String clusterId;
    private Map<String, String> tags;

    public TagsRequest(final String clusterId, final Map<String, String> tags) {
        this.clusterId = clusterId;
        this.tags = tags;
    }

    public TagsRequest() {
    }

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(final String clusterId) {
        this.clusterId = clusterId;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(final Map<String, String> tags) {
        this.tags = tags;
    }
}
