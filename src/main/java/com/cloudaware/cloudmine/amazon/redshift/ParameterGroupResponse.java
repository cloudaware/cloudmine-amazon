package com.cloudaware.cloudmine.amazon.redshift;

import com.amazonaws.services.redshift.model.ClusterParameterGroup;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ParameterGroupResponse extends AmazonResponse {

    private List<ClusterParameterGroup> parameterGroups;

    public List<ClusterParameterGroup> getParameterGroups() {
        return parameterGroups;
    }

    public void setParameterGroups(final List<ClusterParameterGroup> parameterGroups) {
        this.parameterGroups = parameterGroups;
    }
}
