package com.cloudaware.cloudmine.amazon.elbv2;

import java.util.List;

/**
 * User: tolstikov
 * Date: 2/18/16
 */
public final class RemoveTagsRequest {

    private List<String> arns;
    private List<String> tagKeys;

    public RemoveTagsRequest(final List<String> arns, final List<String> tagKeys) {
        this.arns = arns;
        this.tagKeys = tagKeys;
    }

    public RemoveTagsRequest() {
    }

    public List<String> getArns() {
        return arns;
    }

    public void setArns(final List<String> arns) {
        this.arns = arns;
    }

    public List<String> getTagKeys() {
        return tagKeys;
    }

    public void setTagKeys(final List<String> tagKeys) {
        this.tagKeys = tagKeys;
    }
}
