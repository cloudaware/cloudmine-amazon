package com.cloudaware.cloudmine.amazon.codedeploy;

import com.amazonaws.services.codedeploy.model.Tag;

import java.util.List;

public final class TagsRequest {

    private List<String> instanceNames;
    private List<Tag> tags;

    public List<String> getInstanceNames() {
        return instanceNames;
    }

    public void setInstanceNames(final List<String> instanceNames) {
        this.instanceNames = instanceNames;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(final List<Tag> tags) {
        this.tags = tags;
    }
}
