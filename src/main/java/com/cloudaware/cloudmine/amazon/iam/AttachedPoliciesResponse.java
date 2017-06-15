package com.cloudaware.cloudmine.amazon.iam;

import com.amazonaws.services.identitymanagement.model.AttachedPolicy;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 04:48
 */
public final class AttachedPoliciesResponse extends AmazonResponse {
    private List<AttachedPolicy> attachedPolicies;

    public List<AttachedPolicy> getAttachedPolicies() {
        return attachedPolicies;
    }

    public void setAttachedPolicies(final List<AttachedPolicy> attachedPolicies) {
        this.attachedPolicies = attachedPolicies;
    }
}
