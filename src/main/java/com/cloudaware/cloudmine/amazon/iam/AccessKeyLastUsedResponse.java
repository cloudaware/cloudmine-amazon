package com.cloudaware.cloudmine.amazon.iam;

import com.amazonaws.services.identitymanagement.model.AccessKeyLastUsed;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 03:40
 */
public final class AccessKeyLastUsedResponse extends AmazonResponse {
    private AccessKeyLastUsed accessKeyLastUsed;

    public AccessKeyLastUsed getAccessKeyLastUsed() {
        return accessKeyLastUsed;
    }

    public void setAccessKeyLastUsed(final AccessKeyLastUsed accessKeyLastUsed) {
        this.accessKeyLastUsed = accessKeyLastUsed;
    }
}
