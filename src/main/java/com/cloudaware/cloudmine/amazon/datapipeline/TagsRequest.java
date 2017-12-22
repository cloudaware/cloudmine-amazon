package com.cloudaware.cloudmine.amazon.datapipeline;

import java.util.Map;

/**
 * User: tolstikov
 * Date: 2/18/16
 */
public final class TagsRequest {

    private String pipelineId;
    private Map<String, String> tags;

    public TagsRequest(final String pipelineId, final Map<String, String> tags) {
        this.pipelineId = pipelineId;
        this.tags = tags;
    }

    public TagsRequest() {
    }

    public String getPipelineId() {
        return pipelineId;
    }

    public void setPipelineId(final String pipelineId) {
        this.pipelineId = pipelineId;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(final Map<String, String> tags) {
        this.tags = tags;
    }
}
