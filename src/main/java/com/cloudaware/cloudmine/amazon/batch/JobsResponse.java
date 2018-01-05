package com.cloudaware.cloudmine.amazon.batch;

import com.amazonaws.services.batch.model.JobSummary;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class JobsResponse extends AmazonResponse {

    private List<JobSummary> jobs;

    public List<JobSummary> getJobs() {
        return jobs;
    }

    public void setJobs(final List<JobSummary> jobs) {
        this.jobs = jobs;
    }
}
