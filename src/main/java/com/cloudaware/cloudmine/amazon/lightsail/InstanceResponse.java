package com.cloudaware.cloudmine.amazon.lightsail;

import com.amazonaws.services.lightsail.model.Instance;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class InstanceResponse extends AmazonResponse {

    private Instance instance;

    public Instance getInstance() {
        return instance;
    }

    public void setInstance(final Instance instance) {
        this.instance = instance;
    }
}
