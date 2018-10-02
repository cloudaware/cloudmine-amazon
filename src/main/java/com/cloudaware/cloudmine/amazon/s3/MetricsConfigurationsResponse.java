package com.cloudaware.cloudmine.amazon.s3;

import com.amazonaws.services.s3.model.metrics.MetricsConfiguration;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class MetricsConfigurationsResponse extends AmazonResponse {
    private List<MetricsConfiguration> metricsConfigurations;

    MetricsConfigurationsResponse(final AmazonException exception) {
        super(exception);
    }

    MetricsConfigurationsResponse(final List<MetricsConfiguration> metricsConfigurations, final String nextPage) {
        super(nextPage);
        this.metricsConfigurations = metricsConfigurations;
    }

    public List<MetricsConfiguration> getMetricsConfigurations() {
        return metricsConfigurations;
    }

    public void setMetricsConfigurations(final List<MetricsConfiguration> metricsConfigurations) {
        this.metricsConfigurations = metricsConfigurations;
    }
}
