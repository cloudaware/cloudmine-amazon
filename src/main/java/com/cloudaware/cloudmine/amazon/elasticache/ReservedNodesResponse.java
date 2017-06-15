package com.cloudaware.cloudmine.amazon.elasticache;

import com.amazonaws.services.elasticache.model.ReservedCacheNode;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.21.17
 * Time: 15:33
 */
public final class ReservedNodesResponse extends AmazonResponse {
    private List<ReservedCacheNode> reservedNodes;

    public List<ReservedCacheNode> getReservedNodes() {
        return reservedNodes;
    }

    public void setReservedNodes(final List<ReservedCacheNode> reservedNodes) {
        this.reservedNodes = reservedNodes;
    }
}
