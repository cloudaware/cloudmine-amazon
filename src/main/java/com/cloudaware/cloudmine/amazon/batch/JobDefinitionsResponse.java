package com.cloudaware.cloudmine.amazon.batch;

import com.amazonaws.services.batch.model.JobDefinition;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class JobDefinitionsResponse extends AmazonResponse {

    private List<JobDefinition> jobDefinitions;

    public List<JobDefinition> getJobDefinitions() {
        return jobDefinitions;
    }

    public void setJobDefinitions(final List<JobDefinition> jobDefinitions) {
        this.jobDefinitions = jobDefinitions;
    }
}
