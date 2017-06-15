package com.cloudaware.cloudmine.amazon.ecs;

import com.amazonaws.services.ecs.model.Service;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.21.17
 * Time: 15:01
 */
public final class ServicesResponse extends AmazonResponse {
    private List<Service> services;

    public List<Service> getServices() {
        return services;
    }

    public void setServices(final List<Service> services) {
        this.services = services;
    }
}
