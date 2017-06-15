package com.cloudaware.cloudmine.amazon.cloudsearch;

import com.amazonaws.services.cloudsearchv2.model.AccessPoliciesStatus;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: urmuzov
 * Date: 03.20.17
 * Time: 16:54
 */
public final class AccessPoliciesResponse extends AmazonResponse {
    private AccessPoliciesStatus accessPolicies;

    public AccessPoliciesStatus getAccessPolicies() {
        return accessPolicies;
    }

    public void setAccessPolicies(final AccessPoliciesStatus accessPolicies) {
        this.accessPolicies = accessPolicies;
    }
}
