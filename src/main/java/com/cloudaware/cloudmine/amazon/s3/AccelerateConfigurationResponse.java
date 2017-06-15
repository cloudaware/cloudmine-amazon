package com.cloudaware.cloudmine.amazon.s3;

import com.amazonaws.services.s3.model.BucketAccelerateConfiguration;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 22:26
 */
public final class AccelerateConfigurationResponse extends AmazonResponse {
    private BucketAccelerateConfiguration accelerateConfiguration;

    public AccelerateConfigurationResponse(final AmazonException exception) {
        super(exception);
    }

    public AccelerateConfigurationResponse(final BucketAccelerateConfiguration accelerateConfiguration) {
        this.accelerateConfiguration = accelerateConfiguration;
    }

    public BucketAccelerateConfiguration getAccelerateConfiguration() {
        return accelerateConfiguration;
    }

    public void setAccelerateConfiguration(final BucketAccelerateConfiguration accelerateConfiguration) {
        this.accelerateConfiguration = accelerateConfiguration;
    }
}
