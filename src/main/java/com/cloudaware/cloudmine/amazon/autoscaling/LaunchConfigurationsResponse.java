package com.cloudaware.cloudmine.amazon.autoscaling;

import com.amazonaws.services.autoscaling.model.LaunchConfiguration;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.20.17
 * Time: 13:36
 */
public final class LaunchConfigurationsResponse extends AmazonResponse {
    private List<LaunchConfiguration> launchConfigurations;

    public List<LaunchConfiguration> getLaunchConfigurations() {
        return launchConfigurations;
    }

    public void setLaunchConfigurations(final List<LaunchConfiguration> launchConfigurations) {
        this.launchConfigurations = launchConfigurations;
    }
}
