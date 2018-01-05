package com.cloudaware.cloudmine.amazon.rds;

import com.amazonaws.services.rds.model.Tag;

import java.util.List;

/**
 * User: tolstikov
 * Date: 7/10/13
 * Time: 4:12 PM
 */
public final class TagsRequest {
    private List<Tag> tags;

    public TagsRequest() {
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(final List<Tag> tags) {
        this.tags = tags;
    }
}
