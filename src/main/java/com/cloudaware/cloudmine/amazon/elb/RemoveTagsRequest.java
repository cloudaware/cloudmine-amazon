package com.cloudaware.cloudmine.amazon.elb;

import com.amazonaws.services.elasticloadbalancing.model.TagKeyOnly;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * User: tolstikov
 * Date: 7/10/13
 * Time: 4:12 PM
 */
public final class RemoveTagsRequest {
    private List<String> loadBalancerNames;
    private List<TagKeyOnly> tags;

    public RemoveTagsRequest() {
    }

    public RemoveTagsRequest(final List<String> loadBalancerNames, final List<TagKeyOnly> tags) {
        this.loadBalancerNames = loadBalancerNames;
        this.tags = tags;
    }

    public List<String> getLoadBalancerNames() {
        return loadBalancerNames;
    }

    public void setLoadBalancerNames(final List<String> loadBalancerNames) {
        this.loadBalancerNames = loadBalancerNames;
    }

    public RemoveTagsRequest withLoadBalancerNames(final String... loadBalancerNamesIn) {
        if (this.loadBalancerNames == null) {
            this.loadBalancerNames = Lists.newArrayList();
        }

        this.loadBalancerNames.addAll(Lists.newArrayList(loadBalancerNamesIn));

        return this;
    }

    public List<TagKeyOnly> getTags() {
        return tags;
    }

    public void setTags(final List<TagKeyOnly> tags) {
        this.tags = tags;
    }
}
