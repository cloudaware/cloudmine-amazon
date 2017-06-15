package com.cloudaware.cloudmine.amazon.iam;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 04:29
 */
public final class PolicyResponse extends AmazonResponse {
    private String policyDocument;

    public String getPolicyDocument() {
        return policyDocument;
    }

    public void setPolicyDocument(final String policyDocument) {
        this.policyDocument = policyDocument;
    }
}
