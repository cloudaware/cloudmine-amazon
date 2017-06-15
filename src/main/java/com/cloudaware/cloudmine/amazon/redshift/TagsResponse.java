package com.cloudaware.cloudmine.amazon.redshift;

import com.amazonaws.services.redshift.model.TaggedResource;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 20:26
 */
public final class TagsResponse extends AmazonResponse {
    private List<TaggedResource> tags;

    public List<TaggedResource> getTags() {
        return tags;
    }

    public void setTags(final List<TaggedResource> tags) {
        this.tags = tags;
    }
}
