package com.cloudaware.cloudmine.amazon.directoryservice;

import com.amazonaws.services.directory.model.Tag;

import java.util.List;

/**
 * User: tolstikov
 * Date: 2/18/16
 */
public final class AddTagsRequest {

    private List<Tag> tags;

    public AddTagsRequest(final List<Tag> tags) {
        this.tags = tags;
    }

    public AddTagsRequest() {
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(final List<Tag> tags) {
        this.tags = tags;
    }
}
