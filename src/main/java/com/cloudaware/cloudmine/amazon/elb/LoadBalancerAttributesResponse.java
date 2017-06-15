package com.cloudaware.cloudmine.amazon.elb;

import com.amazonaws.services.elasticloadbalancing.model.LoadBalancerAttributes;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: urmuzov
 * Date: 03.22.17
 * Time: 17:08
 */
public final class LoadBalancerAttributesResponse extends AmazonResponse {
    private LoadBalancerAttributes attributes;

    public LoadBalancerAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(final LoadBalancerAttributes attributes) {
        this.attributes = attributes;
    }
}
