package com.cloudaware.cloudmine.amazon.certificatemanager;

import com.amazonaws.services.certificatemanager.model.Tag;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 20:57
 */
public final class TagsResponse extends AmazonResponse {
    private List<Tag> tags;

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(final List<Tag> tags) {
        this.tags = tags;
    }
}
