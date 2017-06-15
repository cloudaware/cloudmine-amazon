package com.cloudaware.cloudmine.amazon.elasticbeanstalk;

import com.amazonaws.services.elasticbeanstalk.model.EnvironmentResourceDescription;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: urmuzov
 * Date: 03.22.17
 * Time: 16:40
 */
public final class EnvironmentResourcesResponse extends AmazonResponse {
    private EnvironmentResourceDescription environmentResources;

    public EnvironmentResourceDescription getEnvironmentResources() {
        return environmentResources;
    }

    public void setEnvironmentResources(final EnvironmentResourceDescription environmentResources) {
        this.environmentResources = environmentResources;
    }
}
