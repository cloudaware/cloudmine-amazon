package com.cloudaware.cloudmine.amazon.emr;

import com.amazonaws.services.elasticmapreduce.model.InstanceGroup;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.22.17
 * Time: 17:45
 */
public final class InstanceGroupsResponse extends AmazonResponse {
    private List<InstanceGroup> instanceGroups;

    public List<InstanceGroup> getInstanceGroups() {
        return instanceGroups;
    }

    public void setInstanceGroups(final List<InstanceGroup> instanceGroups) {
        this.instanceGroups = instanceGroups;
    }
}
