package com.cloudaware.cloudmine.amazon.elbv2;

import java.util.List;
import java.util.Map;

/**
 * User: tolstikov
 * Date: 2/18/16
 */
public final class TagsRequest {

    private List<String> arns;
    private Map<String, String> tags;

    public TagsRequest(final List<String> arns, final Map<String, String> tags) {
        this.arns = arns;
        this.tags = tags;
    }

    public TagsRequest() {
    }

    public List<String> getArns() {
        return arns;
    }

    public void setArns(final List<String> arns) {
        this.arns = arns;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(final Map<String, String> tags) {
        this.tags = tags;
    }
}
