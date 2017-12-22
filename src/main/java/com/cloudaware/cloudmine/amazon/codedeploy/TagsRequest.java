package com.cloudaware.cloudmine.amazon.codedeploy;

import java.util.List;
import java.util.Map;

public final class TagsRequest {

    private List<String> instanceNames;
    private Map<String, String> tags;

    public List<String> getInstanceNames() {
        return instanceNames;
    }

    public void setInstanceNames(final List<String> instanceNames) {
        this.instanceNames = instanceNames;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(final Map<String, String> tags) {
        this.tags = tags;
    }
}
