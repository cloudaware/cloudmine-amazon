package com.cloudaware.cloudmine.amazon.batch;

import com.amazonaws.services.batch.model.JobQueueDetail;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class JobQueuesResponse extends AmazonResponse {

    private List<JobQueueDetail> jobQueues;

    public List<JobQueueDetail> getJobQueues() {
        return jobQueues;
    }

    public void setJobQueues(final List<JobQueueDetail> jobQueues) {
        this.jobQueues = jobQueues;
    }
}
