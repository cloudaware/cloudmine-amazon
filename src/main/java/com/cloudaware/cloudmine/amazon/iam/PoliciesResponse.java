package com.cloudaware.cloudmine.amazon.iam;

import com.amazonaws.services.identitymanagement.model.Policy;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 06:30
 */
public final class PoliciesResponse extends AmazonResponse {
    private List<Policy> policies;

    public List<Policy> getPolicies() {
        return policies;
    }

    public void setPolicies(final List<Policy> policies) {
        this.policies = policies;
    }
}
