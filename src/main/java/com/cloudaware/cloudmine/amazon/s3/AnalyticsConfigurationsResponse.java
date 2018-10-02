package com.cloudaware.cloudmine.amazon.s3;

import com.amazonaws.services.s3.model.analytics.AnalyticsConfiguration;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class AnalyticsConfigurationsResponse extends AmazonResponse {
    private List<AnalyticsConfiguration> analyticsConfigurations;

    AnalyticsConfigurationsResponse(final AmazonException exception) {
        super(exception);
    }

    AnalyticsConfigurationsResponse(final List<AnalyticsConfiguration> configurations, final String nextPage) {
        super(nextPage);
        this.analyticsConfigurations = configurations;
    }

    public List<AnalyticsConfiguration> getAnalyticsConfigurations() {
        return analyticsConfigurations;
    }

    public void setAnalyticsConfigurations(final List<AnalyticsConfiguration> configurations) {
        this.analyticsConfigurations = configurations;
    }
}
