package com.cloudaware.cloudmine.amazon.support;

import com.amazonaws.services.support.model.TrustedAdvisorCheckDescription;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.24.17
 * Time: 03:09
 */
public final class TrustedAdvisorChecksResponse extends AmazonResponse {
    private List<TrustedAdvisorCheckDescription> trustedAdvisorChecks;

    public List<TrustedAdvisorCheckDescription> getTrustedAdvisorChecks() {
        return trustedAdvisorChecks;
    }

    public void setTrustedAdvisorChecks(final List<TrustedAdvisorCheckDescription> trustedAdvisorChecks) {
        this.trustedAdvisorChecks = trustedAdvisorChecks;
    }
}
