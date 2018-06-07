package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.VpcPeeringConnection;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class VpcPeeringConnectionsResponse extends AmazonResponse {

    private List<VpcPeeringConnection> connections;

    public List<VpcPeeringConnection> getConnections() {
        return connections;
    }

    public void setConnections(final List<VpcPeeringConnection> connections) {
        this.connections = connections;
    }
}
