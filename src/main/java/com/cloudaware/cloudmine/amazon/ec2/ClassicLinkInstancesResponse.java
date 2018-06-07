package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.ClassicLinkInstance;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ClassicLinkInstancesResponse extends AmazonResponse {

    private List<ClassicLinkInstance> instances;

    public List<ClassicLinkInstance> getInstances() {
        return instances;
    }

    public void setInstances(final List<ClassicLinkInstance> instances) {
        this.instances = instances;
    }
}
