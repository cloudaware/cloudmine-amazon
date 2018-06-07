package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.EgressOnlyInternetGateway;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class EgressOnlyInternetGatewaysResponse extends AmazonResponse {

    private List<EgressOnlyInternetGateway> egressOnlyInternetGateways;

    public List<EgressOnlyInternetGateway> getEgressOnlyInternetGateways() {
        return egressOnlyInternetGateways;
    }

    public void setEgressOnlyInternetGateways(final List<EgressOnlyInternetGateway> egressOnlyInternetGateways) {
        this.egressOnlyInternetGateways = egressOnlyInternetGateways;
    }
}
