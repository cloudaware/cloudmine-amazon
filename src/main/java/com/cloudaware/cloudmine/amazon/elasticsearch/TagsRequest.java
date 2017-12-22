package com.cloudaware.cloudmine.amazon.elasticsearch;

import java.util.Map;

/**
 * User: tolstikov
 * Date: 2/18/16
 */
public final class TagsRequest {

    private String arn;
    private Map<String, String> tags;

    public TagsRequest(final String arn, final Map<String, String> tags) {
        this.arn = arn;
        this.tags = tags;
    }

    public TagsRequest() {
    }

    public String getArn() {
        return arn;
    }

    public void setArn(final String arn) {
        this.arn = arn;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(final Map<String, String> tags) {
        this.tags = tags;
    }
}
