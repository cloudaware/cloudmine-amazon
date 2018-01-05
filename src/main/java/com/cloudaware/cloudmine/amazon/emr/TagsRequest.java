package com.cloudaware.cloudmine.amazon.emr;

import com.amazonaws.services.elasticmapreduce.model.Tag;

import java.util.List;

/**
 * User: tolstikov
 * Date: 2/18/16
 */
public final class TagsRequest {

    private List<Tag> tags;

    public TagsRequest(final List<Tag> tags) {

        this.tags = tags;
    }

    public TagsRequest() {
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(final List<Tag> tags) {
        this.tags = tags;
    }
}
