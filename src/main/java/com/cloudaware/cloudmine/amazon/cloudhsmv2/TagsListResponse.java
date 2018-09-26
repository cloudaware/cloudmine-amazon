package com.cloudaware.cloudmine.amazon.cloudhsmv2;

import com.amazonaws.services.cloudhsmv2.model.Tag;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class TagsListResponse extends AmazonResponse {
    private List<Tag> tags;

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(final List<Tag> tags) {
        this.tags = tags;
    }
}
