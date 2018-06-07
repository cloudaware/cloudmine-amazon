package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.ServiceConfiguration;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class VpcEndpointServiceConfigurationsResponse extends AmazonResponse {

    private List<ServiceConfiguration> configurations;

    public List<ServiceConfiguration> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(final List<ServiceConfiguration> configurations) {
        this.configurations = configurations;
    }
}
