package com.cloudaware.cloudmine.amazon.elasticbeanstalk;

import com.amazonaws.services.elasticbeanstalk.model.ApplicationVersionDescription;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.22.17
 * Time: 16:29
 */
public final class ApplicationVersionsResponse extends AmazonResponse {
    private List<ApplicationVersionDescription> applicationVersions;

    public List<ApplicationVersionDescription> getApplicationVersions() {
        return applicationVersions;
    }

    public void setApplicationVersions(final List<ApplicationVersionDescription> applicationVersions) {
        this.applicationVersions = applicationVersions;
    }
}
