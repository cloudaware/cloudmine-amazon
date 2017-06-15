package com.cloudaware.cloudmine.amazon.cloudsearch;

import com.amazonaws.services.cloudsearchv2.model.AvailabilityOptionsStatus;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: urmuzov
 * Date: 03.20.17
 * Time: 16:55
 */
public final class AvailabilityOptionsResponse extends AmazonResponse {
    private AvailabilityOptionsStatus availabilityOptions;

    public AvailabilityOptionsStatus getAvailabilityOptions() {
        return availabilityOptions;
    }

    public void setAvailabilityOptions(final AvailabilityOptionsStatus availabilityOptions) {
        this.availabilityOptions = availabilityOptions;
    }
}
