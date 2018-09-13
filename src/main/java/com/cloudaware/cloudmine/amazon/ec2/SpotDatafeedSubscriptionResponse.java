package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.SpotDatafeedSubscription;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class SpotDatafeedSubscriptionResponse extends AmazonResponse {
    private SpotDatafeedSubscription spotDatafeedSubscription;

    public SpotDatafeedSubscription getSpotDatafeedSubscription() {
        return spotDatafeedSubscription;
    }

    public void setSpotDatafeedSubscription(final SpotDatafeedSubscription spotDatafeedSubscription) {
        this.spotDatafeedSubscription = spotDatafeedSubscription;
    }
}
