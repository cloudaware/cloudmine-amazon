package com.cloudaware.cloudmine.amazon.batch;

import com.amazonaws.services.batch.model.JobDetail;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class JobDetailsResponse extends AmazonResponse {

    private List<JobDetail> jobDetails;

    public List<JobDetail> getJobDetails() {
        return jobDetails;
    }

    public void setJobDetails(final List<JobDetail> jobDetails) {
        this.jobDetails = jobDetails;
    }
}
