package com.cloudaware.cloudmine.amazon.elasticbeanstalk;

import com.amazonaws.services.elasticbeanstalk.model.ConfigurationOptionDescription;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.22.17
 * Time: 16:31
 */
public final class ConfigurationOptionsResponse extends AmazonResponse {
    private List<ConfigurationOptionDescription> configurationOptions;

    public List<ConfigurationOptionDescription> getConfigurationOptions() {
        return configurationOptions;
    }

    public void setConfigurationOptions(final List<ConfigurationOptionDescription> configurationOptions) {
        this.configurationOptions = configurationOptions;
    }
}
