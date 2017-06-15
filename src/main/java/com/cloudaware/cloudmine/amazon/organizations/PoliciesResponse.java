package com.cloudaware.cloudmine.amazon.organizations;

import com.amazonaws.services.organizations.model.PolicySummary;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class PoliciesResponse extends AmazonResponse {

    private List<PolicySummary> policies;

    public List<PolicySummary> getPolicies() {
        return policies;
    }

    public void setPolicies(final List<PolicySummary> policies) {
        this.policies = policies;
    }
}
