package com.cloudaware.cloudmine.amazon.elbv2;

import com.amazonaws.services.elasticloadbalancingv2.model.Tag;

import java.util.List;

/**
 * User: tolstikov
 * Date: 2/18/16
 */
public final class AddTagsRequest {

    private List<String> arns;
    private List<Tag> tags;

    public AddTagsRequest(final List<String> arns, final List<Tag> tags) {
        this.arns = arns;
        this.tags = tags;
    }

    public AddTagsRequest() {
    }

    public List<String> getArns() {
        return arns;
    }

    public void setArns(final List<String> arns) {
        this.arns = arns;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(final List<Tag> tags) {
        this.tags = tags;
    }
}
