package com.cloudaware.cloudmine.amazon.elb;

import com.amazonaws.services.elasticloadbalancing.model.PolicyDescription;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.22.17
 * Time: 17:12
 */
public final class PoliciesResponse extends AmazonResponse {
    private List<PolicyDescription> policies;

    public List<PolicyDescription> getPolicies() {
        return policies;
    }

    public void setPolicies(final List<PolicyDescription> policies) {
        this.policies = policies;
    }
}
