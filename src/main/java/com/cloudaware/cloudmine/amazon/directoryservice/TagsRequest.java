package com.cloudaware.cloudmine.amazon.directoryservice;

import java.util.Map;

/**
 * User: tolstikov
 * Date: 2/18/16
 */
public final class TagsRequest {

    private String resourceId;
    private Map<String, String> tags;

    public TagsRequest(final String resourceId, final Map<String, String> tags) {
        this.resourceId = resourceId;
        this.tags = tags;
    }

    public TagsRequest() {
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(final String resourceId) {
        this.resourceId = resourceId;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(final Map<String, String> tags) {
        this.tags = tags;
    }
}
