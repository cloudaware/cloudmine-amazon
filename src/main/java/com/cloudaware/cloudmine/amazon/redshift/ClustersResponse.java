package com.cloudaware.cloudmine.amazon.redshift;

import com.amazonaws.services.redshift.model.Cluster;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 20:32
 */
public final class ClustersResponse extends AmazonResponse {
    private List<Cluster> clusters;

    public List<Cluster> getClusters() {
        return clusters;
    }

    public void setClusters(final List<Cluster> clusters) {
        this.clusters = clusters;
    }
}
