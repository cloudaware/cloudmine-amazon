package com.cloudaware.cloudmine.amazon.elasticbeanstalk;

import com.amazonaws.services.elasticbeanstalk.model.ApplicationDescription;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.22.17
 * Time: 16:27
 */
public final class ApplicationsResponse extends AmazonResponse {
    private List<ApplicationDescription> applications;

    public List<ApplicationDescription> getApplications() {
        return applications;
    }

    public void setApplications(final List<ApplicationDescription> applications) {
        this.applications = applications;
    }
}
