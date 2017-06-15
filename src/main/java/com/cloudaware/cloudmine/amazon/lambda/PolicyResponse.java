package com.cloudaware.cloudmine.amazon.lambda;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 18:25
 */
public final class PolicyResponse extends AmazonResponse {
    private String policy;

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(final String policy) {
        this.policy = policy;
    }
}
