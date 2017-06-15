package com.cloudaware.cloudmine.amazon.elasticache;

import com.amazonaws.services.elasticache.model.CacheSecurityGroup;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.21.17
 * Time: 15:34
 */
public final class CacheSecurityGroupsResponse extends AmazonResponse {
    private List<CacheSecurityGroup> cacheSecurityGroups;

    public List<CacheSecurityGroup> getCacheSecurityGroups() {
        return cacheSecurityGroups;
    }

    public void setCacheSecurityGroups(final List<CacheSecurityGroup> cacheSecurityGroups) {
        this.cacheSecurityGroups = cacheSecurityGroups;
    }
}
