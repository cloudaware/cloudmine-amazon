package com.cloudaware.cloudmine.amazon.sns;

import com.amazonaws.services.sns.model.Subscription;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.24.17
 * Time: 01:18
 */
public final class SubscriptionsResponse extends AmazonResponse {
    private List<Subscription> subscriptions;

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(final List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }
}
