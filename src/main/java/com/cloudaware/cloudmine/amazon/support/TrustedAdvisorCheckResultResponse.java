package com.cloudaware.cloudmine.amazon.support;

import com.amazonaws.services.support.model.TrustedAdvisorCheckResult;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: urmuzov
 * Date: 03.24.17
 * Time: 03:10
 */
public final class TrustedAdvisorCheckResultResponse extends AmazonResponse {
    private TrustedAdvisorCheckResult trustedAdvisorCheckResult;

    public TrustedAdvisorCheckResult getTrustedAdvisorCheckResult() {
        return trustedAdvisorCheckResult;
    }

    public void setTrustedAdvisorCheckResult(final TrustedAdvisorCheckResult trustedAdvisorCheckResult) {
        this.trustedAdvisorCheckResult = trustedAdvisorCheckResult;
    }
}
