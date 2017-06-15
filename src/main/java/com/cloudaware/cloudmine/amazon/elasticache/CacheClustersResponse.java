package com.cloudaware.cloudmine.amazon.elasticache;

import com.amazonaws.services.elasticache.model.CacheCluster;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.21.17
 * Time: 15:16
 */
public final class CacheClustersResponse extends AmazonResponse {
    private List<CacheCluster> cacheClusters;

    public List<CacheCluster> getCacheClusters() {
        return cacheClusters;
    }

    public void setCacheClusters(final List<CacheCluster> cacheClusters) {
        this.cacheClusters = cacheClusters;
    }
}
