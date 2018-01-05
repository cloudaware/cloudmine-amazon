package com.cloudaware.cloudmine.amazon.dynamodb;

import com.amazonaws.services.dynamodbv2.model.Tag;

import java.util.List;

/**
 * User: tolstikov
 * Date: 2/18/16
 */
public final class TagTagsRequest {

    private List<Tag> tags;

    public TagTagsRequest(final List<Tag> tags) {
        this.tags = tags;
    }

    public TagTagsRequest() {
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(final List<Tag> tags) {
        this.tags = tags;
    }
}
