package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.ActiveInstance;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class SpotFleetInstancesResponse extends AmazonResponse {
    private List<ActiveInstance> activeInstances;

    public List<ActiveInstance> getActiveInstances() {
        return activeInstances;
    }

    public void setActiveInstances(final List<ActiveInstance> activeInstances) {
        this.activeInstances = activeInstances;
    }
}
