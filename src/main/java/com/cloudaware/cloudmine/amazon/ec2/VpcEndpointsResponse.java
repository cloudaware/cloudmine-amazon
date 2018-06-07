package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.VpcEndpoint;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class VpcEndpointsResponse extends AmazonResponse {

    private List<VpcEndpoint> vpcEndpoints;

    public List<VpcEndpoint> getVpcEndpoints() {
        return vpcEndpoints;
    }

    public void setVpcEndpoints(final List<VpcEndpoint> vpcEndpoints) {
        this.vpcEndpoints = vpcEndpoints;
    }
}
