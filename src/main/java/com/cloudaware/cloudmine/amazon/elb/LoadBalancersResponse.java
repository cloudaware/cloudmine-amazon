package com.cloudaware.cloudmine.amazon.elb;

import com.amazonaws.services.elasticloadbalancing.model.LoadBalancerDescription;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.22.17
 * Time: 16:56
 */
public final class LoadBalancersResponse extends AmazonResponse {
    private List<LoadBalancerDescription> loadBalancers;

    public List<LoadBalancerDescription> getLoadBalancers() {
        return loadBalancers;
    }

    public void setLoadBalancers(final List<LoadBalancerDescription> loadBalancers) {
        this.loadBalancers = loadBalancers;
    }
}
