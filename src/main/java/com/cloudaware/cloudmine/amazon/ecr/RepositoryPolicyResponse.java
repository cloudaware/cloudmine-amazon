package com.cloudaware.cloudmine.amazon.ecr;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class RepositoryPolicyResponse extends AmazonResponse {
    private String policy;

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(final String policy) {
        this.policy = policy;
    }
}
