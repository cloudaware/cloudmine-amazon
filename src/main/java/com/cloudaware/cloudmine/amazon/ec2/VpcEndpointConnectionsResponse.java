package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.VpcEndpointConnection;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class VpcEndpointConnectionsResponse extends AmazonResponse {

    private List<VpcEndpointConnection> vpcEndpointConnections;

    public List<VpcEndpointConnection> getVpcEndpointConnections() {
        return vpcEndpointConnections;
    }

    public void setVpcEndpointConnections(final List<VpcEndpointConnection> vpcEndpointConnections) {
        this.vpcEndpointConnections = vpcEndpointConnections;
    }
}
