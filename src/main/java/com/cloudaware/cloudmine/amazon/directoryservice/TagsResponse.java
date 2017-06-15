package com.cloudaware.cloudmine.amazon.directoryservice;

import com.amazonaws.services.directory.model.Tag;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class TagsResponse extends AmazonResponse {
    private List<Tag> tags;

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(final List<Tag> tags) {
        this.tags = tags;
    }
}
