package com.cloudaware.cloudmine.amazon.s3;

import com.amazonaws.services.s3.model.NotificationConfiguration;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.Map;

public final class NotificationConfigurationResponse extends AmazonResponse {
    private Map<String, NotificationConfiguration> configurations;

    NotificationConfigurationResponse(final AmazonException exception) {
        super(exception);
    }

    NotificationConfigurationResponse(final Map<String, NotificationConfiguration> configurations) {
        this.configurations = configurations;
    }

    public Map<String, NotificationConfiguration> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(final Map<String, NotificationConfiguration> configurations) {
        this.configurations = configurations;
    }
}
