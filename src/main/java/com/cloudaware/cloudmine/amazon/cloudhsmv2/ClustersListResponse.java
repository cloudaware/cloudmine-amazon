package com.cloudaware.cloudmine.amazon.cloudhsmv2;

import com.amazonaws.services.cloudhsmv2.model.Cluster;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ClustersListResponse extends AmazonResponse {
    private List<Cluster> clusters;

    public List<Cluster> getClusters() {
        return clusters;
    }

    public void setClusters(final List<Cluster> clusters) {
        this.clusters = clusters;
    }
}
