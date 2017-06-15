package com.cloudaware.cloudmine.amazon.cloudfront;

import com.amazonaws.services.cloudfront.model.DistributionSummary;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.20.17
 * Time: 15:59
 */
public final class DistributionsResponse extends AmazonResponse {
    private List<DistributionSummary> distributions;

    public List<DistributionSummary> getDistributions() {
        return distributions;
    }

    public void setDistributions(final List<DistributionSummary> distributions) {
        this.distributions = distributions;
    }
}
