package com.cloudaware.cloudmine.amazon.elb;

import com.amazonaws.services.elasticloadbalancing.model.Tag;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * User: tolstikov
 * Date: 7/10/13
 * Time: 4:12 PM
 */
public final class AddTagsRequest {
    private List<String> loadBalancerNames;
    private List<Tag> tags;

    public AddTagsRequest() {
    }

    public AddTagsRequest(final List<String> loadBalancerNames, final List<Tag> tags) {
        this.loadBalancerNames = loadBalancerNames;
        this.tags = tags;
    }

    public List<String> getLoadBalancerNames() {
        return loadBalancerNames;
    }

    public void setLoadBalancerNames(final List<String> loadBalancerNames) {
        this.loadBalancerNames = loadBalancerNames;
    }

    public AddTagsRequest withLoadBalancerNames(final String... loadBalancerNamesIn) {
        if (this.loadBalancerNames == null) {
            this.loadBalancerNames = Lists.newArrayList();
        }

        this.loadBalancerNames.addAll(Lists.newArrayList(loadBalancerNamesIn));

        return this;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(final List<Tag> tags) {
        this.tags = tags;
    }
}
