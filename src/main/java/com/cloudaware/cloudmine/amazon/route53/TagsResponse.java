package com.cloudaware.cloudmine.amazon.route53;

import com.amazonaws.services.route53.model.ResourceTagSet;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 20:57
 */
public final class TagsResponse extends AmazonResponse {
    private ResourceTagSet tags;

    public ResourceTagSet getTags() {
        return tags;
    }

    public void setTags(final ResourceTagSet tags) {
        this.tags = tags;
    }
}
