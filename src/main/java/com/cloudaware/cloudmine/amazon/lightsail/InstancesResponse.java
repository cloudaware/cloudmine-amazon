package com.cloudaware.cloudmine.amazon.lightsail;

import com.amazonaws.services.lightsail.model.Instance;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class InstancesResponse extends AmazonResponse {

    private List<Instance> instances;

    public List<Instance> getInstances() {
        return instances;
    }

    public void setInstances(final List<Instance> instances) {
        this.instances = instances;
    }
}
