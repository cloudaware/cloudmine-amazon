package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.ServiceDetail;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class VpcEndpointServicesResponse extends AmazonResponse {

    private List<ServiceDetail> services;

    public List<ServiceDetail> getServices() {
        return services;
    }

    public void setServices(final List<ServiceDetail> services) {
        this.services = services;
    }
}
