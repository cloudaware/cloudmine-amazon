package com.cloudaware.cloudmine.amazon.s3;

import com.amazonaws.services.s3.model.TagSet;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 22:19
 */
public final class TagsResponse extends AmazonResponse {
    private List<TagSet> tags;

    public TagsResponse(final AmazonException exception) {
        super(exception);
    }

    public TagsResponse(final List<TagSet> tags) {
        this.tags = tags;
    }

    public List<TagSet> getTags() {
        return tags;
    }

    public void setTags(final List<TagSet> tags) {
        this.tags = tags;
    }
}
