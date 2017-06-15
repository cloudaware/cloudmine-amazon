package com.cloudaware.cloudmine.amazon.sts;

import com.amazonaws.services.securitytoken.model.AssumedRoleUser;
import com.amazonaws.services.securitytoken.model.Credentials;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class AssumeRoleResponse extends AmazonResponse {
    private Credentials credentials;
    private AssumedRoleUser assumedRoleUser;
    private Integer packedPolicySize;

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }

    public AssumedRoleUser getAssumedRoleUser() {
        return assumedRoleUser;
    }

    public void setAssumedRoleUser(final AssumedRoleUser assumedRoleUser) {
        this.assumedRoleUser = assumedRoleUser;
    }

    public Integer getPackedPolicySize() {
        return packedPolicySize;
    }

    public void setPackedPolicySize(final Integer packedPolicySize) {
        this.packedPolicySize = packedPolicySize;
    }
}
