package com.cloudaware.cloudmine.amazon.elb;

import com.amazonaws.services.elasticloadbalancing.model.TagDescription;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.22.17
 * Time: 17:03
 */
public final class TagsResponse extends AmazonResponse {
    private List<TagDescription> tags;

    public List<TagDescription> getTags() {
        return tags;
    }

    public void setTags(final List<TagDescription> tags) {
        this.tags = tags;
    }
}
