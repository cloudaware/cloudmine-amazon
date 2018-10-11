package com.cloudaware.cloudmine.amazon.s3;

import com.amazonaws.services.s3.model.Tag;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ObjectTagsResponse extends AmazonResponse {
    private List<Tag> tags;

    ObjectTagsResponse(final AmazonException exception) {
        super(exception);
    }

    ObjectTagsResponse(final List<Tag> tags) {
        this.tags = tags;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(final List<Tag> tags) {
        this.tags = tags;
    }
}
