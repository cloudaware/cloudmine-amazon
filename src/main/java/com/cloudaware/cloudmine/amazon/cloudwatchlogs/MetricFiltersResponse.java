package com.cloudaware.cloudmine.amazon.cloudwatchlogs;

import com.amazonaws.services.logs.model.MetricFilter;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class MetricFiltersResponse extends AmazonResponse {
    private List<MetricFilter> metricFilters;

    public List<MetricFilter> getMetricFilters() {
        return metricFilters;
    }

    public void setMetricFilters(final List<MetricFilter> metricFilters) {
        this.metricFilters = metricFilters;
    }
}
