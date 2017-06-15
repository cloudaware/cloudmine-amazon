package com.cloudaware.cloudmine.amazon.elb;

import com.amazonaws.services.elasticloadbalancing.model.InstanceState;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.22.17
 * Time: 17:00
 */
public final class InstanceStatesResponse extends AmazonResponse {
    private List<InstanceState> instanceStates;

    public List<InstanceState> getInstanceStates() {
        return instanceStates;
    }

    public void setInstanceStates(final List<InstanceState> instanceStates) {
        this.instanceStates = instanceStates;
    }
}
