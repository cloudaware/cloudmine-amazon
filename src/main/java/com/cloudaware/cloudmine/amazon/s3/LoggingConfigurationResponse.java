package com.cloudaware.cloudmine.amazon.s3;

import com.amazonaws.services.s3.model.BucketLoggingConfiguration;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 22:10
 */
public final class LoggingConfigurationResponse extends AmazonResponse {
    private BucketLoggingConfiguration loggingConfiguration;

    public LoggingConfigurationResponse(final AmazonException exception) {
        super(exception);
    }

    public LoggingConfigurationResponse(final BucketLoggingConfiguration loggingConfiguration) {
        this.loggingConfiguration = loggingConfiguration;
    }

    public BucketLoggingConfiguration getLoggingConfiguration() {
        return loggingConfiguration;
    }

    public void setLoggingConfiguration(final BucketLoggingConfiguration loggingConfiguration) {
        this.loggingConfiguration = loggingConfiguration;
    }
}
