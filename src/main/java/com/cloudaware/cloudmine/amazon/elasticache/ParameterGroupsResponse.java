package com.cloudaware.cloudmine.amazon.elasticache;

import com.amazonaws.services.elasticache.model.CacheParameterGroup;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.21.17
 * Time: 15:30
 */
public final class ParameterGroupsResponse extends AmazonResponse {
    private List<CacheParameterGroup> parameterGroups;

    public List<CacheParameterGroup> getParameterGroups() {
        return parameterGroups;
    }

    public void setParameterGroups(final List<CacheParameterGroup> parameterGroups) {
        this.parameterGroups = parameterGroups;
    }
}
