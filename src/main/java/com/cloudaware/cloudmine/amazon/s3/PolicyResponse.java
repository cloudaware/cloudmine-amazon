package com.cloudaware.cloudmine.amazon.s3;

import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 22:12
 */
public final class PolicyResponse extends AmazonResponse {
    private String policyText;

    public PolicyResponse(final AmazonException exception) {
        super(exception);
    }

    public PolicyResponse(final String policyText) {
        this.policyText = policyText;
    }

    public String getPolicyText() {
        return policyText;
    }

    public void setPolicyText(final String policyText) {
        this.policyText = policyText;
    }
}
