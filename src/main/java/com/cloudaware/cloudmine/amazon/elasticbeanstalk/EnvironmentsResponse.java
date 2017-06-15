package com.cloudaware.cloudmine.amazon.elasticbeanstalk;

import com.amazonaws.services.elasticbeanstalk.model.EnvironmentDescription;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.22.17
 * Time: 16:38
 */
public final class EnvironmentsResponse extends AmazonResponse {

    private List<EnvironmentDescription> environments;

    public List<EnvironmentDescription> getEnvironments() {
        return environments;
    }

    public void setEnvironments(final List<EnvironmentDescription> environments) {
        this.environments = environments;
    }
}
