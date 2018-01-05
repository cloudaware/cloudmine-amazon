package com.cloudaware.cloudmine.amazon.cloudwatchlogs;

import com.amazonaws.services.logs.model.SubscriptionFilter;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class SubscriptionFiltersResponse extends AmazonResponse {
    private List<SubscriptionFilter> subscriptionFilters;

    public List<SubscriptionFilter> getSubscriptionFilters() {
        return subscriptionFilters;
    }

    public void setSubscriptionFilters(final List<SubscriptionFilter> subscriptionFilters) {
        this.subscriptionFilters = subscriptionFilters;
    }
}
